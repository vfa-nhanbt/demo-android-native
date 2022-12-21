package com.nhanbt.demo_kotlin_native.core.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nhanbt.demo_kotlin_native.R
import com.nhanbt.demo_kotlin_native.core.response.BaseResponse
import com.nhanbt.demo_kotlin_native.features.note.presentation.notes.components.RoundedAvatar
import com.nhanbt.demo_kotlin_native.features.weather.presentation.viewModel.WeatherViewModel

@Composable
fun TopBar(
    weatherViewModel: WeatherViewModel = hiltViewModel(),
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RoundedAvatar()
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.h4,
                    textAlign = TextAlign.Left
                )
                Spacer(modifier = Modifier.weight(1f))
                when (val response = weatherViewModel.weatherResponse) {
                    is BaseResponse.Loading -> ProgressBar()
                    is BaseResponse.Success -> Text(
                        text = "${response.data.temperatureString()}",
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center
                    )
                }
            }

        },
        backgroundColor = MaterialTheme.colors.background
    )
}
