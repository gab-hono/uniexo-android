package com.unicofrance.uniexo.data.local.csv

import com.unicofrance.uniexo.data.local.database.entities.Container
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object CsvParser {

    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

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

    fun parseContainer(line: String): Container {
        val fields = parseLine(line)

        return Container(
            id = fields[0],
            longitude = fields[1].toDouble(),
            latitude = fields[2].toDouble(),
            label = fields [3],
            producingPlaceLabel = fields[4],
            description = fields[5],
            streamLabel = fields[6],
            streamColor = fields[7],
            iconUrl = fields[8],
            creationDatetime = parseDateToEpochMillis(fields[9])
        )
    }

    fun parseContainers(csvContent: String): List<Container> {
        val lines = csvContent.lines().filter { it.isNotBlank() }
        val dataLines = lines.drop(1)

        return dataLines.map { line -> parseContainer(line)}
    }
}