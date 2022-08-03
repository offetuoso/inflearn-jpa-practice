package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor  // 생성자 주입
public class MemberService {

    /*
    // 최초 코드 이며, Setter Injection로 대체
    @Autowired
    private MemberRepository memberRepository;
    */

    /*
    //Constructor Injection로 대체
    private MemberRepository memberRepository;

    public void setMemberService(MemberRepository memberRepository) { //Setter Injection
        this.memberRepository = memberRepository;
    }
    */

    /*
    // @RequiredArgsConstructor로 대체
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) { //Constructor Injection
        this.memberRepository = memberRepository;
    }
    */

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional(readOnly = false)
    public Long join(Member member){
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId(); //save()를 통해 em.persist()를 수행하므로 Member 엔티티의 키 생성을 보장함
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(findMembers.size() != 0){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    /**
     * 회원 전체 조회
     */
    //@Transactional(readOnly = true)
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 회원 조회
     */
    //@Transactional(readOnly = true)
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
