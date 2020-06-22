package com.br.leonardosousa.movies.api.movie;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static com.br.leonardosousa.movies.api.utils.ValidatorUtils.checkInput;

@Component
public class MovieValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Movie.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Movie toBeValidetedMovie = (Movie) o;

        if(checkInput(toBeValidetedMovie.getTitle())){
            errors.rejectValue("title", "title.empty");
        }

        if(checkInput(toBeValidetedMovie.getImdb())){
            errors.rejectValue("imdb", "imdb.empty");
        }

        if(checkInput(toBeValidetedMovie.getOriginalLanguage())){
            errors.rejectValue("originalLanguage", "originalLanguage.empty");
        }

    }


}
