package com.sk.com;

import com.sk.mbc.business.repository.IMemberRepository;
import com.sk.mbc.business.repository.JdbcMemberRepository;
import com.sk.mbc.business.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    // JdbcMemberRepoisotry를 만들어서 이것을 빈으로 만들어 준다.
    @Autowired
    public SpringConfig(DataSource dataSource) {
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
