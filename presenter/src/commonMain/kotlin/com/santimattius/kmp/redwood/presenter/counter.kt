package com.santimattius.kmp.redwood.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import app.cash.redwood.Modifier
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.compose.Box
import app.cash.redwood.layout.compose.Column
import com.santimattius.kmp.redwood.example.compose.Button
import com.santimattius.kmp.redwood.example.compose.Text

@Composable
fun Counter(modifier: Modifier = Modifier, value: Int = 0) {
    var count by rememberSaveable { mutableIntStateOf(value) }

    Box(
        width = Constraint.Fill,
        height = Constraint.Fill,
        horizontalAlignment = CrossAxisAlignment.Center,
        verticalAlignment = CrossAxisAlignment.Center,
        modifier = modifier,
    ) {

        Column(
            horizontalAlignment = CrossAxisAlignment.Center,
        ) {
            Text("You have pushed the button this many times")
            Text(text = count.toString())
            Button(
                "Increment",
                onClick = { count++ },
            )
        }
    }
}