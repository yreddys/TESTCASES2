package com.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.dto.IventoryResponse;
import com.inventory.model.Inventory;
import com.inventory.repository.InventoryRepository;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	public Inventory saveInventory(Inventory inventory) {
		return inventoryRepository.save(inventory);
	}

	public boolean updateQuantity(String skuCode, Integer quantity) {
		Inventory inventory = inventoryRepository.findBySkuCode(skuCode);
		if (inventory != null && inventory.getQuantity() >= quantity) {
			inventory.setQuantity(inventory.getQuantity() - quantity);
			inventoryRepository.save(inventory);
			return true;
		} else {
			return false;
		}
	}

	public List<Inventory> getAllProducts() {
		return inventoryRepository.findAll();
	}

	public Inventory getProductBySkuCode(String skuCode) {
		return inventoryRepository.findBySkuCode(skuCode);
	}
}
