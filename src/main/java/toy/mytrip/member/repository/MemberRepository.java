package toy.mytrip.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.mytrip.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
