package com.inc.vr.corp.app.kulbon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.inc.vr.corp.app.kulbo.CatInfo
import com.inc.vr.corp.app.kulbon.adapter.CatAdapter
import com.inc.vr.corp.app.kulbon.api.CatApi
import com.inc.vr.corp.app.kulbon.api.RestApiService
import com.inc.vr.corp.app.kulbon.api.ServiceBuilder
import com.intuit.sdp.BuildConfig
import kotlinx.android.synthetic.main.activity_menu.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        val ss = getIntent().getStringExtra("name").toString()
        getCat(ss,"name")
        Timber.d("isi "+ss);
        cari_input.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                val cari = cari_input.query.toString()
                val intent = Intent(getApplicationContext() , CariActivity::class.java)
                intent.putExtra("name", cari)
                startActivity(intent)
                return false
            }

        })
    }
    fun getCat(title: String, author: String) {
        val apiService= RestApiService()
        val bukuInfo = CatInfo(
            id = null,
            createdAt = null,
            updatedAt = null,
            cover = null,
            name = null
        )
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MenuActivity)
        alertDialog.setTitle("Processing...")
        alertDialog.setMessage("Data sedang diproses")
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
        val retrofit = ServiceBuilder.buildService(CatApi::class.java)
        retrofit.loadBuku(bukuInfo).enqueue(
            object : Callback<List<CatInfo>> {
                override fun onFailure(call: Call<List<CatInfo>>, t: Throwable) {
                    Timber.d(t.toString())
                    alert.dismiss()
                }

                override fun onResponse(call: Call<List<CatInfo>>, response: Response<List<CatInfo>>) {
                    val addedUser = response.body()
                    if(addedUser!==null){
                        Timber.d(addedUser.toString())
                        val heroesAdapter = CatAdapter(addedUser)
                        rc_home.apply {
                            layoutManager = GridLayoutManager(this@MenuActivity,2)
                            adapter = heroesAdapter
                        }
                    }
                    alert.dismiss()
                }
            }
        )
    }
}