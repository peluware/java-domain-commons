package com.peluware.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JacksonTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    void testSlice() throws JsonProcessingException {
        var content = List.of("item1", "item2");
        var slice = new Slice<>(content);

        assertNotNull(slice);
        System.out.println(OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(slice));
    }

    @Test
    void testPage() throws JsonProcessingException {
        var content = List.of("item1", "item2", "item3");
        var page = new Page<>(content, new DefaultPagination(0, 3), Sort.unsorted(), 10);

        assertNotNull(page);
        System.out.println(OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(page));
    }
}