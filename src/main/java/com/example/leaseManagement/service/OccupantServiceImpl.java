package com.example.leaseManagement.service;

import com.example.leaseManagement.dao.OccupantDAO;
import com.example.leaseManagement.entity.Occupant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OccupantServiceImpl implements OccupantService {

    private final OccupantDAO occupantDAO;

    public OccupantServiceImpl(OccupantDAO occupantDAO) {
        this.occupantDAO = occupantDAO;
    }

    @Override
    @Transactional
    public Occupant saveOccupant(Occupant occupant) {
        occupantDAO.save(occupant);
        return occupant;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Occupant> getOccupantById(Long id) {
        return occupantDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Occupant> getAllOccupants() {
        return occupantDAO.findAll();
    }

    @Override
    @Transactional
    public Occupant updateOccupant(Occupant occupant) {
        occupantDAO.update(occupant);
        return occupant;
    }

    @Override
    @Transactional
    public void deleteOccupant(Long id) {
        occupantDAO.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Occupant> getOccupantsByUnitId(Long unitId) {
        return occupantDAO.findByUnitId(unitId);
    }
}