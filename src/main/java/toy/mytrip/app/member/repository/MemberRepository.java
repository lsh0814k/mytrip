package toy.mytrip.app.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.mytrip.app.member.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginId(String loginId);
}
