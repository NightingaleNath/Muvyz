package com.codelytical.muvyz.account.presentation

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codelytical.muvyz.R
import com.codelytical.muvyz.account.domain.model.AccountItem
import com.codelytical.muvyz.core.presentation.components.StandardToolbar
import com.codelytical.muvyz.core.presentation.theme.MoviewTheme
import com.codelytical.muvyz.core.presentation.theme.PrimaryColor
import com.codelytical.muvyz.core.presentation.theme.Theme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination<RootGraph>
@Composable
fun AccountScreen(
    navigator: DestinationsNavigator,
    viewModel: AccountViewModel = hiltViewModel(),
) {
    var shouldShowThemesDialog by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    AccountScreenContent(
        onEvent = { event ->
            when (event) {
                AccountScreenUiEvents.OnShareClicked -> {
                    try {
                        val appPackageName = context.packageName
                        val sendIntent = Intent()
                        sendIntent.action = Intent.ACTION_SEND
                        sendIntent.putExtra(
                            Intent.EXTRA_TEXT,
                            "Check out Muviz App on Playstore: https://play.google.com/store/apps/details?id=$appPackageName"
                        )
                        sendIntent.type = "text/plain"
                        context.startActivity(sendIntent)
                    } catch (e: Exception) {
                        Toast.makeText(
                            context,
                            "Unable to share the app",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                AccountScreenUiEvents.ShowThemesDialog -> {
                    shouldShowThemesDialog = true
                }
            }
        }
    )

    if (shouldShowThemesDialog) {
        ThemesDialog(
            selectedTheme = viewModel.theme.collectAsState().value,
            onDismiss = {
                shouldShowThemesDialog = false
            },
            onSelectTheme = {
                shouldShowThemesDialog = false
                viewModel.updateTheme(it)
            }
        )
    }
}

@Composable
fun AccountScreenContent(
    onEvent: (AccountScreenUiEvents) -> Unit
) {
    Scaffold(
        topBar = {
            StandardToolbar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(80.dp)
                            .padding(8.dp)
                            .padding(top = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Muvyz",
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = PrimaryColor, fontSize = 30.sp, fontStyle = FontStyle.Italic
                            )
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        val context = LocalContext.current

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(context.accountItems()) { index, item ->
                AccountItems(
                    accountItem = item,
                    onClick = {
                        when (item.title) {
                            context.getString(R.string.share) -> {
                                onEvent(
                                    AccountScreenUiEvents.OnShareClicked
                                )
                            }

                            context.getString(R.string.change_theme) -> {
                                onEvent(
                                    AccountScreenUiEvents.ShowThemesDialog
                                )
                            }
                        }
                    }
                )

                if (index != context.accountItems().size - 1) {
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.onBackground.copy(.5f),
                        thickness = .5.dp,
                    )
                }
            }
        }
    }
}

@Composable
fun AccountItems(
    accountItem: AccountItem,
    onClick: () -> Unit = {},
) {
    Column(modifier = Modifier
        .clickable {
            onClick()
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(id = accountItem.icon),
                contentDescription = accountItem.title,
                tint = MaterialTheme.colorScheme.onBackground,
            )

            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = accountItem.title,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
fun ThemesDialog(
    onDismiss: () -> Unit,
    onSelectTheme: (Int) -> Unit,
    selectedTheme: Int,
) {
    AlertDialog(
        containerColor = MaterialTheme.colorScheme.background,
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "Themes",
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(themes) { theme ->
                    ThemeItem(
                        themeName = theme.themeName,
                        themeValue = theme.themeValue,
                        icon = theme.icon,
                        onSelectTheme = onSelectTheme,
                        isSelected = theme.themeValue == selectedTheme
                    )
                }
            }
        },
        confirmButton = {
            Text(
                text = "OK",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable { onDismiss() }
            )
        }
    )
}

@Composable
fun ThemeItem(
    themeName: String,
    themeValue: Int,
    icon: Int,
    onSelectTheme: (Int) -> Unit,
    isSelected: Boolean,
) {
    Card(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        onClick = {
            onSelectTheme(themeValue)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 8.dp,
                    horizontal = 16.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(.75f),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(12.dp),
                    text = themeName,
                    style = MaterialTheme.typography.labelMedium
                )
            }

            if (isSelected) {
                Icon(
                    modifier = Modifier
                        .size(16.dp),
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                )
            }
        }
    }
}

private fun Context.accountItems() = listOf(
    AccountItem(
        getString(R.string.change_theme),
        R.drawable.ic_theme
    ),
    AccountItem(
        getString(R.string.about_title),
        R.drawable.ic_danger_circle
    ),
   /* AccountItem(
        getString(R.string.rate_us),
        R.drawable.ic_star
    ),*/
    AccountItem(
        getString(R.string.share),
        R.drawable.ic_share
    )
)

data class AppTheme(
    val themeName: String,
    val themeValue: Int,
    val icon: Int
)

val themes = listOf(
    AppTheme(
        themeName = "Use System Settings",
        themeValue = Theme.FOLLOW_SYSTEM.themeValue,
        icon = R.drawable.settings_suggest
    ),
    AppTheme(
        themeName = "Light Mode",
        themeValue = Theme.LIGHT_THEME.themeValue,
        icon = R.drawable.light_mode
    ),
    AppTheme(
        themeName = "Dark Mode",
        themeValue = Theme.DARK_THEME.themeValue,
        icon = R.drawable.dark_mode
    ),
    AppTheme(
        themeName = "Material You",
        themeValue = Theme.MATERIAL_YOU.themeValue,
        icon = R.drawable.wallpaper
    )
)

@Preview
@Composable
fun AccountScreenPreview() {
    MoviewTheme {
        AccountScreenContent(
            onEvent = {}
        )
    }
}
