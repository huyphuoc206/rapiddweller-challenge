package rapiddweller.challenge.service;

import rapiddweller.challenge.payload.TranslatorRequest;

import java.io.IOException;

public interface TranslatorService {
    String translate(TranslatorRequest payload) throws IOException;
}
