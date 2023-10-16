package com.api.employment.domain.jobposting.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobPostingGetResponseDTO {

    @JsonProperty("채용공고_id")
    private Long id;

    @JsonProperty("회사명")
    private String companyName;

    @JsonProperty("국가")
    private String country;

    @JsonProperty("지역")
    private String region;

    @JsonProperty("채용포지션")
    private String jobPosition;

    @JsonProperty("채용보상금")
    private int compensation;

    @JsonProperty("사용기술")
    private String technologiesUsed;
}
