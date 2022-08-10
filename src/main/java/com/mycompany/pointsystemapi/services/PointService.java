package com.mycompany.pointsystemapi.services;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.mycompany.pointsystemapi.models.PointModel;
import com.mycompany.pointsystemapi.repositories.PointRepository;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository;

    @Transactional
    public PointModel save(PointModel pointModel) {
        return pointRepository.save(pointModel);
    }

    public Optional<PointModel> findById(UUID id) {
        return pointRepository.findById(id);
    }

    public Page<PointModel> findAll(Pageable pageable) {
        return pointRepository.findAll(pageable);
    }

    @Transactional
    public void delete(PointModel pointModel) {
        pointRepository.delete(pointModel);
    }

    public boolean existsByEmployeeAndDate(String employee, LocalDate date) {
        return pointRepository.existsByEmployeeAndDate(employee, date);
    }
    
}
