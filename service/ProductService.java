package org.jsp.merchantBootApp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantBootApp.dao.MerchantDao;
import org.jsp.merchantBootApp.dao.ProductDao;
import org.jsp.merchantBootApp.dto.Merchant;
import org.jsp.merchantBootApp.dto.Product;
import org.jsp.merchantBootApp.dto.ResponseStructure;
import org.jsp.merchantBootApp.exception.IdNotFoundException;
import org.jsp.merchantBootApp.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao dao;
	@Autowired
	private MerchantDao merchantdao;
	
	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product, int merchant_id){
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantdao.findById(merchant_id);
		
		if(recMerchant.isPresent())
		{
			Merchant m = recMerchant.get();
			m.getProducts().add(product);
			product.setMerchant(m);
			merchantdao.updateMerchant(m);
			dao.saveProduct(product);
			structure.setData(product);
			structure.setMessage("Product Added");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}

	
	public ResponseEntity<ResponseStructure<List<Product>>> findByMerchantId(int merchant_id) {
		ResponseStructure<List<Product>> structure=new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantdao.findById(merchant_id);
		if( recMerchant.isPresent())
		{
			structure.setData(dao.findByMerchantId(merchant_id));
			structure.setMessage("Product Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}	
		throw new IdNotFoundException();
	}	
	

	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(String brand) {
		ResponseStructure<List<Product>> structure=new ResponseStructure<>();
		List<Product> recProduct = dao.findByBrand(brand);
		if (recProduct.isEmpty()) {
			structure.setData(dao.findByBrand(brand));
			structure.setMessage("Invalid Brand Name");
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.NOT_FOUND);
		}
		structure.setData(recProduct);
		structure.setMessage("Product Found");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(String category) {
		ResponseStructure<List<Product>> structure=new ResponseStructure<>();
		List<Product> recProduct = dao.findByCategory(category);
		if (recProduct.isEmpty()) {
			structure.setData(dao.findByCategory(category));
			structure.setMessage("Invalid Category");
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.NOT_FOUND);
		}
		structure.setData(recProduct);
		structure.setMessage("Product Found");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteProduct(int id) {
		ResponseStructure<String> structure=new ResponseStructure<>();
		Optional<Product> recProduct = dao.findById(id);
		if (recProduct.isPresent()) {
			dao.deleteProduct(id);
			structure.setData("Product Deleted");
			structure.setMessage("Product Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}
	
}
