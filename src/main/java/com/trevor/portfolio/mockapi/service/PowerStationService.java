package com.trevor.portfolio.mockapi.service;

import com.trevor.portfolio.mockapi.model.PowerStation;

import java.util.List;

public interface PowerStationService {
    List<PowerStation> getAllPowerStations();
    PowerStation getPowerStationById(Long id);
    PowerStation createPowerStation(PowerStation station);
    PowerStation updatePowerStation(Long id, PowerStation updatedStation);
    void deletePowerStation(Long id);
}