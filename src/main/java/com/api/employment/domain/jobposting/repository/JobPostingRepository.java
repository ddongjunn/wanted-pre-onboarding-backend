package com.api.employment.domain.jobposting.repository;

import com.api.employment.domain.jobposting.entity.JobPosting;
import com.api.employment.domain.jobposting.repository.custom.JobPostingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long>, JobPostingRepositoryCustom {

}
