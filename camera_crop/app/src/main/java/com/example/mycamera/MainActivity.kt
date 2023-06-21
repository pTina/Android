package com.example.mycamera

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.preference.PreferenceManager
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.util.Log
import android.widget.Toast
import com.example.smu_quiz_2.Permission
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var photoUri: Uri? = null
    var croppedFileName: File? = null
    var mCurrentPhotoPath:String? = null

    fun disagree(){
        Toast.makeText(this,"권한이 거부되었습니다.",Toast.LENGTH_SHORT).show()
    }

    // 갤러리에 사진 저장
    private fun galleryAddPhoto(){
        Log.e("galleryAddPhoto","!!yes!!")

        val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)

        val file = File(mCurrentPhotoPath)
        val contentUri = Uri.fromFile(file)
        intent.data = contentUri

        sendBroadcast(intent)
        Toast.makeText(this,"사진이 앨범에 저장되었습니다.",Toast.LENGTH_LONG).show()
    }

    // 앨범에서 이미지 선택
    fun GoToAlbum(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = ("image/*")
        Log.e("앨범 열기", YES)
        startActivityForResult(intent, PICK_FROM_ALBUM)
    }

    // 이미지 자르기
    fun cropImage(){
        val intent = Intent("com.android.camera.action.CROP")
        intent.setDataAndType(photoUri,"image/*")
        val list = packageManager.queryIntentActivities(intent,0)
        grantUriPermission(list[0].activityInfo.packageName, photoUri, Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        val size = list.size
        if(size == 0){
            Toast.makeText(this,"취소되었습니다",Toast.LENGTH_SHORT).show()
            return
        }else{
            Toast.makeText(this,"용량이 큰 경우 시간이 오래걸릴 수 있습니다.", Toast.LENGTH_SHORT).show()
            intent.putExtra("crop",true)    // 카메라 찍고 바로 자르기 설정
            intent.putExtra("outputX", 90)  // crop한 이미지의 x축 크기
            intent.putExtra("outputY", 90)  // crop한 이미지의 y축 크기
            intent.putExtra("aspectX", 1)   // crop 박스의 x축 비율
            intent.putExtra("aspectY", 1)   // crop 박스의 y축 비율
            intent.putExtra("scale", true)  // 비율 설정 해놔서 scale true 한 것 같음

            // 오류 처리( try catch 사용)
            // try{}에서 실행한 코드 중에 오류 발생 -> catch{}에서 해당 에러에 대한 오류 처리
            try {
                croppedFileName = createImageFile()
            }catch (e: IOException){
                e.printStackTrace()
            }

            // file - internal, external storage 존재
            // internal storage(내부 저장장소) - 항상 사용 가능, 내부에 저장된 파일은 해당 앱만 접근 가능/ 앱이 제거될 때 내부에 저장된 파일 모두 제거한다
            // external storage(외부 저장장소) - 이곳에 저장된 파일은 다른 곳에서 읽혀질 수 있음/ 다른 앱과 공유될 수 있고 컴퓨터로 따로 관리할 파일들을 저장하는데 사용하는 것이 좋다
            // 앱은 기본적으로 internal storage에 저장됨
            // manifest 파일내의 android:installLocation 속성으로 external storage에 저장할 수 있다(apk 파일의 사이즈가 매우 큰 경우)

            // croppedFileName 저장 할 파일 folder 새성
            val folder = File(Environment.getExternalStorageDirectory().toString() + "/TEST/")

            // 임시 파일 tempFile 생성
            val tempFile = File(folder.toString(), croppedFileName!!.name)

            // tempFile Uri 받기
            photoUri = FileProvider.getUriForFile(this,"com.example.mycamera.provider",tempFile)
            Log.e("CROP PHOTO URI",photoUri.toString())


            intent.putExtra("return-data", false)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            intent.putExtra("outputFormat",Bitmap.CompressFormat.JPEG.toString())

            val intent2 = Intent(intent)
            val res = list[0]
            grantUriPermission(res.activityInfo.packageName, photoUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION)
//            intent2.component = ComponentName(res.activityInfo.parentActivityName, res.activityInfo.name)
            startActivityForResult(intent2, CROP_FROM_ALBUM)
        }
    }

    // 이미지 파일 만들기(crop하고 사진을 파일로 저장해야합니다.)
    fun createImageFile(): File?{

        val timestamp = SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(Date())
        // 이미지 파일 이름 생성
        val imageFileName = "test_${timestamp}_"
        // 디렉토리 만들기
        val storageDir = File(Environment.getExternalStorageDirectory().toString()+"/TEST/")
        // 디렉토리가 없으면
        if(!storageDir.exists()){
            // mkdir() 호출하여 디렉토리 생성
            storageDir.mkdir()
        }

        val imageFile = File(storageDir, imageFileName)
        mCurrentPhotoPath = imageFile.absolutePath

        // File.createTempFile(prefix, suffix, directory)
        // directory로 지정된 폴더로 임시파일이 생성된다.
        return File.createTempFile(imageFileName,".jpg",storageDir)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 사용자 권한 요청
        var permission = Permission(this)
        permission.checkPermission()

        val user = User()
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val result = pref.getBoolean("isPermission", false)
        user.isPermission = result
        Log.e("UESR.PERMISSION", user.isPermission.toString())



        // 앨범 버튼 리스너
        btnGoAlbum.setOnClickListener {
            val user = application as User
            // 사용자가 권한 허락을 했을 때
            Log.e("main permission",user.isPermission.toString())
            if(result){
                // 앨범 호출
                GoToAlbum()
            }else{// 사용자가 권한 거부했을 때
                disagree()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            PICK_FROM_ALBUM ->{
                if(data != null){
                    photoUri = data!!.data
                    cropImage()
                }
            }
            PHOTO_CROP ->{
                return

            }
            CROP_FROM_ALBUM ->{
                galleryAddPhoto()
                ivImageView!!.setImageURI(null)
                ivImageView!!.setImageURI(photoUri)
                revokeUriPermission(photoUri, Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            }
        }
    }

    companion object{
        val YES:String = "!!yes!!"
        val PICK_FROM_ALBUM = 100
        val PHOTO_CROP = 200
        val CROP_FROM_ALBUM = 300

    }
}
