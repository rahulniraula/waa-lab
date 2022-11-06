package com.waa.lab2.repo;

import com.waa.lab2.domain.Exception;
import org.springframework.data.repository.CrudRepository;

public interface ExceptionRepo extends CrudRepository<Exception,Long> {
}
