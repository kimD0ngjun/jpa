package com.example.jpa.persistenceContext.idea.cache;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ImmutableEntityCache {

    private final Map<Long, Object> cache = new ConcurrentHashMap<>();

    public <T> T get(Long id, Class<T> type) {
        return type.cast(cache.get(id));
    }

    public void put(Long id, Object value) {
        cache.put(id, value);
    }

    public boolean contains(Long id) {
        return cache.containsKey(id);
    }
}
