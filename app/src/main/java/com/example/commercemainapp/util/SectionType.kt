package com.example.commercemainapp.util

enum class SectionType {
    HORIZONTAL, VERTICAL, GRID;

    companion object {
        fun fromValueByString(value: String): SectionType {
            return entries.find { it.name.lowercase() == value } ?: HORIZONTAL
        }
    }
}