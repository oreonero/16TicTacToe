package com.example.a16tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {


    private lateinit var startNewGameButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val Quit = findViewById<Button>(R.id.quitGame)
        Quit.setOnClickListener {
            finish()
        }

        startNewGameButton = findViewById(R.id.startGameButton)

        startNewGameButton.setOnClickListener {
           callActivity()
        }
    }
    private fun callActivity(){
        val pOne = findViewById<EditText>(R.id.playerOneNameEntry)
        val pTwo = findViewById<EditText>(R.id.playerTwoNameEntry)
        val enterName = findViewById<TextView>(R.id.enterNamePrompt)
        if (pOne.text.toString().isEmpty()){
            enterName.text = getString(R.string.enterNamePrompt)
        }
        else {
            val pOneName = pOne.text.toString()
            val pTwoName = pTwo.text.toString()
            val intent1 = Intent(this, GameActivity::class.java)
            intent1.putExtra("playerOneName", pOneName)
            intent1.putExtra("playerTwoName", pTwoName)
            startActivity(intent1)
        }
    }
}

