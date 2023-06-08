package com.example.riddleactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.riddleactivity.databinding.ActivityAnswerBinding
import android.view.View

class ActivityAnswer : AppCompatActivity() {
    lateinit var binding: ActivityAnswerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)
        binding = ActivityAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var Answers: List<String?> = listOf(
            intent.getStringExtra("answer1"),
            intent.getStringExtra("answer2"),
            intent.getStringExtra("answer3"),
            intent.getStringExtra("answerCorrect")
        ).shuffled()

        binding.textViewQuestion.text = intent.getStringExtra("question")
        binding.radioButton.text = Answers[0]
        binding.radioButton2.text = Answers[1]
        binding.radioButton3.text = Answers[2]
        binding.radioButton4.text = Answers[3]
    }


    fun btnClickCheck(view: View) {
        var chosenValue = "";
        if (binding.radioButton.isChecked) {
            chosenValue = binding.radioButton.text.toString()
        }
        else if (binding.radioButton2.isChecked) {
            chosenValue = binding.radioButton2.text.toString()
        }
        else if (binding.radioButton3.isChecked) {
            chosenValue = binding.radioButton3.text.toString()
        }
        else if (binding.radioButton4.isChecked) {
            chosenValue = binding.radioButton4.text.toString()
        }
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("chosenAnswer", chosenValue)
        setResult(RESULT_OK, intent)
        finish()
    }
}