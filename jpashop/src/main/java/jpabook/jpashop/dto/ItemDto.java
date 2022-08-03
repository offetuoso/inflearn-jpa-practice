package jpabook.jpashop.dto;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class ItemDto {
    public ItemDto(long id, String name, int price, int stockQuantity, String dtype, String artist, String etc, String author, String isbn, String director, String actor) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.dtype = dtype;
        this.artist = artist;
        this.etc = etc;
        this.author = author;
        this.isbn = isbn;
        this.director = director;
        this.actor = actor;
    }

    private long id;

    private String name;

    private int price;

    private int stockQuantity;

    private String dtype;

    private String dtypeNm;

    public String getDtypeNm() {
        String dtypeNm = null;

        if(dtype.equals("A")){
            dtypeNm = Item.ALBUM;
        }else if(dtype.equals("B")){
            dtypeNm = Item.BOOK;
        }else if(dtype.equals("M")){
            dtypeNm = Item.MOVIE;
        }
        return dtypeNm;
    }

    private String artist;
    private String etc;
    private String author;
    private String isbn;
    private String director;
    private String actor;

    @Override
    public String toString() {
        return "ItemDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", dtype='" + dtype + '\'' +
                ", dtypeNm='" + dtypeNm + '\'' +
                ", artist='" + artist + '\'' +
                ", etc='" + etc + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", director='" + director + '\'' +
                ", actor='" + actor + '\'' +
                '}';
    }
}
