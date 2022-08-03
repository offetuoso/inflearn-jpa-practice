package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.dto.MemberForm;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    //@Slf4j 사용
    //Logger log = LoggerFactory.getLogger(getClass());
    private final MemberService memberService;

    @GetMapping("/members/new")
    public String newMembersForm(Model modal){
        log.info("call get members/new");

        modal.addAttribute("memberForm", new MemberForm());
        return "members/newMembersForm";


    }

    @PostMapping("/members/new")
    public String newMembers(@Valid MemberForm memberForm, BindingResult result, Model modal){
        log.info("call post members/new");

        if(result.hasErrors()){
            return "members/newMembersForm";
        }

        Member newMember = new Member();
        newMember.setName(memberForm.getName());
        newMember.setAddress(memberForm.getAddress());

        memberService.join(newMember);


        return "redirect:/members";
    }

    @GetMapping("/members")
    public String memberList(Model modal){
        log.info("call get members");

        List<Member> members = memberService.findMembers();
        modal.addAttribute("members", members);

        return "members/memberList";


    }
}
