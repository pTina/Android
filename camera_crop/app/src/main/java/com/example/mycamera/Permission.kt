package com.example.smu_quiz_2

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import com.example.mycamera.R
import com.example.mycamera.User
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import java.util.ArrayList

class Permission (context: Context){
    var context = context
    val pref = PreferenceManager.getDefaultSharedPreferences(context)
    val edit = pref.edit()

   private var permissionlistener:PermissionListener = object :PermissionListener{


        override fun onPermissionGranted() {
            //권한 허가시 실행 할 내용
            edit.putBoolean("isPermission",true)
            edit.apply()
            edit.commit()
        }

        override fun onPermissionDenied(deniedPermissions: ArrayList<String>?) {
            // 권한 거부시 실행 할 내용
            edit.putBoolean("isPermission",false)
            edit.apply()
            edit.commit()
        }
    }

    fun checkPermission(){
        TedPermission.with(context)
            .setPermissionListener(permissionlistener)
            .setRationaleMessage("사진 및 파일을 저장하기 위하여 권한이 필요합니다")
            .setDeniedMessage("[설정]->[권한] 에서 권한을 허용할 수 있습니다.")
            .setPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.CAMERA)
            .check()
    }


}