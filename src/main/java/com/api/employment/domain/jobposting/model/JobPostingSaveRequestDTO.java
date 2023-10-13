package com.api.employment.domain.jobposting.model;

import com.api.employment.domain.jobposting.entity.JobPosting;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class JobPostingSaveRequestDTO {

    @NotNull(message = "회사 id는 필수입니다.")
    @JsonProperty("회사_id")
    private Long companyId;

    @JsonProperty("채용포지션")
    @NotBlank(message = "채용포지션은 필수입니다.")
    private String jobPosition;

    @JsonProperty("채용보상금")
    @Min(value = 0, message = "채용보상금은 0 이상이어야 합니다.")
    private int compensation;

    @JsonProperty("채용내용")
    @NotBlank(message = "채용내용은 필수입니다.")
    private String jobDetail;

    @JsonProperty("사용기술")
    @NotBlank(message = "사용기술은 필수입니다.")
    private String technologiesUsed;

    public JobPosting toEntity(){
        return JobPosting.builder()
                .jobPosition(this.jobPosition)
                .compensation(this.compensation)
                .jobDetail(this.jobDetail)
                .technologiesUsed(this.technologiesUsed).build();
    }

}
