package com.api.employment.domain.jobposting.service;

import com.api.employment.common.domain.ResponseMessage;
import com.api.employment.common.error.SuccesCode;
import com.api.employment.common.error.exception.CustomException;
import com.api.employment.domain.company.entity.Company;
import com.api.employment.domain.company.repository.CompanyRepository;
import com.api.employment.domain.jobposting.entity.JobPosting;
import com.api.employment.domain.jobposting.model.*;
import com.api.employment.domain.jobposting.repository.JobPostingApplicantRepository;
import com.api.employment.domain.jobposting.repository.JobPostingRepository;
import com.api.employment.domain.member.entity.Member;
import com.api.employment.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.api.employment.common.error.ErrorCode;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobPostingService {

    private final JobPostingApplicantRepository jobPostingApplicantRepository;
    private final JobPostingRepository jobPostingRepository;
    private final CompanyRepository companyRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ResponseMessage save(JobPostingSaveRequestDTO jobPostingSaveRequestDTO){
        Company company = companyRepository.getById(jobPostingSaveRequestDTO.getCompanyId());

        JobPosting jobPosting = jobPostingSaveRequestDTO.toEntity();
        jobPosting.linkCompany(company);
        jobPostingRepository.save(jobPosting);

        return new ResponseMessage(SuccesCode.SAVE_SUCCESSFUL.getMessage());
    }

    @Transactional
    public ResponseMessage update(JobPostingUpdateRequestDTO jobPostingUpdateRequestDTO){
        JobPosting jobPosting = jobPostingRepository.getById(jobPostingUpdateRequestDTO.getJobPostingId());
        jobPosting.update(jobPostingUpdateRequestDTO.getJobPosition(), jobPostingUpdateRequestDTO.getCompensation(), jobPostingUpdateRequestDTO.getJobDetail(), jobPostingUpdateRequestDTO.getTechnologiesUsed());
        return new ResponseMessage(SuccesCode.UPDATE_SUCCESSFUL.getMessage());
    }

    public ResponseMessage delete(Long id) {
        JobPosting jobPosting = jobPostingRepository.getById(id);
        jobPostingRepository.delete(jobPosting);
        return new ResponseMessage(SuccesCode.DELETE_SUCCESSFUL.getMessage());
    }

    public List<JobPostingGetResponseDTO> get(String search) {
        return jobPostingRepository.findJobPostingWithKeyword(search);
    }

    public JobPostingGetDetailResponseDTO getDetail(Long id) {
        boolean existsJobPosting = jobPostingRepository.existsById(id);
        if(!existsJobPosting){
            throw new CustomException(ErrorCode.JOB_POSTING_ID_NOT_FOUND);
        }

        JobPostingGetDetailResponseDTO  jobPostingGetDetailResponseDTO = jobPostingRepository.findJobPostingDetail(id);

        String companyName = jobPostingGetDetailResponseDTO.getCompanyName();
        jobPostingGetDetailResponseDTO.setOtherJobPostingsIdByCompany(getOtherJobPostingList(companyName));

        return jobPostingGetDetailResponseDTO;
    }

    public ResponseMessage applyJobPosting(JobPostingApplyRequestDTO jobPostingApplyRequestDTO){
        JobPosting jobPosting = jobPostingRepository.getById(jobPostingApplyRequestDTO.getJobPostingId());
        Member member = memberRepository.getById(jobPostingApplyRequestDTO.getMemberId());

        boolean isAlreadyApplied = jobPostingApplicantRepository.existsByJobPostingAndMember(jobPosting, member);
        if(isAlreadyApplied){
            throw new CustomException(ErrorCode.ALREADY_APPLIED);
        }

        jobPostingApplicantRepository.save(jobPostingApplyRequestDTO.toEntity(jobPosting, member));
        return new ResponseMessage(SuccesCode.SAVE_SUCCESSFUL.getMessage());
    }
    
    public List<Long> getOtherJobPostingList(String companyName){
        return jobPostingRepository.findIdJobPostingByCompanyId(companyName);
    }
}
