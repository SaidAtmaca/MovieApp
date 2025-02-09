package com.example.movieapp.core.gsonAdapters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class LongDeserializer : JsonDeserializer<Long> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Long {
     //   Log.d("LongDeserializer", "Deserializer çağrıldı")
        return try {
            if (json != null && json.isJsonPrimitive && json.asString.isEmpty()) {
       //         Log.d("LongDeserializer", "Boş string tespit edildi, 0L döndürülüyor")
                0L
            } else {
                val result = json?.asLong ?: 0L
       //         Log.d("LongDeserializer", "Değer: $result")
                result
            }
        } catch (e: Exception) {
       //     Log.e("LongDeserializer", "Hata: ${e.message}")
            0L
        }
    }
}