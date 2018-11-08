package workerboard.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler({RegistrationNotAddException.class,ApplicationNotFound.class,
    ApplicationWrongPassword.class, ApplicationToMuchArguments.class})

    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();


        if (ex instanceof RegistrationNotAddException) {
            HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
            RegistrationNotAddException registrationNotAddException = (RegistrationNotAddException) ex;

            return handleRegistrationNotAddException(registrationNotAddException, headers, status, request);

        }
        if (ex instanceof ApplicationNotFound) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            ApplicationNotFound applicationNotFound = (ApplicationNotFound) ex;

            return handleNotFoundException(applicationNotFound, headers, status, request);

        }
        if (ex instanceof ApplicationWrongPassword) {
            HttpStatus status = HttpStatus.NOT_MODIFIED;
            ApplicationWrongPassword applicationNotFound = (ApplicationWrongPassword) ex;

            return handleWrongPasswordException(applicationNotFound,headers, status, request);
        }

        if (ex instanceof ApplicationToMuchArguments) {
            HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
            ApplicationToMuchArguments appex = (ApplicationToMuchArguments) ex;

            return handleApplicationToMuchArgument(appex,headers, status, request);
        }


        /* "There you should add new type of exception, coming from yours controller */
        else {

            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionOther(ex, null, headers, status, request);
        }

    }


    /* "There you should add new handle method of exception, coming from yours controller */

    protected ResponseEntity<ApiError> handleRegistrationNotAddException(RegistrationNotAddException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionOther(ex, new ApiError(errors), headers, status, request);
    }
    protected ResponseEntity<ApiError> handleApplicationToMuchArgument(ApplicationToMuchArguments ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionOther(ex, new ApiError(errors), headers, status, request);
    }

    protected ResponseEntity<ApiError> handleNotFoundException(ApplicationNotFound ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionOther(ex, new ApiError(errors), headers, status, request);
    }
    protected ResponseEntity<ApiError> handleWrongPasswordException(ApplicationWrongPassword ex,
                                                               HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionOther(ex, new ApiError(errors), headers, status, request);
    }

    protected ResponseEntity<ApiError> handleExceptionOther(Exception ex, ApiError body,
                                                            HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }

}
