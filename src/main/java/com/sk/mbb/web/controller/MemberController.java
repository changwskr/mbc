package com.sk.mbb.web.controller;

import com.sk.fwk.common.CommonUtil;
import com.sk.mbb.business.domain.Member;
import com.sk.mbb.business.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    public void kkk() {
        CommonUtil.Char2Str('a');
        System.out.println("------------------------------------------------kkkkk----");
        return;
    }

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
        System.out.println("------------------------------------------------------");
        kkk();
        System.out.println("------------------------------------------------------");
    }

    @GetMapping("/members/new") // http://localhost:8080/members/new
    public String createForm() {
        return "members/createMemberForm";  //리턴하면 template/member/createMemberForm.html 파일을 찾아간
    }

    @PostMapping("/members/new")
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
    @GetMapping("/members")
    public String list(Model model){
        List<Member> list = memberService.findMembers();
        model.addAttribute("members",list);
        System.out.println("------------------------------------------------------");
        return "members/memberList";  // templates/members/memberList.html로 가라는 말이다.
    }


}
