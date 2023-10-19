package com.api.employment.controller;

import com.api.employment.common.domain.ResponseMessage;
import com.api.employment.common.error.SuccesCode;
import com.api.employment.domain.jobposting.controller.JobPostingController;
import com.api.employment.domain.jobposting.model.JobPostingSaveRequestDTO;
import com.api.employment.domain.jobposting.service.JobPostingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(JobPostingController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class JobPostingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobPostingService jobPostingService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("채용공고 등록 테스트")
    public void postJobPosting() throws Exception{
        //given
        JobPostingSaveRequestDTO request = JobPostingSaveRequestDTO.builder()
                .companyId(1L)
                .jobPosition("PM")
                .compensation(1000)
                .jobDetail("프론트 개발자")
                .technologiesUsed("javascript")
                .build();
        String content = mapper.writeValueAsString(request);

        ResponseMessage response = new ResponseMessage(SuccesCode.SAVE_SUCCESSFUL.getMessage());

        given(jobPostingService.save(Mockito.any(JobPostingSaveRequestDTO.class))).willReturn(response);

        //when
        ResultActions actions = mockMvc.perform(post("/jobs")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content));

        //then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(SuccesCode.SAVE_SUCCESSFUL.getMessage()));
    }

}
