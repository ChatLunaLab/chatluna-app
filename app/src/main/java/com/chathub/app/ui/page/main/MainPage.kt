package com.chathub.app.ui.page.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material.icons.twotone.InsertDriveFile
import androidx.compose.material.icons.twotone.MoreVert
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chathub.app.R
import com.chathub.app.ui.page.main.components.MainAppBar
import com.chathub.app.ui.page.main.components.MainDrawer
import com.chathub.app.ui.resource.LocalNavController
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage() {

    val viewModel = viewModel<MainViewModel>()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val drawerOpen by viewModel.drawerShouldBeOpened
        .collectAsStateWithLifecycle()

    if (drawerOpen) {
        // Open drawer and reset state in VM.
        LaunchedEffect(Unit) {
            // wrap in try-finally to handle interruption whiles opening drawer
            try {
                drawerState.open()
            } finally {
                viewModel.resetOpenDrawerAction()
            }
        }
    }

    // Intercepts back navigation when the drawer is open
    val scope = rememberCoroutineScope()
    if (drawerState.isOpen) {
        BackHandler {
            scope.launch {
                drawerState.close()
            }
        }
    }

    MainDrawer(
        drawerState = drawerState,
    ) {
        MainPageContent(viewModel)
    }

}

@Composable
fun MainPageContent(viewModel: MainViewModel) {
    Scaffold(
        topBar = {
            MainAppBar(viewModel)
        },
    ) { paddingValues ->
        // using paddingValues

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues), color = MaterialTheme.colorScheme.background
        ) {
            Text("Android")
        }

    }
}
