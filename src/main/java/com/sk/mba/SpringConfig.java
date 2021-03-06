package com.sk.mba;

import com.sk.mba.business.repository.IMemberRepository;
import com.sk.mba.business.service.MemberService;
import com.sk.mba.business.repository.JdbcTemplateMemberRepository;
import com.sk.mba.business.repository.JdbcMemberRepository;
import com.sk.mba.business.repository.MemoryMemberRepository;
import com.sk.mba.business.repository.JpaMemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.persistence.EntityManager;



import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    // Spring JPA
    private final IMemberRepository memberRepository;

    // Spring JPA
    @Autowired
    public SpringConfig(IMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
}



class SpringConfig_SprJPA {

    // Spring JPA
    private final IMemberRepository memberRepository;

    // Spring JPA
    @Autowired
    public SpringConfig_SprJPA(IMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
}


class SpringConfig_JPA {

    private EntityManager em;

    @Autowired
    public SpringConfig_JPA(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService((IMemberRepository) memberRepository());
    }

    @Bean
    public IMemberRepository memberRepository() {

        return new JpaMemberRepository(em);
    }
}


class SpringConfig_JDBC_TEMPLATE {

    // 1> JDBC DATASOURCE TYPE
    private DataSource dataSource;


    // 1> JDBC TYPE
    // JdbcMemberRepoisotry를 만들어서 이것을 빈으로 만들어 준다.
    @Autowired
    public SpringConfig_JDBC_TEMPLATE(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService((IMemberRepository) memberRepository());
    }

    @Bean
    public IMemberRepository memberRepository() {

        return new JdbcTemplateMemberRepository(dataSource);

    }
}


class SpringConfig_JDBC {

    private DataSource dataSource;

    // JdbcMemberRepoisotry를 만들어서 이것을 빈으로 만들어 준다.
    @Autowired
    public SpringConfig_JDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService((IMemberRepository) memberRepository());
    }

    @Bean
    public IMemberRepository memberRepository() {
        return new JdbcMemberRepository(dataSource);
    }
}


class SpringConfig_MEM {

    @Bean
    public MemberService memberService() {
        return new MemberService((IMemberRepository) memberRepository());
    }

    @Bean
    public IMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
