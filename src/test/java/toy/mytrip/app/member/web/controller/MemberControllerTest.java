package toy.mytrip.app.member.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import toy.mytrip.app.member.domain.Authority;
import toy.mytrip.app.member.domain.Member;
import toy.mytrip.app.member.domain.Password;
import toy.mytrip.app.member.exception.MemberErrorCodes;
import toy.mytrip.app.member.repository.MemberRepository;
import toy.mytrip.app.member.web.request.MemberSaveForm;
import toy.mytrip.app.member.web.request.MemberUpdateForm;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void clear() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원 전체 조회")
    void findAll() throws Exception {
        // given
        Member member = Member.builder()
                .loginId("adminuser")
                .password(new Password("qwe123!@#"))
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
                .andExpect(jsonPath("$[0].loginId").value(member.getLoginId()))
                .andExpect(jsonPath("$[0]..password").isEmpty())
                .andExpect(jsonPath("$[0].name").value(member.getName()))
                .andExpect(jsonPath("$[0]..rrnId").isEmpty())
                .andExpect(jsonPath("$[0]..birth").isEmpty())
                .andExpect(jsonPath("$[0].email").value(member.getEmail()))
                .andExpect(jsonPath("$[0].phoneNumber").value(member.getPhoneNumber()));
    }

    @Test
    @DisplayName("회원 전체 조회(데이터 없는 경우)")
    void findAll_empty_data() throws Exception {
        // expected
        mockMvc.perform(get("/members")
                .contentType(APPLICATION_JSON)
            )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(0)));
    }

    @Test
    @DisplayName("회원 단일 조회")
    void findMember() throws Exception {
        // given
        Member member = Member.builder()
                .loginId("adminuser")
                .password(new Password("qwe123!@#"))
                .name("관리자")
                .rrnId("1151321")
                .birth("931116")
                .email("adminuser@gamil.com")
                .phoneNumber("01051515321")
                .authority(Authority.ADMIN)
                .build();
        memberRepository.save(member);

        // expected
        mockMvc.perform(get("/members/{id}", member.getId())
                    .contentType(APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.loginId").value(member.getLoginId()))
                .andExpect(jsonPath("$..password").isEmpty())
                .andExpect(jsonPath("$.name").value(member.getName()))
                .andExpect(jsonPath("$..rrnId").isEmpty())
                .andExpect(jsonPath("$..birth").isEmpty())
                .andExpect(jsonPath("$.email").value(member.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(member.getPhoneNumber()));
    }

    @Test
    @DisplayName("회원 단일 조회(일치한 아이디가 없는 경우)")
    void findMember_not_found() throws Exception {
        // expected
        mockMvc.perform(get("/members/{id}", 1L)
                    .contentType(APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(MemberErrorCodes.NOT_FOUND.getStatus()))
                .andExpect(jsonPath("$.message").value(MemberErrorCodes.NOT_FOUND.getErrorMessage()));
    }

    @Test
    @DisplayName("회원 저장")
    void saveMember() throws Exception {
        // given
        MemberSaveForm member = MemberSaveForm.builder()
                .loginId("adminuser")
                .password("qwe123!@#")
                .passwordConf("qwe123!@#")
                .name("관리자")
                .rrnId("1241156")
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
    @DisplayName("회원 저장(동일한 로그인 아이디가 있는 경우)")
    void saveMember_same_loginId() throws Exception {
        // given
        MemberSaveForm member = MemberSaveForm.builder()
                .loginId("adminuser")
                .password("qwe123!@#")
                .passwordConf("qwe123!@#")
                .name("관리자")
                .rrnId("1241156")
                .birth("931116")
                .email("adminuser@gamil.com")
                .phoneNumber("01051515321")
                .authority(Authority.ADMIN.toString())
                .build();
        memberRepository.save(member.createMember());

        // expected
        mockMvc.perform(post("/members")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(MemberErrorCodes.DUPLICATE_LOGIN_ID.getStatus()))
                .andExpect(jsonPath("$.message").value(MemberErrorCodes.DUPLICATE_LOGIN_ID.getErrorMessage()));
    }

    @Test
    @DisplayName("회원 저장(비밀번호와 비밀번호 확인이 다른 경우)")
    void saveMember_diff_password_passwordConf() throws Exception {
        // given
        MemberSaveForm member = MemberSaveForm.builder()
                .loginId("adminuser")
                .password("qwe123!@#")
                .passwordConf("123456789")
                .name("관리자")
                .rrnId("1241156")
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
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(MemberErrorCodes.DIFF_CONF_PASSWORD.getStatus()))
                .andExpect(jsonPath("$.message").value(MemberErrorCodes.DIFF_CONF_PASSWORD.getErrorMessage()));
    }

    @Test
    @DisplayName("회원 삭제")
    void deleteMember() throws Exception {
        Member member = Member.builder()
                .loginId("adminuser")
                .password(new Password("qwe123!@#"))
                .name("관리자")
                .rrnId("1241156")
                .birth("931116")
                .email("adminuser@gamil.com")
                .phoneNumber("01051515321")
                .authority(Authority.ADMIN)
                .build();
        memberRepository.save(member);


        // expected
        mockMvc.perform(delete("/members/{id}", member.getId())
                        .contentType(APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원 삭제(존재하지 않는 회원인 경우)")
    void deleteMember_not_found() throws Exception {
        // expected
        mockMvc.perform(delete("/members/{id}", 1L)
                        .contentType(APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(MemberErrorCodes.NOT_FOUND.getStatus()))
                .andExpect(jsonPath("$.message").value(MemberErrorCodes.NOT_FOUND.getErrorMessage()));
    }

    @Test
    @DisplayName("회원 수정")
    void editMember() throws Exception {
        // given
        Member member = Member.builder()
                .loginId("adminuser")
                .password(new Password("qwe123!@#"))
                .name("관리자")
                .rrnId("1241156")
                .birth("931116")
                .email("adminuser@gamil.com")
                .phoneNumber("01051515321")
                .authority(Authority.ADMIN)
                .build();
        memberRepository.save(member);

       // when
        MemberUpdateForm updateForm = MemberUpdateForm.builder()
                .id(member.getId())
                .name("일반 회원")
                .rrnId("1241156")
                .birth("931116")
                .email("adminuser@gamil.com")
                .phoneNumber("01051515321")
                .authority(Authority.USER)
                .build();
        // expected
        mockMvc.perform(patch("/members/{id}", member.getId())
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateForm))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원 수정(존재하지 않는 회원인 경우)")
    void memberEdit_not_found() throws Exception {
        // given
        MemberUpdateForm updateForm = MemberUpdateForm.builder()
                .id(1L)
                .name("일반 회원")
                .rrnId("1241156")
                .birth("931116")
                .email("adminuser@gamil.com")
                .phoneNumber("01051515321")
                .authority(Authority.USER)
                .build();

        // expected
        mockMvc.perform(patch("/members/{id}", 1L)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateForm))
                )
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(MemberErrorCodes.NOT_FOUND.getStatus()))
                .andExpect(jsonPath("$.message").value(MemberErrorCodes.NOT_FOUND.getErrorMessage()));
    }
}