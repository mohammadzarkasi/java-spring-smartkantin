package id.zar.smartkantin.DbModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "cust_order")
public class CustomerOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private UUID userId;
	private double totalPrice;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@Transient
	private List<CustomerOrderDetail> details = new ArrayList<CustomerOrderDetail>();
	
	public void addDetail(CustomerOrderDetail d)
	{
		details.add(d);
	}
	
	public List<CustomerOrderDetail> getDetails()
	{
		return details;
	}
	
	public void setDetails(List<CustomerOrderDetail> d)
	{
		details = d;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getUserId() {
		return userId;
	}
	public void setUserId(UUID userId) {
		this.userId = userId;
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
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
