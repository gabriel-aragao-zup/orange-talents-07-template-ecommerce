package br.com.zup.mercadolivre.shared.config.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorHandle {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ValidationError> handleArgumentNotValidException(MethodArgumentNotValidException exception){
        List<ValidationError> validationErrors = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            String errorMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            ValidationError validationError = new ValidationError(fieldError.getField(), errorMessage);
            validationErrors.add(validationError);
        });
        return validationErrors;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AuthenticationException.class)
    public String handleAuthenticationException(AuthenticationException exception){
        List<ValidationError> validationErrors = new ArrayList<>();
        String message = exception.getMessage();
        return message;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameNotFoundException.class)
    public String handleAuthenticationException(UsernameNotFoundException exception){
        List<ValidationError> validationErrors = new ArrayList<>();
        String message = exception.getMessage();
        return message;
    }
}
