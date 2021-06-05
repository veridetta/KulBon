package com.inc.vr.corp.app.kulbon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inc.vr.corp.app.kulbon.users.LoginActivity
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val sharedPreference:SharedPreference=SharedPreference(this)
        val id_user = sharedPreference.getValueInt("id")
        val nama = sharedPreference.getValueString("name")
        val kelas = sharedPreference.getValueString("kelas")
        val actionbar = supportActionBar
        actionbar!!.title = "Profile"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        i_nama.text=nama.toString()
        i_pp.text=nama.toString().substring(0,1)
        b_logout.setOnClickListener{
            sharedPreference.clearSharedPreference()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}