package com.sk.mba.business.service;

import com.sk.mba.business.domain.Member;

import java.util.List;
import java.util.Optional;

public interface IMemberService {
    public Member execute(char txtype, String name);
    public Long join(Member member);
    public List<Member> findMembers();
    public Optional<Member> findOne(Long id);
}
