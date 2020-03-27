package com.taatefi.movieinfo.MyLib

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.taatefi.movieinfo.R
import kotlinx.android.synthetic.main.recycler_item.view.*

class movierecycleradapter(val names: List<String>):
    RecyclerView.Adapter<movierecycleradapter.TestKotlinRecyclerViewHolder>()
    {

        class TestKotlinRecyclerViewHolder(
            val item: View
        ) : RecyclerView.ViewHolder(item) {


            fun onBind(name: String) {
                item.txttittle.text = name
            }
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): TestKotlinRecyclerViewHolder {
            val v =
                LayoutInflater.from(parent.context).inflate(R.layout.genre_item, parent, false)
            return TestKotlinRecyclerViewHolder(
                v
            )
        }

        override fun getItemCount() = names.size

        override fun onBindViewHolder(holder: TestKotlinRecyclerViewHolder, position: Int) {
            holder.onBind(names[position])
        }
    }