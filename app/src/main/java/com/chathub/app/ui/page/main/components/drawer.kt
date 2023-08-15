package com.chathub.app.ui.page.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material.icons.twotone.History
import androidx.compose.material.icons.twotone.Language
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chathub.app.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDrawer(
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    onItemClicked: (MainDrawerItem) -> Unit = {},
    content: @Composable () -> Unit
) {

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(
                    onItemClicked
                )
            }
        },
        content = content
    )

}

@Composable
fun DrawerContent(
    onItemClicked: (MainDrawerItem) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
        DrawerHeader { onItemClicked(MainDrawerItem.Profile) }

        DividerItem()
        //chat
        DrawerItem(
            text = stringResource(id = R.string.main_drawer_new_chat),
            icon = rememberVectorPainter(image = Icons.TwoTone.Add)
        ) { onItemClicked(MainDrawerItem.NewChat) }

        DrawerItem(
            text = stringResource(id = R.string.main_drawer_chat_history),
            icon = rememberVectorPainter(image = Icons.TwoTone.History)
        ) { onItemClicked(MainDrawerItem.History) }

        DrawerItem(
            text = stringResource(id = R.string.main_drawer_preset_list),
            icon = rememberVectorPainter(image = Icons.TwoTone.Person)
        ) { onItemClicked(MainDrawerItem.PresetList) }

        DividerItem()
        // settings
        DrawerItem(
            text = stringResource(id = R.string.main_menu_settings),
            icon = rememberVectorPainter(image = Icons.TwoTone.Settings)
        ) { onItemClicked(MainDrawerItem.Settings) }

        DrawerItem(
            text = stringResource(id = R.string.main_drawer_configure_model),
            icon = rememberVectorPainter(image = Icons.TwoTone.Language)
        ) {

        }
    }
}


@Composable
private fun DrawerItem(text: String, icon: Painter, clicked: () -> Unit) {
    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(CircleShape)
            .clickable(onClick = clicked),
        verticalAlignment = CenterVertically
    ) {
        Icon(
            painter = icon,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
            contentDescription = null
        )
        Text(
            text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

@Composable
fun DividerItem(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier.padding(vertical = 12.dp, horizontal = 32.dp),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
    )
}


@Composable
private fun DrawerHeader(clicked: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                end = 32.dp,
                start = 32.dp,
                top = 16.dp,
                bottom = 6.dp
            )
            .clip(CircleShape)
            .clickable(onClick = clicked),
        verticalAlignment = CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 4.dp)
                .height(96.dp)
                .width(96.dp)

        )
        Column {
            Text(
                text = "ChatHubLab",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 8.dp)
            )
            Text(
                text = "AI Powered Chat",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(
                    start = 8.dp,
                    top = 12.dp,
                )
            )
        }
    }
}


enum class MainDrawerItem {
    Profile,
    NewChat,
    PresetList,
    History,
    Settings,
    ConfigureModel
}