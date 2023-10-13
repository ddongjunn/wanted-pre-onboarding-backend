package com.api.employment.domain.common.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    EXISTING_COMPANY_ERROR("이미 등록된 회사입니다."),
    COMPANY_ID_NOT_FOUND("회사 ID가 존재하지 않습니다."),
    JOB_POSTING_ID_NOT_FOUND("채용 공고 ID가 존재하지 않습니다.");

    private final String message;
}
