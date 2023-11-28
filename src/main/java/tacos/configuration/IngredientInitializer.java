package tacos.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tacos.entity.Ingredient;
import tacos.entity.Ingredient.Type;
import tacos.repository.IngredientRepository;

@Component
public class IngredientInitializer implements CommandLineRunner {

    private final IngredientRepository ingredientRepository;

    public IngredientInitializer(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Delete existing data if needed
        ingredientRepository.deleteAll();

        // Create and save new Ingredient entities
        Ingredient[] ingredients = {
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        };

        for (Ingredient ingredient : ingredients) {
            ingredientRepository.save(ingredient);
        }

        System.out.println("Ingredient data initialized.");
    }
}

