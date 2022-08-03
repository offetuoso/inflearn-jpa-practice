package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if (item.getId() == null){
            em.persist(item);
        }else{
            Item merge = em.merge(item); //머지는 null도 모두 변경하기 때문에 변경감지로 사용 권장
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
    public List<ItemDto> findDtoListAll(){

        String ItemDto = "jpabook.jpashop.dto.ItemDto";
        return em.createQuery("select new "+ItemDto+"(i.id, i.name, i.price, i.stockQuantity, i.dtype, TREAT(i AS Album ).artist, TREAT(i AS Album ).etc, TREAT(i AS Book ).author, TREAT(i AS Book ).isbn, TREAT(i AS Movie ).director, TREAT(i AS Movie ).actor) from Item i", ItemDto.class)
                .getResultList();
    }
}
