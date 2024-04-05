package id.zar.smartkantin.DbModel;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NutritionalInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private UUID menuId;
	private double calory;
	private double fat;
	private double carbohydrate;
	private double protein;
	private double vitaminA;
	private double vitaminC;
	private double calcium;
	private double iron;
	private double colesterol;
	private double sodium;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getMenuId() {
		return menuId;
	}
	public void setMenuId(UUID menuId) {
		this.menuId = menuId;
	}
	public double getCalory() {
		return calory;
	}
	public void setCalory(double calory) {
		this.calory = calory;
	}
	public double getFat() {
		return fat;
	}
	public void setFat(double fat) {
		this.fat = fat;
	}
	public double getCarbohydrate() {
		return carbohydrate;
	}
	public void setCarbohydrate(double carbohydrate) {
		this.carbohydrate = carbohydrate;
	}
	public double getProtein() {
		return protein;
	}
	public void setProtein(double protein) {
		this.protein = protein;
	}
	public double getVitaminA() {
		return vitaminA;
	}
	public void setVitaminA(double vitaminA) {
		this.vitaminA = vitaminA;
	}
	public double getVitaminC() {
		return vitaminC;
	}
	public void setVitaminC(double vitaminC) {
		this.vitaminC = vitaminC;
	}
	public double getCalcium() {
		return calcium;
	}
	public void setCalcium(double calcium) {
		this.calcium = calcium;
	}
	public double getIron() {
		return iron;
	}
	public void setIron(double iron) {
		this.iron = iron;
	}
	public double getColesterol() {
		return colesterol;
	}
	public void setColesterol(double colesterol) {
		this.colesterol = colesterol;
	}
	public double getSodium() {
		return sodium;
	}
	public void setSodium(double sodium) {
		this.sodium = sodium;
	}
	@Override
	public String toString() {
		return "NutritionalInfo [id=" + id + ", menuId=" + menuId + ", calory=" + calory + ", fat=" + fat
				+ ", carbohydrate=" + carbohydrate + ", protein=" + protein + ", vitaminA=" + vitaminA + ", vitaminC="
				+ vitaminC + ", calcium=" + calcium + ", iron=" + iron + ", colesterol=" + colesterol + ", sodium="
				+ sodium + "]";
	}
	
}
