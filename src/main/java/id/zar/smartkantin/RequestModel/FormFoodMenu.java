package id.zar.smartkantin.RequestModel;

import java.time.LocalDateTime;

import id.zar.smartkantin.DbModel.FoodMenu;

public class FormFoodMenu {	
	private String nama;
	private String gambarMenu;
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getGambarMenu() {
		return gambarMenu;
	}
	public void setGambarMenu(String gambarMenu) {
		this.gambarMenu = gambarMenu;
	}
	
	public FoodMenu asNewFoodMenu()
	{
		var result = new FoodMenu();
		result.setNama(this.nama);
		result.setGambarMenu(this.gambarMenu);
		result.setCreatedAt(LocalDateTime.now());
		return result;
	}
}
