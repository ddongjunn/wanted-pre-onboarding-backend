package com.api.employment.domain.jobposting.entity;

import com.api.employment.global.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobPosting extends BaseTimeEntity {

    @Builder
    public JobPosting(Long companyId, String jobPosition, int compensation, String jobDetail, String technologiesUsed) {
        this.companyId = companyId;
        this.jobPosition = jobPosition;
        this.compensation = compensation;
        this.jobDetail = jobDetail;
        this.technologiesUsed = technologiesUsed;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long companyId;

    private String jobPosition;

    private int compensation;

    private String jobDetail;

    private String technologiesUsed;

    public void update(String jobPosition, int compensation, String jobDetail, String technologiesUsed){
        this.jobPosition = jobPosition;
        this.compensation = compensation;
        this.jobDetail = jobDetail;
        this.technologiesUsed = technologiesUsed;
    }
}
