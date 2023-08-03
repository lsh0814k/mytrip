package toy.mytrip.app.member.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toy.mytrip.app.member.service.MemberService;
import toy.mytrip.app.member.web.request.CreateMember;
import toy.mytrip.app.member.web.request.EditMember;
import toy.mytrip.app.member.web.request.LoginMember;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping( "/join")
    public Long join(@RequestBody @Valid CreateMember member) {
        member.isValid();
        return memberService.create( member);
    }

    @GetMapping( "/login")
    public String login(@RequestBody @Valid LoginMember loginMember) {
        loginMember.isValid();
        return memberService.login( loginMember);
    }

    @PatchMapping( "/edit/{memberId}")
    public String changePassword(@PathVariable(name="memberId") Long id, @RequestBody EditMember request) {

        return memberService.editMember( id, request);
    }
}
