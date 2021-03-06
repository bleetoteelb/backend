package whoami.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import whoami.core.domain.Role;
import whoami.core.domain.members.Members;
import whoami.core.domain.members.MembersRepository;
import whoami.core.dto.members.MembersSaveRequestDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MembersControllerTest {
    @Autowired
    MockMvc mockMvc;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MembersRepository membersRepository;

    @Autowired
    WebApplicationContext wac;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
        Members members = new Members("yeon1", "1111", "yeon", "111-111", "11111", "111@", true, "USER", "1.jpg");
        membersRepository.save(members);
    }

    @After
    public void tearDown() throws Exception {
        membersRepository.deleteAll();
    }

    @Test
    public void Port_??????() throws Exception {
        System.out.println(port);
    }

    @Test
    @WithMockUser(roles = "USER")
    public void ????????????_?????????() throws Exception {
        //given
        String userId = "yeon2";
        String password = "2222";
        String name = "yeon2";
        String registryNum = "111111-1111111";
        String phoneNum = "010-1111-1111";
        String email = "yeon2@naver.com";
        boolean isReceiveNotification = true;
        String role = Role.USER.getValue();
        String profile = "2.jpg";
        MembersSaveRequestDto requestDto = MembersSaveRequestDto.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .registryNum(registryNum)
                .phoneNum(phoneNum)
                .email(email)
                .isReceiveNotification(isReceiveNotification)
                .role(role)
                .profile(profile)
                .build();

        String url = "http://localhost:" + port + "/users/signup";
        // when
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());
        // then
        List<Members> all = membersRepository.findAll();
        assertThat(all.get(1).getUserId()).isEqualTo(userId);

    }

    @Test
    @WithMockUser(roles = "USER")
    public void ?????????_?????????() throws Exception {
        // Given
        String userId = "yeon1";
        String password = "1111";

        mockMvc.perform(formLogin().user(userId).password(password))
                .andExpect(authenticated());
    }

}
