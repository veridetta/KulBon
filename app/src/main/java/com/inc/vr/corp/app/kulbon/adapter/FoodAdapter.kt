package com.inc.vr.corp.app.kulbon.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.inc.vr.corp.app.kulbo.FoodInfo
import com.inc.vr.corp.app.kulbon.MainActivity
import com.inc.vr.corp.app.kulbon.R
import kotlinx.android.synthetic.main.rc_cat.view.*


private var context: Context? = null
class FoodAdapter(private val heroes: List<FoodInfo>) : RecyclerView.Adapter<FoodHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): FoodHolder {
        context = viewGroup.getContext();
        return FoodHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.rc_cat,
                viewGroup,
                false
            )
        )
    }

    override fun getItemCount(): Int = heroes.size

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.bindOrder(heroes[position])
    }
}
class FoodHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val text = view.t_cat
    private val bg = view.bg_cat
    private val card = view.c_cat
    fun bindOrder(buku: FoodInfo) {
        text.text=buku.name
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.logo)
            .error(R.drawable.logo)
        Glide.with(context).load(buku.cover).apply(options).into(bg)
        card.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("id", buku.id)
            intent.putExtra("name", buku.name)
            intent.putExtra("cover", buku.cover)
            intent.putExtra("address", buku.address)
            intent.putExtra("description", buku.description)
            intent.putExtra("facility", buku.facility)
            intent.putExtra("gallery", buku.gallery)
            intent.putExtra("map", buku.map)
            intent.putExtra("open", buku.open)
            intent.putExtra("phone", buku.phone)
            intent.putExtra("price", buku.price)
            context?.startActivity(intent)
        }
    }
}