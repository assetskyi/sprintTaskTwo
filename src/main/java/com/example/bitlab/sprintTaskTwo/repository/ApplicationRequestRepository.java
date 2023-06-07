package com.example.bitlab.sprintTaskTwo.repository;

import com.example.bitlab.sprintTaskTwo.model.ApplicationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRequestRepository extends JpaRepository<ApplicationRequest,Long> {
    public List<ApplicationRequest> findApplicationRequestByHandledIsFalse();
    public List<ApplicationRequest> findApplicationRequestByHandledIsTrue();
}
