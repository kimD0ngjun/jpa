package com.example.jpa.queryDsl.dynamic;

import com.querydsl.core.Tuple;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class DynamicDTOFactory {

    // DTO 캐시
    private final Map<String, DynamicDTO> dtoCache = new HashMap<>();

    public DynamicDTO getDtoFromCacheOrCreate(Tuple tuple) {
        // 튜플로부터 캐시 키 얻음
        String cacheKey = createCacheKey(tuple);

        // 캐시 있을 경우
        if (dtoCache.containsKey(cacheKey)) {
            return dtoCache.get(cacheKey);
        }

        // 캐시 없을 경우
        DynamicDTO dto = createDto(tuple);
        dtoCache.put(cacheKey, dto);

        return dto;
    }

    // 동적 DTO 캐시 키 생성
    private String createCacheKey(Tuple tuple) {
        StringBuilder keyBuilder = new StringBuilder();

        // 각 Tuple 요소의 타입을 기반으로 키를 생성
        for (int i = 0; i < tuple.size(); i++) {
            Object value = tuple.get(i, Object.class);
            keyBuilder.append(value != null ? value.getClass().getName() : "null").append(";");
        }

        return keyBuilder.toString();
    }

    // 동적 DTO 생성
    public DynamicDTO createDto(Tuple tuple) {
        DynamicDTO dto = new DynamicDTO();

        // 튜플 순회하며 필드명, 타입 체킹
        for (int i = 0; i < tuple.size(); i++) {
            String fieldName = "field" + i;
            Object value = tuple.get(i, Object.class);

            dto.setDTOField(fieldName, value);
        }

        return dto;
    }

}
