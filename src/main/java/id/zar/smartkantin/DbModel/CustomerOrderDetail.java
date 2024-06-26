package id.zar.smartkantin.DbModel;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cust_order_detail")
public class CustomerOrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private UUID orderId;
	private UUID foodMenuId;
	private int qty;
	private double priceSnapshot;
	private double subTotal;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String vendorNameSnapshot;
	private String foodMenuNameSnapshot;

	
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	
	
	public double getPriceSnapshot() {
		return priceSnapshot;
	}
	public void setPriceSnapshot(double priceSnapshot) {
		this.priceSnapshot = priceSnapshot;
	}
	public String getVendorNameSnapshot() {
		return vendorNameSnapshot;
	}
	public void setVendorNameSnapshot(String vendorNameSnapshot) {
		this.vendorNameSnapshot = vendorNameSnapshot;
	}
	public String getFoodMenuNameSnapshot() {
		return foodMenuNameSnapshot;
	}
	public void setFoodMenuNameSnapshot(String foodMenuNameSnapshot) {
		this.foodMenuNameSnapshot = foodMenuNameSnapshot;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getFoodMenuId() {
		return foodMenuId;
	}
	public void setFoodMenuId(UUID foodMenuId) {
		this.foodMenuId = foodMenuId;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public UUID getOrderId() {
		return orderId;
	}
	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}
	
}
