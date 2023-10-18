package com.api.employment.domain.company.repository;

import com.api.employment.common.error.ErrorCode;
import com.api.employment.common.error.exception.CustomException;
import com.api.employment.domain.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByCompanyName(String name);

    default Company getById(Long id){
        return this.findById(id).orElseThrow(() -> new CustomException(ErrorCode.COMPANY_ID_NOT_FOUND));
    }
}
