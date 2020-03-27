package com.taatefi.movieinfo.MyLib

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.taatefi.movieinfo.R
import kotlinx.android.synthetic.main.recycler_item.view.*
import java.net.URL


class recycleradapter(
    val names: List<String>,
    val imgsrc:List<String>,
    val clickListener: (String) -> Unit
) :
    RecyclerView.Adapter<recycleradapter.TestKotlinRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TestKotlinRecyclerViewHolder {

        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return TestKotlinRecyclerViewHolder(
            v,
            clickListener
        )
    }

    override fun getItemCount() = names.size

    override fun onBindViewHolder(holder: TestKotlinRecyclerViewHolder, position: Int) =
        holder.onBind(names[position],imgsrc[position])

    class TestKotlinRecyclerViewHolder(
        val item: View,
        val clickListener: (String) -> Unit // TestRecyclerClickListener
    ) : RecyclerView.ViewHolder(item) {


        fun onBind(name: String, imgsrc: String) {
            item.txttittle.text = name
            if (imgsrc != "1") {
                val url = URL("http://image.tmdb.org/t/p/w300/" + imgsrc)
             val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                item.imgbackdrop.setImageBitmap(bmp)
            } else {
                item.imgbackdrop.setImageBitmap(R.drawable.movie)
            }
            item.setOnClickListener {
                clickListener(name)
            }
        }
    }
}

private fun ImageView.setImageBitmap(icLauncherBackground: Int) {

}
