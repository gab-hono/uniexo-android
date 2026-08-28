package com.unicofrance.uniexo.data.local.csv

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object CsvParser {

    private val dateFormatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss")

    fun parseLine(line: String): List<String> {
        val fields = mutableListOf<String>()
        val currentField = StringBuilder()
        var insideQuotes = false

        for (char in line) {
            when {
                char == '"' -> insideQuotes = !insideQuotes
                char == ',' && !insideQuotes -> {
                    fields.add(currentField.toString())
                    currentField.clear()
                }
                else -> currentField.append(char)
            }
        }
        fields.add(currentField.toString())

        return fields
    }

    fun parseDateToEpochMillis(dateString: String): Long {
        val localDateTime = LocalDateTime.parse(dateString, dateFormatter)
        return  localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli()
    }
}