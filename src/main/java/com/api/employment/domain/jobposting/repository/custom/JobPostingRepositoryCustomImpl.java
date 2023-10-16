package com.api.employment.domain.jobposting.repository.custom;

import com.api.employment.domain.jobposting.entity.JobPosting;
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
        String jpql = " select new com.api.employment.domain.jobposting.model.JobPostingGetResponseDTO(CAST(j.id AS java.lang.Long) AS id, c.companyName, c.country, c.region, j.jobPosition, j.compensation, j.technologiesUsed) ";
               jpql += " from JobPosting j join j.company c";
        //boolean isFirstCondition = true;

        if(StringUtils.hasText(keyword)){
            jpql += " where ";
            jpql += "c.companyName LIKE :keyword OR j.jobPosition LIKE :keyword";
        }

        TypedQuery<JobPostingGetResponseDTO> query = entityManager.createQuery(jpql, JobPostingGetResponseDTO.class)
                .setMaxResults(100);

        if(StringUtils.hasText(keyword)){
            query = query.setParameter("keyword", "%" + keyword + "%");
        }

        return query.getResultList();
    }
}
