package com.mycompany.pointsystemapi.repositories;

import java.util.UUID;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mycompany.pointsystemapi.models.PointModel;

@Repository
public interface PointRepository extends JpaRepository<PointModel, UUID> {

    boolean existsByEmployeeAndDate(String employee, LocalDate date);

}
