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

    @PostMapping(value = "/new")
    public ResponseEntity<CommonResponseDto> create(@RequestBody Member member) {
        try {
            String memberUUID = memberService.join(member);

            return ResponseEntity.ok(CommonResponseDto.builder()
                    .statusCode(StatusCode.SUCCESS)
                    .data(memberUUID)
                    .build());
        } catch (Exception ex) {
            return ResponseEntity.ok(CommonResponseDto.builder()
                    .statusCode(StatusCode.NOT_FOUND)
                    .build());
        }
    }
}
