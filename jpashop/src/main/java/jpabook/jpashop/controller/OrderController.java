package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.dto.ItemDto;
import jpabook.jpashop.dto.ItemForm;
import jpabook.jpashop.dto.OrderDto;
import jpabook.jpashop.dto.OrderSearch;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String orderForm(Model modal){
        log.info("call get /order");

        List<Member> members = memberService.findMembers();
        //List<Item> items = itemService.findItems();

        List<ItemDto> items = itemService.findItemDtoList();

        System.out.println(items);

        modal.addAttribute("members", members);
        modal.addAttribute("items", items);
        modal.addAttribute("orderFrom", new ItemForm());
        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order( OrderDto orderDto
                        , Model modal){
        log.info("call post /order");

        System.out.println(orderDto);

        //단건 상품 주문
        //Long order = orderService.order(memberid, itemId, count);

        //여러 상품 주문
        Long order = orderService.orderByList(orderDto.getOrderDtoList());

        return  "redirect:/orders";

    }

    @GetMapping("/orders")
    public String orderList(OrderSearch orderSearch, Model modal){
        log.info("call get /orderList");

        List<Order> orders = orderService.findOrders(orderSearch);

        modal.addAttribute("orders", orders);

        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable(name = "orderId") Long orderId){
        log.info("call get /orderList");

        orderService.cancelOrder(orderId);

        return  "redirect:/orders";
    }
}
