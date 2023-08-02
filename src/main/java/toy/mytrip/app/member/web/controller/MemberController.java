package toy.mytrip.app.member.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toy.mytrip.app.member.domain.MemberEditor;
import toy.mytrip.app.member.service.MemberService;
import toy.mytrip.app.member.web.request.CreateMember;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping( "/join")
    public Long join(@RequestBody @Valid CreateMember member) {
        member.isValid();
        return memberService.create( member);
    }

    @PatchMapping( "/change/password/{memberId}")
    public String changePassword(@PathVariable(name="memberId") Long id, @RequestBody MemberEditor request) {

        return memberService.editMember( id, request);
    }
}
