package rapiddweller.challenge.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rapiddweller.challenge.payload.InterpretCodeRequest;
import rapiddweller.challenge.payload.ResponseCommon;
import rapiddweller.challenge.payload.TranslatorRequest;
import rapiddweller.challenge.service.TranslatorService;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TranslatorController {
    private final TranslatorService translatorService;

    @PostMapping("/translator")
    public ResponseEntity<?> translate(@RequestBody TranslatorRequest payload) throws IOException {
        return ResponseEntity.ok(ResponseCommon.success(translatorService.translate(payload)));
    }
}
