package kr.godgaji.gajiquiz.domain.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class NickNameDuplicateValidator implements ConstraintValidator<NickNameUnique, String> {

    private final MemberRepository memberRepository;

    @Override
    public void initialize(NickNameUnique nickNameUnique) {

    }

    @Override
    public boolean isValid(String nickName, ConstraintValidatorContext cxt) {

        boolean isExistNickName = memberRepository.existsByNickName(nickName);

        if (isExistNickName) {
            cxt.disableDefaultConstraintViolation();
            cxt.buildConstraintViolationWithTemplate("이미 사용중인 닉네임 입니다.")
                    .addConstraintViolation();
        }

        return !isExistNickName;
    }
}
