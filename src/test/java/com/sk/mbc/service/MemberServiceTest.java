package com.sk.mbc.service;

import com.sk.mbc.domain.Member;
import com.sk.mbc.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemoryMemberRepository memberRepository = new MemoryMemberRepository()
    MemberService memberService;


    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService    = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {
        clearStore();
    }

    public void clearStore() {
        memberRepository.clear();
    }


    /**
     * 테스트 준수 원칙
     */

    void principle() {
        // given

        // when

        // then
    }

    @Test
    void 회원가입() {

        // given
        /**
         * 1. 회원을 생성하자
         */
        Member member = new Member();
        member.setName("roian");

        // when
        Long rtv = memberService.join(member);

        // then
        Member findMember = memberService.findOne(rtv).get();
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