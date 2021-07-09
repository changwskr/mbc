package com.sk.mba.transfer;

public class Mba01CDTO extends DTO {
    Long id;
    String name;
    String juso;
    String txno;

    public String getTxno() {
        return txno;
    }

    public void setTxno(String txno) {
        this.txno = txno;
    }


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

    public String getJuso() {
        return juso;
    }

    public void setJuso(String juso) {
        this.juso = juso;
    }
}
