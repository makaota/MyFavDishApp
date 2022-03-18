package com.makaota.myfavdishapp.view.fragments

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.makaota.myfavdishapp.R
import com.makaota.myfavdishapp.application.FavDishApplication
import com.makaota.myfavdishapp.databinding.FragmentRandomDishBinding
import com.makaota.myfavdishapp.model.entities.FavDish
import com.makaota.myfavdishapp.model.entities.RandomDish
import com.makaota.myfavdishapp.utils.Constants
import com.makaota.myfavdishapp.viewmodel.FavDishViewModel
import com.makaota.myfavdishapp.viewmodel.FavDishViewModelFactory
import com.makaota.myfavdishapp.viewmodel.RandomDishViewModel

class RandomDishFragment : Fragment() {

    private lateinit var randomDishViewModel: RandomDishViewModel
    private var mBinding: FragmentRandomDishBinding? = null

    // A global variable for Progress Dialog
    private var progressDialog: Dialog? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentRandomDishBinding.inflate(inflater, container, false)
        val root: View = binding.root
        
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the ViewModel variable.
        randomDishViewModel = ViewModelProvider(this).get(RandomDishViewModel::class.java)

        randomDishViewModel.getRandomRecipeFromAPI()

        randomDishViewModelObserver()

        /**
         * Sets up a SwipeRefreshLayout.OnRefreshListener that is invoked when the user
         * performs a swipe-to-refresh gesture.
         */
        mBinding!!.srlRandomDish.setOnRefreshListener {
            // This method performs the actual data-refresh operation.
            // The method calls setRefreshing(false) when it's finished.
            randomDishViewModel.getRandomRecipeFromAPI()
        }

    }

    /**
     * A function is used to show the Custom Progress Dialog.
     */
    private fun showCustomProgressDialog(){
        progressDialog = Dialog(requireActivity())
        progressDialog?.let {
            /** Set the screen content from a layout resource.
              The resource will be inflated, adding all top-level views to the screen.*/
            it.setContentView(R.layout.dialog_custom_progress)
            //Start the dialog and display it on screen.
            it.show()
        }
    }

    /**
     * This function is used to dismiss the progress dialog if it is visible to user.
     */
    private fun hideProgressDialog(){
        progressDialog?.let {
            it.dismiss()
        }

    }

    /**
     * A function to get the data in the observer after the API is triggered.
     */
    private fun randomDishViewModelObserver(){
        randomDishViewModel.randomDishResponse.observe(viewLifecycleOwner,{randomDishResponse -> randomDishResponse?.let {
            Log.i("Random Dish Response", "${randomDishResponse.recipes[0]}")

            if (mBinding!!.srlRandomDish.isRefreshing){
                mBinding!!.srlRandomDish.isRefreshing = false
            }
            setRandomDishResponseInUI(randomDishResponse.recipes[0])
        }})

        randomDishViewModel.randomDishLoadingError.observe(viewLifecycleOwner,{
            dataError -> dataError?.let {
                Log.e("Random Dish API Error","$dataError")

            if (mBinding!!.srlRandomDish.isRefreshing){
                mBinding!!.srlRandomDish.isRefreshing = false
            }
        }
        })

        randomDishViewModel.loadRandomDish.observe(viewLifecycleOwner,{
            loadRandomDish ->
            loadRandomDish?.let {
                Log.i("Random Dish Loading","$loadRandomDish")

                // Show the progress dialog if the SwipeRefreshLayout is not visible and hide when the usage is completed.
                if (loadRandomDish && !mBinding!!.srlRandomDish.isRefreshing){
                    showCustomProgressDialog()// Used to show the progress dialog
                }else{
                    hideProgressDialog()
                }
            }
        })
    }

    /**
     * A method to populate the API response in the UI.
     *
     * @param recipe - Data model class of the API response with filled data.
     */
    private fun setRandomDishResponseInUI(recipe: RandomDish.Recipe) {

        // Load the dish image in the ImageView.
        Glide.with(requireActivity())
            .load(recipe.image)
            .centerCrop()
            .into(mBinding!!.ivDishImage)

        mBinding!!.tvTitle.text = recipe.title

        // Default Dish Type
        var dishType: String = "other"

        if (recipe.dishTypes.isNotEmpty()) {
            dishType = recipe.dishTypes[0]
            mBinding!!.tvType.text = dishType
        }

        // There is not category params present in the response so we will define it as Other.
        mBinding!!.tvCategory.text = "Other"

        var ingredients = ""
        for (value in recipe.extendedIngredients) {

            if (ingredients.isEmpty()) {
                ingredients = value.original
            } else {
                ingredients = ingredients + ", \n" + value.original
            }
        }

        mBinding!!.tvIngredients.text = ingredients

        // The instruction or you can say the Cooking direction text is in the HTML format so we will you the fromHtml to populate it in the TextView.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mBinding!!.tvCookingDirection.text = Html.fromHtml(
                recipe.instructions,
                Html.FROM_HTML_MODE_COMPACT
            )
        } else {
            @Suppress("DEPRECATION")
            mBinding!!.tvCookingDirection.text = Html.fromHtml(recipe.instructions)
        }

        mBinding!!.ivFavoriteDish.setImageDrawable(ContextCompat.getDrawable(
            requireActivity(),
            R.drawable.ic_favorite_unselected
        ))

        var addedToFavorites = false

        mBinding!!.tvCookingTime.text =
            resources.getString(
                R.string.lbl_estimate_cooking_time,
                recipe.readyInMinutes.toString()
            )

        mBinding!!.ivFavoriteDish.setOnClickListener {

            if (addedToFavorites){
                Toast.makeText(requireActivity(),
                resources.getString(R.string.msg_already_added_to_favorites),
                Toast.LENGTH_SHORT).show()
            }else{

                val randomDishDetails = FavDish(
                    recipe.image,
                    Constants.DISH_IMAGE_SOURCE_ONLINE,
                    recipe.title,
                    dishType,
                    "Other",
                    ingredients,
                    recipe.readyInMinutes.toString(),
                    recipe.instructions,
                    true
                )

                val mFavDishViewModel: FavDishViewModel by viewModels {
                    FavDishViewModelFactory((requireActivity().application as FavDishApplication).repository)
                }

                mFavDishViewModel.insert(randomDishDetails)

                addedToFavorites = true
                mBinding!!.ivFavoriteDish.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.ic_favorite_selected
                    )
                )

                Toast.makeText(
                    requireActivity(),
                    resources.getString(R.string.msg_added_to_favorites),
                    Toast.LENGTH_SHORT
                ).show()
            }


        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}