package com.api.employment.domain.company.model;

import com.api.employment.domain.company.entity.Company;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CompanySaveRequestDTO {

    @NotNull(message = "회사 이름은 필수입니다.")
    private String name;

    public Company toEntity(){
        return Company.builder().companyName(this.name).build();
    }
}
