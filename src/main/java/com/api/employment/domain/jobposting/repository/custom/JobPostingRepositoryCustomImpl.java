package com.api.employment.domain.jobposting.repository.custom;

import com.api.employment.domain.jobposting.model.JobPostingGetDetailResponseDTO;
import com.api.employment.domain.jobposting.model.JobPostingGetResponseDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.util.StringUtils;

import java.util.List;

public class JobPostingRepositoryCustomImpl implements JobPostingRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<JobPostingGetResponseDTO> findJobPostingWithKeyword(String keyword){
        String jpql = " SELECT new com.api.employment.domain.jobposting.model.JobPostingGetResponseDTO(CAST(j.id AS java.lang.Long) AS id, c.companyName, c.country, c.region, j.jobPosition, j.compensation, j.technologiesUsed) ";
               jpql += " FROM JobPosting j JOIN j.company c";
        //boolean isFirstCondition = true;

        if(StringUtils.hasText(keyword)){
            jpql += " WHERE ";
            jpql += "c.companyName LIKE :keyword OR j.jobPosition LIKE :keyword";
        }

        TypedQuery<JobPostingGetResponseDTO> query = entityManager.createQuery(jpql, JobPostingGetResponseDTO.class)
                .setMaxResults(100);

        if(StringUtils.hasText(keyword)){
            query = query.setParameter("keyword", "%" + keyword + "%");
        }

        return query.getResultList();
    }

    @Override
    public JobPostingGetDetailResponseDTO findJobPostingDetail(Long jobPostingId) {
        String jpql = "SELECT NEW com.api.employment.domain.jobposting.model.JobPostingGetDetailResponseDTO(" +
                                "j.id, " +
                                "c.companyName, " +
                                "c.country, " +
                                "c.region, " +
                                "j.jobPosition, " +
                                "j.compensation, " +
                                "j.technologiesUsed) " +
                                "FROM JobPosting j " +
                                "JOIN j.company c " +
                                "WHERE j.id = :jobPostingId";

        return entityManager.createQuery(jpql, JobPostingGetDetailResponseDTO.class)
                .setMaxResults(100)
                .setParameter("jobPostingId", jobPostingId)
                .getSingleResult();
    }
}
