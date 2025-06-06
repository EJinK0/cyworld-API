package ask.anything.controller;

import ask.anything.dto.CommonResponseDto;
import ask.anything.entity.Member;
import ask.anything.service.MemberService;
import ask.anything.enumeration.StatusCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
@CrossOrigin
public class MemberController {
    private final MemberService memberService;

    @GetMapping(value = "/getMember")
    public ResponseEntity<CommonResponseDto> getMember(@RequestParam String memberId) {
        try {
            return ResponseEntity.ok(CommonResponseDto.builder()
                    .statusCode(StatusCode.SUCCESS)
                    .build());
        } catch (Exception ex) {
            return ResponseEntity.ok(CommonResponseDto.builder()
                    .statusCode(StatusCode.NOT_FOUND)
                    .build());
        }
    }

    @PostMapping(value = "/validateMemberId")
    public ResponseEntity<CommonResponseDto> validateMemberId(@RequestParam String memberId) {
        try {
            memberService.validateMemberId(memberId);

            return ResponseEntity.ok(CommonResponseDto.builder()
                    .statusCode(StatusCode.SUCCESS)
                    .build());
        } catch (Exception ex) {
            return ResponseEntity.ok(CommonResponseDto.builder()
                    .statusCode(StatusCode.NOT_FOUND)
                    .build());
        }
    }
}
