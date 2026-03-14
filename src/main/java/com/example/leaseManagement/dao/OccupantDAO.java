package com.example.leaseManagement.dao;

import com.example.leaseManagement.entity.Occupant;
import java.util.List;
import java.util.Optional;

public interface OccupantDAO {

    void save(Occupant occupant);

    Optional<Occupant> findById(Long id);

    List<Occupant> findAll();

    void update(Occupant occupant);

    void delete(Long id);

    List<Occupant> findByUnitId(Long unitId);
}