package com.example.movieapp.core.gsonAdapters


import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class DoubleDeserializer : JsonDeserializer<Double> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Double {
        //Log.d("DoubleDeserializer", "Deserializer çağrıldı")
        return try {
            if (json != null && json.isJsonPrimitive && json.asString.isEmpty()) {
             //   Log.d("DoubleDeserializer", "Boş string tespit edildi, 0L döndürülüyor")
                0.0
            } else {
                val result = json?.asDouble ?: 0.0
             //   Log.d("DoubleDeserializer", "Değer: $result")
                result
            }
        } catch (e: Exception) {
           // Log.e("DoubleDeserializer", "Hata: ${e.message}")
            0.0
        }
    }
}