package com.example.jpa.persistenceContext.objectpool.controller;

import com.example.jpa.persistenceContext.objectpool.entity.Human;
import com.example.jpa.persistenceContext.objectpool.service.ServiceA;
import com.example.jpa.persistenceContext.objectpool.service.ServiceB;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HumanController {

    private final ServiceA serviceA;
    private final ServiceB serviceB;

    /**
     * 호출 시점이 곧 트랜잭션, 즉 한 번 호출할 때 serviceA와 관련된 트랜잭션과 serviceB와 관련된 트랜잭션은
     * 동일 트랜잭션이 되며 그 트랜잭션 내의 영속성 컨텍스트 역시 동일하므로 두 서비스가 동일 객체를 반환하는 것.
     * 하지만 호출할 때마다 새로운 트랜잭션이 되므로 새로운 영속성 컨텍스트로 인해 새로운 객체가 반환되는 것.
     */
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        Human humanA = serviceA.findById(1L);
        Human humanB = serviceB.findById(1L);

        boolean value = humanA.hashCode() == humanB.hashCode();
        String result = "humanA : " +
                humanA.hashCode() + "\nhumanB : " +
                humanB.hashCode() + "\nresult : " + value;

        System.out.println(result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
