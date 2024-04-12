package id.zar.smartkantin.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.zar.smartkantin.DbModel.Vendor;
import id.zar.smartkantin.repository.IVendorRepository;
import id.zar.smartkantin.service.IVendorService;

@Service
public class VendorService implements IVendorService{

	@Autowired
	private IVendorRepository vendorRepo;
	
	@Transactional
	@Override
	public Vendor getById(UUID id) {
		return vendorRepo.getById(id);
	}

	@Transactional
	@Override
	public Vendor getVendorByUserId(UUID userId) {
		return vendorRepo.getByUserId(userId);
	}

	@Transactional
	@Override
	public List<Vendor> getAll() {
		return vendorRepo.getAll();
	}

	@Transactional
	@Override
	public Vendor update(Vendor vendor) {
		return vendorRepo.update(vendor);
	}

	@Transactional
	@Override
	public Vendor add(Vendor vendor) {
		return vendorRepo.add(vendor);
	}

}
