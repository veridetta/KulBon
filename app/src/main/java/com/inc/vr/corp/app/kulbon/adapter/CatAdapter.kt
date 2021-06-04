package com.inc.vr.corp.app.kulbon.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.inc.vr.corp.app.kulbo.CatInfo
import com.inc.vr.corp.app.kulbon.MainActivity
import com.inc.vr.corp.app.kulbon.MenuActivity
import com.inc.vr.corp.app.kulbon.R
import kotlinx.android.synthetic.main.rc_cat.view.*


private var context: Context? = null
class CatAdapter(private val heroes: List<CatInfo>) : RecyclerView.Adapter<CatHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): CatHolder {
        context = viewGroup.getContext();
        return CatHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.rc_cat,
                viewGroup,
                false
            )
        )
    }

    override fun getItemCount(): Int = heroes.size

    override fun onBindViewHolder(holder: CatHolder, position: Int) {
        holder.bindOrder(heroes[position])
    }
}
class CatHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val text = view.t_cat
    private val bg = view.bg_cat
    private val card = view.c_cat
    fun bindOrder(buku: CatInfo) {
        text.text=buku.name
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.logo)
            .error(R.drawable.logo)
        Glide.with(context).load(buku.cover).apply(options).into(bg)
        card.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("id", buku.id.toString())
            intent.putExtra("name", buku.name)
            intent.putExtra("cover", buku.cover)
            context?.startActivity(intent)
        }
    }
}
