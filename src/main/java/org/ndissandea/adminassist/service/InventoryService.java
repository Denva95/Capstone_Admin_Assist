package org.ndissandea.adminassist.service;

import org.ndissandea.adminassist.model.Inventory;

import java.util.List;

public interface InventoryService {
    public List<Inventory> getInventory();
    public Inventory addInventory(Inventory inventory);
    public Inventory updateInventory(Inventory inventory, long id);
    public void deleteInventory(Inventory inventory);
}
