package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository mem;

    @GetMapping("/signup")
    public String newMemberForm() {
        return "members/new";
    }

    @PostMapping("/join")
    public String email2MembberForm(MemberForm mform) {
        System.out.println(mform.toString());

        Member member = new Member();
        member.setEmail(mform.getEmail());
        member.setPassword(mform.getPassword());

        mem.save(member);

        System.out.println(member);
        return "";
    }

}
