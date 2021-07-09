package com.sk.mba.business.service.dao;

import com.sk.mba.business.domain.Member;
import com.sk.mba.transfer.IDTO;
import com.sk.mba.transfer.Mba01CDTO;
import com.sk.mba.transfer.Mba01ListCDTO;
import com.sk.mba.business.repository.IMemberRepository;

import java.util.*;

public class Mbc01DAO {

    public Mba01ListCDTO selectMemberList(IMemberRepository repository) {

        Mba01ListCDTO mba01ListCDTO = new Mba01ListCDTO();
        List<Member> list = repository.findAll();

        Iterator iter = list.iterator(); //Iterator 선언
        while(iter.hasNext()){//다음값이 있는지 체크
            System.out.println(iter.next()); //값 출력
            Member member = (Member) iter.next();
            Mba01CDTO cdto = new Mba01CDTO();
            cdto.setId(member.getId());
            cdto.setName(member.getName());
            mba01ListCDTO.add((IDTO) cdto);
        }

        return mba01ListCDTO;
    }

    public Mba01CDTO selectMember(IMemberRepository repository,Long id) {

        Optional<Member> member = repository.findById(id);

        Mba01CDTO cdto = new Mba01CDTO();
        cdto.setId(member.get().getId());
        cdto.setName(member.get().getName());

        return cdto;

    }


}
