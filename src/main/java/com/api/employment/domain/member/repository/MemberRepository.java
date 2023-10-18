package com.api.employment.domain.member.repository;

import com.api.employment.common.error.ErrorCode;
import com.api.employment.common.error.exception.CustomException;
import com.api.employment.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    default Member getById(String id){
        return this.findById(id).orElseThrow(() -> new CustomException(ErrorCode.USER_ID_NOT_FOUND));
    }
}
