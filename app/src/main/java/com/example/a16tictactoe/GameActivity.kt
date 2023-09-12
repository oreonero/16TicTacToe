package com.example.a16tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat

class GameActivity : AppCompatActivity() {

    private lateinit var gameManager: GameLogic
    private lateinit var one: TextView
    private lateinit var two: TextView
    private lateinit var three: TextView
    private lateinit var four: TextView
    private lateinit var five: TextView
    private lateinit var six: TextView
    private lateinit var seven: TextView
    private lateinit var eight: TextView
    private lateinit var nine: TextView
    private lateinit var startNewGameButton: Button
    private lateinit var player1Points: TextView
    private lateinit var player2Points: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val intent = intent
        val playerOneName = intent.getStringExtra("playerOneName")
        val playerTwoName = intent.getStringExtra("playerTwoName")

        val playerOneNameDisp = findViewById<TextView>(R.id.pOneNameDisp)
        val playerTwoNameDisp = findViewById<TextView>(R.id.pTwoNameDisp)
        playerOneNameDisp.text = "$playerOneName (X):"
        playerTwoNameDisp.text = "$playerTwoName (O):"
        gameManager = GameLogic()
        val ExitButton = findViewById<Button>(R.id.ExitButton)
        ExitButton.setOnClickListener {
            finish()
        }

        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
        startNewGameButton = findViewById(R.id.start_new_game_button)
        player1Points = findViewById(R.id.player_one_score)
        player2Points = findViewById(R.id.player_two_score)

        one.setOnClickListener { onBoxClicked(one, CurrPosi(0, 0)) }
        two.setOnClickListener { onBoxClicked(two, CurrPosi(0, 1)) }
        three.setOnClickListener { onBoxClicked(three, CurrPosi(0, 2)) }
        four.setOnClickListener { onBoxClicked(four, CurrPosi(1, 0)) }
        five.setOnClickListener { onBoxClicked(five, CurrPosi(1, 1)) }
        six.setOnClickListener { onBoxClicked(six, CurrPosi(1, 2)) }
        seven.setOnClickListener { onBoxClicked(seven, CurrPosi(2, 0)) }
        eight.setOnClickListener { onBoxClicked(eight, CurrPosi(2, 1)) }
        nine.setOnClickListener { onBoxClicked(nine, CurrPosi(2, 2)) }

        startNewGameButton.setOnClickListener {
            ///startNewGameButton.visibility = View.GONE
            gameManager.reset()
            resetboxes()
        }

        updatePoints()
    }

    private fun updatePoints() {
        player1Points.text = "${gameManager.player1Points} "
        player2Points.text = "${gameManager.player2Points} "
    }


    private fun resetboxes() {
        one.text = ""
        two.text = ""
        three.text = ""
        four.text = ""
        five.text = ""
        six.text = ""
        seven.text = ""
        eight.text = ""
        nine.text = ""
        one.background = null
        two.background = null
        three.background = null
        four.background = null
        five.background = null
        six.background = null
        seven.background = null
        eight.background = null
        nine.background = null
        one.isEnabled = true
        two.isEnabled = true
        three.isEnabled = true
        four.isEnabled = true
        five.isEnabled = true
        six.isEnabled = true
        seven.isEnabled = true
        eight.isEnabled = true
        nine.isEnabled = true
    }

    private fun onBoxClicked(box: TextView, position: CurrPosi) {
        if (box.text.isEmpty()) {
            box.text = gameManager.currentPlayerMark
            val winningLine = gameManager.makeMove(position)
            if (winningLine != null) {
                updatePoints()
                disableBoxes()
                startNewGameButton.visibility = View.VISIBLE
                showWinner(winningLine)
            }
        }
    }

    private fun disableBoxes() {
        one.isEnabled = false
        two.isEnabled = false
        three.isEnabled = false
        four.isEnabled = false
        five.isEnabled = false
        six.isEnabled = false
        seven.isEnabled = false
        eight.isEnabled = false
        nine.isEnabled = false
    }

    private fun showWinner(winningLine: WinConditions) {
        val (winningBoxes, background) = when (winningLine) {
            WinConditions.ROW_0 -> Pair(listOf(one, two, three), R.drawable.horizontal_line)
            WinConditions.ROW_1 -> Pair(listOf(four, five, six), R.drawable.horizontal_line)
            WinConditions.ROW_2 -> Pair(listOf(seven, eight, nine), R.drawable.horizontal_line)
            WinConditions.COLUMN_0 -> Pair(listOf(one, four, seven), R.drawable.vertical_line)
            WinConditions.COLUMN_1 -> Pair(listOf(two, five, eight), R.drawable.vertical_line)
            WinConditions.COLUMN_2 -> Pair(listOf(three, six, nine), R.drawable.vertical_line)
            WinConditions.DIAGONAL_LEFT -> Pair(listOf(one, five, nine),
                R.drawable.left_diagonal_line
            )
            WinConditions.DIAGONAL_RIGHT -> Pair(listOf(three, five, seven),
                R.drawable.right_diagonal_line
            )
           /* WinningLine.DRAW -> {
                Pair(listOf(one, two, three), R.drawable.horizontal_line)
                Pair(listOf(four, five, six), R.drawable.horizontal_line)
                Pair(listOf(seven, eight, nine), R.drawable.horizontal_line)
            }*/
        }

        winningBoxes.forEach { box ->
            box.background = ContextCompat.getDrawable(this, background)
        }
    }
}