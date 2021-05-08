package com.example.sportsadvisor

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.row_nav_drawer.view.*
//adapted from:https://johncodeos.com/how-to-create-a-custom-navigation-drawer-in-android-using-kotlin/

class NavigationRVAdapter(private var items: ArrayList<NavigationItemModel>, private var currentPos: Int) : RecyclerView.Adapter<NavigationRVAdapter.NavigationItemViewHolder>() {

    private lateinit var context: Context

    class NavigationItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationItemViewHolder {
        context = parent.context
        val navItem = LayoutInflater.from(parent.context).inflate(R.layout.row_nav_drawer, parent, false)
        return NavigationItemViewHolder(navItem)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: NavigationItemViewHolder, position: Int) {
        // To highlight the selected item, show different background color
        if (position == currentPos) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryAccent))
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
        }
        holder.itemView.navigation_icon.setColorFilter(Color.parseColor("#D8F3DC"), PorterDuff.Mode.SRC_ATOP)
        holder.itemView.navigation_title.setTextColor(Color.parseColor("#D8F3DC"))

        holder.itemView.navigation_title.text = items[position].title

        holder.itemView.navigation_icon.setImageResource(items[position].icon)

    }

}