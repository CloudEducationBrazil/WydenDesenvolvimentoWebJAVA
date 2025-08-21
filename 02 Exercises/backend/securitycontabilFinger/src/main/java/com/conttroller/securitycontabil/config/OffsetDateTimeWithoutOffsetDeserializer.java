package com.conttroller.securitycontabil.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;

public class OffsetDateTimeWithoutOffsetDeserializer extends JsonDeserializer<OffsetDateTime> {

    @Override
    public OffsetDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText();

        try {
            // Tenta com Offset normal
            return OffsetDateTime.parse(value);
        } catch (DateTimeParseException e) {
            // Se falhar, tenta LocalDateTime e aplica offset manual
            LocalDateTime localDateTime = LocalDateTime.parse(value);
            return localDateTime.atOffset(ZoneOffset.ofHours(-3)); // ajuste conforme necessário
        }
    }
}