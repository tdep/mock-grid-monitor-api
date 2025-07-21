package com.trevor.portfolio.mockapi.controller;

import com.trevor.portfolio.mockapi.model.PowerStation;
import com.trevor.portfolio.mockapi.service.PowerStationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import jakarta.validation.Valid;

import java.net.http.HttpResponse.ResponseInfo;
import java.util.List;

@RestController
@RequestMapping("/api/powerstations")
public class PowerStationController {
    private final PowerStationService powerStationService;

    @Autowired
    public PowerStationController(PowerStationService powerStationService) {
        this.powerStationService = powerStationService;
    }

    @GetMapping
    public ResponseEntity<List<PowerStation>> getAllPowerStations() {
        List<PowerStation> powerStations = powerStationService.getAllPowerStations();
        return ResponseEntity.ok(powerStations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PowerStation> getPowerStationById(@PathVariable Long id) {
        PowerStation found = powerStationService.getPowerStationById(id);
        return ResponseEntity.ok(found);
    }

    @PostMapping
    public ResponseEntity<PowerStation> createPowerStation(@Valid @RequestBody PowerStation station) {
        PowerStation created = powerStationService.createPowerStation(station);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PowerStation> updatePowerStation(@PathVariable Long id, @Valid @RequestBody PowerStation powerStation) {
            PowerStation updated = powerStationService.updatePowerStation(id, powerStation);
            return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePowerStation(@PathVariable Long id) {
        powerStationService.deletePowerStation(id);
        return ResponseEntity.noContent().build();
    }
}