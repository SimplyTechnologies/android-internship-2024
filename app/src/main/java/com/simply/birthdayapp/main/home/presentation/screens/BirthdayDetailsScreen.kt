package com.simply.birthdayapp.main.home.presentation.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commondomain.model.Birthday
import com.simply.birthdayapp.commonpresentation.components.actionbar.auth.TopAppBarWithBackButton
import com.simply.birthdayapp.commonpresentation.components.image.NetworkImage
import com.simply.birthdayapp.commonpresentation.theme.DarkGray
import com.simply.birthdayapp.commonpresentation.theme.DarkPink
import com.simply.birthdayapp.commonpresentation.theme.LightPinkBackground
import com.simply.birthdayapp.commonpresentation.theme.MiddlePink
import com.simply.birthdayapp.commonpresentation.theme.PrimaryTextStyle
import com.simply.birthdayapp.main.home.presentation.BirthdayDetailsViewModel
import com.simply.birthdayapp.main.home.presentation.components.formatDate
import com.simply.birthdayapp.main.navigation.BirthdayMode
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parameterSetOf

@Composable
fun BirthdayDetailsScreen(
    birthday: Birthday,
    viewModel: BirthdayDetailsViewModel = koinViewModel() {
        parameterSetOf(birthday)
    },
    navigateToHomeScreen: () -> Unit,
    navigateToEditScreen: (BirthdayMode) -> Unit
) {
    var isDialogOpen by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val birthday by viewModel.birthday.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TopAppBarWithBackButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
        )
        { navigateToHomeScreen() }


        Box(
            Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd
        ) {
            IconButton(
                onClick = {
                    navigateToEditScreen(BirthdayMode.Edit(birthday))
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = null,
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NetworkImage(
                url = birthday.image,
                modifier = Modifier.size(100.dp),
                border = BorderStroke(1.dp, DarkGray)
            )

            Text(
                text = birthday.name,
                style = PrimaryTextStyle,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 18.dp, start = 36.dp, end = 36.dp)
                    .fillMaxWidth(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )

            Text(
                text = formatDate(birthday.date),
                style = PrimaryTextStyle,
                modifier = Modifier.padding(top = 18.dp)
            )

            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.birthday_relationship),
                    style = PrimaryTextStyle,
                )
                Surface(shape = RoundedCornerShape(8.dp)) {
                    Text(
                        text = birthday.relation,
                        style = PrimaryTextStyle,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }

            Text(
                text = buildAnnotatedString {
                    append(stringResource(R.string.zodiac_sign))
                    withStyle(style = SpanStyle(color = DarkPink)) {
                        this.append(viewModel.getZodiacSign())
                    }
                },
                style = PrimaryTextStyle,
                modifier = Modifier.padding(top = 8.dp)
            )

            Button(
                onClick = { isDialogOpen = true },
                colors = ButtonDefaults.buttonColors(MiddlePink),
                modifier = Modifier.padding(top = 224.dp)
            ) {
                Text(
                    text = stringResource(R.string.gen_message),
                    color = DarkPink,
                )
            }
        }
    }

    if (isDialogOpen) {
        MessageToSendToJubilee(
            onDismiss = { isDialogOpen = false },
            onSend = { message -> shareMessage(message = message, context = context) })
    }
}

@Composable
fun MessageToSendToJubilee(
    onDismiss: () -> Unit, onSend: (String) -> Unit
) {
    val message = stringResource(R.string.generated_message)
    var messageText by remember { mutableStateOf(message) }

    Dialog(onDismissRequest = { onDismiss() }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.End,
            ) {
                TextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = LightPinkBackground,
                        disabledIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Black
                    ),
                    shape = RoundedCornerShape(8.dp)
                )

                Button(
                    modifier = Modifier.padding(top = 8.dp),
                    onClick = {
                        onSend(messageText)
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(LightPinkBackground)
                ) {
                    Text(
                        text = stringResource(R.string.send_button),
                        color = DarkPink
                    )
                }
            }
        }
    }
}

fun shareMessage(message: String, context: Context) {
    val share = R.string.send_message
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, message)
        type = "text/plain"
    }
    context.startActivity(Intent.createChooser(shareIntent, "$share"))
}