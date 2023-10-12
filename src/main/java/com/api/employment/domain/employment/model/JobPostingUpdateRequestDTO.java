package com.api.employment.domain.employment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JobPostingUpdateRequestDTO {

    @NotNull(message = "회사 id는 필수입니다.")
    @JsonProperty("회사_id")
    private Long companyId;

    @JsonProperty("채용포지션")
    private String jobPosition;

    @Min(value = 0, message = "채용보상금은 0 이상이어야 합니다.")
    @JsonProperty("채용보상금")
    private int compensation;

    @JsonProperty("채용내용")
    private String jobDetail;

    @JsonProperty("사용기술")
    private String technologiesUsed;
}
