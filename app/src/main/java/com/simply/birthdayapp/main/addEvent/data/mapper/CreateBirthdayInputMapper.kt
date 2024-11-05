package com.simply.birthdayapp.main.addEvent.data.mapper

import com.simply.birthdayapp.main.addEvent.data.model.CreateBirthdayInputData
import com.simply.birthdayapp.main.addEvent.domain.model.CreateBirthdayInputDomain

fun CreateBirthdayInputData.toCreateBirthdayInputDomain() = CreateBirthdayInputDomain(
    date = this.date,
    image = this.image,
    message = this.message,
    name = this.name,
    relation = this.relation
)

fun CreateBirthdayInputDomain.toCreateBirthdayInputData() = CreateBirthdayInputData(
    date = this.date,
    image = this.image,
    message = this.message,
    name = this.name,
    relation = this.relation
)