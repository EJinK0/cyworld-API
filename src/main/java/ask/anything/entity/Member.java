package ask.anything.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "MEMBER")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseDatetime{
    @Id
    @Schema(description = "사용자 아이디", example = "id")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "MEMBER_NO", length = 36, updatable = false, nullable = false, unique = true)
    private String memberNo;

    @Column(name = "MEMBER_ID")
    @Comment("아이디")
    private String memberId;

    @Column(name = "MEMBER_PW")
    @Comment("비밀번호")
    private String memberPw;

    @Column(name = "NAME")
    @Comment("이름")
    private String name;

    @Column(name = "GENDER")
    @Comment("성별")
    private String gender;

    @Column(name = "BIRTHDAY")
    @Comment("생년월일")
    private String birthDay;

    @Column(name = "NICKNAME")
    @Comment("닉네임")
    private String nickName;
}