package com.sk.mbc.business.repository;
import com.sk.mbc.business.domain.Member;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements IMemberRepository{
    private final EntityManager em ;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> resultList = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return resultList.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        return result;

        // inline 방식으로 표현시
//        return em.createQuery("select m from Member m", Member.class)
//                .getResultList();

    }

    @Override
    public void clear() {

    }

    @Override
    public Long removeTest(String name) {
        return null;
    }
}
