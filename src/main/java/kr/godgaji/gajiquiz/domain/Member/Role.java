package kr.godgaji.gajiquiz.domain.Member;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor
@Getter
public enum Role {
    ADMIN("관리자"),
    USER("사용자");
    private String name;

    Role(String name){
        this.name = name;
    }

    public static Role valueOfName(String name)throws NullPointerException{
        return Arrays.stream(Role.values())
                .filter(role -> role.getName().equals(name))
                .findAny()
                .orElse(null);
    }
}
