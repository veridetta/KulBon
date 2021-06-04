package com.inc.vr.corp.app.kulbon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AlertDialog
import com.inc.vr.corp.app.kulbo.RateGlobalInfo
import com.inc.vr.corp.app.kulbo.RateInfo
import com.inc.vr.corp.app.kulbon.api.RateApi
import com.inc.vr.corp.app.kulbon.api.RateGlobalApi
import com.inc.vr.corp.app.kulbon.api.RestApiService
import com.inc.vr.corp.app.kulbon.api.ServiceBuilder
import com.inc.vr.corp.app.kulbon.users.LoginActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_review.*
import kotlinx.android.synthetic.main.rc_review.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class ReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        val id = getIntent().getStringExtra("id").toString()
        val rating = getIntent().getStringExtra("rating").toString()
        val sharedPreference:SharedPreference=SharedPreference(this)
        val user_id=sharedPreference.getValueInt("id")
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Review"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        b_review.setOnClickListener {
            val com = i_comment.text.toString()
            addReview(id.toInt(),rating, com ,user_id)
        }
    }
    fun addReview(id: Int?, rating: String?, comment: String?, user_id: Int?) {
        val apiService = RestApiService()
        val userInfo = RateInfo(
                id = null,
                comment = comment,
                food_id = id,
                rating = rating,
                user_id = user_id,
        )
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@ReviewActivity)
        alertDialog.setTitle("Processing...")
        alertDialog.setMessage("Data sedang diproses")
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
        val retrofit = ServiceBuilder.buildService(RateApi::class.java)
        retrofit.loadBuku(userInfo).enqueue(
                object : Callback<List<RateInfo>> {
                    override fun onFailure(call: Call<List<RateInfo>>, t: Throwable) {
                        Timber.d(t.toString())
                        alert.dismiss()
                    }

                    override fun onResponse(call: Call<List<RateInfo>>, response: Response<List<RateInfo>>) {
                        val addedUser = response.body()
                        if(addedUser!==null){
                            val intent = Intent(this@ReviewActivity, ResultActivity::class.java)
                            intent.putExtra("status", "benar")
                            startActivity(intent)
                        }else{
                            val intent = Intent(this@ReviewActivity, ResultActivity::class.java)
                            intent.putExtra("status", "salah")
                            startActivity(intent)
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