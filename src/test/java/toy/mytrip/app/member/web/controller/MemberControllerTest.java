package toy.mytrip.app.member.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import toy.mytrip.app.member.domain.Authority;
import toy.mytrip.app.member.domain.Member;
import toy.mytrip.app.member.repository.MemberRepository;
import toy.mytrip.app.member.web.request.MemberSaveForm;

import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("회원 전체 조회")
    void findAll() throws Exception {
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

        // expected
        mockMvc.perform(get("/members")
                            .contentType(APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].loginId").value("adminuser"));
    }

    @Test
    void findMember() {
    }

    @Test
    void saveMember() throws Exception {
        // given
        MemberSaveForm member = MemberSaveForm.builder()
                .loginId("adminuser")
                .password("qwe123!@#")
                .passwordConf("qwe123!@#")
                .name("관리자")
                .rrnId("1151321123")
                .birth("931116")
                .email("adminuser@gamil.com")
                .phoneNumber("01051515321")
                .authority(Authority.ADMIN.toString())
                .build();
        // expected
        mockMvc.perform(post("/members")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member))
                )
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void editMember() {
    }

    @Test
    void deleteMember() {
    }
}