package org.jsp.merchantBootApp.service;

import java.util.Optional;

import org.jsp.merchantBootApp.dao.MerchantDao;
import org.jsp.merchantBootApp.dto.Merchant;
import org.jsp.merchantBootApp.dto.ResponseStructure;
import org.jsp.merchantBootApp.exception.IdNotFoundException;
import org.jsp.merchantBootApp.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao dao;
	
	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(Merchant merchant){
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		merchant = dao.saveMerchant(merchant);
		structure.setData(merchant);
		structure.setMessage("Merchant Registered " + merchant.getId());
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(Merchant merchant){
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		merchant = dao.updateMerchant(merchant);
		structure.setData(merchant);
		structure.setMessage("Merchant Update Successfully " + merchant.getId());
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(long phone, String password){
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = dao.verifyMerchant(phone,password);
		
		if(recMerchant.isPresent()) {
			structure.setData(recMerchant.get());
			structure.setMessage("Merchant Verified");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException();
	}

	
}
