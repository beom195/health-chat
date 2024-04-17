package com.beom195.health_chat.controller;

import com.beom195.health_chat.domain.Member;
import com.beom195.health_chat.dto.MemberDTO;
import com.beom195.health_chat.security.AuthMember;
import com.beom195.health_chat.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 멤버 회원가입 폼으로 이동
    @GetMapping("/member/join")
    public String join(){
        return "member/joinPage";
    }

    // 멤버 로그인 폼으로 이동
    @GetMapping("/auth/memberLogin")
    public String memberlogin(){
        return "member/loginPage";
    }

    // 마이 페이지로 이동
    @GetMapping("/member/myPage")
    public String getMyPage(@AuthMember Member member, Model model){

        log.info("memberName = {}", member.getMemberName());
        model.addAttribute("currentMember", member);
        return "member/myPage";
    }

    // 멤버 회원가입(기능구현 후 ajax로 바꾸기)
    @PostMapping("/memberJoin_proc")
    public String memberJoin(MemberDTO.Request memberDTO){

        log.info("memberDTO = {}", memberDTO.getMemberPassword());
        memberService.memberSave(memberDTO);
        return "redirect:/";
    }
}
