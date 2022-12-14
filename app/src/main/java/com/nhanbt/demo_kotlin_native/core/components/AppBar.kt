package com.nhanbt.demo_kotlin_native.core.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.nhanbt.demo_kotlin_native.R

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Left
            )
        },
        backgroundColor = MaterialTheme.colors.background
    )
}