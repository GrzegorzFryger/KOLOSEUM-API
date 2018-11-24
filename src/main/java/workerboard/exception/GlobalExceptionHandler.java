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
public class GlobalExceptionHandler {
    @ExceptionHandler({SingUpNotAddException.class, NotFound.class,
    UserWrongPassword.class, WrongTypeArguments.class})

    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();


        if (ex instanceof SingUpNotAddException) {
            HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
            SingUpNotAddException singUpNotAddException = (SingUpNotAddException) ex;

            return handleRegistrationNotAddException(singUpNotAddException, headers, status, request);

        }
        if (ex instanceof NotFound) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            NotFound notFound = (NotFound) ex;

            return handleNotFoundException(notFound, headers, status, request);

        }
        if (ex instanceof UserWrongPassword) {
            HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
            UserWrongPassword applicationNotFound = (UserWrongPassword) ex;

            return handleWrongPasswordException(applicationNotFound,headers, status, request);
        }

        if (ex instanceof WrongTypeArguments) {
            HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
            WrongTypeArguments appex = (WrongTypeArguments) ex;

            return handleApplicationToMuchArgument(appex,headers, status, request);
        }


        /* "There you should add new type of exception, coming from yours controller */
        else {

            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionOther(ex, null, headers, status, request);
        }

    }


    /* "There you should add new handle method of exception, coming from yours controller */

    protected ResponseEntity<ApiError> handleRegistrationNotAddException(SingUpNotAddException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionOther(ex, new ApiError(errors), headers, status, request);
    }
    protected ResponseEntity<ApiError> handleApplicationToMuchArgument(WrongTypeArguments ex,
                                                                       HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionOther(ex, new ApiError(errors), headers, status, request);
    }

    protected ResponseEntity<ApiError> handleNotFoundException(NotFound ex,
                                                               HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionOther(ex, new ApiError(errors), headers, status, request);
    }
    protected ResponseEntity<ApiError> handleWrongPasswordException(UserWrongPassword ex,
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
