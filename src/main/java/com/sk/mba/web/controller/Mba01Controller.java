package com.sk.mba.web.controller;

import com.sk.mba.business.domain.Member;
import com.sk.mba.business.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class Mba01Controller {


    private final MemberService memberService;

    @Autowired
    public Mba01Controller(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members2/new2") // http://localhost:8080/members/new
    public String createForm() {
        return "members/createMemberForm";  //리턴하면 template/member/createMemberForm.html 파일을 찾아간
    }

    @PostMapping("/members2/new2")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("------------------------------------------------------");
        System.out.println("************ name = " + member.getName());

        memberService.join(member);


        return "redirect:/";
    }

    // 회원목록
    // http://localhost:8080/members
    @GetMapping("/members2")
    public String list(Model model){
        List<Member> list = memberService.findMembers();
        model.addAttribute("members",list);
        System.out.println("------------------------------------------------------");
        return "members2/memberList";  // templates/members/memberList.html로 가라는 말이다.
    }

}
