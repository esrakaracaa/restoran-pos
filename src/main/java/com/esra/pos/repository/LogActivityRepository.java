package com.esra.pos.repository;

import com.esra.pos.model.LogActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogActivityRepository extends JpaRepository<LogActivity, Long> {
}