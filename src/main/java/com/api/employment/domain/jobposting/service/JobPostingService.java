package com.api.employment.domain.jobposting.service;

import com.api.employment.domain.common.error.SuccesCode;
import com.api.employment.domain.common.error.exception.CustomException;
import com.api.employment.domain.jobposting.entity.JobPosting;
import com.api.employment.domain.jobposting.model.JobPostingSaveRequestDTO;
import com.api.employment.domain.jobposting.model.JobPostingResponseDTO;
import com.api.employment.domain.jobposting.model.JobPostingUpdateRequestDTO;
import com.api.employment.domain.jobposting.repository.JobPostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.api.employment.domain.common.error.ErrorCode;


@Service
@RequiredArgsConstructor
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;

    @Transactional
    public JobPostingResponseDTO save(JobPostingSaveRequestDTO jobPostingSaveRequestDTO){
        jobPostingRepository.save(jobPostingSaveRequestDTO.toEntity());
        return new JobPostingResponseDTO(SuccesCode.SAVE_SUCCESSFUL.getMessage());
    }

    @Transactional
    public JobPostingResponseDTO update(JobPostingUpdateRequestDTO jobPostingUpdateRequestDTO){
        JobPosting entity = jobPostingRepository.findJobPostingByCompanyId(jobPostingUpdateRequestDTO.getCompanyId())
                .orElseThrow(() -> new CustomException(ErrorCode.COMPANY_ID_NOT_FOUND));

        entity.update(jobPostingUpdateRequestDTO.getJobPosition(), jobPostingUpdateRequestDTO.getCompensation(), jobPostingUpdateRequestDTO.getJobDetail(), jobPostingUpdateRequestDTO.getTechnologiesUsed());
        return new JobPostingResponseDTO(SuccesCode.UPDATE_SUCCESSFUL.getMessage());
    }
}
