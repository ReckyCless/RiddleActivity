package com.example.riddleactivity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.riddleactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val RiddlesList: List<Riddles> = listOf(
        Riddles("Загадка 1", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 2", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 3", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 4", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 5", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 6", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 7", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 8", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 9", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 10", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 11", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 12", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 13", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 14", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 15", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 16", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 17", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 18", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 19", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 20", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 21", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 22", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 23", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 24", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 25", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 26", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 27", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 28", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 29", setOf<String>("Верно", "No1", "No2", "No3")),
        Riddles("Загадка 30", setOf<String>("Верно", "No1", "No2", "No3"))
    )

    var GameList = (RiddlesList.shuffled()).take(10)
    var gameInProgress = false
    var answeredTotal = 0;
    var answeredCorrect = 0;
    var correctAnswer = 0;
    var questionAnswered = 0;
    var userAnswer = "";

    lateinit var binding: ActivityMainBinding
    private var launcher: ActivityResultLauncher<Intent>? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val answer = result.data?.getStringExtra("chosenAnswer")
                var corr = "Неправильный ответ"
                if (answer == GameList.get(questionAnswered).answers.elementAtOrNull(0).toString()) {
                    corr = "Правильный ответ"
                    answeredCorrect++
                }
                val qText = GameList.get(questionAnswered).riddleText
                questionAnswered++
                if (questionAnswered == 10)
                {
                    binding.buttonInfo.isEnabled = true
                    gameInProgress = false
                    answeredTotal += questionAnswered
                    questionAnswered = 0
                    binding.buttonQuestion.text = "Начать"
                }
                binding.buttonQuestion.isEnabled = true
                binding.buttonAnswer.isEnabled = false
                binding.textQuest.text =
                    qText + "\nВыбранный ответ: " + answer + "\n" + corr
            }
        }
    }



    fun btnClickNext(view: View) {
        if (gameInProgress) {
            binding.textQuest.text = GameList.get(questionAnswered).riddleText
            binding.textView.text = "Вопрос " + (questionAnswered+1)
            binding.buttonAnswer.isEnabled = true
            binding.buttonQuestion.isEnabled = false
            binding.buttonQuestion.text = "Загадка"
        }
        else {
            GameList = (RiddlesList.shuffled()).take(10)
            binding.textQuest.text = GameList.get(questionAnswered).riddleText
            binding.buttonAnswer.isEnabled = true
            binding.buttonInfo.isEnabled = false
            gameInProgress = true
            binding.textView.text = "Вопрос 1"
            questionAnswered = 0
            answeredCorrect = 0
        }
    }

    fun btnClickAnswer(view: View) {
        val intent = Intent(this, ActivityAnswer::class.java)
        intent.putExtra("question", GameList[questionAnswered].riddleText)
        intent.putExtra("answerCorrect", GameList[questionAnswered].answers.elementAtOrNull(0))
        intent.putExtra("answer1", GameList[questionAnswered].answers.elementAtOrNull(1))
        intent.putExtra("answer2", GameList[questionAnswered].answers.elementAtOrNull(2))
        intent.putExtra("answer3", GameList[questionAnswered].answers.elementAtOrNull(3))
        launcher?.launch(intent)
    }

    fun btnClickInfo(view: View) {
        val intent2 = Intent(this, ActivityInfo::class.java)
        intent2.putExtra("answeredTotal", answeredTotal.toString())
        intent2.putExtra("answeredCorrect", answeredCorrect.toString())
        intent2.putExtra("answeredWrong", (answeredTotal - answeredCorrect).toString())
        startActivity(intent2)
    }
}

class Riddles(
    val riddleText: String,
    var answers:Set<String>) {
}