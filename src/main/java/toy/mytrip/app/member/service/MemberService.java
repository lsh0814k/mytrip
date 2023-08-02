package toy.mytrip.app.member.service;

import org.springframework.stereotype.Service;
import toy.mytrip.app.member.domain.Member;
import toy.mytrip.app.member.domain.MemberEditor;
import toy.mytrip.app.member.repository.MemberRepository;
import toy.mytrip.app.member.web.request.CreateMember;

import java.util.Optional;

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

    public String editMember(Long id, MemberEditor editMember) {
        Optional<Member> member = memberRepository.findById( id);

        MemberEditor.MemberEditorBuilder editorBuilder = member.get().toEdit();

        MemberEditor memberEditor = editorBuilder
                        .AUTHORITY( editMember.getAUTHORITY())
                        .BIRTH( editMember.getBIRTH())
                        .PASSWORD(editMember.getPASSWORD())
                        .PASSWORD_CHECK(editMember.getPASSWORD_CHECK())
                        .UPDATE_ID(editMember.getUPDATE_ID())
                        .UPDATE_TIME(editMember.getUPDATE_TIME())
                        .EMAIL( editMember.getEMAIL())
                        .NAME( editMember.getNAME())
                        .RRN_ID(editMember.getRRN_ID())
                        .MILEAGE( editMember.getMILEAGE())
                        .LOGIN_ID( editMember.getLOGIN_ID())
                .build();
        member.get().edit( memberEditor);
        return member.get().toString();
    }
}
