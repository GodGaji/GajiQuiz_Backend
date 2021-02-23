package kr.godgaji.gajiquiz.domain.Member;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NickNameDuplicateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NickNameUnique {

    String message() default "Email is Duplication";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
