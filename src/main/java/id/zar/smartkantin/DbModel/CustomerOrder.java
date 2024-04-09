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
	private UUID paymentMethodId;
	private double totalPrice;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime paymentExpiredAt;
	private LocalDateTime paymentDoneAt;
	
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
	
	public boolean isExpired()
	{
		var skrg = LocalDateTime.now();
		
		if(skrg.compareTo(this.paymentExpiredAt) > 0)
		{
			return true;
		}
		return false;
	}
	
	public boolean isPaid()
	{
		if(this.paymentDoneAt == null)
		{
			return false;
		}
		return true;
	}
	
	public UUID getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(UUID paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public LocalDateTime getPaymentExpiredAt() {
		return paymentExpiredAt;
	}

	public void setPaymentExpiredAt(LocalDateTime paymentExpiredAt) {
		this.paymentExpiredAt = paymentExpiredAt;
	}

	public LocalDateTime getPaymentDoneAt() {
		return paymentDoneAt;
	}

	public void setPaymentDoneAt(LocalDateTime paymentDoneAt) {
		this.paymentDoneAt = paymentDoneAt;
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
