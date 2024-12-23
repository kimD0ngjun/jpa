package com.example.persistenceContext.transaction.controller;

import com.example.persistenceContext.transaction.entity.Human;
import com.example.persistenceContext.transaction.service.ServiceA;
import com.example.persistenceContext.transaction.service.ServiceB;
import com.example.persistenceContext.transaction.service.TransactionService;
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
    private final TransactionService transactionService;

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

    @GetMapping("/transaction")
    public ResponseEntity<String> transaction() {
        String with = transactionService.updateWithTransaction(2L);
        String without = transactionService.updateWithoutTransaction(2L);
        String check = transactionService.check();

        String result = "\n* 트랜잭션 부여 :\n" +
                with + "\n\n* 트랜잭션 미부여 :\n" +
                without + "\n" + check;

        System.out.println(result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
