package com.example.leaseManagement.service;

import com.example.leaseManagement.dao.UnitDAO;
import com.example.leaseManagement.entity.Unit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UnitServiceImpl implements UnitService {

    private final UnitDAO unitDAO;

    public UnitServiceImpl(UnitDAO unitDAO) {
        this.unitDAO = unitDAO;
    }

    @Override
    @Transactional
    public Unit saveUnit(Unit unit) {
        unitDAO.save(unit);
        return unit;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Unit> getUnitById(Long id) {
        return unitDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Unit> getAllUnits() {
        return unitDAO.findAll();
    }

    @Override
    @Transactional
    public Unit updateUnit(Unit unit) {
        unitDAO.update(unit);
        return unit;
    }

    @Override
    @Transactional
    public void deleteUnit(Long id) {
        unitDAO.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Unit> getUnitsByPropertyId(Long propertyId) {
        return unitDAO.findByPropertyId(propertyId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Unit> getUnitsByStatus(Unit.UnitStatus status) {
        return unitDAO.findByStatus(status);
    }
}