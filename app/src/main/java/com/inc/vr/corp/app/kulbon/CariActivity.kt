package com.inc.vr.corp.app.kulbon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.inc.vr.corp.app.kulbo.FoodInfo
import com.inc.vr.corp.app.kulbon.adapter.FoodAdapter
import com.inc.vr.corp.app.kulbon.api.FoodApi
import com.inc.vr.corp.app.kulbon.api.RestApiService
import com.inc.vr.corp.app.kulbon.api.ServiceBuilder
import com.intuit.sdp.BuildConfig
import kotlinx.android.synthetic.main.activity_cari.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class CariActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cari)
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        val ss = getIntent().getStringExtra("name").toString()
        getFood(ss,"name")
        Timber.d("isi "+ss);
        kategori_text.text="Menampilkan hasil cari '"+ss+"'"
        btn_back.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
    fun getFood(title: String, author: String) {
        val apiService= RestApiService()
        val bukuInfo = FoodInfo(
            id = null,
            address = null,
            catId = null,
            cover = null,
            createdAt = null,
            description = null,
            facility = null,
            gallery = null,
            map = null,
            name = null,
            open = null,
            phone = null,
            price = null,
            updatedAt = null
        )
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@CariActivity)
        alertDialog.setTitle("Processing...")
        alertDialog.setMessage("Data sedang diproses")
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
        val retrofit = ServiceBuilder.buildService(FoodApi::class.java)
        retrofit.loadBuku(bukuInfo).enqueue(
            object : Callback<List<FoodInfo>> {
                override fun onFailure(call: Call<List<FoodInfo>>, t: Throwable) {
                    Timber.d(t.toString())
                    alert.dismiss()
                }

                override fun onResponse(call: Call<List<FoodInfo>>, response: Response<List<FoodInfo>>) {
                    val addedUser = response.body()
                    if(addedUser!==null){
                        Timber.d(addedUser.toString())
                        val heroesAdapter = FoodAdapter(addedUser)
                        rc_home.apply {
                            layoutManager = GridLayoutManager(this@CariActivity,2)
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
}