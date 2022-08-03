package jpabook.jpashop.domain.item;

import jpabook.jpashop.dto.ItemForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M") //구분값 M
@Getter //@Setter setter 지양
public class Movie extends Item{
    private String director;
    private String actor;

    @Override
    public Item createItem(ItemForm itemForm) {
        this.setId(itemForm.getId());
        this.setName(itemForm.getName());
        this.setPrice(itemForm.getPrice());
        this.setStockQuantity(itemForm.getStockQuantity());
        this.director = itemForm.getDirector();
        this.actor = itemForm.getActor();
        return this;
    }

    @Override
    public ItemForm transItemForm() {
        Movie movie = (Movie) this;
        ItemForm itemForm = new ItemForm();
        itemForm.setDtype(movie.getDtype());
        itemForm.setId(movie.getId());
        itemForm.setName(movie.getName());
        itemForm.setPrice(movie.getPrice());
        itemForm.setStockQuantity(movie.getStockQuantity());

        itemForm.setDirector(movie.getDirector());
        itemForm.setActor(movie.getActor());

        return itemForm;
    }

    @Override
    public Item changeItem(ItemForm itemForm) {
        this.name = itemForm.getName();
        this.price = itemForm.getPrice();
        this.stockQuantity = itemForm.getStockQuantity();
        this.director = itemForm.getDirector();
        this.actor = itemForm.getActor();
        return this;
    }
}
