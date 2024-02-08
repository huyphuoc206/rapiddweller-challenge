package rapiddweller.challenge.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rapiddweller.challenge.payload.InterpretCodeRequest;
import rapiddweller.challenge.payload.InterpretContextRequest;
import rapiddweller.challenge.payload.ResponseCommon;
import rapiddweller.challenge.service.InterpreterService;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InterpreterController {
    private final InterpreterService interpreterService;

    @PostMapping("/interpreter")
    public ResponseEntity<?> interpretCode(@RequestBody InterpretCodeRequest payload) {
        return ResponseEntity.ok(ResponseCommon.success(interpreterService.interpret(payload)));
    }

    @PostMapping("/interpreter-context")
    public ResponseEntity<?> addCodeToInterpreterContext(@RequestBody InterpretContextRequest payload) {
        interpreterService.addCodeToInterpreterContext(payload);
        return ResponseEntity.ok(ResponseCommon.success("OK"));
    }
}
