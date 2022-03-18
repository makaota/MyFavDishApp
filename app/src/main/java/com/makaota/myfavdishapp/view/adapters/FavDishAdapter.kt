package com.makaota.myfavdishapp.view.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makaota.myfavdishapp.R
import com.makaota.myfavdishapp.databinding.ItemDishLayoutBinding
import com.makaota.myfavdishapp.model.entities.FavDish
import com.makaota.myfavdishapp.utils.Constants
import com.makaota.myfavdishapp.view.activities.AddUpdateDishActivity
import com.makaota.myfavdishapp.view.fragments.AllDishesFragment
import com.makaota.myfavdishapp.view.fragments.FavoriteDishesFragment

class FavDishAdapter(private val fragment: Fragment) : RecyclerView.Adapter<FavDishAdapter.ViewHolder>() {

    private var dishes: List<FavDish> = listOf()


    class ViewHolder(view: ItemDishLayoutBinding) : RecyclerView.ViewHolder(view.root) {

        val ivDishImage = view.ivDishImage
        val tvTile = view.tvDishTitle
        val ibMoore = view.ibMore

    }

    /**
     * Inflates the item views which is designed in xml layout file
     *
     * create a new
     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemDishLayoutBinding =
            ItemDishLayoutBinding.inflate(LayoutInflater.from(fragment.context), parent, false)
        return ViewHolder(binding)
    }

    /**
     * Binds each item in the ArrayList to a view
     *
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dish = dishes[position]
        // Load the dish image in the ImageView.
        Glide.with(fragment)
            .load(dish.image)
            .into(holder.ivDishImage)
        holder.tvTile.text = dish.title

        holder.itemView.setOnClickListener {
            if (fragment is AllDishesFragment){
                fragment.dishDetails(dish)
            }
            if (fragment is FavoriteDishesFragment){
                fragment.dishDetails(dish)
            }
        }

        holder.ibMoore.setOnClickListener {

            val popup = PopupMenu(fragment.context, holder.ibMoore)
            popup.menuInflater.inflate(R.menu.menu_adapter, popup.menu)

            popup.setOnMenuItemClickListener {
                if (it.itemId == R.id.action_edit_dish){
                    val intent =
                        Intent(fragment.requireActivity(), AddUpdateDishActivity::class.java)
                    intent.putExtra(Constants.EXTRA_DISH_DETAILS, dish)
                    fragment.requireActivity().startActivity(intent)
                    Log.i("You have clicked on","Edit Option of ${dish.title}")
                }else if (it.itemId == R.id.action_delete_dish){
                    if(fragment is AllDishesFragment){
                        fragment.deleteDish(dish)
                    Log.i("You have clicked on","Delete Option of ${dish.title}")
                    }
                }
                true
            }
            popup.show()
        }
        if (fragment is AllDishesFragment){
            holder.ibMoore.visibility = View.VISIBLE
        }else if (fragment is FavoriteDishesFragment){
            holder.ibMoore.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return dishes.size
    }

    fun dishesList(list: List<FavDish>) {
        dishes = list
        notifyDataSetChanged()
    }


}