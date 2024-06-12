@file:OptIn(ExperimentalMaterial3Api::class)

package com.ukhanyov.core.presentation.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ukhanyov.core.presentation.designsystem.*
import com.ukhanyov.core.presentation.designsystem.R
import com.ukhanyov.core.presentation.designsystem.components.util.DropDownItem

@Composable
fun RunningAppToolbar(
    modifier: Modifier = Modifier,
    showBackButton: Boolean,
    title: String,
    menuItems: List<DropDownItem> = emptyList(),
    onMenuItemClick: (Int) -> Unit = {},
    onBackClick: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    startContent: (@Composable () -> Unit)? = null,
) {
    var isDropDownOpen by rememberSaveable {
        mutableStateOf(false)
    }
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                startContent?.invoke()
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = Poppins
                )
            }
        },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
        ),
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = ArrowLeftIcon,
                        contentDescription = stringResource(id = R.string.go_back),
                        tint = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
        },
        actions = {
            if (menuItems.isNotEmpty()) {
                Box {
                    DropdownMenu(
                        expanded = isDropDownOpen,
                        onDismissRequest = { isDropDownOpen = false }
                    ) {
                        menuItems.forEachIndexed { index, item ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clickable { onMenuItemClick(index) }
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = item.title)
                            }
                        }
                    }
                    IconButton(onClick = { isDropDownOpen = true }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = stringResource(id = R.string.open_menu),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun RunningAppToolbarPreview() {
    RunningAppTheme {
        RunningAppToolbar(
            showBackButton = false,
            title = "RunningApp",
            modifier = Modifier.fillMaxWidth(),
            startContent = {
                Icon(
                    imageVector = LogoIcon,
                    contentDescription = null,
                    tint = RunningAppGreen,
                    modifier = Modifier.size(35.dp)
                )
            },
            menuItems = listOf(
                DropDownItem(
                    icon = AnalyticsIcon,
                    title = "Analytics",
                )
            )
        )
    }
}