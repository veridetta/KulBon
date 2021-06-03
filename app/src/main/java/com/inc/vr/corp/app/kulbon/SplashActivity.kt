package com.inc.vr.corp.app.kulbon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inc.vr.corp.app.kulbon.users.LoginActivity
import com.intuit.sdp.BuildConfig
import timber.log.Timber

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        val sharedPreference:SharedPreference=SharedPreference(this)
        if (sharedPreference.getValueString("name")!=null) {
            val intent = Intent(this,MenuActivity::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}