package com.unnamedcardgame.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyCircle(
    color: Color,
    modifier: Modifier = Modifier,
    size: Int
) {
    Box(
        modifier = modifier.composed {
            size((size - 10).dp)
                .clip(CircleShape)
                .background(color)
        }
    )
}
