package br.com.ez.finances.domain.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Generic object validator. Uses Hibernate Validator.
 */
public class ObjectValidator {
    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static Validator validator = factory.getValidator();

    /**
     * Validate an object through Hibernate Validator notations.
     * @param obj Object to be validated.
     * @throws ConstraintViolationException In case the object is not valid.
     */
    public static <T> void validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);

        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}