package org.optaweb.vehiclerouting.domain;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neovisionaries.i18n.CountryCode;

/**
 * Validates ISO 3166-1 alpha-2 country codes.
 */
public class CountryCodeValidator {

    private static final Logger logger = LoggerFactory.getLogger(CountryCodeValidator.class);

    private CountryCodeValidator() {
        throw new AssertionError("Utility class");
    }

    /**
     * Validates the list of country codes and returns a normalized copy.
     *
     * @param countryCodes input list
     * @return normalized copy of the input list converted to upper case and without duplicates
     * @throws NullPointerException if the list is {@code null} or if any of its elements is {@code null}
     * @throws IllegalArgumentException if any of the elements is not an ISO 3166-1 alpha-2 country code
     */
    public static List<String> validate(List<String> countryCodes) {
        List<String> upperCaseCountries = Objects.requireNonNull(countryCodes).stream()
                .map(String::toUpperCase)
                .collect(toList());
        List<String> invalidCodes = upperCaseCountries.stream()
                .filter(s -> CountryCode.getByAlpha2Code(s) == null)
                .collect(toList());
        if (!invalidCodes.isEmpty()) {
            throw new IllegalArgumentException(
                    "Following elements (" + invalidCodes + ") are not valid ISO 3166-1 alpha-2 country codes");
        }
        List<String> uniqueCountries = upperCaseCountries.stream().distinct().collect(toList());
        if (uniqueCountries.size() < countryCodes.size()) {
            logger.warn("Duplicate items were removed from {}", countryCodes);
        }
        return uniqueCountries;
    }
}
