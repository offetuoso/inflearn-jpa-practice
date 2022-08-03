package jpabook.jpashop.domain.item;

import jpabook.jpashop.dto.ItemForm;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    public final static String ALBUM = "음반";
    public final static String BOOK = "책";
    public final static String MOVIE = "영화";

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    protected String name;
    protected int price;
    protected int stockQuantity;

    @Column(name="dtype", insertable = false, updatable = false)
    protected String dtype;

    public String getDtype() {
        return dtype;
    }

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

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//

    /**
     * 재고 증가
     * @param quantity
     */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     * 재고 감소
     * @param quantity
     */
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;

        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

    public abstract Item createItem(ItemForm itemForm);

    public abstract ItemForm transItemForm();

    public abstract Item changeItem(ItemForm itemForm);




    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dtype=" + dtype +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", categories=" + categories +
                '}';
    }
}
