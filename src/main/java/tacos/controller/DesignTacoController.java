package tacos.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import tacos.entity.Ingredient;
import tacos.entity.Ingredient.Type;
import tacos.entity.Taco;
import tacos.entity.TacoOrder;
import tacos.repository.IngredientRepository;

@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
	

	private static final org.slf4j.Logger log =
			org.slf4j.LoggerFactory.getLogger(DesignTacoController.class);
	
	private final IngredientRepository ingredientRepo;
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo) {
	this.ingredientRepo = ingredientRepo;
	}
	
	
	private List<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
	    return StreamSupport.stream(ingredients.spliterator(), false)
	            .filter(x -> x.getType().equals(type))
	            .collect(Collectors.toList());
	}

	
	

	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		
	Iterable<Ingredient> ingredients = ingredientRepo.findAll();
	
	Type[] types = Ingredient.Type.values();
	
	for (Type type : types) {
		model.addAttribute(type.toString().toLowerCase(),
				filterByType(ingredients, type));
	}
	
	
} 
	
	@ModelAttribute(name = "tacoOrder")
	public TacoOrder order() {
		return new TacoOrder();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	
	@GetMapping
	public String showDesignForm() {
		return "design";
	}
	
	@PostMapping
	public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
		tacoOrder.addTaco(taco);
		if (errors.hasErrors()) {
			log.info(String.valueOf(errors));
			return "design";
		}
		log.info("Processing taco: {}", taco);
		return "redirect:/orders/current";
	}
	
}
