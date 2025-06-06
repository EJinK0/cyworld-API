package ask.anything.service;

import ask.anything.dto.SignUpDto;
import ask.anything.entity.Member;
import ask.anything.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Integer signUp(SignUpDto dto) throws Exception {
        if (!StringUtils.isEmpty(dto.getUserid())) {
            String encodedPassword = passwordEncoder.encode(dto.getPassword());

            memberRepository.save(
                Member.builder()
                    .memberNo(UUID.randomUUID().toString())
                    .memberPw(encodedPassword)
                    .memberId(dto.getUserid())
                    .name(dto.getName())
                    .gender(dto.getGender())
                    .birthDay(dto.getBirthday())
                    .nickName(dto.getNickName())
                    .build());

            return 1;
        } else {
            throw new IllegalArgumentException("올바르지 않은 사용자입니다.");
        }
    }
}
