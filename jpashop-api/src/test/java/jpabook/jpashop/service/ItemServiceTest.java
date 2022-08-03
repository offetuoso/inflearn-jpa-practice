package jpabook.jpashop.service;

import static org.junit.jupiter.api.Assertions.*;

import jpabook.jpashop.domain.item.Album;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.Movie;
import jpabook.jpashop.dto.ItemForm;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class ItemServiceTest {
    // 테스트 케이스에서는 다른곳에서 참조할 곳이 없으므로 @Autowired로 사용
    @Autowired ItemRepository itemRepository;
    @Autowired ItemService itemService;
    @Autowired EntityManager em;

    @Test
    public void 음반_상품등록() throws Exception{
        //given
        ItemForm itemForm = new ItemForm();
        itemForm.setName("멜론 TOP 100");
        itemForm.setArtist("Various Artists");
        itemForm.setEtc("방탄소년단 외 다수");
        itemForm.setPrice(20000);
        itemForm.setStockQuantity(50);

        Item item = new Album().createItem(itemForm);

        //when
        Item savedItem = itemService.saveItem(item);

        //then
        em.flush();
        assertEquals(item, itemRepository.findOne(savedItem.getId()));
    }
    
    @Test
    public void 책_상품등록() throws Exception{
        //given
        ItemForm itemForm = new ItemForm();
        itemForm.setName("멜론 TOP 100");
        itemForm.setAuthor("김영한");
        itemForm.setIsbn("11111");
        itemForm.setPrice(15000);
        itemForm.setStockQuantity(100);

        Item item = new Book().createItem(itemForm);


        //when
        Item savedItem = itemService.saveItem(item);

        //then
        em.flush();
        assertEquals(item, itemRepository.findOne(savedItem.getId()));
    }
    
    @Test
    public void 영화_상품등록() throws Exception{
        //given

        ItemForm itemForm = new ItemForm();
        itemForm.setName("쥬라기월드: 도미니언");
        itemForm.setDirector("콜린 트레보로우");
        itemForm.setActor("크리스 프랫");
        itemForm.setPrice(15000);
        itemForm.setStockQuantity(1000);

        Item item = new Movie().createItem(itemForm);

        //when
        Item savedItem = itemService.saveItem(item);

        //then
        em.flush();
        assertEquals(item, itemRepository.findOne(savedItem.getId()));
    }
}