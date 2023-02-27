package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //persist 영속하다 => 자동으로 저장됨
        return member;
    }

    @Override
    public Optional<Member> findByID(Long id) {
        Member member = em.find(Member.class, id); //find(조회할타입.식별자 Pk값)
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
                                                //Entity 객체를 대상으로 Query를 날림 (보통은 테이블을 대상으로 쿼리문을 날리지만)
    }
}
