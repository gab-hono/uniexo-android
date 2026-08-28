package com.unicofrance.uniexo.data.local.csv

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

class CsvParserTest {

    @Test
    fun `parseLine splits simple comma separated fields`() {
        val result = CsvParser.parseLine("a,b,c")
        assertEquals(listOf("a", "b", "c"), result)
    }

    @Test
    fun `parseLine keeps a comma inside quotes as part of the same field`() {
        val result = CsvParser.parseLine("""a,"b, with coma",c""")
        assertEquals(listOf("a", "b, with coma", "c"), result)
    }

    @Test
    fun `parseDateToEpochMillis round-trips back to the original date`() {
        val millis = CsvParser.parseDateToEpochMillis("2025-05-05 15:38:10")

        val recovered = Instant.ofEpochMilli(millis)
            .atZone(ZoneOffset.UTC)
            .toLocalDateTime()

        assertEquals(LocalDateTime.of(2025, 5, 5, 15, 38, 10), recovered)
    }

    @Test
    fun `parseContainer maps a real CSV line to a Container correctly`() {
        val line = """0019a520-33e5-4b87-a8b0-f035eb71fff9,3.22270773135022,43.3643198066834,BÉZ-528-OM,BÉZ-528,"BÉZ-528 74 Boulevard Colette Besson BEZIERS 34500, 4000L Enterrée",Déchets ménagers,#767678,https://static.prod.unicofrance.com/icons/containers/contenant%20-%2011.svg,2025-05-05 15:38:10"""

        val container = CsvParser.parseContainer(line)

        assertEquals("0019a520-33e5-4b87-a8b0-f035eb71fff9", container.id)
        assertEquals(3.22270773135022, container.longitude, 0.0)
        assertEquals(43.3643198066834, container.latitude, 0.0)
        assertEquals("BÉZ-528-OM", container.label)
        assertEquals("BÉZ-528", container.producingPlaceLabel)
        assertEquals(
            "BÉZ-528 74 Boulevard Colette Besson BEZIERS 34500, 4000L Enterrée",
            container.description
        )
        assertEquals("Déchets ménagers", container.streamLabel)
        assertEquals("#767678", container.streamColor)
    }

    @Test
    fun `parseContainers skips header row and blank lines`() {
        val csvContent = """
            id,longitude,latitude,label,producing_place_label,description,stream_label,stream_color,icon_url,creation_datetime
            0019a520-33e5-4b87-a8b0-f035eb71fff9,3.2227,43.3643,BÉZ-528-OM,BÉZ-528,"desc simple",Déchets ménagers,#767678,https://icon.svg,2025-05-05 15:38:10
            
        """.trimIndent()

        val containers = CsvParser.parseContainers(csvContent)

        assertEquals(1, containers.size)
        assertEquals("0019a520-33e5-4b87-a8b0-f035eb71fff9", containers[0].id)
    }
}