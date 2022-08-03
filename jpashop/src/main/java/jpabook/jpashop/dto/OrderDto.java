package jpabook.jpashop.dto;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class OrderDto {

    private long id;
    private long memberId;
    private long itemId;
    private int count;

    private List<OrderDto> orderDtoList;

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", itemId=" + itemId +
                ", count=" + count +
                ", orderDtoList=" + orderDtoList +
                '}';
    }
}
