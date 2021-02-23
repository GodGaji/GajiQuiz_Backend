package kr.godgaji.gajiquiz.domain.Member;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberCustomRepository {

    public MemberRepositoryImpl() {
        super(Member.class);
    }

    @Override
    public Member findByNickName(String nickname) {
        final QMember member = QMember.member;

        return from(member)
                .where(member.nickName.eq(nickname))
                .fetchOne();
    }

    @Override
    public boolean existsByNickName(String nickName) {
        final QMember member = QMember.member;

        if(nickName == null) return false;

        Member fetchOne = from(member)
                .where(member.nickName.eq(nickName))
                .fetchFirst();

        return fetchOne != null;
    }
}
