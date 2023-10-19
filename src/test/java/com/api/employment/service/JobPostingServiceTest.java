package com.api.employment.service;


import com.api.employment.common.error.ErrorCode;
import com.api.employment.common.error.exception.CustomException;
import com.api.employment.domain.company.entity.Company;
import com.api.employment.domain.company.repository.CompanyRepository;
import com.api.employment.domain.jobposting.model.JobPostingSaveRequestDTO;
import com.api.employment.domain.jobposting.repository.JobPostingRepository;
import com.api.employment.domain.jobposting.service.JobPostingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JobPostingServiceTest {

    @Mock
    private JobPostingRepository jobPostingRepository;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    JobPostingService jobPostingService;

    @Test
    @DisplayName("채용공고 저장 - 존재하지 않는 회사 ID 예외 발생")
    void saveJobPostingException(){

        Company testCompany = Company.builder().companyName("테스트").country("대한민국").region("서울").build();
        when(companyRepository.findById(anyLong())).thenReturn(Optional.of(testCompany));

        //when
        Throwable throwable = catchThrowable(() -> jobPostingService.save(JobPostingSaveRequestDTO.builder()
                .companyId(5L)
                .jobPosition("PM")
                .compensation(1000)
                .jobDetail("프론트 개발자")
                .technologiesUsed("javascript")
                .build()));

        //Then
        assertThat(throwable)
                .isInstanceOf(CustomException.class)
                .hasMessageContaining(ErrorCode.COMPANY_ID_NOT_FOUND.getMessage());
    }
}
