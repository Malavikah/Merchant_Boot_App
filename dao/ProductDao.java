package org.jsp.merchantBootApp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantBootApp.dto.Product;
import org.jsp.merchantBootApp.repository.MerchantRepository;
import org.jsp.merchantBootApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository repository;
	
	public Product saveProduct(Product product) {
		return repository.save(product);
	}
	
	public Optional<Product> findById(int id) {
		return repository.findById(id);
	}
	
	public List<Product> findByMerchantId(int merchant_id){
		return repository.findByMerchantId(merchant_id);
	}
	
	public List<Product> findByBrand(String brand) {
		return repository.findByBrand(brand);
	}
	
	public List<Product> findByCategory(String category) {
		return repository.findByCategory(category);
	}
	
	
	public boolean deleteProduct(int id) {
		Optional<Product> recProduct = findById(id);
		if (recProduct.isPresent()) {
			repository.delete(recProduct.get());
			return true;
		}
		return false;
	}
	
}
