package com.inc.vr.corp.app.kulbon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val status = getIntent().getStringExtra("status").toString()
        if(status.equals("salah")){
            t_title.setText("Gagal")
            t_ket.setText("Permintaan tidak dapat di proses, mungkin kamu sudah memberi review sebelumnya")
        }else{
            t_title.setText("SUBMITTED")
            t_ket.setText("Review Makanan telah berhasil dikirim")
        }
        b_riwayat.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}

