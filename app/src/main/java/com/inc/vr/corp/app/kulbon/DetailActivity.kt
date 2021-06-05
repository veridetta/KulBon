package com.inc.vr.corp.app.kulbon

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.inc.vr.corp.app.kulbo.CatInfo
import com.inc.vr.corp.app.kulbo.RateGlobalInfo
import com.inc.vr.corp.app.kulbo.RateInfo
import com.inc.vr.corp.app.kulbo.RateListInfo
import com.inc.vr.corp.app.kulbon.adapter.CatAdapter
import com.inc.vr.corp.app.kulbon.adapter.GalleryAdapter
import com.inc.vr.corp.app.kulbon.adapter.ReviewAdapter
import com.inc.vr.corp.app.kulbon.api.*
import com.intuit.sdp.BuildConfig
import im.delight.android.webview.AdvancedWebView
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.activity_menu.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class DetailActivity : AppCompatActivity() {
    private val galList = ArrayList<CatInfo>()
    @SuppressLint("BinaryOperationInTimber", "LogNotTimber")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

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
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = name
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        t_name.setText(name)
        t_address.setText(address)
        t_description.setText(description)
        t_facility.setText(facility)
        t_open.setText(open)
        t_phone.setText(phone)
        t_price.setText(price)
        t_address.setOnLongClickListener{
            val url = "https://maps.google.com/?q="+map
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
            return@setOnLongClickListener true
        }
        t_phone.setOnLongClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + phone)
            startActivity(dialIntent)
            return@setOnLongClickListener true
        }
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
        val heroesAdapter = GalleryAdapter(result)
        rc_gall.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity)
            (layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.HORIZONTAL
            adapter = heroesAdapter
        }
        getRateGlobal(id,"")
        getReview(id,"")
        navigate.setOnClickListener {
            val url = "https://maps.google.com/?q="+map
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        rating_set.setOnRatingBarChangeListener { ratingBar, fl, b ->
            val intent = Intent(this , ReviewActivity::class.java)
            intent.putExtra("rating", fl.toString())
            intent.putExtra("id", id)
            startActivity(intent)
        }

    }
    fun getRateGlobal(title: String, author: String) {
        val apiService= RestApiService()
        val bukuInfo = RateGlobalInfo(
                foodId = title,
                average = "rata",
                total = 0
        )
        apiService.getReviewGlobal(bukuInfo) {
            Timber.d("info "+bukuInfo.toString())
            if (it?.average != null) {
                // it = newly added user parsed as response
                // it?.id = newly added user ID
                t_rate_value.setText(it?.average.toString())
                t_rate_user.setText("("+it?.total.toString()+")")
                rating.rating=it?.average.toFloat()
            } else {
                Timber.d("Error ")
                toast("Error! Silahkan Coba lagi")
            }
        }
    }
    fun getReview(title: String, author: String) {
        val apiService= RestApiService()
        val userInfo = RateInfo(
                id = null,
                comment = "comment",
                food_id = title.toInt(),
                rating = "rating",
                user_id = 0,
        )
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@DetailActivity)
        alertDialog.setTitle("Processing...")
        alertDialog.setMessage("Data sedang diproses")
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
        val retrofit = ServiceBuilder.buildService(RateListApi::class.java)
        retrofit.loadBuku(userInfo).enqueue(
                object : Callback<List<RateListInfo>> {
                    override fun onFailure(call: Call<List<RateListInfo>>, t: Throwable) {
                        Timber.d(t.toString())
                        alert.dismiss()
                    }

                    override fun onResponse(call: Call<List<RateListInfo>>, response: Response<List<RateListInfo>>) {
                        val addedUser = response.body()
                        if(addedUser!==null){
                            Timber.d("tambah "+addedUser.toString())
                            val heroesAdapter = ReviewAdapter(addedUser)
                            rc_rating.apply {
                                layoutManager = GridLayoutManager(this@DetailActivity,1)
                                adapter = heroesAdapter
                            }
                        }
                        alert.dismiss()
                    }
                }
        )
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}



