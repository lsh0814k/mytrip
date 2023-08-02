package toy.mytrip.app.member.web.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import toy.mytrip.app.member.service.MemberService;
import toy.mytrip.app.member.web.request.CreateMember;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping( "/join")
    public Long join(@RequestBody @Valid CreateMember member) {
        member.isValid();
        return memberService.create( member);
    }
}
