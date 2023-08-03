package toy.mytrip.app.member.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import toy.mytrip.app.member.domain.Member;
import toy.mytrip.app.member.repository.MemberRepository;
import toy.mytrip.app.member.web.request.CreateMember;
import toy.mytrip.app.member.web.request.EditMember;
import toy.mytrip.app.member.web.request.LoginMember;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void clean() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName( "회원가입 테스트")
    void createMember() throws Exception {
        CreateMember joinMember = CreateMember.builder()
                .loginId("로그인아이디")
                .password("123")
                .passwordCheck("123")
                .phoneNumber("010-0000-0000")
                .rrnId("900718-1111111")
                .name("조")
                .birth("900718")
                .authority("USER")
                .email("cho@aaaaaaa")
                .mileage( 0L)
                .build();

        String jasonData = objectMapper.writeValueAsString( joinMember);
        mockMvc.perform(MockMvcRequestBuilders.post( "/join")
                .content( jasonData)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName( "로그인 테스트")
    void login() throws Exception {
        Member member = Member.builder()
                .loginId("로그인아이디")
                .password("123")
                .phoneNumber("010-0000-0000")
                .rrnId("900718-1111111")
                .name("조")
                .birth("900718")
                .authority("USER")
                .email("cho@aaaaaaa")
                .mileage( 0L)
                .build();
        memberRepository.save( member);

        LoginMember loginMember = LoginMember.builder()
                .loginId("로그인아이디")
                .password("123")
                .build();
        String jsonData = objectMapper.writeValueAsString( loginMember);
        mockMvc.perform(MockMvcRequestBuilders.get( "/login")
                        .content( jsonData)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName( "중복테스트")
    void duplicateTest() throws Exception {
        Member member = Member.builder()
                .loginId("로그인아이디")
                .password("123")
                .phoneNumber("010-0000-0000")
                .rrnId("900718-1111111")
                .name("조")
                .birth("900718")
                .authority("USER")
                .email("cho@aaaaaaa")
                .mileage( 0L)
                .build();
        memberRepository.save( member);

        CreateMember joinMember = CreateMember.builder()
                .loginId("로그인아이디")
                .password("123")
                .passwordCheck("123")
                .phoneNumber("010-0000-0000")
                .rrnId("900718-1111111")
                .name("조")
                .birth("900718")
                .authority("USER")
                .email("cho2@aaaaaaa")
                .mileage( 0L)
                .build();

        String jsonData = objectMapper.writeValueAsString( joinMember);
        mockMvc.perform(MockMvcRequestBuilders.post( "/join")
                        .content( jsonData)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("회원 수정 테스트")
    void editMember() throws Exception {
        Member member = Member.builder()
                .loginId("로그인아이디")
                .password("123")
                .phoneNumber("010-0000-0000")
                .rrnId("900718-1111111")
                .name("조")
                .birth("900718")
                .authority("USER")
                .email("cho@aaaaaaa")
                .mileage( 0L)
                .build();

        memberRepository.save( member);
        EditMember editor = EditMember.builder()
                .authority( "USER")
                .phoneNumber( "010-1234-1234")
                .name( "조성")
                .email( "cho@naver.com")
                .build();

        String jsonData = objectMapper.writeValueAsString( editor);
        mockMvc.perform( MockMvcRequestBuilders.patch( "/edit/{memberId}", member.getID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content( jsonData)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

//    @Test
//    @DisplayName("비밀번호 수정 테스트")
//    void editPasswordTest() throws Exception {
//        Member member = Member.builder()
//                .loginId("로그인아이디")
//                .password("123")
//                .phoneNumber("010-0000-0000")
//                .rrnId("900718-1111111")
//                .name("조")
//                .birth("900718")
//                .authority("USER")
//                .email("cho@aaaaaaa")
//                .mileage( 0L)
//                .build();
//
//        memberRepository.save( member);
//    }
}