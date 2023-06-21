package com.example.smu_quiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.*
import android.widget.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_answer.*
import kotlinx.android.synthetic.main.item_answer.view.*
import kotlinx.android.synthetic.main.mock_test_start.*

class MainActivity : AppCompatActivity() {


    var answerList= mutableListOf<Answer>(
        Answer(false,"choice1"),
        Answer(false,"choice2"),
        Answer(false,"choice3"),
        Answer(false,"choice4")

    )

    var questionList = mutableListOf<Question>(

        Question("database","문제1번",1,1,false,false,"1-1","1-2","1-3","1-4"),
        Question("algorithm","문제2번",2,2,false, false,"2-1","2-2","2-3","2-4"),
        Question("operation system","문제3번",3,4,false,false,"3-1","3-2","3-3","3-4"),
        Question("database","문제4번",4,3,false,false,"4-1","4-2","4-3","4-4")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        var i = 0
        var j = 0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //
        tvMainTopTitle.setText(questionList[0].subject)
        tvMainQuestionContent.setText(questionList[0].content)
        tvMainQuestionNum.text="Question ${questionList[0].quiznum}"


        tvStop.setOnClickListener{

                val intent = Intent(this,StopActivity::class.java)
                startActivity(intent)

        }


        val mAdapter = AnswerAdapter(this,answerList, questionList, object: OnItemClickListener{

            override fun itemClick(isChecked: Boolean, position: Int) {
                if( position+1 == questionList[j].anwser){
                    ivAnswerCorrect.visibility = VISIBLE
                    ivAnswerWrong.visibility = GONE
                    if(questionList[j].correct == false){
                        questionList[j].correct = true
                    }
                }else{
                    ivAnswerWrong.visibility = VISIBLE
                    ivAnswerCorrect.visibility = GONE
                    questionList[j].correct = false
                    if(questionList[j].correct == true){
                        questionList[j].correct = false
                    }

                }

                for( k in 0..3){
                    println("${k+1} 번째 문제 == 맞췄니? ${questionList[k].correct} like: ${questionList[k].like}")
                }

            }


        })

        rvMainMultipleChoice.adapter = mAdapter
        val lm = LinearLayoutManager(this)
        rvMainMultipleChoice.layoutManager = lm
        rvMainMultipleChoice.setHasFixedSize(true)

        tvNext.setOnClickListener {
            j += 1
            if(j < questionList.size){
                tvMainTopTitle.setText(questionList[j].subject)
                tvMainQuestionContent.setText(questionList[j].content)
                tvMainQuestionNum.text="Question ${questionList[j].quiznum}"
                ivAnswerCorrect.visibility = View.INVISIBLE
                ivAnswerWrong.visibility = View.INVISIBLE
                rvMainMultipleChoice.adapter = mAdapter
                ivMainLike.setImageResource(R.drawable.like_empty)


            }else{
                Toast.makeText(this,"더이상 문제가 없습니다.",Toast.LENGTH_SHORT).show()
            }

        }

        ivMainLike.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View){

                i = 1-i

                if(i == 1){
                    ivMainLike.setImageResource(R.drawable.like_fill)
                    if(questionList[j].like == false){
                        questionList[j].like = true
                    }
                    }else{
                    ivMainLike.setImageResource(R.drawable.like_empty)
                    if(questionList[j].like == true){
                        questionList[j].like = false
                    }
                }
            }
        })




    }



}


