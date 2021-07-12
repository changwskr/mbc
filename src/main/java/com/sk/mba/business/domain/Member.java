package com.sk.mba.business.domain;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  //시스템에 저장하기 위해 사용하는 키값

    @Column(name = "name") // 데이타베이스의 컬럼명이 name과 매핑된다.
    private String name; //이름은 고객이 직접 화면에서 올려준다.

    @Column(name = "juso") // 데이타베이스의 컬럼명이 juso와 매핑된다.
    private String juso;


    public String getJuso() {
        return juso;
    }
    public void setJuso(String juso) {
        this.juso = juso;
    }


    // alt+insert

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
