package com.sk.mba.business.repository;

import com.sk.mba.business.domain.Member;
import com.sk.mba.business.repository.IMemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISpringDataMemberRepository extends JpaRepository<Member,Long>, IMemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
