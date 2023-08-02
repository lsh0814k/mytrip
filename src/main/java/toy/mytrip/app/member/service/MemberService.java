package toy.mytrip.app.member.service;

import org.springframework.stereotype.Service;
import toy.mytrip.app.member.domain.Member;
import toy.mytrip.app.member.repository.MemberRepository;
import toy.mytrip.app.member.web.request.CreateMember;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long create( CreateMember cMember) {
        Member member = Member.builder()
                .LOGIN_ID( cMember.getLOGIN_ID())
                .PASSWORD(cMember.getPASSWORD())
                .NAME(cMember.getNAME())
                .EMAIL( cMember.getEMAIL())
                .RRN_ID(cMember.getRRN_ID())
                .BIRTH(cMember.getBIRTH())
                .MILEAGE(cMember.getMILEAGE())
                .PHONE_NUMBER(cMember.getPHONE_NUMBER())
                .AUTHORITY(cMember.getAUTHORITY())
                .build();
        memberRepository.save( member);

        return member.getID();
    }
}
