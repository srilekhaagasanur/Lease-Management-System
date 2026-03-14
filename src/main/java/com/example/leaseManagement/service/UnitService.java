package com.example.leaseManagement.service;

import com.example.leaseManagement.entity.Unit;
import java.util.List;
import java.util.Optional;

public interface UnitService {

    Unit saveUnit(Unit unit);

    Optional<Unit> getUnitById(Long id);

    List<Unit> getAllUnits();

    Unit updateUnit(Unit unit);

    void deleteUnit(Long id);

    List<Unit> getUnitsByPropertyId(Long propertyId);

    List<Unit> getUnitsByStatus(Unit.UnitStatus status);
}