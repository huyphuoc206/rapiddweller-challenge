package rapiddweller.challenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/translator")
    public String translator() {
        return "translator";
    }

    @GetMapping("/interpreter")
    public String interpreter() {
        return "interpreter";
    }
}
