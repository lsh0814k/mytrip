package toy.mytrip.app.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.mytrip.app.member.domain.Member;
import toy.mytrip.app.member.exception.InvalidRequest;
import toy.mytrip.app.member.repository.MemberRepository;
import toy.mytrip.app.member.web.request.CreateMember;
import toy.mytrip.app.member.web.request.EditMember;
import toy.mytrip.app.member.web.request.LoginMember;

import java.util.Optional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long create( CreateMember cMember) {

        if( memberRepository.existsByEmail( cMember.getEmail()))
            throw new InvalidRequest( "중복된 이메일입니다.");
        if( memberRepository.existsByLoginId( cMember.getLoginId()))
            throw new InvalidRequest( "중복된 아이디입니다.");
        Member member = Member.builder()
                .loginId( cMember.getLoginId())
                .password(cMember.getPassword())
                .name(cMember.getName())
                .email( cMember.getEmail())
                .rrnId(cMember.getRrnId())
                .birth(cMember.getBirth())
                .mileage(cMember.getMileage())
                .phoneNumber(cMember.getPhoneNumber())
                .authority(cMember.getAuthority())
                .build();
        memberRepository.save( member);

        return member.getID();
    }

    public String login(LoginMember loginMember) {
        Optional<Member> member = memberRepository.findByLoginIdAndPassword( loginMember.getLoginId(), loginMember.getPassword());
        if( member.isEmpty())
            throw new InvalidRequest( "아이디와 비밀번호를 확인하세요.");

        return member.get().toString();
    }

    public String editMember(Long id, EditMember editMember) {
        Optional<Member> member = memberRepository.findById( id);

        EditMember.MemberEditorBuilder editorBuilder = member.get().toEdit();

        EditMember memberEditor = editorBuilder
                        .authority( editMember.getAuthority())
                        .birth( editMember.getBirth())
                        .password(editMember.getPassword())
                        .passwordCheck(editMember.getPasswordCheck())
                        .updateId(editMember.getUpdateId())
                        .updateTime(editMember.getUpdateTime())
                        .email( editMember.getEmail())
                        .name( editMember.getName())
                        .rrnId(editMember.getRrnId())
                        .mileage( editMember.getMileage())
                        .loginId( editMember.getLoginId())
                        .phoneNumber( editMember.getPhoneNumber())
                .build();
        member.get().edit( memberEditor);
        return member.get().toString();
    }
}
