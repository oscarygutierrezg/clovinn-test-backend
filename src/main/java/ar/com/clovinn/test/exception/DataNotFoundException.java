package ar.com.clovinn.test.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7829075154657265455L;
	
    public DataNotFoundException(String message) {
        super(message);
    }

}
