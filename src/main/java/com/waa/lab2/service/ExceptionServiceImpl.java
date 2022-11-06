package com.waa.lab2.service;

import com.waa.lab2.domain.Exception;
import com.waa.lab2.repo.ExceptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExceptionServiceImpl implements ExceptionService{
    @Autowired
    private ExceptionRepo exceptionRepo;
    @Override
    public void save(Exception exception) {
        exceptionRepo.save(exception);
    }
}
