package com.api.employment.domain.jobposting.entity;

import com.api.employment.domain.company.entity.Company;
import com.api.employment.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobPosting extends BaseTimeEntity {

    @Builder
    public JobPosting(Company company, String jobPosition, int compensation, String jobDetail, String technologiesUsed) {
        this.company = company;
        this.jobPosition = jobPosition;
        this.compensation = compensation;
        this.jobDetail = jobDetail;
        this.technologiesUsed = technologiesUsed;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

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

    public void linkCompany(Company company){
        this.company = company;
    }
}
