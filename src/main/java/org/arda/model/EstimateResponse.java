package org.arda.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EstimateResponse {

    @JsonProperty("Keyword")
    private String keyword;
    @JsonProperty("score")
    private int score;
}
