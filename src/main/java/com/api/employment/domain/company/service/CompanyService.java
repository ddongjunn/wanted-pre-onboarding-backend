package com.api.employment.domain.company.service;

import com.api.employment.domain.common.error.ErrorCode;
import com.api.employment.domain.common.error.SuccesCode;
import com.api.employment.domain.company.model.CompanyResponseDTO;
import com.api.employment.domain.company.model.CompanySaveRequestDTO;
import com.api.employment.domain.company.repository.CompanyRepository;
import com.api.employment.domain.common.error.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyResponseDTO save(CompanySaveRequestDTO companySaveRequestDTO) {
        companyRepository.findCompanyByCompanyName(companySaveRequestDTO.getName()).orElseThrow(() -> new CustomException(ErrorCode.EXISTING_COMPANY_ERROR));
        companyRepository.save(companySaveRequestDTO.toEntity());
        return new CompanyResponseDTO(SuccesCode.COMPANY_REGISTRATION_SUCCESS.getMessage());
    }
}
