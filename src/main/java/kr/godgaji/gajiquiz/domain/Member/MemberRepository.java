package kr.godgaji.gajiquiz.domain.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.nickName =:nickName")
    Optional<Member> findByNickName(@Param("nickName") String nickName);
    @Query("select m from Member m where m.email =:email")
    Optional<Member> findByEmail(@Param("email") String email);
}
