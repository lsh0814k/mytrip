package toy.mytrip.app.member.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toy.mytrip.app.member.domain.Member;
import toy.mytrip.app.member.service.MemberService;
import toy.mytrip.app.member.web.request.MemberSaveForm;
import toy.mytrip.app.member.web.request.MemberUpdateForm;
import toy.mytrip.app.member.web.response.MemberResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public List<MemberResponse> findAll() {
        List<Member> members = memberService.findAll();
        return members.stream()
                .map(member -> new MemberResponse(member))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MemberResponse findMember(@PathVariable Long id) {
        Member member = memberService.findMember(id);

        return new MemberResponse(member);
    }

    @PostMapping
    public void saveMember(@RequestBody @Valid MemberSaveForm memberSaveForm) {
        memberService.saveMember(memberSaveForm.createMember());
    }

    @PatchMapping("/{id}")
    public void editMember(@PathVariable Long id, @RequestBody MemberUpdateForm memberUpdateForm) {
        memberService.editMember(id, memberUpdateForm.toMember());
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}
