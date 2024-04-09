package id.zar.smartkantin.RequestModel;

import java.util.List;
import java.util.UUID;

public class FormAddOrder {
	private List<UUID> cartIds;

	public List<UUID> getCartIds() {
		return cartIds;
	}

	public void setCartIds(List<UUID> cartIds) {
		this.cartIds = cartIds;
	}
}
