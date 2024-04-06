package id.zar.smartkantin.ResponseModel;

import java.time.LocalDateTime;
import java.util.UUID;

import id.zar.smartkantin.DbModel.MyUser;

public class ResponseMyUser {
	private UUID id;
	private String username;
	private String email;
	private String status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@Override
	public String toString() {
		return "ResponseMyUser [id=" + id + ", username=" + username + ", email=" + email + ", status=" + status
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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

	public static ResponseMyUser fromMyUser(MyUser user) {
		var result = new ResponseMyUser();
		result.setCreatedAt(user.getCreatedAt());
		result.setEmail(user.getEmail());
		result.setId(user.getId());
		result.setStatus(user.getStatus());
		result.setUpdatedAt(user.getUpdatedAt());
		result.setUsername(user.getUsername());
		
		return result;
	}
}
