package com.nhanbt.demo_kotlin_native.features.note.domain.entities

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

data class Note(
    var id: String = "",
    var title: String = "",
    var description: String = "",
    var categories: List<String> = listOf(),
    var deadline: Timestamp = Timestamp.now(),
) {
    fun deadlineToTimeString(): String {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale("en"))
            val date: Date = deadline.toDate()
            sdf.format(date)
        } catch (e: Exception) {
            e.toString()
        }
    }

    fun toMap(): HashMap<String, Any?> {
        return hashMapOf(
            "title" to title,
            "description" to description,
            "categories" to categories,
            "deadline" to deadline,
        )
    }
}
