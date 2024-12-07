package org.ndissandea.adminassist.service;

import org.ndissandea.adminassist.exception.ItemNotFoundException;
import org.ndissandea.adminassist.model.Inventory;
import org.ndissandea.adminassist.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {
    private InventoryRepository inventoryRepository;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }


    @Override
    public List<Inventory> getInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);

    }

    @Override
    public Inventory updateInventory(Inventory inventory, long id) {
        return inventoryRepository.findById(id).map(in->{
            in.setName(inventory.getName());
            in.setDescription(inventory.getDescription());
            in.setStatus(inventory.getStatus());
            in.setQuantity(inventory.getQuantity());
            in.setAssignTo(inventory.getAssignTo());
            in.setAddedDate(inventory.getAddedDate());
            return inventoryRepository.save(in);

        }).orElse(null);
    }

    @Override
    public void deleteInventory(long id) {
        if(!inventoryRepository.existsById(id)) {
            throw new ItemNotFoundException("Item not found");

        }
        inventoryRepository.deleteById(id);

    }

    @Override
    public Inventory getInventoryById(long id) {
        return inventoryRepository.findById(id).orElseThrow(()->new ItemNotFoundException("Item not found"));
    }
}



