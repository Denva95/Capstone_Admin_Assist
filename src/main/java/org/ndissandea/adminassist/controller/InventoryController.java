package org.ndissandea.adminassist.controller;

import org.ndissandea.adminassist.model.Inventory;
import org.ndissandea.adminassist.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Get all inventory items
    @GetMapping
    public ResponseEntity<List<Inventory>> getInventory() {
        List<Inventory> inventoryList = inventoryService.getInventory();
        return new ResponseEntity<>(inventoryList, HttpStatus.OK);
    }

    // Add a new inventory item
    @PostMapping
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) {
        Inventory newInventory = inventoryService.addInventory(inventory);
        return new ResponseEntity<>(newInventory, HttpStatus.CREATED);
    }

    // Update an existing inventory item
    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@RequestBody Inventory inventory, @PathVariable long id) {
        Inventory updatedInventory = inventoryService.updateInventory(inventory, id);
        return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
    }

    // Delete an inventory item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable long id) {
        Inventory inventory = new Inventory();
        inventory.setId(id);  // Assuming Inventory has an id field
        inventoryService.deleteInventory(inventory);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
*/
