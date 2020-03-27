package com.taatefi.movieinfo.MyLib

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.taatefi.movieinfo.R
import kotlinx.android.synthetic.main.recycler_saveitem.view.*

class saveitemadapter(
    val names: List<String>,
    val backdrop: List<String>
) : RecyclerView.Adapter<saveitemadapter.myviewholder>() {
    private val myLib = MyLib()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): saveitemadapter.myviewholder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_saveitem, parent, false)
        return saveitemadapter.myviewholder(v)
    }

    override fun getItemCount() = names.size


    override fun onBindViewHolder(holder: saveitemadapter.myviewholder, position: Int) =
        holder.onBind(names[position], backdrop[position])

    class myviewholder(
        val item: View
    ) : RecyclerView.ViewHolder(item) {
        val myLib = MyLib()
        fun onBind(name: String, s: String) {
            item.txttittle.text = name
            val bm = myLib.stringtobitmap(s)
            item.imgbackdrop.setImageBitmap(bm)

        }
    }
}