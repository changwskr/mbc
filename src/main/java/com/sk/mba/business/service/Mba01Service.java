package com.sk.mba.business.service;

import com.sk.mba.business.domain.Member;
import com.sk.mba.business.repository.IMemberRepository;
import com.sk.mba.transfer.IDTO;
import com.sk.mba.transfer.Mba01CDTO;
import com.sk.mba.transfer.Mba01ListCDTO;
import com.sk.mba.business.service.dao.Mbc01DAO;

import java.util.List;
import java.util.Optional;


public class Mba01Service implements IMba01Service {

    private final IMemberRepository repository;
    public Mba01Service(IMemberRepository memberRepository){
        this.repository = memberRepository;
    }

    @Override
    public IDTO execute(Mba01CDTO mba01CDTO) {
        Mba01CDTO tomba01CDTO1 = new Mba01CDTO();


        Member member = null;
        String name = mba01CDTO.getName();

        String txno = mba01CDTO.getTxno();

        switch(Integer.parseInt(mba01CDTO.getTxno())){

            case 1000:
                member = createMember(name).get();
                break;
            case 2000:
                queryMember(name);
                break;
            case 3000:
                updateMember(name);
                break;
            case 4000:
                deleteMember(name);
                break;
            case 5000:
                member = new Member();
                member.setName(mba01CDTO.getName());
                join(member);
                break;
            case 6000:
                List<Member> list = findMembers();
                Mbc01DAO dao = new Mbc01DAO();
                Mba01ListCDTO listCDTO = dao.selectMemberList(repository);

                break;
            case 7000:
                Optional<Member> member1 = findOne(mba01CDTO.getId());
                Mbc01DAO dao2 = new Mbc01DAO();
                Mba01CDTO cdto = dao2.selectMember(repository,tomba01CDTO1.getId());
                break;

            default:
                break;
        }

        return null;
    }





    @Override
    public Long join(Member member){

        // 중복이름 안된다.
        validateDuplicateMember(member);

        repository.save(member);

        return member.getId();
    }

    /**
     * 전체회원 조회
     * @return
     */
    @Override
    public List<Member> findMembers() {
        List<Member> o = repository.findAll();
        return o;
    }

    @Override
    public Optional<Member> findOne(Long id) {
        return repository.findById(id);
    }


    private boolean validateDuplicateMember(Member member) {
        boolean rtn = false;

        repository.findByName(member.getName())
                .ifPresent(member1 -> {
                    throw new IllegalStateException("이미 회원이 존재합니다.");
                });
        return rtn;
    }


    Optional<Member> createMember(String name) {
        Optional<Member> member = repository.findByName(name);

        if (! validateDuplicateMember(member.get())) {
            repository.save(member.get());
        }

        return member;
    }

    void queryMember(String name){
        repository.findByName(name);
    }

    void updateMember(String name){
        Optional<Member> member = repository.findByName(name);

        if( !validateDuplicateMember(member.get())){
            member.get().setJuso("nayangju bando");
        }
        else{

        }
    }

    void deleteMember(String name){
        repository.remove(name);
    }



}

