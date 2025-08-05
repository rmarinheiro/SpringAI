package br.com.example.rafael.spring_ai_rafael.service;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final OpenAiImageModel openaiImageModel;

    public ImageService(OpenAiImageModel openaiImageModel) {
        this.openaiImageModel = openaiImageModel;
    }

    public ImageResponse generateImage(String prompt,String quality,Integer n,
                                       Integer height,Integer width){
        //ImageResponse imageResponse = openaiImageModel.call(new ImagePrompt(prompt));
       /* ImageResponse imageResponse = openaiImageModel.call(
                new ImagePrompt("A light cream colored mini golden doodle",
                        OpenAiImageOptions.builder()
                                .quality("hd")
                                .N(4)
                                .height(1024)
                                .width(1024).build());
        */
        ImageResponse imageResponse = openaiImageModel.call(
                new ImagePrompt(prompt,
                        OpenAiImageOptions.builder()
                                .quality(quality)
                                .N(n)
                                .height(height)
                                .width(width).build()
                ));
        return imageResponse;
    }

}
