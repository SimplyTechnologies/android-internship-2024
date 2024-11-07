package com.simply.birthdayapp.main.addEvent.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commondomain.model.Birthday
import com.simply.birthdayapp.commonpresentation.components.actionbar.auth.TopAppBarWithBackButton
import com.simply.birthdayapp.commonpresentation.components.button.DoneButton
import com.simply.birthdayapp.commonpresentation.components.image.ProfileImage
import com.simply.birthdayapp.commonpresentation.theme.DarkPink
import com.simply.birthdayapp.commonpresentation.theme.SecondaryTextStyle
import com.simply.birthdayapp.main.addEvent.presentation.components.CustomCalendar
import com.simply.birthdayapp.main.addEvent.presentation.components.RelativesSelection
import com.simply.birthdayapp.main.navigation.BirthdayMode
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun AddEventScreen(
    birthdayMode: BirthdayMode = BirthdayMode.Add(Birthday()),
    modifier: Modifier = Modifier,
    viewModel: AddEventViewModel = getViewModel { parametersOf(birthdayMode) },
    navigateToMain: () -> Unit = {},
    navigateToDetails: () -> Unit
) {
    val name = viewModel.name.collectAsState()
    val relationship = viewModel.relationship.collectAsState()
    val familyRelations = viewModel.familyRelation.collectAsState()
    val selectedDay = viewModel.selectedDay.collectAsState()
    val selectedMonth = viewModel.selectedMonth.collectAsState()
    val selectedYear = viewModel.selectedYear.collectAsState()
    val isAddRelation = viewModel.isAddRelation.collectAsState()
    val addNewRelation = viewModel.newRelation.collectAsState()
    val scrollState = rememberScrollState()
    val uiState by viewModel.addEventUiState.collectAsState()
    val imageSource by viewModel.imageSource.collectAsState()
    val showDialog by viewModel.showDialog.collectAsState()
    val context = LocalContext.current
    when (uiState) {
        is AddEventUiState.Loading -> {
            Dialog(
                onDismissRequest = {}
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(56.dp),
                    color = DarkPink
                )
            }
        }

        is AddEventUiState.Success -> {
            Toast.makeText(
                LocalContext.current,
                (uiState as AddEventUiState.Success).message,
                Toast.LENGTH_SHORT
            ).show()
            viewModel.resetState()
            navigateToMain.invoke()
        }

        is AddEventUiState.Error -> {
            Toast.makeText(
                LocalContext.current,
                (uiState as AddEventUiState.Error).message,
                Toast.LENGTH_SHORT
            ).show()
            viewModel.resetState()
        }

        else -> {}
    }
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        TopAppBarWithBackButton(
            modifier = Modifier
                .padding(bottom = 8.dp, start = 24.dp, end = 24.dp)
                .fillMaxWidth(),
            showTopBar = true,
            showBackButton = birthdayMode is BirthdayMode.Edit,
            onBackPress = {
                navigateToDetails.invoke()
            }
        )
        if (birthdayMode is BirthdayMode.Edit) {
            Icon(
                modifier = Modifier
                    .padding(end = 28.dp)
                    .clickable {
                        viewModel.setShowDialog(true)
                    },
                painter = painterResource(R.drawable.ic_trash),
                contentDescription = null
            )
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    viewModel.setShowDialog(false)
                },
                text = { Text(text = stringResource(R.string.delete_birthday_text)) },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.deleteEvent(context)
                        navigateToMain.invoke()
                    }) {
                        Text(text = "Yes")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        viewModel.setShowDialog(false)
                    }) {
                        Text(text = "No")
                    }
                })
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .verticalScroll(state = scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileImage(imageSource = imageSource) {
                viewModel.setImageUri(it)
                viewModel.imageEncode(context)
            }
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopStart
            ) {
                Text(
                    text = stringResource(R.string.name_text),
                    modifier = Modifier
                        .width(54.dp)
                        .height(28.dp),
                    style = SecondaryTextStyle
                )
            }
            TextField(
                value = name.value,
                onValueChange = {
                    viewModel.setName(it)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(66.dp),
                textStyle = SecondaryTextStyle.copy(color = Color.Black),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = DarkPink,
                    unfocusedTextColor = DarkPink,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),


                singleLine = true
            )
            Box(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopStart
            ) {
                Text(
                    text = stringResource(R.string.relationship_text),
                    modifier = Modifier
                        .width(108.dp)
                        .height(28.dp),
                    style = SecondaryTextStyle,
                )
            }
            RelativesSelection(familyRelations.value, relationship.value) {
                viewModel.setRelationship(it)
            }
            FloatingActionButton(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 20.dp)
                    .size(30.dp),
                onClick = {
                    viewModel.setIsAddRelation(!isAddRelation.value)
                },
                containerColor = DarkPink,
                elevation = FloatingActionButtonDefaults.elevation(0.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = null,
                    tint = Color.White
                )
            }
            if (isAddRelation.value) {
                TextField(
                    value = addNewRelation.value,
                    onValueChange = {
                        viewModel.setNewRelation(it)
                    },
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(7.dp),
                    trailingIcon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_check),
                            modifier = Modifier
                                .background(color = DarkPink, shape = CircleShape)
                                .clickable {
                                    if (addNewRelation.value.isNotEmpty()) {
                                        viewModel.setFamilyRelation(addNewRelation.value)
                                    }
                                    viewModel.setIsAddRelation(!isAddRelation.value)
                                },
                            contentDescription = null,
                            tint = Color.White
                        )
                    },
                    textStyle = SecondaryTextStyle.copy(color = Color.Black),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = DarkPink,
                        unfocusedTextColor = DarkPink,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    singleLine = true
                )
            }
            CustomCalendar(
                selectedDay = selectedDay.value,
                selectedMonth = selectedMonth.value,
                selectedYear = selectedYear.value,
                onSelectedDay = { viewModel.setSelectedDay(it) },
                onSelectedMonth = { viewModel.setSelectedMonth(it) },
                onSelectedYear = { viewModel.setSelectedYear(it) })
            DoneButton(
                modifier = Modifier.padding(top = 48.dp, bottom = 100.dp),
                text = stringResource(R.string.done_button_text),
                isEnabled = name.value.isNotEmpty() && relationship.value.isNotEmpty(),
                onClick = {
                    if (birthdayMode is BirthdayMode.Edit) {
                        viewModel.updateEvent(context)
                    } else {
                        viewModel.addEvent(context)
                    }
                }
            )
        }
    }
}