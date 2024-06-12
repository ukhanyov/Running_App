@file:OptIn(ExperimentalMaterial3Api::class)

package com.ukhanyov.run.presentation.run_overview

import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ukhanyov.core.presentation.designsystem.*
import com.ukhanyov.core.presentation.designsystem.components.RunningAppFloatingActionButton
import com.ukhanyov.core.presentation.designsystem.components.RunningAppScaffold
import com.ukhanyov.core.presentation.designsystem.components.RunningAppToolbar
import com.ukhanyov.core.presentation.designsystem.components.util.DropDownItem
import com.ukhanyov.run.presentation.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun RunOverviewScreenRoot(
    onStartRunClick: () -> Unit,
    viewModel: RunOverviewViewModel = koinViewModel()
) {
    RunOverviewScreen(
//        state = viewModel.state,
//        onAction = viewModel::onAction
        onAction = { action ->
            when (action) {
                RunOverviewAction.OnStartClick -> onStartRunClick()
                else -> {
                }
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
private fun RunOverviewScreen(
//    state: RunOverviewState,
    onAction: (RunOverviewAction) -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState
    )
    RunningAppScaffold(
        topAppBar = {
            RunningAppToolbar(
                showBackButton = false,
                title = stringResource(id = R.string.running_app),
                scrollBehavior = scrollBehavior,
                menuItems = listOf(
                    DropDownItem(
                        icon = AnalyticsIcon,
                        title = stringResource(id = R.string.analytics),
                    ),
                    DropDownItem(
                        icon = LogoutIcon,
                        title = stringResource(id = R.string.logout),
                    ),
                ),
                onMenuItemClick = { index ->
                    when (index) {
                        0 -> onAction(RunOverviewAction.OnAnalyticsClick)
                        1 -> onAction(RunOverviewAction.OnLogoutClick)
                    }
                },
                startContent = {
                    Icon(
                        imageVector = LogoIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(30.dp),
                    )
                }
            )
        },
        floatingActionButton = {
            RunningAppFloatingActionButton(
                icon = RunIcon,
                onClick = { onAction(RunOverviewAction.OnStartClick) }
            )
        }
    ) { padding ->

    }
}

@Preview
@Composable
private fun RunOverviewScreenPreview() {
    RunningAppTheme {
        RunOverviewScreen(
//            state = RunOverviewState(),
            onAction = {}
        )
    }
}