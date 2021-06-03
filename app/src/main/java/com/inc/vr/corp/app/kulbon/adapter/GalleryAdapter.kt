package com.inc.vr.corp.app.kulbon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.inc.vr.corp.app.kulbo.CatInfo
import com.inc.vr.corp.app.kulbon.R
import kotlinx.android.synthetic.main.rc_cat.view.bg_cat


private var context: Context? = null
class GalleryAdapter(private val heroes: List<CatInfo>) : RecyclerView.Adapter<GalleryHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): GalleryHolder {
        context = viewGroup.getContext();
        return GalleryHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.rc_gallery,
                viewGroup,
                false
            )
        )
    }

    override fun getItemCount(): Int = heroes.size

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
        holder.bindOrder(heroes[position])
    }
}
class GalleryHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val bg = view.bg_cat
    fun bindOrder(buku: CatInfo) {
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.logo)
            .error(R.drawable.logo)
        Glide.with(context).load(buku.cover).apply(options).into(bg)
    }
}
