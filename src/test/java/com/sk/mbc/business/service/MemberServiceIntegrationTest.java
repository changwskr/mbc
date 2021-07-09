package com.sk.mbc.business.service;

import com.sk.mbc.business.repository.IMemberRepository;
import com.sk.mbc.business.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired IMemberRepository memberRepository;

    @Test
    void 회원가입() {

        // given
        /**
         * 1. 회원을 생성하자
         */
        Member member = new Member();
        member.setName("roian");

        System.out.println("---------------------------------1");

        // when
        Long rtv = memberService.join(member);

        System.out.println("---------------------------------2");

        // then
        Member findMember = null;
        try {
            findMember = memberService.findOne(rtv).get();
        } catch (IllegalStateException ex){
            ex.printStackTrace();
            System.out.println("-----------mmm");
        }

        System.out.println("---------------------------------3");
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    void 중복회원예외_C1(){
        //given
        Member member = new Member();
        member.setName("roian");

        Member member2 = new Member();
        member2.setName("roian");

        //when
        memberService.join(member);

        try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            Assertions.assertThat( e.getMessage() ).isEqualTo("이미 회원이 존재합니다.");
        }
    }
    @Test
    void 중복회원예외_C2(){
        //given
        Member member = new Member();
        member.setName("roian");

        Member member2 = new Member();
        member2.setName("roian");

        //when
        memberService.join(member);
        //IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> memberService.join((memeber2)));
        //Assertions.assertThat(e.getMessage()).isEqualToisEqualTo("이미 회원이 존재합니다.");


    }

    @Test
    void 회원찾기여러명() {
    }

    @Test
    void 회원찾기한명() {
    }
}