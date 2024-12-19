package com.example.persistenceContext.objectpool.dto;

import com.example.nPlusOne.entity.multiOneToN.B;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BDto {

    private final String name;

    public BDto(B b) {
        this.name = b.getName();
    }
}
