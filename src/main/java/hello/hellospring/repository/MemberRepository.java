package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByID(Long id); //Optional 은 null 반환시 옵션 추가? 라는데
    Optional<Member> findByName(String name);

    List<Member> findAll();
}
