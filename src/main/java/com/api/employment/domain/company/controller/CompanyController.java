package com.api.employment.domain.company.controller;

import com.api.employment.common.logging.LoggableController;
import com.api.employment.domain.company.model.CompanySaveRequestDTO;
import com.api.employment.domain.company.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("company")
@RequiredArgsConstructor
@LoggableController
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody CompanySaveRequestDTO companySaveRequestDTO){
        return ResponseEntity.ok().body(companyService.save(companySaveRequestDTO));
    }
}
