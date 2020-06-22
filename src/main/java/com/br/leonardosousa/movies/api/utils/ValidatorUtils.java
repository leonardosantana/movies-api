package com.br.leonardosousa.movies.api.utils;

import static java.util.Objects.isNull;

public class ValidatorUtils {

    public static boolean checkInput(String input) {

        return isNull(input) || input.isEmpty() || input.trim().length() ==0;
    }
}
