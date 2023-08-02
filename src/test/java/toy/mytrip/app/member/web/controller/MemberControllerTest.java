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
import toy.mytrip.app.member.repository.MemberRepository;
import toy.mytrip.app.member.web.request.CreateMember;

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
                .LOGIN_ID("로그인아이디")
                .PASSWORD("123")
                .PASSWORD_CHECK("12")
                .PHONE_NUMBER("010-0000-0000")
                .RRN_ID("900718-1111111")
                .NAME("조")
                .BIRTH("900718")
                .AUTHORITY("USER")
                .EMAIL("cho@aaaaaaa")
                .MILEAGE( 0L)
                .build();

        String jasonData = objectMapper.writeValueAsString( joinMember);
        mockMvc.perform(MockMvcRequestBuilders.post( "/join")
                .content( jasonData)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}