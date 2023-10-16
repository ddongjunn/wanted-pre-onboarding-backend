package com.api.employment.domain.jobposting.repository;

import com.api.employment.domain.jobposting.entity.JobPosting;
import com.api.employment.domain.jobposting.repository.custom.JobPostingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long>, JobPostingRepositoryCustom {

    @Query("SELECT j.id FROM JobPosting j where j.company.companyName = :companyName")
    List<Long> findIdJobPostingByCompanyId(@Param("companyName") String companyName);
}
