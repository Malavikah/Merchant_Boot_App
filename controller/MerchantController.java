package org.jsp.merchantBootApp.controller;

import org.jsp.merchantBootApp.dto.Merchant;
import org.jsp.merchantBootApp.dto.ResponseStructure;
import org.jsp.merchantBootApp.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MerchantController {
	@Autowired
	private MerchantService service;
	
	@PostMapping("/merchants")
	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(@RequestBody Merchant merchant){
		return service.saveMerchant(merchant);
	}
	
	@PutMapping("/merchants/{id}")
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(@RequestBody Merchant merchant, @PathVariable int id){
		return service.updateMerchant(merchant);
	}
	
	@PostMapping("/merchants/verify-by-phone")
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(@RequestParam long phone, @RequestParam String password){
		return service.verifyMerchant(phone, password);
	}
	
	
}
