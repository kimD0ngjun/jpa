package com.example.jpa.queryDsl.dynamic;

import lombok.Getter;

import java.util.*;

@Getter
public class DynamicDTOMap {
    private final Map<String, Object> fieldMap;

    public DynamicDTOMap() {
        this.fieldMap = new LinkedHashMap<>();
    }

    public void setDTOField(String fieldName, Object value) {
        fieldMap.put(fieldName, value);
    }
}

