package com.api.employment.domain.employment.repository;

import com.api.employment.domain.employment.entity.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
    Optional<JobPosting> findJobPostingByCompanyId(Long id);
}
