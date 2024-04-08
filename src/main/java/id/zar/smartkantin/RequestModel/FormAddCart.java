package id.zar.smartkantin.RequestModel;

import java.util.UUID;

public class FormAddCart {
	private UUID foodId;
	private int qty;
	public UUID getFoodId() {
		return foodId;
	}
	public void setFoodId(UUID foodId) {
		this.foodId = foodId;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
}
