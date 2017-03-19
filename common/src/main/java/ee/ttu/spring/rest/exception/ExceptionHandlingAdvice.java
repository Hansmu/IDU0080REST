package ee.ttu.spring.rest.exception;

import ee.ttu.spring.rest.domain.common.Result;
import ee.ttu.spring.rest.exception.exceptions.InvalidDataException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hans on 12.03.2017.
 */
@ControllerAdvice
@RestController
public class ExceptionHandlingAdvice {

    @ExceptionHandler(Exception.class)
    public Result exception(Exception ex) {
        return Result.nok("Technical error.");
    }

    @ExceptionHandler(InvalidDataException.class)
    public Result invalidDataException(InvalidDataException e) {
        return Result.nok(e.getMessage());
    }

}
