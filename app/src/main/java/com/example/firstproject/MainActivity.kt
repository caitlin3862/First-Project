package com.example.firstproject

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var layout: LinearLayout
    private lateinit var scoreText: TextView
    private lateinit var strikeText: TextView
    private lateinit var num1Text: TextView
    private lateinit var num2Text: TextView
    private lateinit var button: Button
    private lateinit var instructions: TextView

    private var score: Int = 0
    private var strike: Int = 0
    private var num1: Int = 0
    private var num2: Int = 0
    private var gameStart: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*-----------------------------------------------*/
        //Setting variables for each component in the game
        layout = findViewById(R.id.main)

        scoreText= findViewById(R.id.score)
        strikeText = findViewById(R.id.strikes)
        instructions = findViewById(R.id.instructions)
        num1Text = findViewById(R.id.num1)
        num2Text = findViewById(R.id.num2)

        button = findViewById(R.id.button)
        /*-----------------------------------------------*/
        scoreText.text = "Score: " + score
        strikeText.text = "Strikes: " + strike
        gameStart = false;

        button.setOnClickListener {
            if (!gameStart){
                gameStart = true
                playGame()
            } else {
                /* Resets all values back to default/starting values */
                instructions.text = resources.getText(R.string.tap)
                score = 0
                strike = 0
                scoreText.setTextColor(Color.BLACK)
                strikeText.setTextColor(Color.BLACK)
                scoreText.text = "Score: " + score
                strikeText.text = "Strike: " + strike
                reset()
                setRandNum()
            }
        }

    }

    private fun playGame(){
        button.text = resources.getText(R.string.reset)
        instructions.text = resources.getText(R.string.tap)
        setRandNum()

        num1Text.setOnClickListener {
            checkAnswer(num1, num2)
            checkWin()
        }

        num2Text.setOnClickListener {
            checkAnswer(num2, num1)
            checkWin()
        }
    }

    private fun checkWin(){
        if (score == 10){
            reset()
            scoreText.setTextColor(Color.GREEN)
            strikeText.setTextColor(Color.BLACK)
            scoreText.text = "Score: " + score
            instructions.text = resources.getText(R.string.again)
            val winToast: Toast = Toast.makeText(this, "Congrats, you won! ^^", Toast.LENGTH_SHORT)
            winToast.show()
        } else if (strike == 3){
            reset()
            strikeText.setTextColor(Color.RED)
            scoreText.setTextColor(Color.BLACK)
            strikeText.text = "Strikes: " + strike
            instructions.text = resources.getText(R.string.again)
            val loseToast: Toast = Toast.makeText(this, "Sorry, you lost :(", Toast.LENGTH_SHORT)
            loseToast.show()
        } else {
            //Nothing
        }
    }

    private fun reset() {
        layout.setBackgroundColor(Color.parseColor("#FDEF74"))
        num1Text.text = null
        num2Text.text = null
    }

    private fun checkAnswer(first: Int, second: Int){
        if (first > second){
            incrementScore()
            layout.setBackgroundColor(Color.GREEN)
            setRandNum()
        } else {
            incrementStrike()
            layout.setBackgroundColor(Color.RED)
            setRandNum()
        }
    }

    private fun setRandNum() {
        num1 = Random.nextInt(101)
        num2 = Random.nextInt(101)
        while (num1 == num2){
            num1 = Random.nextInt(101)
        }
        num1Text.text = num1.toString()
        num2Text.text = num2.toString()
    }

    private fun incrementScore(){
        if (score < 10 && strike < 3) {
            score++;
            scoreText.setTextColor(Color.YELLOW)
            strikeText.setTextColor(Color.BLACK)
            scoreText.text = "Score: " + score
        }
    }

    private fun incrementStrike(){
        if (strike < 3 && score < 10) {
            strike++;
            strikeText.setTextColor(Color.YELLOW)
            scoreText.setTextColor(Color.BLACK)
            strikeText.text = "Strikes: " + strike
        }
    }

}