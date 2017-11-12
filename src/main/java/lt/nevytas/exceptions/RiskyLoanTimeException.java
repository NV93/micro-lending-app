package lt.nevytas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by nevyt on 17-Oct-17.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Cannot provide a quick loan during 00:00 - 06:00 hours")
public class RiskyLoanTimeException extends RuntimeException {

    public RiskyLoanTimeException(String message) {
        super(message);
    }

    public RiskyLoanTimeException(String message, Throwable cause) {
        super(message, cause);
    }
}


