package com.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.inventory.model.Inventory;
import com.inventory.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	@PostMapping("/save")
	public Inventory saveInventory(@RequestBody Inventory inventory) {
		return inventoryService.saveInventory(inventory);
	}

	@PostMapping("/update-quantity")
	public ResponseEntity<String> updateQuantity(@RequestParam String skuCode, @RequestParam Integer quantity) {
		boolean isUpdated = inventoryService.updateQuantity(skuCode, quantity);
		if (isUpdated) {
			return ResponseEntity.ok("Quantity updated successfully");
		} else {
			return ResponseEntity.status(400).body("Product is not available");
		}

	}

	@GetMapping("/all")
	public ResponseEntity<List<Inventory>> getAllProducts() {
		List<Inventory> products = inventoryService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/{skuCode}")
	public ResponseEntity<Inventory> getProductBySkuCode(@PathVariable String skuCode) {
		Inventory inventory = inventoryService.getProductBySkuCode(skuCode);
		if (inventory != null) {
			return new ResponseEntity<>(inventory, HttpStatus.OK);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
}