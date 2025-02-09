package com.example.common.gsonAdapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class  LongTypeAdapter extends TypeAdapter<Long> {

    @Override
    public void write(JsonWriter out, Long value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        out.value(value);
    }

    @Override
    public Long read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return 0L;
        }
        try {
            String value = in.nextString();
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return 0L; // Default value if parsing fails
        }
    }
}

