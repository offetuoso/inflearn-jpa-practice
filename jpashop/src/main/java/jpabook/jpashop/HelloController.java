package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") // hello 라는 응답을 받으면
    public String hello(Model model){

        model.addAttribute("data", "hello !!"); // addAttribute data의 값에 "hello !!" 를 넣어서
        System.out.println(1121);

        return "hello"; /* view 라는 페이지를 오픈*/
    }
}
