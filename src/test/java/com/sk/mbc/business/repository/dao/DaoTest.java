package com.sk.mbc.business.repository.dao;

import com.sk.mbc.business.domain.Info;
import java.util.ArrayList;

public class DaoTest {
    public static void main(String[] args) {
        // TODO Auto-generated method stub 
        print();
    }

    public static void print() {
        DAO dao = new DAO();
        //select1() 
        dao.select1();

        //select2(); 
        ArrayList<Info> list = dao.select2();
        if (list != null) {
            for (Info i : list) {
                System.out.print(i.getId() + " ");
                System.out.print(i.getPass() + " ");
                System.out.print(i.getName() + " ");
                System.out.print(i.getPhone() + " ");
                System.out.println(i.getEmail() + " ");
            }
        } else {
            System.out.println("list null!");
        }
    }
}