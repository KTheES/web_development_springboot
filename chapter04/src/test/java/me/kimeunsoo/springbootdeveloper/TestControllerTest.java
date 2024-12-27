package me.kimeunsoo.springbootdeveloper;
/*
    Test 클래스를 만드는 방법

    1) 테스트하고자 하는 클래스(main/java 내에 있는 클래스)를 엽니다.
    2) public class 클래스명이 있는 곳에 클래스명을 클릭
    3) alt + enter 를 누르면 팝업이 나옵니다.
    4) create test 선택
    5) 저희 프로젝트 상으로는 JUnit5로 고정되어 있습니다.
 */
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // 테스트용 애너테이션 컨텍스트 생성
@AutoConfigureMockMvc   // MockMvc 생성 및 자동 구성 - 목업
class TestControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    //위에 부분은 객체 생성
    // 밑에 부분은 메서드
    @BeforeEach
    public void mockMvcSetup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();           //builder().build()의 응용 형태
    }

    @AfterEach  //테스트 실행 후 실행하는 메서드
    public void cleanUp(){
        memberRepository.deleteAll();
    }

    @DisplayName("getAllMembers : 아티클 조회에 성공한다.")
    @Test
    public void getAllMembers() throws Exception {       // throws Exception : 예외로 처리함
        // given
        final String url = "/test";   //앞으로 자주 나오는 방식이라 알아두시기 바랍니다.
        Member savedMember = memberRepository.save(new Member(1L,"홍길동"));
        // maybeags/web_development_java 에 c15_casting에 CentralControll.java/ Main.java 확인

        // when
        final ResultActions result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
    }

/*
    @SpringBootTest :
        애플리케이션 클래스에 추가하는 애너테이션인 @SpringBootApplication이
        있는 클래스를 찾고, 그 클래스에 포함돼 있는 빈을 찾은 다음,
        테스트용 어플리케이션 컨텍스트라는 것을 생성합니다.

    @AutoConfigureMocMvc :
        MockMvc를 생성하고 자동으로 구성하는 애너테이션으로,
        이 것은 애플리케이션을 서버에 배포하지 않고도 테스트용 MVC 환경을 만들어
        요청, 전송, 응갑 기능을 제공하는 유틸리티 클래스.
            즉, '컨트롤러를 테스트할 때 사용되는 클래스'
*/





}