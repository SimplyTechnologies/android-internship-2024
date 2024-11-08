package com.simply.birthdayapp.main.addEvent.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commonpresentation.theme.DarkPink
import com.simply.birthdayapp.commonpresentation.theme.SecondaryTextStyle
import com.simply.birthdayapp.commonpresentation.theme.TextFieldTextStyle
import java.util.Calendar
import java.util.Locale

@Composable
fun CustomCalendar(
    selectedDay: Int,
    selectedMonth: Int,
    selectedYear: Int,
    onSelectedDay: (Int) -> Unit,
    onSelectedMonth: (Int) -> Unit,
    onSelectedYear: (Int) -> Unit
) {
    val calendar by remember {
        mutableStateOf(
            Calendar.getInstance().apply {
                set(selectedYear, selectedMonth - 1, selectedDay)
            }
        )
    }
    var currentMonth by remember {
        mutableStateOf(
            calendar.getDisplayName(
                Calendar.MONTH,
                Calendar.LONG,
                Locale.US
            )
        )
    }
    var currentYear by remember { mutableIntStateOf(calendar.get(Calendar.YEAR)) }
    var daysInMonth by remember { mutableIntStateOf(calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) }
    var firstDayOfMonth by remember { mutableIntStateOf(calendar.get(Calendar.DAY_OF_WEEK) - 1) }

    fun updateCalendar() {
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        currentMonth = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) ?: ""
        currentYear = calendar.get(Calendar.YEAR)
        daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK) - 1
    }
    Box(
        modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(13.dp))
            .height(380.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopStart
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$currentMonth $currentYear",
                        style = SecondaryTextStyle,
                        modifier = Modifier
                    )
                    Row {
                        Icon(
                            modifier = Modifier
                                .padding(end = 31.dp)
                                .clickable {
                                    calendar.add(Calendar.MONTH, -1)
                                    updateCalendar()
                                },
                            painter = painterResource(R.drawable.ic_prev),
                            contentDescription = null,
                            tint = DarkPink
                        )
                        Icon(
                            modifier = Modifier
                                .clickable {
                                    calendar.add(Calendar.MONTH, 1)
                                    updateCalendar()
                                },
                            painter = painterResource(R.drawable.ic_next),
                            contentDescription = null,
                            tint = DarkPink
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .padding(vertical = 15.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                stringArrayResource(
                    R.array.weekdays
                ).forEach { day ->
                    Text(
                        text = day,
                        style = SecondaryTextStyle.copy(fontSize = 13.sp)
                    )
                }
            }
            var day = 1
            for (week in 0 until (daysInMonth + firstDayOfMonth) / 7 + 1) {
                Row(
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    for (dayOfWeek in 0 until 7) {
                        val isWithinCurrentMonth = week != 0 || dayOfWeek >= firstDayOfMonth
                        val dayToDisplay =
                            if (isWithinCurrentMonth && day <= daysInMonth) day else null

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    color = if (
                                        dayToDisplay == selectedDay &&
                                        selectedMonth == calendar.get(Calendar.MONTH) + 1 &&
                                        selectedYear == currentYear
                                    ) DarkPink else Color.White,
                                    shape = CircleShape
                                )
                                .clickable(onClick = {
                                    if (dayToDisplay != null) {
                                        onSelectedDay.invoke(dayToDisplay)
                                        onSelectedMonth.invoke(calendar.get(Calendar.MONTH) + 1)
                                        onSelectedYear.invoke(calendar.get(Calendar.YEAR))
                                    }
                                })
                        ) {
                            Text(
                                modifier = Modifier,
                                text = dayToDisplay?.toString() ?: "",
                                style = TextFieldTextStyle,
                                fontSize = 20.sp,
                                color = if (
                                    dayToDisplay == selectedDay &&
                                    selectedMonth == calendar.get(Calendar.MONTH) + 1 &&
                                    selectedYear == currentYear
                                ) Color.White else DarkPink,
                            )
                        }
                        if (dayToDisplay != null) day++
                    }
                }
            }
        }
    }
}
