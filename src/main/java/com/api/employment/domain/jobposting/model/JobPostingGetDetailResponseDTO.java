package com.api.employment.domain.jobposting.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JobPostingGetDetailResponseDTO {

    public JobPostingGetDetailResponseDTO(Long jobPostingId, String companyName, String country, String region, String jobPosition, int compensation, String technologiesUsed){
        this.jobPostingId = jobPostingId;
        this.companyName = companyName;
        this.country = country;
        this.region = region;
        this.jobPosition = jobPosition;
        this.compensation = compensation;
        this.technologiesUsed = technologiesUsed;
    }

    @JsonProperty("채용공고_id")
    private Long jobPostingId;

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

    @JsonProperty("회사가올린다른채용공고")
    private List<Long> otherJobPostingsIdByCompany;
}
