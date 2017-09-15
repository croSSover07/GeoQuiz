package com.example.developer.geoquiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class CheatActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ANSWER_IS_TRUE="answer_is_true"
        fun newIntent(packageContext:Context,answeredTrue:Boolean):Intent{
            val intent=Intent(packageContext,CheatActivity::class.java)
            intent.putExtra(EXTRA_ANSWER_IS_TRUE,answeredTrue)
            return intent
        }
    }
    var answerIsTrue:Boolean=false
    var answerTextView:TextView?=null
    var showAnswerButton:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
        answerIsTrue=intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false)

        answerTextView=findViewById(R.id.answer_text_view) as TextView
        showAnswerButton=findViewById(R.id.show_answer_button) as Button
        showAnswerButton!!.setOnClickListener {
            if (answerIsTrue){
                answerTextView!!.setText(R.string.true_button)
            }else{
            answerTextView!!.setText(R.string.false_button)
        }}
    }
}
