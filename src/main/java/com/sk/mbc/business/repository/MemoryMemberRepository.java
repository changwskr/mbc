package com.sk.mbc.business.repository;

import com.sk.mbc.business.domain.Member;

import java.util.*;


/*
 * 데이타베이스를 사용하기전에 메모리저장소를 마련해서 사용한다.
 * HashMap을 이용한다.
 * 저장소명은 static Map store이다.
 * 데이타를 들어올때마다, sequence를 증가시키면서 Member객체를 저장한다.
 */

//@Repository --> springconfig.java 로 변경해서 삭제
public class MemoryMemberRepository implements IMemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); // typedl Long, Member  인 HashMap() 객체를 만들어라
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);  // 여기서 id를 셋팅한다.
        store.put(member.getId(), member);

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  // store.get(id)가 없어 null이 나오더라도 null도 감싸달라는 말이다. Option.ofNullable()의 의미이다.
    }

    // 하나를 찾으면 반환해라
    // 찾을땐 람다함수를 이용해라
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()  // store에 저장된 것들을 stream화해서
                .filter(member -> member.getName().equals(name)) //이 스트림에서 member객체에서 name이 파라미터로 전달된 name이 같은것을 필터링해라
                .findAny(); // 뭔가를 찾으면 반환해라
    }


    // 반환견 Member를 가진 List로 해라
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Long remove(String name){
        Optional<Member> member = findByName(name);
        member.get().getName();
        Long idv = member.get().getId();
        System.out.println(member.get().getName() + " " + member.get().getId());

        store.remove(idv);
        return idv;
    }

    public void clear(){
        store.clear();
    }

}
