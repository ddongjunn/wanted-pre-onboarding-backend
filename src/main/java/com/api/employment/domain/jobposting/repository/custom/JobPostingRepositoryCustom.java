package com.api.employment.domain.jobposting.repository.custom;

import com.api.employment.domain.jobposting.entity.JobPosting;
import com.api.employment.domain.jobposting.model.JobPostingGetResponseDTO;

import java.util.List;

public interface JobPostingRepositoryCustom {
    public List<JobPostingGetResponseDTO> findJobPostingWithKeyword(String keyword);
}
