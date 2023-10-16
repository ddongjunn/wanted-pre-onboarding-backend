package com.api.employment.domain.jobposting.repository;

import com.api.employment.domain.jobposting.entity.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
}
