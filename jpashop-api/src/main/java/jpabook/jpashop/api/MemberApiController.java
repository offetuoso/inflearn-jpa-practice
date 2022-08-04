package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

//@Controller @RequestBody // 두개 합친 것이 @RestController
@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    /*
     *  첫번째 버전의 회원등록
     * */
    @PostMapping("/api/v1/members")
    public CreateMemberResopnse saveMemberV1(@RequestBody @Valid Member member){
        Long id = memberService.join(member);
        return new CreateMemberResopnse(id);
    }

    /*
     *  두번째 버전의 회원등록
     * */
    @PostMapping("/api/v2/members")
    public CreateMemberResopnse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){

        Member member = new Member();
        member.setName(request.getName());
        member.setAddress(new Address(request.getCity(),request.getStreet(),request.getZipcode()));

        Long id = memberService.join(member);
        return new CreateMemberResopnse(id);
    }

    @Data
    static class CreateMemberResopnse {
        private long id;

        public CreateMemberResopnse(long id) {
            this.id = id;
        }
    }

    @Data
    static class CreateMemberRequest {
        @NotEmpty
        private String name;
        @NotEmpty
        private String city;
        @NotEmpty
        private String street;
        @NotEmpty
        private String zipcode;
    }
}
