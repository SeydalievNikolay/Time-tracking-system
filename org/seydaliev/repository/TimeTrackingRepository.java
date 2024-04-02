package org.seydaliev.repository;

import org.seydaliev.model.TimeTrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeTrackingRepository extends JpaRepository<TimeTrackingEntity, Long> {
    @Query("SELECT methodName, AVG(executionTime) FROM TimeTrackingEntity GROUP BY methodName")
    List<Object[]> findAverageExecutionTimeByMethodName();
}
