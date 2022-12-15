package com.nhanbt.demo_kotlin_native.features.note.presentation.addNote

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nhanbt.demo_kotlin_native.core.components.ProgressBar
import com.nhanbt.demo_kotlin_native.core.response.BaseResponse
import com.nhanbt.demo_kotlin_native.features.category.presentation.CategoriesViewModel
import com.nhanbt.demo_kotlin_native.features.note.presentation.addNote.components.DateTimePicker
import com.nhanbt.demo_kotlin_native.features.note.presentation.addNote.components.DefaultCheckBox
import com.nhanbt.demo_kotlin_native.features.note.presentation.addNote.components.TransparentHintTextField
import com.nhanbt.demo_kotlin_native.features.note.presentation.addNote.viewModel.AddNoteEvent
import com.nhanbt.demo_kotlin_native.features.note.presentation.addNote.viewModel.AddNoteViewModel
import kotlinx.coroutines.flow.collectLatest
import java.time.ZoneId

@Composable
fun AddNoteScreen(
    navController: NavController,
    cateViewModel: CategoriesViewModel = hiltViewModel(),
    viewModel: AddNoteViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()

    val titleState = viewModel.noteTitle.value
    val descriptionState = viewModel.noteDescription.value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddNoteViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddNoteViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddNoteEvent.SaveNote)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Done, contentDescription = "Save note")
            }
        },
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.background
    ) { padding ->
        when (val response = viewModel.noteResponse) {
            is BaseResponse.Loading -> ProgressBar()
            is BaseResponse.Success -> Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
            ) {
                when (val res = cateViewModel.categoriesResponse) {
                    is BaseResponse.Loading -> ProgressBar()
                    is BaseResponse.Success -> LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        content = {
                            items(res.data.size) { i ->
                                DefaultCheckBox(
                                    title = res.data[i].title,
                                    isChecked = response.data.categories.contains(res.data[i].id),
                                    onCheckedChange = { value ->
                                        viewModel.onEvent(
                                            AddNoteEvent.SelectCategory(
                                                value,
                                                res.data[i].id
                                            )
                                        )
                                    },
                                )
                            }
                        },
                    )
                    is BaseResponse.Failure -> print(res.e)
                }
                DateTimePicker(
                    dateTimePickerSubmit = {
                        viewModel.onEvent(AddNoteEvent.SelectDateTime(it))
                    },
                    initDateTime = response.data.deadline.toDate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime()
                )
                Spacer(modifier = Modifier.height(16.dp))
                TransparentHintTextField(
                    text = titleState.text,
                    hint = titleState.hint,
                    onValueChange = {
                        viewModel.onEvent(AddNoteEvent.EnteredTitle(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddNoteEvent.ChangeTitleFocus(it))
                    },
                    isHintVisible = titleState.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(16.dp))
                TransparentHintTextField(
                    text = descriptionState.text,
                    hint = descriptionState.hint,
                    onValueChange = {
                        viewModel.onEvent(AddNoteEvent.EnteredDescription(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddNoteEvent.ChangeDescriptionFocus(it))
                    },
                    isHintVisible = descriptionState.isHintVisible,
                    textStyle = MaterialTheme.typography.body1,
                    modifier = Modifier.fillMaxHeight()
                )
            }
            is BaseResponse.Failure -> print(response.e)
        }
    }
}
