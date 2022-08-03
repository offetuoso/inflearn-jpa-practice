package jpabook.jpashop.domain.item;

import jpabook.jpashop.dto.ItemForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A") //구분값 A
@Getter //@Setter setter 지양
public class Album extends Item{
    private String artist;
    private String etc;

    @Override
    public Item createItem(ItemForm itemForm) {
        this.setId(itemForm.getId());
        this.setName(itemForm.getName());
        this.setPrice(itemForm.getPrice());
        this.setStockQuantity(itemForm.getStockQuantity());
        this.artist = itemForm.getArtist();
        this.etc = itemForm.getEtc();
        return this;
    }

    @Override
    public ItemForm transItemForm() {
        Album album = (Album) this;

        ItemForm itemForm = new ItemForm();
        itemForm.setDtype(album.getDtype());
        itemForm.setId(album.getId());
        itemForm.setName(album.getName());
        itemForm.setPrice(album.getPrice());
        itemForm.setStockQuantity(album.getStockQuantity());

        itemForm.setArtist(album.getArtist());
        itemForm.setEtc(album.getEtc());

        return itemForm;
    }

    @Override
    public Item changeItem(ItemForm itemForm) {
        this.name = itemForm.getName();
        this.price = itemForm.getPrice();
        this.stockQuantity = itemForm.getStockQuantity();
        this.artist= itemForm.getArtist();
        this.etc = itemForm.getEtc();
        return this;
    }
}
