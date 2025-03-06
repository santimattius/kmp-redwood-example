package com.santimattius.kmp.skeleton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.cash.redwood.composeui.RedwoodContent
import app.cash.redwood.layout.composeui.ComposeUiRedwoodLayoutWidgetFactory
import com.santimattius.kmp.redwood.compose.ComposeUiWidgetFactory
import com.santimattius.kmp.redwood.example.widget.SchemaWidgetSystem
import com.santimattius.kmp.redwood.presenter.Counter
import com.santimattius.kmp.skeleton.ui.theme.KMPGradleSkeletonTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val widgetSystem = SchemaWidgetSystem(
            Schema = ComposeUiWidgetFactory,
            RedwoodLayout = ComposeUiRedwoodLayoutWidgetFactory(),
        )
        setContent {
            KMPGradleSkeletonTheme {
                RedwoodContent(widgetSystem,modifier = Modifier.padding(16.dp)) {
                    Counter()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KMPGradleSkeletonTheme {
        //Greeting("Android")
    }
}