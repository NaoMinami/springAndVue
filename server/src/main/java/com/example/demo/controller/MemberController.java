package com.example.demo.controller;

import com.example.demo.dto.MemberDto;
import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app")
public class MemberController {
    private final MemberService memberService;

    @CrossOrigin
    @GetMapping("/members")
    public List<MemberDto> findAll() {
        return MemberDto.ofList( memberService.findAll() );
    }

    @GetMapping("/test")
    public List<Member> findTest() {
        return memberService.findAll();
    }
}
