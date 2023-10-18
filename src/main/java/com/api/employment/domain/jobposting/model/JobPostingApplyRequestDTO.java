package com.api.employment.domain.jobposting.model;

import com.api.employment.domain.jobposting.entity.JobPosting;
import com.api.employment.domain.jobposting.entity.JobPostingApplicant;
import com.api.employment.domain.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class JobPostingApplyRequestDTO {

    @JsonProperty("채용공고_id")
    @NotNull(message = "채용공고 id는 필수입니다.")
    private Long jobPostingId;

    @JsonProperty("사용자_id")
    @NotNull(message = "사용자 id는 필수입니다.")
    private String MemberId;

    public JobPostingApplicant toEntity(JobPosting jobPosting, Member member){
        return JobPostingApplicant.builder()
                .jobPosting(jobPosting)
                .member(member)
                .build();
    }
}
