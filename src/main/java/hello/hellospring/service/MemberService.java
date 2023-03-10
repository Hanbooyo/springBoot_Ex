package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Optional;
@Transactional //데이터를 저장하거나 변경할땐 항상 트랜잭션이 있어야함 (JPA는 모든 데이터 변경이 트랜잭션 안에서 수행됨)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } // 외부에서 넣어주는걸로 바꿈  (직접 new로 생성이 아니라)

    /**
     * 회원가입
     */

    public Long join(Member member) {
        //같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).
            ifPresent(m -> {
                throw  new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     *  전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberID) {
        return memberRepository.findByID(memberID);
    }
}
