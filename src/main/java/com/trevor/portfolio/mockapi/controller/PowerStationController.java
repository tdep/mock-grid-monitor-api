package com.trevor.portfolio.mockapi.controller;

import com.trevor.portfolio.mockapi.model.PowerStation;
import com.trevor.portfolio.mockapi.service.PowerStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<PowerStation> getAllPowerStations() {
        return powerStationService.getAllPowerStations();
    }

    @GetMapping("/{id}")
    public PowerStation getPowerStationById(@PathVariable Long id) {
        return powerStationService.getPowerStationById(id);
    }

    @PostMapping
    public PowerStation createPowerStation(@RequestBody PowerStation station) {
        return powerStationService.createPowerStation(station);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePowerStation(@PathVariable Long id) {
        powerStationService.deletePowerStation(id);
        return ResponseEntity.noContent().build();
    }
}