import java.util.Collection;
import java.util.HashMap;

public class Cake {

	// instance variables
	private String cake;
	private String cakeId;
	private double price;

	public Cake(String cakeId, String cake, double price) {
		this.cakeId = cakeId;
		this.cake = cake;
		this.price = price;
	}

	// getter and setter method
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCake() {
		return cake;
	}

	public void setCake(String cake) {
		this.cake = cake;
	}

	public String getCakeId() {
		return cakeId;
	}

	public void setCakeId(String cakeId) {
		this.cakeId = cakeId;
	}
}
