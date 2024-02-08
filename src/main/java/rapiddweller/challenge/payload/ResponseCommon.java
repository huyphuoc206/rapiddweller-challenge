package rapiddweller.challenge.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCommon<T> {
    private T data;
    private String message;
    private boolean success;

    public static <T> ResponseCommon<T> success(T data) {
        return new ResponseCommon<>(data, null, true);
    }

    public static <T> ResponseCommon<T> fail(String message) {
        return new ResponseCommon<>(null, message, false);
    }
}
