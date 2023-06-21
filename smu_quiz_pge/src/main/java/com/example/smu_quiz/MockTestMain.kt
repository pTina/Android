package com.example.smu_quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.ivAnswerCorrect
import kotlinx.android.synthetic.main.activity_main.ivAnswerWrong
import kotlinx.android.synthetic.main.activity_main.ivMainLike
import kotlinx.android.synthetic.main.activity_main.rvMainMultipleChoice
import kotlinx.android.synthetic.main.activity_main.tvMainQuestionContent
import kotlinx.android.synthetic.main.activity_main.tvMainQuestionNum
import kotlinx.android.synthetic.main.activity_main.tvMainTopTitle
import kotlinx.android.synthetic.main.activity_main.tvNext
import kotlinx.android.synthetic.main.mock_test_main.*

class MockTestMain : AppCompatActivity(){


    var answerList= mutableListOf<Answer>(
        Answer(false,"mock1"),
        Answer(false,"mock2"),
        Answer(false,"mock3"),
        Answer(false,"mock4")
    )

    var questionList = mutableListOf<Question>(
        Question("subject1","문제1번",1,1,false,false,"1-1","1-2","1-3","1-4"),
        Question("subject2","문제2번",2,2,false, false,"2-1","2-2","2-3","2-4"),
        Question("subject3","문제3번",3,4,false,false,"3-1","3-2","3-3","3-4"),
        Question("subject4","문제4번",4,3,false,false,"4-1","4-2","4-3","4-4"),
        Question("subject5","문제5번",4,3,false,false,"4-1","4-2","4-3","4-4"),
        Question("subject6","문제6번",4,3,false,false,"4-1","4-2","4-3","4-4"),
        Question("subject7","문제7번",4,3,false,false,"4-1","4-2","4-3","4-4"),
        Question("subject8","문제8번",4,3,false,false,"4-1","4-2","4-3","4-4"),
        Question("subject9","문제9번",4,3,false,false,"4-1","4-2","4-3","4-4"),
        Question("subject10","문제10번",4,3,false,false,"4-1","4-2","4-3","4-4")

    )

    override fun onCreate(savedInstanceState: Bundle?){
        var i = 0
        var j = 0

        var totalcorrect = 0

        super.onCreate(savedInstanceState)
        setContentView(R.layout.mock_test_main)

        tvMainTopTitle.setText(questionList[0].subject)
        tvMainQuestionContent.setText(questionList[0].content)
        tvMainQuestionNum.text="Question ${questionList[0].quiznum}"

        val mAdapter = AnswerAdapter(this,answerList, questionList, object: OnItemClickListener{

            override fun itemClick(isChecked: Boolean, position: Int) {
                if( position+1 == questionList[j].anwser){
                    ivAnswerCorrect.visibility = View.VISIBLE
                    ivAnswerWrong.visibility = View.GONE
                    if(questionList[j].correct == false){
                        questionList[j].correct = true
                        totalcorrect += 1
                    }
                }else{
                    ivAnswerWrong.visibility = View.VISIBLE
                    ivAnswerCorrect.visibility = View.GONE
                    questionList[j].correct = false
                    if(questionList[j].correct == true){
                        questionList[j].correct = false
                        totalcorrect -= 1
                    }

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
                val intent = Intent(this, TotalResult::class.java)
                intent.putExtra("total",totalcorrect)
                startActivity(intent)
            }

        }

        tvPrev.setOnClickListener {
        }



    }
}