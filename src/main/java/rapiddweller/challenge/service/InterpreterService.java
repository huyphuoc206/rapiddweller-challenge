package rapiddweller.challenge.service;

import rapiddweller.challenge.payload.InterpretCodeRequest;
import rapiddweller.challenge.payload.InterpretContextRequest;

import java.lang.reflect.InvocationTargetException;

public interface InterpreterService {
    String interpret(InterpretCodeRequest payload);

    void addCodeToInterpreterContext(InterpretContextRequest payload);
}
