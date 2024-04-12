package id.zar.smartkantin.repository;

import java.util.List;
import java.util.UUID;

import id.zar.smartkantin.DbModel.Vendor;

public interface IVendorRepository {
	Vendor getById(UUID id);
	Vendor getByUserId(UUID userId);
	List<Vendor> getAll();
	List<Vendor> getAllWithDeleted();
	void delete(Vendor item);
	Vendor update(Vendor vendor);
	Vendor add(Vendor vendor);
}
