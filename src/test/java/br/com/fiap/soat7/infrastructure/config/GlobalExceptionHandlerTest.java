package br.com.fiap.soat7.infrastructure.config;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.Locale;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @Mock
    private MessageSource messageSource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        exceptionHandler = new GlobalExceptionHandler(messageSource);
    }

    @Test
    void handleHttpMessageNotReadableException_withInvalidEnumFormat() {
        // Configuração do mock
        InvalidFormatException ife = mock(InvalidFormatException.class);
        when(ife.getTargetType()).thenAnswer(invocation -> TestEnum.class);
        when(messageSource.getMessage(eq(GlobalExceptionHandler.INVALID_CATEGORY_VALUE), any(), any(Locale.class)))
                .thenReturn("Invalid category value. Allowed values: ");

        HttpMessageNotReadableException exception = mock(HttpMessageNotReadableException.class);
        when(exception.getRootCause()).thenReturn(ife);

        // Execução
        ResponseEntity<String> response = exceptionHandler.handleHttpMessageNotReadableException(exception);

        // Verificações
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid category value. Allowed values: [VALUE1, VALUE2]", response.getBody());
    }

    @Test
    void handleHttpMessageNotReadableException_withGeneralError() {
        // Configuração do mock
        HttpMessageNotReadableException exception = mock(HttpMessageNotReadableException.class);
        when(exception.getRootCause()).thenReturn(new RuntimeException());
        when(messageSource.getMessage(eq(GlobalExceptionHandler.BAD_REQUEST), any(), any(Locale.class)))
                .thenReturn("Bad request");

        // Execução
        ResponseEntity<String> response = exceptionHandler.handleHttpMessageNotReadableException(exception);

        // Verificações
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Bad request", response.getBody());
    }

    @Test
    void handleNoSuchElementException() {
        // Configuração do mock
        NoSuchElementException exception = new NoSuchElementException("Element not found");

        // Execução
        ResponseEntity<String> response = exceptionHandler.handleNoSuchElementException(exception);

        // Verificações
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Element not found", response.getBody());
    }

    // Enum de teste para simular o caso com valores permitidos
    public enum TestEnum {
        VALUE1, VALUE2
    }
}
