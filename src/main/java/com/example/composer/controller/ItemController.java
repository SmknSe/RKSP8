package com.example.composer.controller;

import api.ItemServiceApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.ItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item-service/api")
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    private final ItemServiceApi itemServiceApi;

    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemDTO itemDTO) {
        log.info("Creating new item: {}", itemDTO);
        var createdItem = itemServiceApi.createItem(itemDTO);
        return ResponseEntity.ok(createdItem);
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        log.info("Getting all items");
        return ResponseEntity.ok(itemServiceApi.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable String id) {
        log.info("Getting item by id: {}", id);
        return ResponseEntity.ok(itemServiceApi.getItemById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemById(@PathVariable String id) {
        log.info("Deleting item by id: {}", id);
        itemServiceApi.deleteItemById(id);
        return ResponseEntity.noContent().build();
    }
}
