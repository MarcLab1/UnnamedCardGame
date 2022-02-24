package com.unnamedcardgame.ui.presentation

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.unnamedcardgame.util.GameStatus

@Composable
fun MyAlertDialog(vm: MyViewModel) {
    AlertDialog(
        onDismissRequest = {
            vm.newGame()
        },
        title = {
            Text(text = "Catch 21")
        },
        text = {
            if (vm.gameStatus.value == GameStatus.WINNER)
                Text("You win!")
            else
                Text("Busted :(")
        },
        confirmButton = {
            Button(

                onClick = {
                    vm.newGame()
                }) {
                Text("Ok")
            }
        },
        dismissButton = {
        }
    )
}