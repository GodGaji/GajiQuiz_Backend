package kr.godgaji.gajiquiz.domain.Member;

public interface MemberCustomRepository {
    Member findByNickName(String nickname);
    boolean existsByNickName(String nickName);
}
