package id.zar.smartkantin.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.zar.smartkantin.DbModel.CustomerCartItem;
import id.zar.smartkantin.DbModel.CustomerOrder;
import id.zar.smartkantin.DbModel.CustomerOrderDetail;
import id.zar.smartkantin.DbModel.FoodMenu;
import id.zar.smartkantin.DbModel.MyUser;
import id.zar.smartkantin.DbModel.Vendor;
import id.zar.smartkantin.repository.ICustomerCartRepository;
import id.zar.smartkantin.repository.ICustomerOrderRepository;
import id.zar.smartkantin.repository.IFoodMenuRepository;
import id.zar.smartkantin.repository.IMyUserRepository;
import id.zar.smartkantin.repository.IVendorRepository;
import id.zar.smartkantin.service.ICustomerOrderService;

@Service
public class CustomerOrderService implements ICustomerOrderService{

	@Autowired
	private ICustomerOrderRepository orderRepo;
	@Autowired
	private ICustomerCartRepository cartRepo;
	@Autowired
	private IFoodMenuRepository foodRepo;
	@Autowired
	private IVendorRepository vendorRepo;
	
	@Transactional
	@Override
	public List<CustomerOrder> getAll(UUID userId) {
		return orderRepo.getByUserId(userId);
	}

	@Transactional
	@Override
	public CustomerOrder create(UUID userId, List<UUID> cartIds) {
		var listCart = new ArrayList<CustomerCartItem>();
		var listItem = new ArrayList<FoodMenu>();
		var listVendor = new ArrayList<Vendor>();
		
		for(var cartId : cartIds)
		{
			var cart = cartRepo.getById(cartId);
			
//			cart tidak ditemukan
			if (cart == null)
			{
				continue;
			}
//			cart milik orang lain
			if(cart.getUserId().equals(userId) == false)
			{
				continue;
			}
			
			var foodItem = foodRepo.getById(cart.getFoodMenuId());
//			menu pada cart sudah dihapus
			if(foodItem == null)
			{
				continue;
			}
			
//			var vendor = userRepo.getById(foodItem.getOwnerId());
			var vendor = vendorRepo.getById(foodItem.getVendorId());
			
			listCart.add(cart);
			listItem.add(foodItem);
			listVendor.add(vendor);
		}
		
		if(listCart.isEmpty() == true)
		{
			return null;
		}
		
		var co = new CustomerOrder();
		co.setUserId(userId);
		co.setPaymentExpiredAt(LocalDateTime.now().plusMinutes(30));
		
		var nco = orderRepo.add(co);
		double price = 0;
		for(var i = 0; i < listItem.size(); i++)
		{
			var cart = listCart.get(i);
			var item = listItem.get(i);
			var vendor = listVendor.get(i);
			
			var od = new CustomerOrderDetail();
			od.setOrderId(co.getId());
			od.setFoodMenuId(cart.getFoodMenuId());
			od.setOrderId(nco.getId());
			od.setQty(cart.getQty());
			od.setPriceSnapshot(item.getPrice());
			od.setFoodMenuNameSnapshot(item.getNama());
			if(vendor != null)
			{
				od.setVendorNameSnapshot(vendor.getName());
			}
			od.setSubTotal(cart.getQty() * item.getPrice());
			
			var nod = orderRepo.addDetail(nco.getId(), od);
			nco.addDetail(nod);
			price += nod.getSubTotal();
			
			cartRepo.deleteCartItem(cart);
		}
		
		nco.setTotalPrice(price);
		orderRepo.update(nco);
		
		return co;
	}

}
