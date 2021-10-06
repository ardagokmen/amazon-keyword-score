package org.arda.service;

import org.arda.accessor.AmazonAutocompleteAccessor;
import org.arda.constants.Constants;
import org.arda.model.EstimateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class EstimateService {

    private final AmazonAutocompleteAccessor amazonAutocompleteAccessor;

    /**
     * Returns search volume score for given keyword.
     *
     * @param keyword The keyword which its score is wanted.
     * @return Response object containing keyword and its score.
     */
    public EstimateResponse getSearchVolumeEstimate(String keyword){
        int totalSuggestedPhrases = 0;
        int matchingPhrases = 0;
        String lowerCaseKeyword = keyword.toLowerCase(Locale.ROOT);

        // Iterates through the letters' positions, creating a substring each loop.
        for(int i=0; i < lowerCaseKeyword.length(); i++){

            String subKeyword = lowerCaseKeyword.substring(0, i + 1);
            String suggestionResponse = amazonAutocompleteAccessor.getSuggestions(
                    Constants.SEARCH_ALIAS, Constants.CLIENT, Constants.MKT, subKeyword).getBody();
            List<String> suggestedPhrases = extractSuggestedPhrases(suggestionResponse);
            totalSuggestedPhrases += suggestedPhrases.size();

            // Iterates the suggested phrases and counts which match the initial keyword.
            for(String phrase : suggestedPhrases){
                Pattern pattern = Pattern.compile(String.format(".*\\b%s\\b.*", lowerCaseKeyword));
                Matcher matcher = pattern.matcher(phrase);
                if(matcher.find()){
                    matchingPhrases++;
                }
            }
        }

        int score = 0;
        if(totalSuggestedPhrases != 0){
            score = matchingPhrases * 100 / totalSuggestedPhrases;
        }
        return EstimateResponse.builder()
                .keyword(keyword)
                .score(score)
                .build();
    }

    /**
     * Parses and returns the list of suggested phrases from Amazon Autocomplete API's response.
     *
     * @param suggestionResponse Response retrieved from the autocompletion endpoint.
     * @return List of keywords obtained from the autocompletion endpoint.
     */
    private static List<String> extractSuggestedPhrases(String suggestionResponse){
        suggestionResponse = suggestionResponse.substring(1, suggestionResponse.length() - 2);
        suggestionResponse =  suggestionResponse.substring( suggestionResponse.indexOf("[") + 1 , suggestionResponse.indexOf("]") );
        return Arrays.asList(suggestionResponse.replace("\"","").split(","));
    }

}
