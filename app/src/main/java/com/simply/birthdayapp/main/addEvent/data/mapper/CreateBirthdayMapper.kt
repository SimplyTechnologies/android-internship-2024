package com.simply.birthdayapp.main.addEvent.data.mapper

import com.simply.birthdayapp.commondomain.model.Birthday
import com.simply.birthdayapp.main.addEvent.data.model.BirthdayDataModel


fun Birthday.toBirthdayDataModel() = BirthdayDataModel(
    createdAt = this.createdAt,
    date = this.date,
    id = this.id,
    image = this.image,
    message = this.message,
    name = this.name,
    relation = this.relation,
    upcomingAge = this.upcomingAge,
    upcomingBirthday = this.upcomingBirthday,
    updatedAt = this.updatedAt,
    userId = this.userId
)

fun BirthdayDataModel.toBirthday() = Birthday(
    createdAt = this.createdAt,
    date = this.date,
    id = this.id,
    image = this.image,
    message = this.message,
    name = this.name,
    relation = this.relation,
    upcomingAge = this.upcomingAge,
    upcomingBirthday = this.upcomingBirthday,
    updatedAt = this.updatedAt,
    userId = this.userId
)

