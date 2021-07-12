package com.sk.mbc.business.repository;

import com.sk.mbc.business.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISpringDataMemberRepository extends JpaRepository<Member,Long>, IMemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
