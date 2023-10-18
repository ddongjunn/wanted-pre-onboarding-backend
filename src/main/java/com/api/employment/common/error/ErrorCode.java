package com.api.employment.common.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    EXISTING_COMPANY_ERROR("이미 등록된 회사입니다."),
    ALREADY_APPLIED("이미 신청한 채용공고 입니다."),
    COMPANY_ID_NOT_FOUND("회사 ID가 존재하지 않습니다."),
    JOB_POSTING_ID_NOT_FOUND("채용 공고 ID가 존재하지 않습니다."),
    USER_ID_NOT_FOUND("존재하지 않는 사용자 ID입니다."),
    JOB_POSTING_ID_REQUIRED("채용공고 ID는 필수입니다."),;

    private final String message;
}
