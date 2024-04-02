package org.seydaliev.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.seydaliev.repository.TimeTrackingRepository;
import org.seydaliev.service.TimeTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "API для отслеживания времени выполнения методов", description = "Операции для получения статистики по времени выполнения методов")
@RestController
public class TimeTrackingController {
    @Autowired
    private  TimeTrackingService service;
    @Autowired
    private TimeTrackingRepository repository;

    @ApiOperation(value = "Получение статистики по времени выполнения методов", notes = "Возвращает статистику по времени выполнения методов в формате JSON")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение статистики"),
            @ApiResponse(code = 500, message = "Ошибка при получении статистики")
    })
    @GetMapping("/statistics")
    public String getStatistics() throws JsonProcessingException {
        List<Object[]> results = repository.findAverageExecutionTimeByMethodName();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> statistics = new ArrayList<>();
        for (Object[] result : results) {
            Map<String, Object> stat = new HashMap<>();
            stat.put("methodName", result[0]);
            stat.put("averageExecutionTime", result[1]);
            statistics.add(stat);
        }
        return objectMapper.writeValueAsString(statistics);
    }
}
