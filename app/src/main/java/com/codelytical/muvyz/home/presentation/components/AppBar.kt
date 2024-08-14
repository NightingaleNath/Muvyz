package com.codelytical.muvyz.home.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codelytical.muvyz.core.presentation.components.StandardToolbar
import com.codelytical.muvyz.core.presentation.theme.PrimaryColor

@Composable
fun AppBar(
    onBackArrowClicked: () -> Unit
) {
    StandardToolbar(
        onBackArrowClicked = onBackArrowClicked,
        title = { AppBarTitle() },
        modifier = Modifier.fillMaxWidth(),
        showBackArrow = false,
    )
}

@Composable
private fun AppBarTitle() {
    Column {
        Text(
            text = "Muvyz",
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(80.dp)
                .padding(8.dp)
                .padding(top = 16.dp),
            style = MaterialTheme.typography.titleLarge.copy(
                color = PrimaryColor, fontSize = 30.sp, fontStyle = FontStyle.Italic
            )
        )
    }
}