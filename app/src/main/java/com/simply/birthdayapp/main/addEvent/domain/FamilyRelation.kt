package com.simply.birthdayapp.main.addEvent.domain

enum class FamilyRelation(val displayName: String) {
    BEST_FRIEND("Best Friend"),
    MOTHER("Mother"),
    FATHER("Father"),
    GRANDMOTHER("Grandmother"),
    GRANDFATHER("Grandfather"),
    BROTHER("Brother"),
    SISTER("Sister"),
    UNCLE("Uncle");

    companion object {
        fun getDisplayNames(): MutableList<String> = entries.map { it.displayName }.toMutableList()
    }
}