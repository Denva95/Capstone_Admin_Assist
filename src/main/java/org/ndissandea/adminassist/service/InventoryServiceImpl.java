package org.ndissandea.adminassist.service;

import org.ndissandea.adminassist.exception.itemNotFound;
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
            in.setDescription(in.getDescription());
            in.setStatus(inventory.getStatus());
            in.setQuantity(in.getQuantity());
            in.setAssignTo(inventory.getAssignTo());
            in.setDepartment(in.getDepartment());
            in.setAddedDate(inventory.getAddedDate());
            return inventoryRepository.save(in);

        }).orElse(null);
    }

    @Override
    public void deleteInventory(Inventory inventory) {
        if(!inventoryRepository.existsById(inventory.getId())) {
            throw new itemNotFound("Item not found");

        }

    }
}


