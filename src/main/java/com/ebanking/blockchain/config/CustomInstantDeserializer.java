package com.ebanking.blockchain.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Instant;

public class CustomInstantDeserializer extends StdDeserializer<Instant> {
    public CustomInstantDeserializer() {
        super(Instant.class);
    }

    @Override
    public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        // Check for null values before accessing the fields
        if (node.has("epochSecond") && node.has("nano")) {
            long epochSecond = node.get("epochSecond").asLong();
            int nano = node.get("nano").asInt();
            return Instant.ofEpochSecond(epochSecond, nano);
        } else {
            return null; // Handle null case appropriately
        }
    }
}
