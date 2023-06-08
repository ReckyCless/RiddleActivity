package com.example.riddleactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.riddleactivity.databinding.ActivityInfoBinding

class ActivityInfo : AppCompatActivity() {
    lateinit var binding: ActivityInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTotal.text = "Всего ответов: " + intent.getStringExtra("answeredTotal")
        binding.tvCorrect.text = "Правильных ответов: " + intent.getStringExtra("answeredCorrect")
        binding.tvWrong.text = "Неправильных ответов: " + intent.getStringExtra("answeredWrong")
    }

    fun btnBack(view: View) {
        finish()
    }
}