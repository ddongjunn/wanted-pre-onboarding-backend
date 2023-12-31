package com.api.employment.domain.company.service;

import com.api.employment.common.error.ErrorCode;
import com.api.employment.common.error.SuccesCode;
import com.api.employment.domain.company.model.CompanyResponseDTO;
import com.api.employment.domain.company.model.CompanySaveRequestDTO;
import com.api.employment.domain.company.repository.CompanyRepository;
import com.api.employment.common.error.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyResponseDTO save(CompanySaveRequestDTO companySaveRequestDTO) {
        if(companyRepository.existsByCompanyName(companySaveRequestDTO.getName())){
            throw new CustomException(ErrorCode.EXISTING_COMPANY_ERROR);
        }

        companyRepository.save(companySaveRequestDTO.toEntity());
        return new CompanyResponseDTO(SuccesCode.COMPANY_REGISTRATION_SUCCESS.getMessage());
    }
}
