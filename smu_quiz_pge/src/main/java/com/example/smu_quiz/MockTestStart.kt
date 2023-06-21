package com.example.smu_quiz

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_stop.*
import kotlinx.android.synthetic.main.mock_test_start.*

class MockTestStart : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){

        super.onCreate(savedInstanceState)
        setContentView(R.layout.mock_test_start)

        var already: Int = 0;

        tvContinue.setOnClickListener {
            if(already == 0){
                Toast.makeText(this,"이어푸시던 문제가 없습니다.",Toast.LENGTH_SHORT).show()
            }else{

            }
        }
        tvNew.setOnClickListener {
            val intent = Intent(this,MockTestMain::class.java)
            startActivity(intent)
        }

    }
}