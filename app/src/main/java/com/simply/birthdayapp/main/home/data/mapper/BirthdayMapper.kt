package com.simply.birthdayapp.main.home.data.mapper

import com.simply.GetBirthdaysQuery
import com.simply.birthdayapp.commondomain.model.Birthday

object BirthdayMapper {

    // I don't use this now function, but it might be useful in the future.
    fun mapToDomain(apolloBirthday: GetBirthdaysQuery.Birthday): Birthday {
        return Birthday(
            id = apolloBirthday.id,
            name = apolloBirthday.name,
            date = apolloBirthday.date.toString(),
            upcomingAge = apolloBirthday.upcomingAge,
            upcomingBirthday = apolloBirthday.upcomingBirthday.toString(),
            relation = apolloBirthday.relation,
            image = apolloBirthday.image,
            message = apolloBirthday.message,
            userId = apolloBirthday.userId,
            createdAt = apolloBirthday.createdAt.toString(),
            updatedAt = apolloBirthday.updatedAt.toString()
        )
    }

    // Function to map a list of Apollo Birthdays to a list of domain Birthday types
    fun mapToDomainList(apolloBirthdays: List<GetBirthdaysQuery.Birthday>): List<Birthday> {
        return apolloBirthdays.map { mapToDomain(it) }
    }
}
