package com.sk.mbc.repository;

import com.sk.mbc.domain.Member;

import java.util.List;
import java.util.Optional;


// 1) 제일먼저 인터페이스를 저장한다.
// 저장하고 찾고 하는 과정을 하면된다.
public interface IMemberRepository {
    Member save(Member member); // 회원저장
    Optional<Member> findById(Long id); //findById에서 id값에 해당하는 값이 없을때 null을 반환하는 것이 아닌 Optional한것을 반환한다.
    Optional<Member> findByName(String name);
    List<Member> findAll();
    void clear();
    Long remove(String name);
}
