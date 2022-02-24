package com.unnamedcardgame.ui.presentation

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.unnamedcardgame.R
import com.unnamedcardgame.model.Card
import com.unnamedcardgame.util.GameStatus

@Composable
fun GameConstraintLayout() {
    var vm: MyViewModel = viewModel()

    if (vm.gameStatus.value != GameStatus.ACTIVE)
        MyAlertDialog(vm)

    ConstraintLayout {
        val (card1) = createRefs()
        val (box1, box2, box3) = createRefs()
        val (lazyCol1, lazyCol2, lazyCol3) = createRefs()

        var size = 0
        var spacedBy = 0
        var fraction = 0f
        var bottomMargin = 0

        val configuration = LocalConfiguration.current
        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                size = 75
                spacedBy = -90
                fraction = .025f
                bottomMargin = 10
            }
            else -> {
                size = 100
                spacedBy = -130
                fraction = .1f
                bottomMargin = 20
            }
        }
        val topGuideline = createGuidelineFromTop(fraction)

        Image(
            painter = rememberImagePainter(
                data = vm.card.value?.image,
                builder = {
                    placeholder(R.drawable.placeholder)
                    crossfade(true)
                },
            ),
            contentDescription = vm.card.value?.toString(),
            modifier = Modifier
                .size(size.dp)
                .constrainAs(card1)
                {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(topGuideline)
                }
        )

        MyCircleColumn(
            modifier = Modifier.constrainAs(box1)
            {
                bottom.linkTo(parent.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(box2.start)
            },
            onClick = { vm.card.value?.let { vm.addCardToGroup(it, 0) } },
            text = vm.scores[0].toString(),
            size
        )

        MyCircleColumn(
            modifier = Modifier.constrainAs(box2)
            {
                bottom.linkTo(parent.bottom, margin = 10.dp)
                start.linkTo(box1.end)
                end.linkTo(box3.start)
            },
            onClick = { vm.card.value?.let { vm.addCardToGroup(it, 1) } },
            text = vm.scores[1].toString(),
            size
        )

        MyCircleColumn(
            modifier = Modifier.constrainAs(box3)
            {
                bottom.linkTo(parent.bottom, margin = 10.dp)
                start.linkTo(box2.end)
                end.linkTo(parent.end)
            },
            onClick = { vm.card.value?.let { vm.addCardToGroup(it, 2) } },
            text = vm.scores[2].toString(),
            size
        )
        MyCardColumn(modifier = Modifier.constrainAs(lazyCol1)
        {
            bottom.linkTo(box1.top, margin = bottomMargin.dp)
            start.linkTo(parent.start)
            end.linkTo(lazyCol2.start)
        }, group = vm.groups.get(0),
            size,
            spacedBy
        )

        MyCardColumn(modifier = Modifier.constrainAs(lazyCol2)
        {
            bottom.linkTo(box2.top, margin = bottomMargin.dp)
            start.linkTo(lazyCol1.end)
            end.linkTo(lazyCol3.start)
        }, group = vm.groups.get(1),
            size,
            spacedBy
        )

        MyCardColumn(
            modifier = Modifier.constrainAs(lazyCol3)
            {
                bottom.linkTo(box3.top, margin = bottomMargin.dp)
                start.linkTo(lazyCol2.end)
                end.linkTo(parent.end)
            }, group = vm.groups.get(2),
            size,
            spacedBy
        )
    }
}

@Composable
fun MyCircleColumn(modifier: Modifier, onClick: () -> Unit, text: String, size: Int) {
    Box(modifier = modifier)
    {
        MyCircle(
            color = MaterialTheme.colors.primary, modifier = Modifier
                .clickable {
                    onClick()
                },
            size
        )
        Text(
            text,
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onPrimary,
        )
    }
}

@Composable
fun MyCardColumn(modifier: Modifier, group: List<Card>, size: Int, spacedBy:Int) {
    Column(
        verticalArrangement = Arrangement.spacedBy(spacedBy.dp),
        modifier = modifier
    )
    {
        for (card in group) {
            Image(
                painter = rememberImagePainter(
                    data = card.image,
                    builder = {
                       // size(OriginalSize)
                        placeholder(R.drawable.placeholder)
                        crossfade(true)
                    },
                ),
                contentDescription = card.value,
                modifier = Modifier
                    .size(size.dp)
            )
        }
    }
}
