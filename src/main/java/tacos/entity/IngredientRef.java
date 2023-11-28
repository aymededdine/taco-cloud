package tacos.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class IngredientRef {
    public IngredientRef(String ingredient) {
        this.ingredient = ingredient;
    }
    public IngredientRef(Long id, String ingredient) {
        this.ingredient = ingredient;
        this.id=id;
    }
    
    @Id
    public Long id;
    
    

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	private final String ingredient;
    public String getIngredient() {
        return ingredient;
    }
}
