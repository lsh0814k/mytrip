package toy.mytrip.app.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.mytrip.app.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
