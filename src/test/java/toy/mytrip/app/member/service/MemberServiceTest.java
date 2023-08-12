package toy.mytrip.app.member.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import toy.mytrip.app.member.domain.Authority;
import toy.mytrip.app.member.domain.Member;
import toy.mytrip.app.member.repository.MemberRepository;
import toy.mytrip.errors.codes.ErrorCode;
import toy.mytrip.errors.exception.MyTripException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 전체 조회")
    void findAll() {
        // given
        Member member = Member.builder()
                .loginId("adminuser")
                .password("qwe123!@#")
                .name("관리자")
                .rrnId("1151321")
                .birth("931116")
                .email("adminuser@gamil.com")
                .phoneNumber("01051515321")
                .authority(Authority.ADMIN)
                .build();
        memberRepository.save(member);

        // when
        List<Member> members = memberService.findAll();

        // then
        assertEquals(1, members.size());
    }

    @Test
    @DisplayName("회원 단일 조회")
    void findMember() {
        // given
        Member member = Member.builder()
                .loginId("adminuser")
                .password("qwe123!@#")
                .name("관리자")
                .rrnId("1151321")
                .birth("931116")
                .email("adminuser@gamil.com")
                .phoneNumber("01051515321")
                .authority(Authority.ADMIN)
                .build();
        memberRepository.save(member);

        // when
        Member findMember = memberService.findMember(member.getId());
        // then
        assertEquals(Authority.ADMIN, findMember.getAuthority());
        assertEquals("adminuser", findMember.getLoginId());
    }

    @Test
    @DisplayName("회원 단일 조회(일치한 아이디가 없는 경우)")
    void findMember_not_found() {
        // expected
        MyTripException myTripException = assertThrows(MyTripException.class, () -> memberService.findMember(1L));
        ErrorCode errorCode = myTripException.getErrorCode();
        assertEquals("존재하지 않는 사용자입니다.", errorCode.getErrorMessage());
        assertEquals(HttpStatus.NOT_FOUND.value(), errorCode.getStatus());
    }

    @Test
    @DisplayName("회원 저장")
    void saveMember() {
        // given
        Member member = Member.builder()
                .loginId("adminuser")
                .password("qwe123!@#")
                .name("관리자")
                .rrnId("1151321")
                .birth("931116")
                .email("adminuser@gamil.com")
                .phoneNumber("01051515321")
                .authority(Authority.ADMIN)
                .build();

        // when
        memberService.saveMember(member);
        Member findMember = memberRepository.findById(member.getId()).orElseThrow();
        // then
        assertEquals(member.getLoginId(), findMember.getLoginId());
        assertEquals(member.getPassword(), findMember.getPassword());
        assertEquals(member.getName(), findMember.getName());
        assertEquals(member.getRrnId(), findMember.getRrnId());
        assertEquals(member.getEmail(), findMember.getEmail());
        assertEquals(member.getPhoneNumber(), findMember.getPhoneNumber());
        assertEquals(member.getAuthority(), findMember.getAuthority());
    }

    @Test
    @DisplayName("회원 저장(동일한 로그인 아이디가 있는 경우)")
    void saveMember_same_loginId() {
        // given
        Member member = Member.builder()
                .loginId("adminuser")
                .password("qwe123!@#")
                .name("관리자")
                .rrnId("1151321")
                .birth("931116")
                .email("adminuser@gamil.com")
                .phoneNumber("01051515321")
                .authority(Authority.ADMIN)
                .build();
        memberRepository.save(member);

        // when
        MyTripException myTripException = assertThrows(MyTripException.class, () -> memberService.saveMember(member));

        // then
        assertEquals(HttpStatus.BAD_REQUEST.value(), myTripException.getErrorCode().getStatus());
        assertEquals("동일한 ID가 존재합니다.", myTripException.getErrorCode().getErrorMessage());
    }

    @Test
    @DisplayName("회원 삭제")
    void deleteMember() {
        // given
        Member member = Member.builder()
                .loginId("adminuser")
                .password("qwe123!@#")
                .name("관리자")
                .rrnId("1151321")
                .birth("931116")
                .email("adminuser@gamil.com")
                .phoneNumber("01051515321")
                .authority(Authority.ADMIN)
                .build();
        memberRepository.save(member);

        // when
        memberService.deleteMember(member.getId());

        // then
        Optional<Member> optional = memberRepository.findById(member.getId());
        assertTrue(optional.isEmpty());
    }

    @Test
    @DisplayName("회원 수정")
    @Transactional()
    void editMember() {
        // given
        Member member = Member.builder()
                .loginId("adminuser")
                .password("qwe123!@#")
                .name("관리자")
                .rrnId("1151321")
                .birth("931116")
                .email("adminuser@gamil.com")
                .phoneNumber("01051515321")
                .authority(Authority.ADMIN)
                .build();
        memberRepository.save(member);

        Member editMember = Member.builder()
                .name("관리자2")
                .build();
        // when
        memberService.editMember(member.getId(), editMember);
        Member findMember = memberRepository.findById(member.getId()).orElseThrow();


        // then
        assertEquals(member.getLoginId(), findMember.getLoginId());
        assertEquals(editMember.getName(), findMember.getName());
    }
}