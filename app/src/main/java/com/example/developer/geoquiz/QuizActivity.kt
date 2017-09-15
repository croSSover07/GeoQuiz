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

    private var answeredQuestion: Array<Boolean?>
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
        answeredQuestion = arrayOfNulls(questionBank.size)

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putInt(KEY_INDEX,currentIndex)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        if (savedInstanceState!=null)
        {
            currentIndex= savedInstanceState.get(KEY_INDEX) as Int
        }
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
            if(currentIndex<questionBank.size-1){
                currentIndex ++
                updateQuestion()

            }
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
        isBlockButtons()
    }

    private  fun checkAnswer(userPressedTrue:Boolean) {
        val answerIsTrue=questionBank[currentIndex].answerTrue
        var messageResId=0;
        if(userPressedTrue==answerIsTrue) {
            messageResId=R.string.correct_toast
            answeredQuestion[currentIndex]=true;
        } else {
            messageResId=R.string.incorrect_toast
            answeredQuestion[currentIndex]=false;
        }
        isBlockButtons()
        Toast.makeText(this@QuizActivity, messageResId ,Toast.LENGTH_SHORT).show()
        checkScore()
    }
    private fun isBlockButtons(){

        if( answeredQuestion[currentIndex]!=null){
            true_button!!.isClickable=false;
            false_button!!.isClickable=false;
        }
         else{
            true_button!!.isClickable=true;
            false_button!!.isClickable=true;
        }
    }

    private fun checkScore()
    {
        var sum=0
        var allAnswered=0

        answeredQuestion.filter { it!=null }.forEach {  allAnswered++ }
        if(allAnswered==questionBank.size) {
            answeredQuestion.filter { it==true }.forEach { sum++ }
            val percentage=sum.toFloat().div(questionBank.size)

            Toast.makeText(this@QuizActivity,percentage.toString() ,Toast.LENGTH_SHORT).show()
        }

    }
}






























