package ask.anything.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {
    @Schema(description = "사용자 id", example = "jkcyworld")
    private String userid;

    @Schema(description = "비밀번호", example = "12345678")
    private String password;

    @Schema(description = "이름", example = "이진경")
    private String name;

    @Schema(description = "성별", example = "F")
    private String gender;

    @Schema(description = "생년월일", example = "1995.11.08")
    private String birthday;

    @Schema(description = "닉네임", example = "진교니")
    private String nickName;
}
