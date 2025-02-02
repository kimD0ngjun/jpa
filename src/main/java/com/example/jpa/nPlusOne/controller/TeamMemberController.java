package com.example.jpa.nPlusOne.controller;

import com.example.jpa.nPlusOne.service.FetchJoinService;
import com.example.jpa.nPlusOne.service.TeamMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TeamMemberController {

    private final TeamMemberService teamMemberService;
    private final FetchJoinService fetchJoinService;

    @GetMapping("/npo")
    public ResponseEntity<String> getTeam() {
        List<String> list = teamMemberService.findAllMembersByTeamRepo();
//        List<String> list = fetchJoinService.findAllMembersByFetchJoin();

        return ResponseEntity.ok(list.toString());
    }

}
