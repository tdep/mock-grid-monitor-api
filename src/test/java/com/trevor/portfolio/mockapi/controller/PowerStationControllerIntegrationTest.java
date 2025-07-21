package com.trevor.portfolio.mockapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trevor.portfolio.mockapi.model.PowerStation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PowerStationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreatePowerrStation() throws Exception {
        PowerStation ps = new PowerStation();
        ps.setName("Timmy's Magic Nuclear Reactor");
        ps.setCapacityMw(1000000.3);
        ps.setRegion("North of France");
        ps.setStatus("Active");

        mockMvc.perform(post("/api/powerstations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ps)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value("Timmy's Magic Nuclear Reactor"))
            .andExpect(jsonPath("$.capacityMw").value(1000000.3))
            .andExpect(jsonPath("$.region").value("North of France"))
            .andExpect(jsonPath("$.status").value("Active"));
    }
}