package com.sk.mbc.repository;

import com.sk.mbc.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

public class MemoryMemberRepositoryTest {
    IMemberRepository repository = new MemoryMemberRepository();

    /*
    * 모든 메소드가 실행후 호출되는 콜백메소드를 하나 작성한다.
     */
    @AfterEach
    public void afterEach() {
        clearStore();
    }

    /*
    * 만약 save() 메소스를 하고 싶다.
    * save() 메소드를 하나만든다.
    * @Test 입력해서
     */

    @Test
    public void save(){
        Member member = new Member();
        member.setName("roian");
        // control + shift + enter
        repository.save(member);
        repository.findByName("roian");
        Member result = repository.findById(member.getId()).get();
        // 1
        System.out.println(result.getId() + " " + result.getName());
        System.out.println("result = " + (result == member));

        // 2 이 부분은 org.junit.jupiter.api.Assertions 부분이다.
        //Assertions.assertEquals(member, result);
        //Assertions.assertEquals(member, null); // 이 부분은 에러가 발생할 것이다.

        //3. 이 부분은 org.assert.core.api.Assertions 이다.
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {

        Member member1 = new Member();
        member1.setName("roian");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring");
        repository.save(member2);

        Member rtv1 = repository.findByName(member1.getName()).get();
        Member rtv2 = repository.findByName(member2.getName()).get();

        System.out.println(rtv1.getName() + " " + rtv2.getName());

        Assertions.assertThat(rtv1).isEqualTo(member1);

    }

    @Test
    public void findByAll() {
        Member member1 = new Member();
        member1.setName("roian");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring");
        repository.save(member2);

        ArrayList <Member> al = (ArrayList) repository.findAll();

        Iterator iter = al.iterator(); //Iterator 선언
        while(iter.hasNext()){//다음값이 있는지 체크
            Member rtv = (Member) iter.next();
            System.out.println(rtv.getName()); //값 출력
        }

        Assertions.assertThat(al.size()).isEqualTo(2);

    }

    public void clearStore() {
        repository.clear();
    }

}

