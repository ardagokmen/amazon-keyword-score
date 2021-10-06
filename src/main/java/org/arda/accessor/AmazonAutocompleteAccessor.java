package org.arda.accessor;

import org.arda.constants.Constants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = Constants.FEIGN_CLIENT_NAME,
        url = Constants.AMAZON_AUTOCOMPLETION_URL)
public interface AmazonAutocompleteAccessor {

    @GetMapping
    ResponseEntity<String> getSuggestions(@RequestParam(name = "search-alias") String searchAlias,
            @RequestParam String client, @RequestParam int mkt, @RequestParam String q);
}

