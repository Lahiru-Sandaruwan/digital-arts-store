package com.digital.art.service;

import com.digital.art.dto.ItemDTO;
import com.digital.art.jpa.ItemJPA;
import com.digital.art.model.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemJPA itemJPA;

    @Autowired
    ObjectMapper objectMapper;

    public ItemDTO save(ItemDTO itemDTO) {
        return convertModelToDTO(itemJPA.save(convertDTOtoModel(itemDTO)));
    }

    public ItemDTO getById(int id) {
        return convertModelToDTO(itemJPA.findById(id).orElseThrow());
    }

    private Item convertDTOtoModel(ItemDTO itemDTO){
      return   objectMapper.convertValue(itemDTO, Item.class);
    }

    private ItemDTO convertModelToDTO(Item item){
        return   objectMapper.convertValue(item, ItemDTO.class);
    }

    public List<Item> getAll() {
        return itemJPA.findAll();
    }

    public Item update(ItemDTO itemDTO) throws Exception {
        Item updateItem =convertDTOtoModel(itemDTO);
        Optional<Item> existingItem = itemJPA.findById(itemDTO.getItemId());

        if(existingItem.isPresent())
            throw new Exception("Item is Already Exist");

        return itemJPA.save(updateItem);
    }

    public Item deleteById(Integer id) throws Exception {
        Optional<Item> itemOptional = itemJPA.findById(id);
        if(itemOptional.isEmpty())
            throw new Exception("Item is Not Found");

        itemJPA.delete(itemOptional.get());
     return itemOptional.get();
    }

    public void saveItem(MultipartFile file, String itemCode, String itemName) throws IOException {
        Item item = new Item();
        item.setItemCode(itemCode);
        item.setItemName(itemName);
        // Set other fields as needed
        item.setFilename(file.getOriginalFilename());
        item.setContent(file.getBytes());
        // Handle file upload and save the item
        // ...

        itemJPA.save(item);
    }
}
