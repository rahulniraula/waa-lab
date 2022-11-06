package com.waa.lab2.repo;

import com.waa.lab2.domain.Logger;
import org.springframework.data.repository.CrudRepository;

public interface LoggerRepo extends CrudRepository<Logger,Long> {
}
