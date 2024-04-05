package id.zar.smartkantin.DbModel;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FoodMenu {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private UUID ownerId;
	private String nama;
	private String gambarMenu;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private double price;
	
	@Override
	public String toString() {
		return "FoodMenu [id=" + id + ", ownerId=" + ownerId + ", nama=" + nama + ", gambarMenu=" + gambarMenu
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", price=" + price + "]";
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public UUID getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(UUID ownerId) {
		this.ownerId = ownerId;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
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
	
}
