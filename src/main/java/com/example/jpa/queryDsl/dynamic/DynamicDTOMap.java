package com.example.jpa.queryDsl.dynamic;

import lombok.Getter;

import java.util.*;

@Getter
public class DynamicDTOMap {
    private final Map<String, Object> fieldMapElement;

    public DynamicDTOMap() {
        this.fieldMapElement = new LinkedHashMap<>();
    }

    public void setDTOField(String fieldName, Object value) {
        fieldMapElement.put(fieldName, value);
    }
}

