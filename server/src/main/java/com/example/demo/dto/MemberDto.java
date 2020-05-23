package com.example.demo.dto;

import com.example.demo.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class MemberDto {
    private String name;

    public static MemberDto of(Member member){
        return new MemberDto(
                member.getName()
        );
    }

    public static List<MemberDto> ofList(List<Member> members){
        List<MemberDto> memberDtos = new ArrayList<MemberDto>();
        for (Member member: members) {
            memberDtos.add( MemberDto.of(member) );
        }
        return memberDtos;
    }
}
