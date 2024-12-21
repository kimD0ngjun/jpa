package com.example.persistenceContext.aop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller // 뷰 반환
@RestController // RestAPI 응답 반환
@RequestMapping("/aop")
@RequiredArgsConstructor
public class AopController {

    private final AopService aopService;

//    ResponseEntity<?>
    @GetMapping("/find")
    public void find() {
        aopService.find();
//        return ResponseEntity.ok().build();
    }

    @GetMapping("/save")
    public void save() {
        aopService.save();
//        return ResponseEntity.ok().build();
    }
}
