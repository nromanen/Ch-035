package com.crsms.validator;

import com.crsms.domain.Question;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Petro Andriets
 */

@Component
public class QuestionFormValidator implements Validator {
    
    @Override
    public boolean supports(Class<?> clazz) {
        return Question.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "crsms.question.error.text.required");
        Question question = (Question) target;
        if (question.getText().length() > Question.MAX_TEXT_LENGTH) {
            errors.rejectValue("text", "crsms.error.too.long", new Object[]{Question.MAX_TEXT_LENGTH}, "text is too long");
        }
    }
}
