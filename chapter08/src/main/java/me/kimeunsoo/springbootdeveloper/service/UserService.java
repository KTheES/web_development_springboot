package me.kimeunsoo.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.kimeunsoo.springbootdeveloper.domain.User;
import me.kimeunsoo.springbootdeveloper.dto.AddUserRequest;
import me.kimeunsoo.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;        // 이메일
    private final BCryptPasswordEncoder bCryptPasswordEncoder;  //비밀번호

    public Long save(AddUserRequest dto){
        return userRepository.save(User.builder()
                        .email(dto.getEmail())
                //1. 패스워드 암호화
                        .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }
    /*
        1. 패스워드를 저장할 때 시큐리티를 설정하며 패스워드 인코딩용으로 등록한 빈을 사용해서
            암호화한 이후에 저장

            컨트롤러 작성할겁니다
                회원 가입 폼에서 회원 가입 요청을 받으면 서비스 메서드를 사용해서
                사용자를 '저장'한 뒤, 로그인 페이지로 이동하는 signup() 메서드를 작성할 예정

                controller 패키지에 UserApiController.java 파일 생성
     */

}
