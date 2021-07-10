package com.sk.mbc;

import com.sk.mbc.business.repository.*;
import com.sk.mbc.business.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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


class SpringConfig_JPA {

    // 2> JPA TYPE
    private EntityManager em;

    // 2> JPA TYPE
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

        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
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

        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);

    }
}

class SpringConfig_JDBC {

    // 1> JDBC DATASOURCE TYPE
    private DataSource dataSource;


    // 1> JDBC TYPE
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

        //return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);

    }
}


class SpringConfig_JDBC_OLD {

    @Bean
    public MemberService memberService() {
        return new MemberService((IMemberRepository) memberRepository());
    }

    @Bean
    public IMemberRepository memberRepository() {

        return new MemoryMemberRepository();
    }
}

