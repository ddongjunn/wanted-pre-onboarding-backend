package com.api.employment.domain.jobposting.entity;

import com.api.employment.domain.member.entity.Member;
import com.api.employment.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobPostingApplicant extends BaseTimeEntity {

    @Builder
    public JobPostingApplicant(JobPosting jobPosting, Member member){
        this.jobPosting = jobPosting;
        this.member = member;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private JobPosting jobPosting;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
