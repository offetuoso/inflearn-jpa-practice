package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class ItemUpdateTest {

    @Autowired
    EntityManager em;
    
    @Test
    public void updateTest() throws Exception{
        Book book = em.find(Book.class, 1L);

        //TX
        book.setName("asdasd");
        
        //변경 감지(duty checking)

    }
}
