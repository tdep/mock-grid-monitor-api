package com.trevor.portfolio.mockapi.service;

import com.trevor.portfolio.mockapi.exception.ResourceNotFoundException;
import com.trevor.portfolio.mockapi.model.PowerStation;
import com.trevor.portfolio.mockapi.repository.PowerStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PowerStationServiceImpl implements PowerStationService {

    private final PowerStationRepository repository;

    @Autowired
    public PowerStationServiceImpl(PowerStationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PowerStation> getAllPowerStations() {
        return repository.findAll();
    }

    @Override 
    public PowerStation getPowerStationById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> 
                    new ResourceNotFoundException("Powerstation not found with id: " + id
                ));
    }

    @Override
    public PowerStation createPowerStation(PowerStation station) {
        return repository.save(station);
    }

    @Override
    public void deletePowerStation(Long id) {
        repository.deleteById(id);
    }
}