package com.example.jpa.persistenceContext.objectpool.dto;

import com.example.jpa.nPlusOne.entity.multiOneToN.A;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ADto {

    private final String name;

    public ADto(A a) {
        this.name = a.getName();
    }
}
