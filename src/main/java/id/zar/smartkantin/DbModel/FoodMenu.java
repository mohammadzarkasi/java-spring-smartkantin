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
	private String nama;
	private String gambarMenu;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	
	@Override
	public String toString() {
		return "FoodMenu [id=" + id + ", nama=" + nama + ", gambarMenu=" + gambarMenu + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
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
