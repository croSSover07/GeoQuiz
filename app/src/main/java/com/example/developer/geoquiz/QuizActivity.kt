package com.example.developer.geoquiz

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class QuizActivity : AppCompatActivity() {


    private var true_button: Button? =null
    private var false_button:Button?=null
    private var next_button:Button?=null
    private var prev_button:Button?=null
    private var question_text_view:TextView?=null
    private var currentIndex:Int
    private val KEY_INDEX="index"

    private var questionBank: Array<Question>
    init {
        questionBank= arrayOf(
                Question(R.string.question_australia,true),
                Question(R.string.question_oceans,true),
                Question(R.string.question_mideast,false),
                Question(R.string.question_africa,false),
                Question(R.string.question_americas,true),
                Question(R.string.question_asia,true)
                )
        currentIndex=0

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putInt(KEY_INDEX,currentIndex)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        currentIndex= savedInstanceState!![KEY_INDEX] as Int

        configView()




    }
    private fun configView(){
        true_button=findViewById(R.id.true_button) as Button
        false_button=findViewById(R.id.false_button) as Button
        next_button=findViewById(R.id.next_button) as Button
        prev_button=findViewById(R.id.prev_button) as Button
        question_text_view =findViewById(R.id.question_text_view) as TextView
        true_button!!.setOnClickListener {  checkAnswer(true)}
        false_button!!.setOnClickListener {  checkAnswer(false)}
        next_button!!.setOnClickListener {
            currentIndex = (currentIndex + 1) %  questionBank.size
            updateQuestion()
        }
        prev_button!!.setOnClickListener {
            currentIndex = if( (currentIndex - 1)<0)   0 else currentIndex-1
            updateQuestion()
        }

        updateQuestion()
    }
    private fun updateQuestion(){
        val question=questionBank[currentIndex].textResId
        question_text_view!!.setText(question!!)
    }

    private  fun checkAnswer(userPressedTrue:Boolean) {
        val answerIsTrue=questionBank[currentIndex].answerTrue
        val messageResId=if(userPressedTrue==answerIsTrue) R.string.correct_toast else R.string.incorrect_toast
        Toast.makeText(this@QuizActivity, messageResId ,Toast.LENGTH_SHORT).show()


    }
}






























