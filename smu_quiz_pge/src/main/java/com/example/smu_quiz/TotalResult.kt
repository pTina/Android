package com.example.smu_quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.total_result.*

class TotalResult : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.total_result)

        if(intent.hasExtra("total")){
            var total = intent.getIntExtra("total",0)

            tvTotalResult.setText("10문제 중에서 ${total}문제 맞췄습니다.")

        }

        tvBoxGotoMain.setOnClickListener{
            finish()
        }




    }
}