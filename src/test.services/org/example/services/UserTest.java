package org.example.services;

//import org.example.api.Recipes;
import org.example.data.model.Recipe;
import org.example.data.model.User;
import org.example.data.model.UserSignUp;
import org.example.data.repository.LoginRepository;
import org.example.data.repository.RecipeRepository;
import org.example.data.repository.SignUpRepository;
import org.example.dto.request.CreateRecipeRequest;
import org.example.dto.request.CreateUserLoginRequest;
import org.example.dto.request.CreateUserSignUpRequest;
import org.example.dto.response.CreateRecipeResponse;
import org.example.dto.response.CreateUserLoginRespond;
import org.example.dto.response.CreateUserSignUpRespond;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTest {
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private UserSignUpService userSignUpService;
    @Autowired
    private SignUpRepository signUpRepository;
    @Autowired
    private LoginRepository loginRepository ;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private RecipeRepository recipeRepository;
//    @Autowired
//    private Recipe recipe;

//    @BeforeEach
//    void delete(){
//        signUpRepository.deleteAll();
//        loginRepository.deleteAll();
//    }


    @BeforeEach
    void clear(){

    }

    @Test
    public void testThatUserCanRegister(){
        CreateUserSignUpRespond respond = userSignUpService.createUser(new CreateUserSignUpRequest());
        CreateUserSignUpRequest request = new CreateUserSignUpRequest();
        UserSignUp signUp = new UserSignUp();
        signUp.setFullName(request.getFullName());
        signUp.setUsername(request.getUserName());
        signUp.setEmail(request.getEmail());
        signUp.setPassword(request.getPassword());
        signUpRepository.save(signUp);
        respond.setMessage("Registered successful");
        assertEquals(respond.getMessage() , "Registered successful");
    }
    @Test
    public void TestThatUserCanLogin(){
        CreateUserLoginRespond respond = userLoginService.createLogin(new CreateUserLoginRequest());
        CreateUserSignUpRequest request = new CreateUserSignUpRequest();
        User login = new User();
        login.setUsername(request.getUserName());
        login.setPassword(request.getPassword());
        loginRepository.save(login);
        respond.setMessage(respond.getMessage());
        assertEquals(respond.getMessage() , "You're welcome, Login successful");
    }
    @Test
    public void testThatExistingUserCannotRegisterTwice(){
        CreateUserSignUpRequest request = new CreateUserSignUpRequest();
        request.setFullName("Taoreed Olawale");
        request.setUserName("@whales");
        request.setEmail("ajaditaoreed@gmail.com");
        request.setPassword("11111");
        CreateUserSignUpRespond respond = userSignUpService.createUser(request);
        assertNotNull(respond);
        request.setFullName("Taoreed Olawale");
        request.setUserName("@whales");
        request.setEmail("ajaditaoreed@gmail.com");
        request.setPassword("11111");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {userSignUpService.createUser(request);});
        assertEquals(signUpRepository.count() , 1);
        assertEquals(exception.getMessage() , "User already exist");
    }
    @Test
    public void testThatUserCanGetRecipe(){
        CreateRecipeResponse recipesRespond = recipeService.recipeRespond(new CreateRecipeRequest());
        CreateRecipeRequest request = new CreateRecipeRequest();
        Recipe recipe = new Recipe();
        recipe.setFoodName("White rice");
        recipeRepository.save(recipe);
        List<Recipe> recipes = recipeService.getRecipesByFoodName("White rice");
        assertEquals(recipes.size() , 1);
        Recipe fetch = new Recipe();
        assertEquals(fetch.getGetAllRecipes() , "");


//        recipesRespond.setRecipes({"Rice, water, salt","Boil rice in salted water until tender.", "org.example.data.model.Recipe"});
//        assertEquals(recipesRespond.getRecipes() , "1");
    }
//    @Test
//    public void testThatUserCanGetRecipyAfterRegisterAndLogin(){
//        CreateRecipeRequest request = new CreateRecipeRequest();
//        CreateRecipeResponse respond =  recipeService.createRecipe(request);
//        Recipe recipe = new Recipe();
//        recipe.setFoodName("cassava");
//        recipeRepository.save(recipe);
//        respond.setIngredients("maggi,onion,pepper suya,grinding fish");
//        respond.setInstructions("boils for 5Minutes");
//        assertThat(respond).isNotNull();
//        assertEquals(respond.getIngredients() , "maggi,onion,pepper suya,grinding fish");
//        assertEquals(respond.getInstructions() , "boils for 5Minutes");
//    }
//    @Test
//    public void testToDeployRecipesIntoDataBase(){
//        CreateRecipeRequest request = new CreateRecipeRequest();
//        Recipe recipe = new Recipe();
//        recipe.setFoodName("Jollof rice");
//        recipe.setIngredients("Rice, Tomatoes, Onions, Spices");
//        recipe.setInstructions("Cook rice with tomatoes and spices.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 1);

//        recipe.setFoodName("Egusi soup");
//        recipe.setIngredients("Egusi, Spinach, Meat, Spices");
//        recipe.setInstructions("Cook egusi with spinach and meat.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 50);
//
//        recipe.setFoodName("Okro soup");
//        recipe.setIngredients("Okra, spinach, meat, spices");
//        recipe.setInstructions("Cook okra with spinach and meat.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 50);
////
//        recipe.setFoodName("Gbegiri soup");
//        recipe.setIngredients("Beans, palm oil, onions, crayfish, spices");
//        recipe.setInstructions(" Cook beans until soft, blend into a smooth paste, and simmer with palm oil, onions, crayfish, and spice.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//
//
//        recipe.setFoodName("Jollof rice");
//        recipe.setIngredients("Rice, tomatoes, onions, bell peppers, spices.");
//        recipe.setInstructions("Cook rice with a blend of tomatoes, onions, and bell peppers, seasoned with spices.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);

//        recipe.setFoodName("fried rice");
//        recipe.setIngredients("Rice, mixed vegetables (carrots, peas, bell peppers), chicken or shrimp, soy sauce, spices.");
//        recipe.setInstructions("Cook rice, stir-fry with mixed vegetables and chicken or shrimp, and season with soy sauce and spices.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//
////
//        recipe.setFoodName("White rice");
//        recipe.setIngredients("Rice, water, salt.");
//        recipe.setInstructions("Boil rice in salted water until tender.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//
//
//        recipe.setFoodName("Concortion rice");
//        recipe.setIngredients(" Rice, palm oil, onions, tomatoes, assorted meat, spices.");
//        recipe.setInstructions("Cook rice with palm oil, onions, tomatoes, and assorted meat, seasoned with spices.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//
//        recipe.setFoodName("Pepper soup rice");
//        recipe.setIngredients("Rice, assorted meat or fish, pepper soup spices, onions, peppers.");
//        recipe.setInstructions("Cook rice with assorted meat or fish and pepper soup spices until tender.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//
//        recipe.setFoodName("Assorted rice");
//        recipe.setIngredients("Rice, assorted meat or fish, pepper soup spices, onions, peppers.");
//        recipe.setInstructions("Cook rice with assorted meat or fish and pepper soup spices until tender.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//
//        recipe.setFoodName("Coconut rice");
//        recipe.setIngredients("Rice, coconut milk, water, salt.");
//        recipe.setInstructions("Cook rice in a mixture of coconut milk and water, seasoned with salt.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//
//        recipe.setFoodName("Ofada rice");
//        recipe.setIngredients("Ofada rice, water, salt");
//        recipe.setInstructions("Boil Ofada rice in salted water until tender, often served with Ayamase");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//
//        recipe.setFoodName("Basmati Rice");
//        recipe.setIngredients("Ofada rice, water, salt");
//        recipe.setInstructions("Boil Ofada rice in salted water until tender, often served with Ayamase");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//
//        recipe.setFoodName("Basmati RiceOfada rice");
//        recipe.setIngredients("Fried Rice with Pineapple");
//        recipe.setInstructions("Rinse basmati rice, then cook in salted water until fluffy.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);

//        recipe.setFoodName("Rice and beans");
//        recipe.setIngredients("Rice, beans, onions, tomatoes, spices");
//        recipe.setInstructions("Cook rice, stir-fry with pineapple chunks and mixed vegetables, and season with soy sauce and spic.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);


//        recipe.setFoodName(" Rice Pilaf");
//        recipe.setIngredients("Rice, broth, onions, garlic, spices");
//        recipe.setInstructions("Sauté onions and garlic, add rice and broth, and cook until rice is tender");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//
//        recipe.setFoodName("Sushi Rice");
//        recipe.setIngredients("Sushi rice, rice vinegar, sugar, salt");
//        recipe.setInstructions("Cook sushi rice, then mix with rice vinegar, sugar, and salt.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//
//        recipe.setFoodName("Afang Soup");
//        recipe.setIngredients("Afang leaves, waterleaf, assorted meat, crayfish, palm oil, periwinkle, stockfish, onions, bouillon cubes, chili pepper, salt");
//        recipe.setInstructions("Cook afang leaves with waterleaf, assorted meat, crayfish, palm oil, periwinkle, stockfish, onions, bouillon cubes, chili pepper, and salt.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//
//        recipe.setFoodName("Edikang Ikong Soup");
//        recipe.setIngredients("Pumpkin leaves, waterleaf, assorted meat, crayfish, palm oil, periwinkle, stockfish, onions, bouillon cubes, chili pepper, salt");
//        recipe.setInstructions("Cook pumpkin leaves with waterleaf, assorted meat, crayfish, palm oil, periwinkle, stockfish, onions, bouillon cubes, chili pepper, and salt.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//
//        recipe.setFoodName("Banga Soup");
//        recipe.setIngredients("Palm fruit extract, assorted meat, dried fish, crayfish, bitter leaf, onions, bouillon cubes, chili pepper, salt");
//        recipe.setInstructions("Cook palm fruit extract with assorted meat, dried fish, crayfish, bitter leaf, onions, bouillon cubes, chili pepper, and salt.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//
//        recipe.setFoodName("Oha Soup");
//        recipe.setIngredients("Oha leaves, assorted meat, palm oil, crayfish, cocoyam paste, onions, bouillon cubes, chili pepper, salt");
//        recipe.setInstructions("Cook oha leaves with assorted meat, palm oil, crayfish, cocoyam paste, onions, bouillon cubes, chili pepper, and salt.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//
//        recipe.setFoodName("Pepper Soup");
//        recipe.setIngredients("Assorted meat or fish, pepper soup spices, onions, peppers, salt");
//        recipe.setInstructions("Cook assorted meat or fish with pepper soup spices, onions, peppers, and salt.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//
//        recipe.setFoodName("Suya");
//        recipe.setIngredients("Beef, suya spice mix, vegetable oil, onions, tomatoes");
//        recipe.setInstructions("Marinate beef with suya spice mix and vegetable oil, then grill and serve with onions and tomatoes.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);

//        recipe.setFoodName("Asun");
//        recipe.setIngredients("Goat meat, onions, bell peppers, chili peppers, vegetable oil, spices");
//        recipe.setInstructions("Grill goat meat, then stir-fry with onions, bell peppers, chili peppers, and spices.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//
//        recipe.setFoodName("Nkwobi");
//        recipe.setIngredients("Cow foot, palm oil, potash, ugba (oil bean), onions, utazi leaves, spices");
//        recipe.setInstructions("Cook cow foot until tender, mix with palm oil and potash, then add ugba, onions, utazi leaves, and spices.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//
//
//        recipe.setFoodName("Peppered Snails");
//        recipe.setIngredients("Snails, onions, bell peppers, chili peppers, vegetable oil, spices");
//        recipe.setInstructions("Clean and boil snails, then stir-fry with onions, bell peppers, chili peppers, and spices.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//
//        recipe.setFoodName("Goat Meat Pepper Soup");
//        recipe.setIngredients("Goat meat, pepper soup spices, onions, peppers, salt");
//        recipe.setInstructions("Cook goat meat with pepper soup spices, onions, peppers, and salt.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//
//        recipe.setFoodName("Beef Stew");
//        recipe.setIngredients("Beef, tomatoes, onions, bell peppers, vegetable oil, spices");
//        recipe.setInstructions("Cook beef with tomatoes, onions, bell peppers, and spices.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//
//        recipe.setFoodName("Chicken Stew");
//        recipe.setIngredients("Chicken, tomatoes, onions, bell peppers, vegetable oil, spices");
//        recipe.setInstructions("Cook chicken with tomatoes, onions, bell peppers, and spices.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//
//        recipe.setFoodName("Fish Stew");
//        recipe.setIngredients("Fish, tomatoes, onions, bell peppers, vegetable oil, spices");
//        recipe.setInstructions("Cook fish with tomatoes, onions, bell peppers, and spices.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 1);
//
//        recipe.setFoodName("Goat Meat Stew");
//        recipe.setIngredients("Goat meat, tomatoes, onions, bell peppers, vegetable oil, spices");
//        recipe.setInstructions("Cook goat meat with tomatoes, onions, bell peppers, and spices.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//
//        recipe.setFoodName("Chicken Pepper Soup");
//        recipe.setIngredients("Chicken, pepper soup spices, onions, peppers, salt");
//        recipe.setInstructions("Cook chicken with pepper soup spices, onions, peppers, and salt.");
//        recipeRepository.save(recipe);
//        List<Recipe> recipes = recipeRepository.findAll(); assertEquals(recipes.size(), 32);
//        System.out.println(recipes.size());
    }





