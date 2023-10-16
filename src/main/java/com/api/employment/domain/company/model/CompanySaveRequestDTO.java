package com.api.employment.domain.company.model;

import com.api.employment.domain.company.entity.Company;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CompanySaveRequestDTO {

    @NotNull(message = "회사 이름은 필수입니다.")
    @JsonProperty("회사이름")
    private String name;

    @JsonProperty("지역")
    private String region;

    @JsonProperty("국가")
    private String country;

    public Company toEntity(){
        return Company.builder().
                companyName(this.name)
                .region(this.region)
                .country(this.country).build();
    }
}
