package com.inc.vr.corp.app.kulbon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.inc.vr.corp.app.kulbo.FoodInfo
import com.inc.vr.corp.app.kulbon.adapter.FoodAdapter
import com.inc.vr.corp.app.kulbon.api.CatApi
import com.inc.vr.corp.app.kulbon.api.FoodApi
import com.inc.vr.corp.app.kulbon.api.RestApiService
import com.inc.vr.corp.app.kulbon.api.ServiceBuilder
import com.intuit.sdp.BuildConfig
import kotlinx.android.synthetic.main.activity_cari.*
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.img_kat
import kotlinx.android.synthetic.main.activity_main.rc_home
import kotlinx.android.synthetic.main.activity_menu.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import timber.log.Timber.d

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Daftar Makanan"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)



        val id = getIntent().getStringExtra("id").toString()
        val name = getIntent().getStringExtra("name").toString()
        val cover = getIntent().getStringExtra("cover").toString()
        d("Inin id "+id)
        t_kategori.setText("Menampilkan kategori "+name)
        getFood(id.toInt(),"cat_id")

        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.logo)
            .error(R.drawable.logo)
        Glide.with(this).load(cover).apply(options).into(img_kat)

    }
    fun getFood(title: Int, author: String) {
        val apiService= RestApiService()
        val bukuInfo = FoodInfo(
            id = title,
            address = null,
            catId = title,
            cover = null,
            createdAt = null,
            description = null,
            facility = null,
            gallery = null,
            map = null,
            name = author,
            open = null,
            phone = null,
            price = null,
            updatedAt = null
        )
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Processing...")
        alertDialog.setMessage("Data sedang diproses")
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
        val retrofit = ServiceBuilder.buildService(FoodApi::class.java)
        retrofit.loadBuku(bukuInfo).enqueue(
            object : Callback<List<FoodInfo>> {
                override fun onFailure(call: Call<List<FoodInfo>>, t: Throwable) {
                    d(t.toString())
                    alert.dismiss()
                }

                override fun onResponse(call: Call<List<FoodInfo>>, response: Response<List<FoodInfo>>) {
                    val addedUser = response.body()
                    if(addedUser!==null){
                        d(addedUser.toString())
                        val heroesAdapter = FoodAdapter(addedUser)
                        rc_home.apply {
                            layoutManager = GridLayoutManager(this@MainActivity,2)
                            adapter = heroesAdapter
                        }
                    }
                    alert.dismiss()

                }
            }
        )

        /*
        apiService.loadBuku(bukuInfo) {
            Timber.d(" hasil " + it.toString())
            if (it != null) {

            }
        }

         */
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
