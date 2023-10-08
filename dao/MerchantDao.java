package org.jsp.merchantBootApp.dao;

import java.util.Optional;

import org.jsp.merchantBootApp.dto.Merchant;
import org.jsp.merchantBootApp.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao {
	@Autowired
	private MerchantRepository repository;
	
	public Merchant saveMerchant(Merchant merchant)
	{
		System.out.println(repository);
		return repository.save(merchant);
	}
	
	public Merchant updateMerchant(Merchant merchant)
	{
		return repository.save(merchant);
	}
	
	public Optional<Merchant> verifyMerchant(long phone, String password)
	{
		return repository.verifyMerchant(phone, password);
	}
	
	public Optional<Merchant> findById(int id)
	{
		return repository.findById(id);
	}
	
}
