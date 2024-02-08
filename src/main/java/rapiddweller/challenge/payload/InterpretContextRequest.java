package rapiddweller.challenge.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterpretContextRequest {
    private String input;
    private boolean isClear;
}
