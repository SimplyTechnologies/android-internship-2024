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
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import com.simply.birthdayapp.commonpresentation.components.actionbar.auth.TopAppBarWithBackButton
import com.simply.birthdayapp.commonpresentation.components.image.ProfileImage
import com.simply.birthdayapp.commonpresentation.theme.DarkPink
import com.simply.birthdayapp.commonpresentation.theme.SecondaryTextStyle
import com.simply.birthdayapp.main.addEvent.presentation.components.CustomCalendar
import com.simply.birthdayapp.main.addEvent.presentation.components.RelativesSelection
import org.koin.androidx.compose.koinViewModel


@Composable
fun AddEventScreen(
    modifier: Modifier = Modifier,
    viewModel: AddEventViewModel = koinViewModel(),
    navigateToMain: () -> Unit = {}
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
                stringResource(R.string.success),
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
    Box(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 24.dp)
                .verticalScroll(state = scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBarWithBackButton(
                modifier = Modifier.fillMaxWidth(),
                showTopBar = true,
                showBackButton = false
            )

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
                colors = TextFieldDefaults.textFieldColors(
                    textColor = DarkPink,
                    backgroundColor = Color.White,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
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
                backgroundColor = DarkPink,
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
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = DarkPink,
                        backgroundColor = Color.White,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }
            CustomCalendar(
                selectedDay = selectedDay.value,
                selectedMonth = selectedMonth.value,
                selectedYear = selectedYear.value,
                onSelectedDay = { viewModel.setSelectedDay(it) },
                onSelectedMonth = { viewModel.setSelectedMonth(it) },
                onSelectedYear = { viewModel.setSelectedYear(it) })
            Button(
                modifier = Modifier
                    .padding(30.dp)
                    .background(color = Color.Transparent),
                shape = RoundedCornerShape(16.dp),
                onClick = {
                    viewModel.addEvent(context)
                },
                colors = androidx.compose.material.ButtonDefaults.buttonColors(
                    backgroundColor = DarkPink
                )
            ) {
                Text(
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 16.dp),
                    text = stringResource(R.string.done_button_text),
                    style = SecondaryTextStyle.copy(color = Color.White)
                )
            }
        }
    }
}







