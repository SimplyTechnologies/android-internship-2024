package com.simply.birthdayapp.main.home.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import com.simply.birthdayapp.commondomain.model.Birthday
import com.simply.birthdayapp.core.ErrorMessages
import com.simply.birthdayapp.core.ZodiacSign
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class BirthdayDetailsViewModel(
    birthday: Birthday,
    private val context: Context,
) : ViewModel() {
    private val _birthday = MutableStateFlow(birthday)
    val birthday = _birthday.asStateFlow()

    fun getZodiacSign(): String {
        val inputFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
        val date = inputFormat.parse(_birthday.value.date)
        val calendar = Calendar.getInstance()
        calendar.time = date
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1

        return when (month) {
            1 -> if (day <= 20) getZodiacSignName(ZodiacSign.CAPRICORN) else getZodiacSignName(
                ZodiacSign.AQUARIUS
            )

            2 -> if (day <= 20) getZodiacSignName(ZodiacSign.AQUARIUS) else getZodiacSignName(
                ZodiacSign.PISCES
            )

            3 -> if (day <= 20) getZodiacSignName(ZodiacSign.PISCES) else getZodiacSignName(
                ZodiacSign.ARIES
            )

            4 -> if (day <= 20) getZodiacSignName(ZodiacSign.ARIES) else getZodiacSignName(
                ZodiacSign.TAURUS
            )

            5 -> if (day <= 20) getZodiacSignName(ZodiacSign.TAURUS) else getZodiacSignName(
                ZodiacSign.GEMINI
            )

            6 -> if (day <= 21) getZodiacSignName(ZodiacSign.GEMINI) else getZodiacSignName(
                ZodiacSign.CANCER
            )

            7 -> if (day <= 22) getZodiacSignName(ZodiacSign.CANCER) else getZodiacSignName(
                ZodiacSign.LEO
            )

            8 -> if (day <= 23) getZodiacSignName(ZodiacSign.LEO) else getZodiacSignName(ZodiacSign.VIRGO)
            9 -> if (day <= 23) getZodiacSignName(ZodiacSign.VIRGO) else getZodiacSignName(
                ZodiacSign.LIBRA
            )

            10 -> if (day <= 23) getZodiacSignName(ZodiacSign.LIBRA) else getZodiacSignName(
                ZodiacSign.SCORPIO
            )

            11 -> if (day <= 22) getZodiacSignName(ZodiacSign.SCORPIO) else getZodiacSignName(
                ZodiacSign.SAGITTARIUS
            )

            12 -> if (day <= 21) getZodiacSignName(ZodiacSign.SAGITTARIUS) else getZodiacSignName(
                ZodiacSign.CAPRICORN
            )

            else -> throw IllegalArgumentException(ErrorMessages.INVALID_DATE)
        }
    }

    fun getZodiacSignName(zodiacSign: ZodiacSign): String {
        return context.getString(zodiacSign.displayNameResId)
    }
}