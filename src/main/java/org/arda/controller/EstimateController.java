package org.arda.controller;

import org.arda.model.EstimateResponse;
import org.arda.service.EstimateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/estimate", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstimateController {

    private final EstimateService estimateService;

    @GetMapping
    public ResponseEntity<EstimateResponse> getSearchVolumeEstimate(@RequestParam String keyword){
        EstimateResponse estimateResponse = estimateService.getSearchVolumeEstimate(keyword);
        return ResponseEntity.ok(estimateResponse);
    }
}
