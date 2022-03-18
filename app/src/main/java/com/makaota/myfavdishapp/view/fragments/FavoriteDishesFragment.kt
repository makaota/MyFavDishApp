package com.makaota.myfavdishapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.makaota.myfavdishapp.application.FavDishApplication
import com.makaota.myfavdishapp.databinding.FragmentFavoriteDishesBinding
import com.makaota.myfavdishapp.model.entities.FavDish
import com.makaota.myfavdishapp.view.activities.MainActivity
import com.makaota.myfavdishapp.view.adapters.FavDishAdapter
import com.makaota.myfavdishapp.viewmodel.FavDishViewModel
import com.makaota.myfavdishapp.viewmodel.FavDishViewModelFactory

class FavoriteDishesFragment : Fragment() {


    private var _binding: FragmentFavoriteDishesBinding? = null

    /**
     * To create the ViewModel we used the viewModels delegate, passing in an instance of our FavDishViewModelFactory.
     * This is constructed based on the repository retrieved from the FavDishApplication.
     */
    private val mFavDishViewModel: FavDishViewModel by viewModels {
        FavDishViewModelFactory((requireActivity().application as FavDishApplication).repository)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentFavoriteDishesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Add an observer on the LiveData returned by getFavoriteDishesList.
         * The onChanged() method fires when the observed data changes and the activity is in the foreground.
         */
        mFavDishViewModel.favoriteDishes.observe(viewLifecycleOwner){

            _binding!!.rvFavoriteDishesList.layoutManager = GridLayoutManager(requireActivity(), 2)
            val adapter = FavDishAdapter(this)
            _binding!!.rvFavoriteDishesList.adapter = adapter
            if (it.isNotEmpty()){
             _binding!!.rvFavoriteDishesList.visibility = View.VISIBLE
              _binding!!.tvNoFavoriteDishesAvailable.visibility = View.GONE
                adapter.dishesList(it)
            }else{
                _binding!!.rvFavoriteDishesList.visibility = View.GONE
                _binding!!.tvNoFavoriteDishesAvailable.visibility = View.VISIBLE
            }
        }
    }

    /**
     * A function to navigate to the Dish Details Fragment.
     *
     * @param favDish
     */
    fun dishDetails(favDish: FavDish) {

        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)!!.hideBottomNavigationView()
        }

        findNavController().navigate(FavoriteDishesFragmentDirections.actionFavoriteDishesToDishDetails(favDish))
    }

    override fun onResume() {
        super.onResume()

        (activity as MainActivity).showBottomNavigationView()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}