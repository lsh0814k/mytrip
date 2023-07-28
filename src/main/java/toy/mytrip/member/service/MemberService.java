package toy.mytrip.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.mytrip.member.domain.Member;
import toy.mytrip.member.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findMember(Long id) {
        return memberRepository.findById(id).orElseThrow();
    }

    public void saveMember(Member member) {
        memberRepository.save(member);
    }

    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow();
        memberRepository.delete(member);
    }

    public void editMember(Long id, Member eidtMember) {
        Member member = memberRepository.findById(id).orElseThrow();

    }
}
