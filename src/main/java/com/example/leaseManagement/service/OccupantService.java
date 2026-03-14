package com.example.leaseManagement.service;

import com.example.leaseManagement.entity.Occupant;
import java.util.List;
import java.util.Optional;

public interface OccupantService {

    Occupant saveOccupant(Occupant occupant);

    Optional<Occupant> getOccupantById(Long id);

    List<Occupant> getAllOccupants();

    Occupant updateOccupant(Occupant occupant);

    void deleteOccupant(Long id);

    List<Occupant> getOccupantsByUnitId(Long unitId);
}