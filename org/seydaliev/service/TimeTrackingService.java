package org.seydaliev.service;

public interface TimeTrackingService {
    void saveExecutionTime(String methodName, long executionTime);
}
