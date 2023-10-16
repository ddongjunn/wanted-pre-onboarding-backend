package com.api.employment.domain.jobposting.service;

import com.api.employment.domain.common.error.SuccesCode;
import com.api.employment.domain.common.error.exception.CustomException;
import com.api.employment.domain.company.entity.Company;
import com.api.employment.domain.company.repository.CompanyRepository;
import com.api.employment.domain.jobposting.entity.JobPosting;
import com.api.employment.domain.jobposting.model.*;
import com.api.employment.domain.jobposting.repository.JobPostingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.api.employment.domain.common.error.ErrorCode;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public JobPostingResponseDTO save(JobPostingSaveRequestDTO jobPostingSaveRequestDTO){
        Company company = companyRepository.findById(jobPostingSaveRequestDTO.getCompanyId())
                .orElseThrow(() -> new CustomException(ErrorCode.COMPANY_ID_NOT_FOUND));

        JobPosting jobPosting = jobPostingSaveRequestDTO.toEntity();
        jobPosting.linkCompany(company);
        jobPostingRepository.save(jobPosting);

        return new JobPostingResponseDTO(SuccesCode.SAVE_SUCCESSFUL.getMessage());
    }

    @Transactional
    public JobPostingResponseDTO update(JobPostingUpdateRequestDTO jobPostingUpdateRequestDTO){
        JobPosting jobPosting = jobPostingRepository.findById(jobPostingUpdateRequestDTO.getJobPostingId())
                .orElseThrow(() -> new CustomException(ErrorCode.JOB_POSTING_ID_NOT_FOUND));

        jobPosting.update(jobPostingUpdateRequestDTO.getJobPosition(), jobPostingUpdateRequestDTO.getCompensation(), jobPostingUpdateRequestDTO.getJobDetail(), jobPostingUpdateRequestDTO.getTechnologiesUsed());
        return new JobPostingResponseDTO(SuccesCode.UPDATE_SUCCESSFUL.getMessage());
    }

    public JobPostingResponseDTO delete(JobPostingDeleteRequestDTO jobPostingDeleteRequestDTO) {
        JobPosting jobPosting = jobPostingRepository.findById(jobPostingDeleteRequestDTO.getJobPostingId())
                .orElseThrow(() -> new CustomException(ErrorCode.JOB_POSTING_ID_NOT_FOUND));
        jobPostingRepository.delete(jobPosting);
        return new JobPostingResponseDTO(SuccesCode.DELETE_SUCCESSFUL.getMessage());
    }

    public List<JobPostingGetResponseDTO> get(String search) {
        List<JobPostingGetResponseDTO> jobPostingWithKeyword = jobPostingRepository.findJobPostingWithKeyword(search);
        return jobPostingWithKeyword;
    }
}
