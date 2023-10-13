package com.api.employment.domain.jobposting.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JobPostingDeleteRequestDTO {

    @NotNull(message = "채용 id는 필수입니다.")
    @JsonProperty("채용공고_id")
    private Long jobPostingId;
}
