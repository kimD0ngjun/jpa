package com.example.jpa.queryDsl.dynamic;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class DynamicDTO {
    private final Map<String, Object> fieldMap;

    public DynamicDTO() {
        this.fieldMap = new HashMap<>();
    }

    public void setDTOField(String fieldName, Object value) {
        fieldMap.put(fieldName, value);
    }

    public Object getDTOField(String fieldName) {
        return fieldMap.get(fieldName);
    }

    @Override
    public String toString() {
        return "DynamicDto {" +
                "fieldMap=" + fieldMap +
                '}';
    }
}

