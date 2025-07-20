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
    public List<PowerStation> getAllPowerrStations() {
        return powerStationService.getAllPowerStations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PowerStation> getPowerStationById(@PathVariable Long id) {
        PowerStation station = powerStationService.getPowerStationById(id);
        if (station != null) {
            return ResponseEntity.ok(station);
        } else {
            return ResponseEntity.notFound().build();
        }
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