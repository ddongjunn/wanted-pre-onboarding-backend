package com.api.employment.domain.jobposting.repository;

import com.api.employment.domain.jobposting.entity.JobPosting;
import com.api.employment.domain.jobposting.entity.JobPostingApplicant;
import com.api.employment.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostingApplicantRepository extends JpaRepository<JobPostingApplicant, Long> {
    boolean existsByJobPostingAndMember(JobPosting jobPosting, Member member);
}
