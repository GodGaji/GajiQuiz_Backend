package kr.godgaji.gajiquiz.domain.Role;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Role {
    USER("사용자"),
    ADMIN("관리자");
    String value;

    Role(String value) {
        this.value = value;
    }

    public Role ofValue(String value) {
        return Arrays.asList(Role.values())
                .stream()
                .filter(role -> role.getValue().equals(value))
                .findAny()
                .orElse(null);
    }
}