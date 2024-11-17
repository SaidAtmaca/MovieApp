package com.example.movieapp.core.gsonAdapters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class IntegerDeserializer : JsonDeserializer<Int> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Int {
       // Log.d("IntegerDeserializer", "Deserializer çağrıldı")
        return try {
            if (json != null && json.isJsonPrimitive && json.asString.isEmpty()) {
       //         Log.d("IntegerDeserializer", "Boş string tespit edildi, 0L döndürülüyor")
                0
            } else {
                val result = json?.asInt ?: 0
       //         Log.d("IntegerDeserializer", "Değer: $result")
                result
            }
        } catch (e: Exception) {
     //       Log.e("IntegerDeserializer", "Hata: ${e.message}")
            0
        }
    }
}