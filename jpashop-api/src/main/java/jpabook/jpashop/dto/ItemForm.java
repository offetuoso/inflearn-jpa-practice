package jpabook.jpashop.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class ItemForm {
    private long id;

    @NotEmpty(message = "상품명은 필수 입니다.")
    private String name;

    private int price;

    private int stockQuantity;

    @NotEmpty(message = "상품 구분을 선택해 주세요.")
    private String dtype;

    private String artist;
    private String etc;
    private String author;
    private String isbn;
    private String director;
    private String actor;

    @Override
    public String toString() {
        return "ItemForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", dtype='" + dtype + '\'' +
                ", artist='" + artist + '\'' +
                ", etc='" + etc + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", director='" + director + '\'' +
                ", actor='" + actor + '\'' +
                '}';
    }
}
