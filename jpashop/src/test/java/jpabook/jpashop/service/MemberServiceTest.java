package jpabook.jpashop.service;

import static org.junit.jupiter.api.Assertions.*;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class MemberServiceTest {

    // 테스트 케이스에서는 다른곳에서 참조할 곳이 없으므로 @Autowired로 사용
    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;
    @Autowired EntityManager em;

    @Test
    //@Rollback(value = false)
    public void 회원가입() throws Exception{
        //given //given : 이렇게 주어졌을때
        Member member = new Member();
        member.setName("userA");
        
        //when //when : 이렇게 하면
        Long savedId = memberService.join(member);

        //then //then : 이렇게 된다.
        // JPA안에서 하나의 트랜잭션에서 같은 엔티티에서 PK 키가 같으면 같은 영속성 컨텍스트 1차 캐시로 같은 객체로 관리
        em.flush();
        assertEquals(member, memberRepository.findOne(savedId));
    }


    @Test
    public void 중복_회원_예외() throws Exception{
        //given

        String username = "user";
        Member member1 = new Member();
        member1.setName(username);

        Member member2 = new Member();
        member2.setName(username);

        //when
        memberService.join(member1);

        //then
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

    }
}