package id.zar.smartkantin.service;

import java.util.List;
import java.util.UUID;

import id.zar.smartkantin.DbModel.Vendor;

public interface IVendorService {
	Vendor getById(UUID id);
	Vendor getVendorByUserId(UUID userId);
	List<Vendor> getAll();
	Vendor update(Vendor vendor);
	Vendor add(Vendor vendor);
	
}
