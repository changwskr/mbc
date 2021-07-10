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

    // 1> JDBC DATASOURCE TYPE
    //private DataSource dataSource;

    // 2> JPA TYPE
    private EntityManager em;

    // 1> JDBC TYPE
    // JdbcMemberRepoisotry를 만들어서 이것을 빈으로 만들어 준다.
    //@Autowired
    //public SpringConfig(DataSource dataSource) {
    //    this.dataSource = dataSource;
    //}

    // 2> JPA TYPE
    @Autowired
    public SpringConfig(EntityManager em) {
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
