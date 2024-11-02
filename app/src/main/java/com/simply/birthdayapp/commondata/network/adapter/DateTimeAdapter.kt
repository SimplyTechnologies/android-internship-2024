package com.simply.birthdayapp.commondata.network.adapter

import com.apollographql.apollo.api.Adapter
import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.json.JsonReader
import com.apollographql.apollo.api.json.JsonWriter
import com.apollographql.apollo.exception.JsonDataException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


object DateAdapter : Adapter<Date> {
    private val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()) // Adjust format as needed

    override fun fromJson(reader: JsonReader, customScalarAdapters: CustomScalarAdapters): Date {
        val dateString = reader.nextString()
        return dateString?.let { formatter.parse(it) }
            ?: throw JsonDataException("Invalid date format")
    }

    override fun toJson(
        writer: JsonWriter,
        customScalarAdapters: CustomScalarAdapters,
        value: Date
    ) {
        writer.value(formatter.format(value))

    }


}
