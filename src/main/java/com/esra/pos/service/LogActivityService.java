package com.esra.pos.service;

import com.esra.pos.model.LogActivity;
import com.esra.pos.repository.LogActivityRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LogActivityService {

    private final LogActivityRepository logActivityRepository;

    public LogActivityService(LogActivityRepository logActivityRepository) {
        this.logActivityRepository = logActivityRepository;
    }

    public List<LogActivity> getAllLogs() {
        return logActivityRepository.findAll();
    }

    public LogActivity createLog(LogActivity logActivity) {
        return logActivityRepository.save(logActivity);
    }
}