package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Album;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.Movie;
import jpabook.jpashop.dto.ItemDto;
import jpabook.jpashop.dto.ItemForm;
import jpabook.jpashop.exception.NotHasDiscriminator;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor  // 생성자 주입
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Item saveItem(Item item){
        itemRepository.save(item);
        return item;
    }

    @Transactional
    public Item updateItem(Long itemId, ItemForm itemForm){

        if("A".equals(itemForm.getDtype())){
            Album findItem = (Album) itemRepository.findOne(itemId);

            return findItem.changeItem(itemForm);

        }else if("B".equals(itemForm.getDtype())){
            Book findItem = (Book) itemRepository.findOne(itemId);

            return findItem.changeItem(itemForm);

        }else if("M".equals(itemForm.getDtype())){
            Movie findItem = (Movie) itemRepository.findOne(itemId);


            return findItem.changeItem(itemForm);

        }else{
            throw new NotHasDiscriminator("Not Has Discriminator");
        }
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public List<ItemDto> findItemDtoList(){
        return itemRepository.findDtoListAll();
    }

    public Item findOne(Long item_id){
        return itemRepository.findOne(item_id);
    }

    public Item transItemEntity(ItemForm itemForm) {
        Item item = null;
        String dtype = itemForm.getDtype();

        if("A".equals(itemForm.getDtype())){
            item = new Album().createItem(itemForm); // 앨범 생성
        }else if("B".equals(itemForm.getDtype())){
            item = new Book().createItem(itemForm); // 책 생성
        }else if("M".equals(itemForm.getDtype())){
            item = new Movie().createItem(itemForm);  // 영화 생성
        }else{
            throw new NotHasDiscriminator("Not Has Discriminator");
        }

        return item;
    }
}
