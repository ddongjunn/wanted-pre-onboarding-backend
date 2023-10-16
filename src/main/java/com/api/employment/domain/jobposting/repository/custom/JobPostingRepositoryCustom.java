package com.api.employment.domain.jobposting.repository.custom;

import com.api.employment.domain.jobposting.model.JobPostingGetDetailResponseDTO;
import com.api.employment.domain.jobposting.model.JobPostingGetResponseDTO;

import java.util.List;

public interface JobPostingRepositoryCustom {
    public List<JobPostingGetResponseDTO> findJobPostingWithKeyword(String keyword);

    public JobPostingGetDetailResponseDTO findJobPostingDetail(Long id);
}
