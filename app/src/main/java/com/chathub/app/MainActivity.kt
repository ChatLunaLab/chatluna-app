package com.chathub.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import com.chathub.app.ui.page.NavGraph
import com.chathub.app.ui.resource.theme.MyLuaAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyLuaAppTheme {
                NavGraph()
            }
        }
    }
}

