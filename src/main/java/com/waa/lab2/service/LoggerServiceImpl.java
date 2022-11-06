package com.waa.lab2.service;

import com.waa.lab2.domain.Logger;
import com.waa.lab2.repo.LoggerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggerServiceImpl implements LoggerService{
    @Autowired
    private LoggerRepo loggerRepo;
    @Override
    public void logEvent(Logger logger) {
        loggerRepo.save(logger);
    }
}
