package id.zar.smartkantin.RequestModel;

import java.util.UUID;

public class FormUpdateCart {
	private UUID cartId;
	private int qty;
	public UUID getCartId() {
		return cartId;
	}
	public void setCartId(UUID cartId) {
		this.cartId = cartId;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = Math.max(1, qty);
	}
	
}
