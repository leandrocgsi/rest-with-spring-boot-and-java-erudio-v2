package br.com.erudio.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.erudio.vo.request.ChatGptRequest;
import br.com.erudio.vo.response.ChatGptResponse;

@Service
public class ChatGPTService {

    private Logger logger = Logger.getLogger
            (ChatGPTService.class.getName());
    
    @Value("${openai.model}")
    private String model;
    
    @Value("${openai.api.url}")
    private String url;
    
    @Autowired
    private RestTemplate template;
    
    public Object chat(String prompt) {
        
        logger.info("Starting Prompt");
        
        ChatGptRequest request = new ChatGptRequest(model, prompt);

        logger.info("Processing Prompt");
        ChatGptResponse response = 
                template.postForObject(url, request, ChatGptResponse.class);

        return response;
    }
}
