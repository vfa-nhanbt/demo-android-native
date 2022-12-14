package com.nhanbt.demo_kotlin_native.features.note.presentation.addNote.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun DateTimePicker(
    dateTimePickerSubmit: (LocalDateTime) -> Unit
) {
    var pickedDate by remember {
        mutableStateOf(LocalDate.now().plusDays(1))
    }
    var pickedTime by remember {
        mutableStateOf(LocalTime.MIDNIGHT)
    }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("dd/MM/yyyy")
                .format(pickedDate)
        }
    }
    val formattedTime by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("hh:mm")
                .format(pickedTime)
        }
    }
    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()

    val textStyle = MaterialTheme.typography.body1
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Deadline: ",
            style = textStyle
        )
        TextButton(onClick = {
            dateDialogState.show()
        }) {
            Text(
                text = formattedDate,
                style = textStyle
            )
        }
        TextButton(onClick = {
            timeDialogState.show()
        }) {
            Text(
                text = formattedTime,
                style = textStyle
            )
        }
        MaterialDialog(
            dialogState = dateDialogState,
            buttons = {
                positiveButton(text = "OK") {
                    dateTimePickerSubmit(pickedDate.atTime(pickedTime))
                }
                negativeButton(text = "Cancel")
            }
        ) {
            datepicker(
                initialDate = LocalDate.now().plusDays(1),
                title = "Pick deadline date",
                allowedDateValidator = {
                    it.isAfter(LocalDate.now())
                }
            ) {
                pickedDate = it
            }
        }
        MaterialDialog(
            dialogState = timeDialogState,
            buttons = {
                positiveButton(text = "OK") {
                    dateTimePickerSubmit(pickedDate.atTime(pickedTime))
                }
                negativeButton(text = "Cancel")
            }
        ) {
            timepicker(
                initialTime = LocalTime.MIDNIGHT,
                title = "Pick deadline time",
                is24HourClock = false,
            ) {
                pickedTime = it
            }
        }
    }
}
