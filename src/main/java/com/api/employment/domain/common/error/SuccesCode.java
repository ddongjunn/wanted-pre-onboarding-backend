package com.api.employment.domain.common.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SuccesCode {

    COMPANY_REGISTRATION_SUCCESS("회사가 등록되었습니다."),
    SAVE_SUCCESSFUL("성공적으로 저장되었습니다."),
    UPDATE_SUCCESSFUL("성공적으로 수정되었습니다."),
    DELETE_SUCCESSFUL("삭제되었습니다.");
    private final String message;
}
