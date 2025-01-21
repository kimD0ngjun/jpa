package com.example.jpa.queryDsl.repository.self;

import com.example.jpa.queryDsl.entity.Self;

import java.util.List;

public interface CustomSelfRepository {
    List<Self> selfJoin();
}
