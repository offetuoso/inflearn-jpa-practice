package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Album;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.Movie;
import jpabook.jpashop.dto.ItemDto;
import jpabook.jpashop.dto.ItemForm;
import jpabook.jpashop.exception.NotHasDiscriminator;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String newItemsForm(Model modal){
        log.info("call get /items/new");

        modal.addAttribute("itemForm", new ItemForm());
        return "items/newItemForm";
    }

    @PostMapping("/items/new")
    public String newItems(@Valid ItemForm itemForm, BindingResult result, Model modal){
        log.info("call post members/new");

        if(result.hasErrors()){
            return "items/newItemForm";
        }

        Item item = itemService.transItemEntity(itemForm);

        itemService.saveItem(item);

        return  "redirect:/items";

    }

    @GetMapping("/items")
    public String itemList(Model modal){
        log.info("call get /items");

        List<Item> items = itemService.findItems();

        modal.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String editItemsForm(@PathVariable("itemId") Long itemId, Model modal){
        log.info("call get /items/edit");

        Item item = itemService.findOne(itemId);

        ItemForm itemForm = item.transItemForm();

        modal.addAttribute("itemForm", itemForm);

        return "items/editItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItem(@ModelAttribute("form") ItemForm itemForm){

        Item item = itemService.transItemEntity(itemForm);
        //itemService.saveItem(item);
        itemService.updateItem(itemForm.getId(), itemForm);

        return "redirect:items";

    }



}
