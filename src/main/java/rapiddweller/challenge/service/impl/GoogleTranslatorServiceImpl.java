package rapiddweller.challenge.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rapiddweller.challenge.payload.TranslatorRequest;
import rapiddweller.challenge.service.TranslatorService;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class GoogleTranslatorServiceImpl implements TranslatorService {
    private final String ENGLISH_CODE = "en";
    private final String GERMAN_CODE = "de";

    @Value("${translator-script-url}")
    private String translatorScriptUrl;

    @Override
    public String translate(TranslatorRequest payload) throws IOException {
        String urlStr = translatorScriptUrl +
                "?q=" + URLEncoder.encode(payload.getInput(), StandardCharsets.UTF_8) +
                "&source=" + ENGLISH_CODE +
                "&target=" + GERMAN_CODE;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(urlStr, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootExchange = mapper.readTree(response.getBody());
        return rootExchange.path("translatedText").asText();
    }
}
