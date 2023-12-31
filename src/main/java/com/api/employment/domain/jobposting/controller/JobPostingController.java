package com.api.employment.domain.jobposting.controller;

import com.api.employment.common.logging.LoggableController;
import com.api.employment.domain.jobposting.model.JobPostingApplyRequestDTO;
import com.api.employment.domain.jobposting.model.JobPostingGetDetailResponseDTO;
import com.api.employment.domain.jobposting.model.JobPostingSaveRequestDTO;
import com.api.employment.domain.jobposting.model.JobPostingUpdateRequestDTO;
import com.api.employment.domain.jobposting.service.JobPostingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("jobs")
@RequiredArgsConstructor
@LoggableController
public class JobPostingController {

    private final JobPostingService jobPostingService;

    @PostMapping
    public ResponseEntity<?> saveJobPosting(@Valid @RequestBody final JobPostingSaveRequestDTO jobPostingSaveRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(jobPostingService.save(jobPostingSaveRequestDTO));
    }

    @GetMapping
    public ResponseEntity<?> getJobPosting(@RequestParam(required = false) final String search){
        return ResponseEntity.ok().body(jobPostingService.get(search));
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> getJobPostingDetail(@PathVariable final Long id){
        return ResponseEntity.ok().body(jobPostingService.getDetail(id));
    }

    @PatchMapping
    public ResponseEntity<?> updateJobPosting(@Valid @RequestBody final JobPostingUpdateRequestDTO jobPostingUpdateRequestDTO){
        return ResponseEntity.ok().body(jobPostingService.update(jobPostingUpdateRequestDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteJobPosting(@PathVariable final Long id){
        return ResponseEntity.ok().body(jobPostingService.delete(id));
    }

    @PostMapping("apply")
    public ResponseEntity<?> applyJobPosting(@Valid @RequestBody JobPostingApplyRequestDTO jobPostingApplyRequestDTO){
        return ResponseEntity.ok().body(jobPostingService.applyJobPosting(jobPostingApplyRequestDTO));
    }

}
