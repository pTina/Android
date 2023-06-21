package com.example.copy_instagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    var photoList = arrayListOf<RvPhoto>(

        RvPhoto("flower"),
        RvPhoto("selfie")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mAdapter = RvPhotoAdapter(this,photoList)
        rvMainPhoto.adapter = mAdapter

        val glm = GridLayoutManager(this,3)
        rvMainPhoto.layoutManager = glm
        rvMainPhoto.setHasFixedSize(true)

    }



}
