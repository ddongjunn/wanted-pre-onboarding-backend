package com.api.employment.domain.employment.service;

import com.api.employment.domain.employment.entity.JobPosting;
import com.api.employment.domain.employment.model.JobPostingSaveRequestDTO;
import com.api.employment.domain.employment.model.JobPostingResponseDTO;
import com.api.employment.domain.employment.model.JobPostingUpdateRequestDTO;
import com.api.employment.domain.employment.repository.JobPostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;

    @Transactional
    public JobPostingResponseDTO save(JobPostingSaveRequestDTO jobPostingSaveRequestDTO){
        jobPostingRepository.save(jobPostingSaveRequestDTO.toEntity());
        return new JobPostingResponseDTO("성공적으로 저장되었습니다.");
    }

    @Transactional
    public JobPostingResponseDTO update(JobPostingUpdateRequestDTO jobPostingUpdateRequestDTO){
        JobPosting entity = jobPostingRepository.findJobPostingByCompanyId(jobPostingUpdateRequestDTO.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException("회사 아이디가 존재하지 않습니다."));

        entity.update(jobPostingUpdateRequestDTO.getJobPosition(), jobPostingUpdateRequestDTO.getCompensation(), jobPostingUpdateRequestDTO.getJobDetail(), jobPostingUpdateRequestDTO.getTechnologiesUsed());
        return new JobPostingResponseDTO("성공적으로 수정되었습니다.");
    }
}
