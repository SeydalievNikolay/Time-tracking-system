package org.seydaliev.service.impl;

import org.seydaliev.model.TimeTrackingEntity;
import org.seydaliev.repository.TimeTrackingRepository;
import org.seydaliev.service.TimeTrackingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TimeTrackingServiceImpl implements TimeTrackingService {
    private final TimeTrackingRepository repository;

    public TimeTrackingServiceImpl(TimeTrackingRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void saveExecutionTime(String methodName, long executionTime) {
        TimeTrackingEntity entity = new TimeTrackingEntity();
        entity.setMethodName(methodName);
        entity.setExecutionTime(executionTime);
        repository.save(entity);
    }
}
