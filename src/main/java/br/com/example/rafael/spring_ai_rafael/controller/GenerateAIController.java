package br.com.example.rafael.spring_ai_rafael.controller;

import br.com.example.rafael.spring_ai_rafael.service.ChatService;
import br.com.example.rafael.spring_ai_rafael.service.ImageService;
import br.com.example.rafael.spring_ai_rafael.service.RecipeService;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenerateAIController {

    private  final ChatService chatService;
    private final RecipeService recipeService;

    private  final ImageService imageService;
    public GenerateAIController(ChatService chatService, RecipeService recipeService, ImageService imageService) {
        this.chatService = chatService;
        this.recipeService = recipeService;
        this.imageService = imageService;
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

    @GetMapping("generate-image")
    public List<String> GenerateImage(@RequestParam String prompt,
                                     @RequestParam(defaultValue = "hd") String quality,
                                     @RequestParam(defaultValue = "1") Integer n,
                                     @RequestParam(defaultValue = "1024") Integer height,
                                     @RequestParam(defaultValue = "1024") Integer width){
        ImageResponse imageResponse= imageService.generateImage(prompt,quality,n,height,width);
        List<String>imageUrls = imageResponse.getResults().stream().map(result->result.getOutput().getUrl()).toList();
        return imageUrls;
    }


}
