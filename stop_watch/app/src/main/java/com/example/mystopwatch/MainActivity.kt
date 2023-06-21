package com.example.mystopwatch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer


// 스탑워치
// 빠르게 계산하면서 UI를 갱신해야 함


class MainActivity : AppCompatActivity() {

    private var time = 0 // 시간을 계산할 변수를 0으로 초기화
    private var timerTask: Timer? = null    // 나중에 timer를 취소, 실행하고 반환되는 Timer 객체를 저장해두기 위한 변수
                                            // null을 허용하는 Timer타입의 변수
    private var isRunning = false           // 타이머를 시작시키고 종료시키기 위한 변수

    private var lap = 1         // 몇 번째 랩인지 표시하기 위한 변수

    // 타이머 작동시키기
    private fun start(){
        fabStart.setImageResource(R.drawable.pause)

        timerTask = timer(period = 10){ // 안드로이드에서 제공하는 timer메소드 사용, period를 10으로 함 == 0.01초가 기준
            // 워커 스레드에서 동작하므로 UI 조작이 불가능
            time++ // 0.01초 마다 time을 1씩 증가시킴
            val sec = time/100      //초
            val milli = time%100    //밀리초

            runOnUiThread { // 메인 스레드에서 동작하여 UI 갱신하기 위해 runOnUiThread 사용하여 UI를 변경함
                tvSecond.text = "$sec"      //id가 tvSecond인 뷰의 텍스트에 변수 sec표시
                tvMilisecond.text="$milli"  //idrk tvMilisecond의 뷰의 텍스트에 변수 milli표시
            }
        }
    }

    private fun pause(){
        fabStart.setImageResource(R.drawable.play)  //fabStart 버튼 이미지를 설정한 이미지로 변경
        timerTask?.cancel()    // 타이머 취소
    }


    // 시간 기록하기
    private fun recordLaptTime(){
        val lapTime =  this.time    // 현재 시간이 저장되어있는 time의 값을 저장하는 변수
        val textView = TextView(this)       // 동적으로 생성된 TextView 저장하는 변수
        textView.text = "$lap : ${lapTime/ 100}.${lapTime%100}"     // 동적으로 생성된 textView의 text 설정.

        linearLap.addView(textView, 0)      // 스크롤 뷰 안에 있는 Linearlayout에 addView()메서드를 사용하여 동적으로 생성된 textView를 생성. 첫번째에 쌓게 만들려고 index를 0으로 설정

        lap++       // lap을 1증가
    }

    // 초기화
    private fun resetTime(){

        timerTask?.cancel()     // 타이머 취소시킨다.


        //모든 변수를 0으로 초기화
        time = 0
        isRunning = false
        fabStart.setImageResource(R.drawable.play)
        tvSecond.text = "0"
        tvMilisecond.text= "00"

        linearLap.removeAllViews()      //모든 기록 초기화
        lap = 1     // lap 변수도 1로 초기화
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fabStart.setOnClickListener {
            isRunning = !isRunning // isRunning변수를 반전시킴

            if(isRunning){ //isRunning이 true면
                start()     //start()함수 호출
            }
            else{       //isRUnning이 false면
                pause() //pause()함수 호출
            }
        }

        // 기록 버튼 누를때
        btnLaptime.setOnClickListener {
            recordLaptTime() //recordLaptTime()함수 호출
        }

        // 초기화
        fabReset.setOnClickListener {
            resetTime()
        }
    }
}
