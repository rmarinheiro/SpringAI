package br.com.example.rafael.spring_ai_rafael.controller;

import br.com.example.rafael.spring_ai_rafael.service.ChatService;
import br.com.example.rafael.spring_ai_rafael.service.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerateAIController {

    private  final ChatService chatService;
    private final RecipeService recipeService;
    public GenerateAIController(ChatService chatService, RecipeService recipeService) {
        this.chatService = chatService;
        this.recipeService = recipeService;
    }

    @GetMapping("ask-ai")
    public String getRespondse(@RequestParam String prompt){
        return chatService.getResponse(prompt);
    }

    @GetMapping("ask-ai-options")
    public String getResponseWithOptions(){
        return chatService.getResponseWithOptions();
    }

    @GetMapping("recipe-create")
    public String RecipeCreate(@RequestParam String ingredients,
                               @RequestParam(defaultValue = "any") String cuisine,
                               @RequestParam(defaultValue = "none") String dietaryRestriction){
        return recipeService.createRecipe(ingredients,cuisine, dietaryRestriction);
    }

}
