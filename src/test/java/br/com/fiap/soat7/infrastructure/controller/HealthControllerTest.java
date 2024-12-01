package br.com.fiap.soat7.infrastructure.controller;

import br.com.fiap.soat7.infrastructure.controllers.HealthController;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HealthControllerTest {


    @Test
    void readiness_deveRetornarOk() {
        HealthController healthController = new HealthController(); // Instanciar o controller diretamente
        ResponseEntity<Map<String, String>> response = healthController.readiness();
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("ok", response.getBody().get("status"));

    }
}