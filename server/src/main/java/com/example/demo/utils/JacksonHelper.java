package com.example.demo.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class JacksonHelper {
    @Getter
    private ObjectMapper defaultObjectMapper;

    @PostConstruct
    public void postConstruct() {
        defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
