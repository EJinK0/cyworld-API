package ask.anything.controller;

import ask.anything.dto.CommonResponseDto;
import ask.anything.dto.SignUpDto;
import ask.anything.enumeration.StatusCode;
import ask.anything.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    private final AuthService authService;

    @PostMapping("signUp")
    public ResponseEntity<CommonResponseDto> signUp(@RequestBody SignUpDto params) {
        try {
            if (authService.signUp(params) == 1) {
                return ResponseEntity.ok(CommonResponseDto.builder()
                        .statusCode(StatusCode.SUCCESS)
                        .message("회원가입이 완료되었습니다.")
                        .build());
            } else {
                return ResponseEntity.ok(CommonResponseDto.builder()
                        .statusCode(StatusCode.NOT_FOUND)
                        .message("회원가입에 실패했습니다.")
                        .build());
            }
        } catch (Exception ex) {
            return ResponseEntity.ok(CommonResponseDto.builder()
                    .statusCode(StatusCode.NOT_FOUND)
                    .message("회원가입에 실패했습니다.")
                    .build());
        }
    }
}
