package com.github.talkbacktutorial.activities.lesson0

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.talkbacktutorial.R
import com.github.talkbacktutorial.TextToSpeechEngine
import com.github.talkbacktutorial.databinding.ActivityLesson0Part1Binding

class Lesson0Part1Activity : AppCompatActivity() {

    private lateinit var ttsEngine: TextToSpeechEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        val binding: ActivityLesson0Part1Binding = DataBindingUtil.setContentView(this,
            R.layout.activity_lesson0_part1
        )
        binding.continueButton.button.visibility = View.GONE

        this.ttsEngine = TextToSpeechEngine(this)
            .onFinishedSpeaking(triggerOnce = true) {
                binding.continueButton.button.visibility = View.VISIBLE
            }

        binding.continueButton.button.setOnClickListener {
            startActivity(Intent(this, Lesson0Part2Activity::class.java))
        }

        val intro = """
            Welcome. 
            In your first lesson, you'll learn to move forwards and backwards between menu items, as well as interact with them.
            Double tap to continue.
        """.trimIndent()
        this.ttsEngine.speakOnActivityOpen(intro)
    }

}