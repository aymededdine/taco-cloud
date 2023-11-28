package tacos.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import tacos.User;


@Entity
public class TacoOrder {
	
	
	
	public TacoOrder() {
		super();
		// TODO Auto-generated constructor stub
	}



	public TacoOrder(String deliveryName, String deliveryStreet, String deliveryCity, String deliveryState,
			String deliveryZip, String ccNumber, String ccExpiration, String ccCVV, List<Taco> tacos) {
		super();
		this.deliveryName = deliveryName;
		this.deliveryStreet = deliveryStreet;
		this.deliveryCity = deliveryCity;
		this.deliveryState = deliveryState;
		this.deliveryZip = deliveryZip;
		this.ccNumber = ccNumber;
		this.ccExpiration = ccExpiration;
		this.ccCVV = ccCVV;
		this.tacos = tacos;
	}
	
	
	
	



	public TacoOrder(Long id, Date placedAt, @NotBlank(message = "Delivery name is required") String deliveryName,
			@NotBlank(message = "Street is required") String deliveryStreet,
			@NotBlank(message = "City is required") String deliveryCity,
			@NotBlank(message = "State is required") String deliveryState,
			@NotBlank(message = "Zip code is required") String deliveryZip,
			@CreditCardNumber(message = "Not a valid credit card number") String ccNumber,
			@Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message = "Must be formatted MM/YY") String ccExpiration,
			@Digits(integer = 3, fraction = 0, message = "Invalid CVV") String ccCVV, List<Taco> tacos) {
		super();
		this.id = id;
		this.placedAt = placedAt;
		this.deliveryName = deliveryName;
		this.deliveryStreet = deliveryStreet;
		this.deliveryCity = deliveryCity;
		this.deliveryState = deliveryState;
		this.deliveryZip = deliveryZip;
		this.ccNumber = ccNumber;
		this.ccExpiration = ccExpiration;
		this.ccCVV = ccCVV;
		this.tacos = tacos;
	}





	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	private Date placedAt;
	
	@NotBlank(message="Delivery name is required")
	private String deliveryName;
	@NotBlank(message="Street is required")
	private String deliveryStreet;
	@NotBlank(message="City is required")
	private String deliveryCity;
	@NotBlank(message="State is required")
	private String deliveryState;
	@NotBlank(message="Zip code is required")
	private String deliveryZip;
	@CreditCardNumber(message="Not a valid credit card number")
	private String ccNumber;
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
			message="Must be formatted MM/YY")
	private String ccExpiration;
	@Digits(integer=3, fraction=0, message="Invalid CVV")
	private String ccCVV;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Taco> tacos = new ArrayList<>();
	
	@ManyToOne
	private User user;
	
	
	public void addTaco(Taco taco) {
		this.tacos.add(taco);
	}
	
	
	
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Date getPlacedAt() {
		return placedAt;
	}



	public void setPlacedAt(Date placedAt) {
		this.placedAt = placedAt;
	}



	public String getDeliveryName() {
		return deliveryName;
	}



	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}



	public String getDeliveryStreet() {
		return deliveryStreet;
	}



	public void setDeliveryStreet(String deliveryStreet) {
		this.deliveryStreet = deliveryStreet;
	}



	public String getDeliveryCity() {
		return deliveryCity;
	}



	public void setDeliveryCity(String deliveryCity) {
		this.deliveryCity = deliveryCity;
	}



	public String getDeliveryState() {
		return deliveryState;
	}



	public void setDeliveryState(String deliveryState) {
		this.deliveryState = deliveryState;
	}



	public String getDeliveryZip() {
		return deliveryZip;
	}



	public void setDeliveryZip(String deliveryZip) {
		this.deliveryZip = deliveryZip;
	}



	public String getCcNumber() {
		return ccNumber;
	}



	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}



	public String getCcExpiration() {
		return ccExpiration;
	}



	public void setCcExpiration(String ccExpiration) {
		this.ccExpiration = ccExpiration;
	}



	public String getCcCVV() {
		return ccCVV;
	}



	public void setCcCVV(String ccCVV) {
		this.ccCVV = ccCVV;
	}



	public List<Taco> getTacos() {
		return tacos;
	}



	public void setTacos(List<Taco> tacos) {
		this.tacos = tacos;
	}



	

}
