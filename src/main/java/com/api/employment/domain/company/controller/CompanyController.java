package com.api.employment.domain.company.controller;

import com.api.employment.domain.company.model.CompanySaveRequestDTO;
import com.api.employment.domain.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("company")
@RequiredArgsConstructor
@Slf4j
public class CompanyController {

    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<?> save(CompanySaveRequestDTO companySaveRequestDTO){
        return ResponseEntity.ok().body(companyService.save(companySaveRequestDTO));
    }
}
