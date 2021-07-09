package com.sk.mba.business.service;

import com.sk.mba.business.domain.Member;
import com.sk.mba.transfer.IDTO;
import com.sk.mba.transfer.Mba01CDTO;

import java.util.List;
import java.util.Optional;

public interface IMba01Service {
    public IDTO execute(Mba01CDTO mba01CDTO);
    public Long join(Member member);
    public List<Member> findMembers();
    public Optional<Member> findOne(Long id);

}

