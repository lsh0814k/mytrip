package toy.mytrip.app.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.mytrip.app.member.domain.Member;
import toy.mytrip.app.member.domain.MemberEditor;
import toy.mytrip.app.member.exception.MemberErrorCodes;
import toy.mytrip.app.member.repository.MemberRepository;
import toy.mytrip.errors.exception.MyTripException;

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
        return memberRepository.findById(id).orElseThrow(() -> new MyTripException(MemberErrorCodes.NOT_FOUND));
    }

    public void saveMember(Member member) {
        Optional<Member> optional = memberRepository.findByLoginId(member.getLoginId());
        if (!optional.isEmpty()) {
            throw new MyTripException(MemberErrorCodes.DUPLICATE_LOGIN_ID);
        }

        memberRepository.save(member);
    }

    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new MyTripException(MemberErrorCodes.NOT_FOUND));
        memberRepository.delete(member);
    }

    public void editMember(Long id, Member member) {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new MyTripException(MemberErrorCodes.NOT_FOUND));

        MemberEditor.MemberEditorBuilder editorBuilder = member.toEditor();
        MemberEditor editor = editorBuilder.name(member.getName())
                .rrnId(member.getRrnId())
                .birth(member.getBirth())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .authority(member.getAuthority())
                .build();
        findMember.edit(editor);
    }
}
