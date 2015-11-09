package com.crsms.validator;

import com.crsms.domain.Answer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Petro Andriets
 */

@Component
public class AnswerFormValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return Answer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "crsms.question.error.text.required");
        Answer answer = (Answer) target;
        if (answer.getText().length() > Answer.MAX_TEXT_LENGTH) {
            errors.rejectValue("text", "crsms.error.too.long", new Object[]{Answer.MAX_TEXT_LENGTH}, "text is too long");
        }
    }
}
