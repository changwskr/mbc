package com.sk.mbc.domain;

public class Member {
    private Long id;  //시스템에 저장하기 위해 사용하는 키값
    private String name; //이름은 고객이 직접 화면에서 올려준다.
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
