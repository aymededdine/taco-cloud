package tacos.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Ingredient {
	
	public Ingredient() {
    }

	public Ingredient(String id, String name, Type type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}

	@Id
	private  String id;
	private  String name;
	private  Type type;

	public enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", type=" + type + "]";
	}
	
	
	
	

}
