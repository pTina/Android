package com.example.smu_quiz

import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.annotation.MainThread
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_answer.view.*
import org.w3c.dom.Text
import java.util.zip.Inflater

interface OnItemClickListener{
    fun itemClick(isCheck : Boolean, position: Int)
}

class AnswerAdapter(val context: Context, val answerList: List<Answer>, val questionList: List<Question>, val listener: OnItemClickListener):
        RecyclerView.Adapter<AnswerAdapter.Holder>(){

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val answerCheckbox =  itemView.findViewById<CheckBox>(R.id.cbCheck)
        val answerChoice = itemView.findViewById<TextView>(R.id.tvChoice)


        fun bind(answer: Answer, question: Question, context: Context){
            answerCheckbox.isClickable = answer.ischecked
            answerChoice.text = answer.choice
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val view =  LayoutInflater.from(context).inflate(
            R.layout.item_answer,parent,false
        )
        return Holder(view)

    }

    override fun getItemCount(): Int {
        return answerList.size
    }


    //position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(answerList[position], questionList[1], context)

        holder.answerCheckbox.setOnClickListener ({
            listener.itemClick(holder.answerCheckbox.isChecked, position)
        })

    }


}