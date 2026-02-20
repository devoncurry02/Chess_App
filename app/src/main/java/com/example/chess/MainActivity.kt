package com.example.chess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chess.ui.theme.ChessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChessTheme {

                var startGame by remember {mutableStateOf(false)}

                if (startGame) {
                    PlayGame()
                }
                else {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Greeting(gamename = "Chess", modifier = Modifier.padding(innerPadding), onStartClick = {startGame = true }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(gamename: String, modifier: Modifier = Modifier, onStartClick: () -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = gamename)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onStartClick) {
            Text(text = "Play")
        }
    }
}

enum class pPiece {K, Q, R, B, N, P}
enum class pColor {W, B}

@Composable
fun PlayGame() {
    var turn by remember {mutableStateOf("W")}
    val validRange = remember {arrayOf(0, 1, 2, 3, 4, 5, 6, 7)}
    val board = remember {arrayOf(
        arrayOf("BR", "BP", "", "", "", "", "WP", "WR"),
        arrayOf("BN", "BP", "", "", "", "", "WP", "WN"),
        arrayOf("BB", "BP", "", "", "", "", "WP", "WB"),
        arrayOf("BQ", "BP", "", "", "", "", "WP", "WQ"),
        arrayOf("BK", "BP", "", "", "", "", "WP", "WK"),
        arrayOf("BB", "BP", "", "", "", "", "WP", "WB"),
        arrayOf("BN", "BP", "", "", "", "", "WP", "WN"),
        arrayOf("BR", "BP", "", "", "", "", "WP", "WR")
    )}
    var selectedY by remember {mutableStateOf(-1)}
    var selectedX by remember {mutableStateOf(-1)}
    Column(
        modifier = Modifier.fillMaxSize().padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(20.dp),
            text = "uɹnʇ s,ǝʇıɥM"
        )
        Box(
            modifier = Modifier.fillMaxWidth().aspectRatio(1f).border(width = 5.dp, color = Color.Black).padding(6.dp).background(Color.White)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                for (X in validRange) {
                    Row (
                        modifier = Modifier.weight(1f)
                    ) {
                        for (Y in validRange) {
                            val selection: Boolean = X == selectedX && Y == selectedY
                            var highlight = Color.White
                            if (selection) {
                                highlight = Color.LightGray
                            }
                            Box(
                                modifier = Modifier.weight(1f).fillMaxHeight().border(width = 1.dp, color = Color.LightGray).clickable {
                                    if (selectedX == -1) {
                                        if (board[Y][X][0].toString() == turn) {
                                            selectedX = X
                                            selectedY = Y
                                        }
                                    }
                                    else {
                                        val validMove = checkMovePiece(board, X, Y, selectedX, selectedY)
                                        if (validMove) {
                                            board[Y][X] = board[selectedX][selectedY]
                                            board[selectedY][selectedX] = ""
                                            if (turn == "W") {
                                                turn = "B"
                                            }
                                            else {
                                                turn = "W"
                                            }
                                        }
                                    }
                                },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = reSkin(board[Y][X]),
                                    fontSize = 36.sp
                                )
                            }
                        }
                    }
                }
            }
        }
        Text(
            modifier = Modifier.padding(20.dp),
            text = "Your move"
        )
    }
}


fun reSkin(cell: String): String {
    if (cell == "WP") {return "♙"}
    if (cell == "WK") {return "♔"}
    if (cell == "WQ") {return "♕"}
    if (cell == "WB") {return "♗"}
    if (cell == "WN") {return "♘"}
    if (cell == "WR") {return "♖"}
    if (cell == "BP") {return "♟"}
    if (cell == "BK") {return "♚"}
    if (cell == "BQ") {return "♛"}
    if (cell == "BB") {return "♝"}
    if (cell == "BN") {return "♞"}
    if (cell == "BR") {return "♜"}
    return ""
}

fun checkMovePiece(board: Array<Array<String>>, StartX: Int, StartY: Int, EndX: Int, EndY: Int): Boolean {
    val validMove: Boolean = false
    val piece = board[StartY][StartX][1].toString()
    val color = board[StartY][StartX][0].toString()

    val top_left = board[]

    if (color == "W") {
        when(piece) {
            "Pawn" -> if (top_left == )
        }
    }
    if (color == "B") {

    }
    return validMove
}