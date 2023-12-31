package com.api.employment.domain.jobposting.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JobPostingUpdateRequestDTO {

    @NotNull(message = "채용공고 id는 필수입니다.")
    @JsonProperty("채용공고_id")
    private Long jobPostingId;

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
