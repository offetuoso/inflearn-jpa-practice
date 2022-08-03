package jpabook.jpashop.domain.item;

import jpabook.jpashop.dto.ItemForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B") //구분값 B
@Getter //@Setter setter 지양
public class Book extends Item{
    private String author;
    private String isbn;

    @Override
    public Item createItem(ItemForm itemForm) {
        this.setId(itemForm.getId());
        this.setName(itemForm.getName());
        this.setPrice(itemForm.getPrice());
        this.setStockQuantity(itemForm.getStockQuantity());
        this.isbn = itemForm.getIsbn();
        this.author = itemForm.getAuthor();
        return this;
    }

    @Override
    public ItemForm transItemForm() {
        Book book = (Book) this;

        ItemForm itemForm = new ItemForm();
        itemForm.setDtype(book.getDtype());
        itemForm.setId(book.getId());
        itemForm.setName(book.getName());
        itemForm.setPrice(book.getPrice());
        itemForm.setStockQuantity(book.getStockQuantity());

        itemForm.setAuthor(book.getAuthor());
        itemForm.setIsbn(book.getIsbn());

        return itemForm;
    }

    @Override
    public Item changeItem(ItemForm itemForm) {
        this.name = itemForm.getName();
        this.price = itemForm.getPrice();
        this.stockQuantity = itemForm.getStockQuantity();
        this.author= itemForm.getAuthor();
        this.isbn = itemForm.getIsbn();
        return this;
    }
}
