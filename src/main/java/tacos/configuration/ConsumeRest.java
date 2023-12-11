package tacos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import tacos.entity.Ingredient;

public class ConsumeRest {

	RestTemplate rest = new RestTemplate();

	public Ingredient getIngredientById(String ingredientId) {
		 return rest.getForObject("http://localhost:8080/ingredients/{id}",
		 Ingredient.class, ingredientId);
		}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
