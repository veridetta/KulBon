package com.inc.vr.corp.app.kulbon

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.inc.vr.corp.app.kulbo.CatInfo
import com.inc.vr.corp.app.kulbon.adapter.CatAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_menu.*

class DetailActivity : AppCompatActivity() {
    private val galList = ArrayList<CatInfo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val id = getIntent().getStringExtra("id").toString()
        val name = getIntent().getStringExtra("name").toString()
        val cover = getIntent().getStringExtra("cover").toString()
        val address = getIntent().getStringExtra("address").toString()
        val description = getIntent().getStringExtra("description").toString()
        val facility = getIntent().getStringExtra("facility").toString()
        val gallery = getIntent().getStringExtra("gallery").toString()
        val map = getIntent().getStringExtra("map").toString()
        val open = getIntent().getStringExtra("open").toString()
        val phone = getIntent().getStringExtra("phone").toString()
        val price = getIntent().getStringExtra("price").toString()

        t_name.setText(name)
        t_address.setText(address)
        t_description.setText(description)
        t_facility.setText(facility)
        t_open.setText(open)
        t_phone.setText(phone)
        t_price.setText(price)

        val options: RequestOptions = RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
        Glide.with(this).load(cover).apply(options).into(bg_img)
        val result: MutableList<CatInfo> = ArrayList()

        val lis = gallery.split("kulbon2021")

        for(item in lis){
            var cat = CatInfo(
                id = null,
                createdAt = null,
                updatedAt = null,
                cover = item,
                name = null
            )
            result.add(cat)
        }
        val heroesAdapter = CatAdapter(result)
        rc_gall.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity)
            (layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.HORIZONTAL
            adapter = heroesAdapter
        }
        web_map.settings.setJavaScriptEnabled(true)

        web_map.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(map)
                return true
            }
        }
        web_map.loadUrl(map)

        rating_set.setOnRatingBarChangeListener { ratingBar, fl, b ->
            val intent = Intent(this, ReviewActivity::class.java)
            intent.putExtra("id", id)
           startActivity(intent)
        }
    }

}



