package com.example.composer.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GrantedAuthorityDeserializer extends JsonDeserializer<GrantedAuthority> {
    @Override
    public GrantedAuthority deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        if (jsonParser.currentToken() == JsonToken.VALUE_STRING) {
            // Если это строка, обрабатываем как SimpleGrantedAuthority
            String authority = jsonParser.getValueAsString();
            return new SimpleGrantedAuthority(authority);
        } else if (jsonParser.currentToken() == JsonToken.START_OBJECT) {
            // Если это объект, обрабатываем как OAuth2UserAuthority
            Map<String, Object> attributes = jsonParser.readValueAs(HashMap.class);
            return new OAuth2UserAuthority(attributes);
        }
        throw new IllegalArgumentException("Invalid format for GrantedAuthority");
    }
}
