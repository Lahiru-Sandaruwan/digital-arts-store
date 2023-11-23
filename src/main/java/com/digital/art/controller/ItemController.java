package com.digital.art.controller;

import com.digital.art.dto.ItemDTO;
import com.digital.art.model.Item;
import com.digital.art.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping("/")
    public ResponseEntity<?> saveItem(@RequestParam("file") MultipartFile file,
                                      @RequestParam("itemCode") String itemCode,
                                      @RequestParam("itemName") String itemName) throws IOException {
        try {
            // Save the item with the provided data and file
            itemService.saveItem(file, itemCode, itemName);
            return ResponseEntity.ok("Item saved successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving item");
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> getById(@RequestParam int id){
        return ResponseEntity.ok(itemService.getById(id)) ;
    }

    @GetMapping("/items")
    public ResponseEntity<?> getAll(Model model){
        return ResponseEntity.ok(itemService.getAll());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateItem(@RequestBody ItemDTO itemDTO) throws Exception {
        return ResponseEntity.ok(itemService.update(itemDTO));
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Integer id) throws Exception{
        return ResponseEntity.ok(itemService.deleteById(id));
    }



}
