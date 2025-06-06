package ask.anything.dto;

import ask.anything.entity.BaseDatetime;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    @Schema(description = "사용자 uid", example = "dfdc5754-a2f4-42a2-ba5a-6cb40c3af64d")
    private String memberNo;

    @Schema(description = "사용자 아이디", example = "jkworld")
    private String memberId;

    @Schema(description = "비밀번호", example = "12345678")
    private String memberPw;

    @Schema(description = "이름", example = "이진경")
    private String name;

    @Schema(description = "성별", example = "F")
    private String gender;

    @Schema(description = "생년월일", example = "1995.11.08")
    private String birthDay;

    @Schema(description = "닉네임", example = "진교니")
    private String nickName;
}