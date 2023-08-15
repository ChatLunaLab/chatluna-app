package com.chathub.app.ui.page.main.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.material.icons.twotone.InsertDriveFile
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material.icons.twotone.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chathub.app.R
import com.chathub.app.ui.page.common.ChatHubAppBar
import com.chathub.app.ui.page.main.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(
    viewModel: MainViewModel
) {

    val showMenuState by viewModel.isExpandedMenu.collectAsStateWithLifecycle()

    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name)) },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.TwoTone.Add, contentDescription = null)
            }

            IconButton(onClick = {
                viewModel.toggleMenu()
            }) {
                Icon(Icons.TwoTone.MoreVert, contentDescription = null)
            }

            MainMenu(isExpanded = showMenuState) {
                viewModel.toggleMenu()
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                viewModel.toggleDrawer()
            }) {
                Icon(Icons.TwoTone.Menu, contentDescription = null)
            }
        }
    )
}
