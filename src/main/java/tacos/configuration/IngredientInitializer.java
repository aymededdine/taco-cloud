package tacos.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import tacos.entity.Ingredient;
import tacos.entity.Ingredient.Type;
import tacos.entity.Taco;
import tacos.repository.IngredientRepository;
import tacos.repository.TacoRepository;

@Component
public class IngredientInitializer implements CommandLineRunner {

	@Autowired
	TacoRepository tacoRepo;

	private final IngredientRepository ingredientRepository;

	public IngredientInitializer(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		// Delete existing data if needed
		ingredientRepository.deleteAll();

		Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
		Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Type.WRAP);
		Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Type.PROTEIN);
		Ingredient carnitas = new Ingredient("CARN", "Carnitas", Type.PROTEIN);
		Ingredient tomatoes = new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
		Ingredient lettuce = new Ingredient("LETC", "Lettuce", Type.VEGGIES);
		Ingredient cheddar = new Ingredient("CHED", "Cheddar", Type.CHEESE);
		Ingredient jack = new Ingredient("JACK", "Monterrey Jack", Type.CHEESE);
		Ingredient salsa = new Ingredient("SLSA", "Salsa", Type.SAUCE);
		Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Type.SAUCE);
		ingredientRepository.save(flourTortilla);
		ingredientRepository.save(cornTortilla);
		ingredientRepository.save(groundBeef);
		ingredientRepository.save(carnitas);
		ingredientRepository.save(tomatoes);
		ingredientRepository.save(lettuce);
		ingredientRepository.save(cheddar);
		ingredientRepository.save(jack);
		ingredientRepository.save(salsa);
		ingredientRepository.save(sourCream);

		Taco taco1 = new Taco();
		taco1.setId(1L);
		taco1.setName("Carnivore");
		taco1.setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));
		tacoRepo.save(taco1);
		Taco taco2 = new Taco();
		taco2.setId(2L);
		taco2.setName("Bovine Bounty");
		taco2.setIngredients(Arrays.asList(cornTortilla, groundBeef, cheddar, jack, sourCream));
		tacoRepo.save(taco2);
		Taco taco3 = new Taco();
		taco3.setId(3L);
		taco3.setName("Veg-Out");
		taco3.setIngredients(Arrays.asList(flourTortilla, cornTortilla, tomatoes, lettuce, salsa));
		tacoRepo.save(taco3);

		System.out.println("Ingredient data initialized.");
	}
}
