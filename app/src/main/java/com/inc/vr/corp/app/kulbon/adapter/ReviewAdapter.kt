package com.inc.vr.corp.app.kulbon.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.inc.vr.corp.app.kulbo.CatInfo
import com.inc.vr.corp.app.kulbo.RateInfo
import com.inc.vr.corp.app.kulbo.RateListInfo
import com.inc.vr.corp.app.kulbon.MainActivity
import com.inc.vr.corp.app.kulbon.MenuActivity
import com.inc.vr.corp.app.kulbon.R
import com.inc.vr.corp.app.kulbon.model.UserInfo
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.activity_detail.view.rating
import kotlinx.android.synthetic.main.rc_cat.view.*
import kotlinx.android.synthetic.main.rc_review.view.*


private var context: Context? = null
class ReviewAdapter(private val heroes: List<RateListInfo>) : RecyclerView.Adapter<ReviewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ReviewHolder {
        context = viewGroup.getContext();
        return ReviewHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.rc_review,
                viewGroup,
                false
            )
        )
    }

    override fun getItemCount(): Int = heroes.size

    override fun onBindViewHolder(holder: ReviewHolder, position: Int) {

        holder.bindOrder(heroes[position], position)
    }
}
class ReviewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name = view.t_nama
    private val rating = view.rating_rev
    private val comment = view.t_comment
    @SuppressLint("LogNotTimber")
    fun bindOrder(buku: RateListInfo, position: Int) {

        val userinfo : List<UserInfo> = buku.users
        name.setText(userinfo.get(0).name)
        rating.rating=buku.rating.toFloat()
        comment.setText(buku.comment)
    }
}
