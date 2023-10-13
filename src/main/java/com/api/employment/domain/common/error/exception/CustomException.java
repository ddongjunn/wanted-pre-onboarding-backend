package com.api.employment.domain.common.error.exception;

import com.api.employment.domain.common.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CustomException extends RuntimeException{

    private final ErrorCode errorCode;

}
