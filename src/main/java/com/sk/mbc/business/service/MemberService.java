package com.sk.mbc.business.service;

import com.sk.mbc.business.domain.Member;
import com.sk.mbc.business.repository.IMemberRepository;
import com.sk.mbc.business.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MemberService implements IMemberService{

    private final IMemberRepository repository;

    @Autowired
    public MemberService(MemoryMemberRepository memberRepository){
        this.repository = memberRepository;
    }

    @Override
    public Member execute(char txtype, String name) {
        Optional<Member> member = null;

        if( name.isBlank() || name.isEmpty()) {
            return null;
        }

        switch(txtype){
            case 'c':
                member = createMember(name);
                break;
            case 'r':
                queryMember(name);
                break;
            case 'u':
                updateMember(name);
                break;
            case 'd':
                deleteMember(name);
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
