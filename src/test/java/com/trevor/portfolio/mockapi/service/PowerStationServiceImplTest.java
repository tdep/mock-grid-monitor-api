package com.trevor.portfolio.mockapi.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.trevor.portfolio.mockapi.exception.ResourceNotFoundException;
import com.trevor.portfolio.mockapi.model.PowerStation;
import com.trevor.portfolio.mockapi.repository.PowerStationRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class PowerStationServiceImplTest {
    @Mock
    private PowerStationRepository powerStationRepository;

    @InjectMocks
    private PowerStationServiceImpl powerStationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPowerStationNyId_notFound() {
        when(powerStationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            powerStationService.getPowerStationById(1L);
        });
    }

    @Test
    void getPowerStationById_shouldReturnPowerStation_whenIdExists() {
        PowerStation powerStation = new PowerStation(1L, "Test Station", 100.0, "Western New York", "Active");
        when(powerStationRepository.findById(1L)).thenReturn(Optional.of(powerStation));

        PowerStation result = powerStationService.getPowerStationById(1L);

        assertEquals("Test Station", result.getName());
        verify(powerStationRepository, times(1)).findById(1L);
    }

    @Test
    void getPowerStationById_shouldThrowException_whenIdNotFound() {
        when(powerStationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> powerStationService.getPowerStationById(1L));
        verify(powerStationRepository, times(1)).findById(1L);
    }

    @Test
    void createPowerStation_shouldSaveAndReturnPowerStation() {
        PowerStation powerStation = new PowerStation(null, "New Station", 2000.3, "Central Pennsylvania", "Innactive");
        PowerStation savedStation = new PowerStation(1L, "New Station", 2000.3, "Central Pennsylvania", "Innactive");

        when(powerStationRepository.save(powerStation)).thenReturn(savedStation);

        PowerStation result = powerStationService.createPowerStation(powerStation);

        assertEquals(1L, result.getId());
        assertEquals("New Station", result.getName());
        verify(powerStationRepository, times(1)).save(powerStation);
    }

    @Test
    void updatePowerStation_shouldUpdateAndReturnPowerStation_whenIdExists() {
        PowerStation existing = new PowerStation(1L, "Old Station", 100.0, "Eastern Ohio", "Active");
        PowerStation updated = new PowerStation(null, "Updated Station", 150.0, "Eastern Ohio", "Active");
        PowerStation saved = new PowerStation(1L, "Updated Station", 150.0, "Eastern Ohio", "Active");

        when(powerStationRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(powerStationRepository.save(any(PowerStation.class))).thenReturn(saved);

        PowerStation result = powerStationService.updatePowerStation(1L, updated);

        assertEquals("Updated Station", result.getName());
        assertEquals(150.0, result.getCapacityMw());
        assertEquals("Eastern Ohio", result.getRegion());
        assertEquals("Active", result.getStatus());
    }

    @Test
    void updatePowerStation_shouldThrowException_whenIdNotFound() {
        PowerStation updated = new PowerStation(null, "Updated Station", 100.0, "Central Maine", "Innactive");
        when(powerStationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
            powerStationService.updatePowerStation(1L, updated));
    }

    @Test
    void deletePowerStation_shouldThrowException_whenIdNotFound() {
        when(powerStationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
            powerStationService.deletePowerStation(1L));
    }
}