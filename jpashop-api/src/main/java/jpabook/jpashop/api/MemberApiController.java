package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

//@Controller @RequestBody // 두개 합친 것이 @RestController
@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;


    @GetMapping("/api/v1/members")
    public List<Member> getMembersV1(){
        List<Member> members = memberService.findMembers();
        return members;
    }


    @GetMapping("/api/v2/members")
    public Result getMembersV2(){
        List<Member> findMembers = memberService.findMembers();

        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName(), m.getAddress()))
                .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }

    @Data
    @AllArgsConstructor
    public class Result<T> {
        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
        private Address address;
    }



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

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResopnse updateMemberV2(
            @PathVariable("id") Long id
            , @RequestBody @Valid UpdateMemberRequest request
    ){

        // 수정 커맨드
        memberService.update(id, request.getName());
        // 조회 쿼리
        Member findMember = memberService.findOne(id);
        
        // 커맨드와 쿼리를 분리

        return new UpdateMemberResopnse(findMember.getId(), findMember.getName());
    }


    @Data
    @AllArgsConstructor
    static class UpdateMemberResopnse {
        private long id;
        private String name;
    }

    @Data
    static class UpdateMemberRequest {
        @NotEmpty
        private String name;
        private String city;
        private String street;
        private String zipcode;
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
