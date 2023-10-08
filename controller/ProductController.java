package org.jsp.merchantBootApp.controller;

import java.util.List;
import java.util.Locale.Category;

import org.jsp.merchantBootApp.dto.Product;
import org.jsp.merchantBootApp.dto.ResponseStructure;
import org.jsp.merchantBootApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	@Autowired
	private ProductService service;
	
	@PostMapping("/products/{merchant_id}")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product, @PathVariable int merchant_id){
		return service.saveProduct(product, merchant_id);
	}
	
	@GetMapping("/products/{merchant_id}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByMerchantId(@PathVariable int merchant_id) {
		return service.findByMerchantId(merchant_id);
	}
	
	@GetMapping("/products/find-by-brand")
	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(@RequestParam String brand) {
		return service.findByBrand(brand);
	}
	
	@GetMapping("/products/find-by-category")
	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(@RequestParam String category) {
		return service.findByCategory(category);
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable int id) {
		return service.deleteProduct(id);
	}
}
