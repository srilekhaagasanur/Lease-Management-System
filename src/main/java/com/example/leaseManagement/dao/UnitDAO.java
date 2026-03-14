package com.example.leaseManagement.dao;

import com.example.leaseManagement.entity.Unit;
import java.util.List;
import java.util.Optional;

public interface UnitDAO {

    void save(Unit unit);

    Optional<Unit> findById(Long id);

    List<Unit> findAll();

    void update(Unit unit);

    void delete(Long id);

    List<Unit> findByPropertyId(Long propertyId);

    List<Unit> findByStatus(Unit.UnitStatus status);
}