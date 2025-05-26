package ask.anything.dto;

import ask.anything.enumeration.StatusCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;


@Getter
public class CommonResponseDto<T> {

    // 0000 : 이면 성공
    // 그 외 실패.
    @Schema(description = "상태코드", example = "0000")
    private final String code;

    @Schema(description = "메시지", example = "정상처리되었습니다")
    private final String message;

    /* 사용자에게 알림을 해야할 경우 true */
    @Schema(description = "alert여부", example = "false")
    private final boolean alertFlag;

    @Schema(description = "요청 타입", example = "false")
    private String requestType;

    @Schema(description = "dataObject", example = "Object")
    private final T data;

    @JsonIgnore
    private final StatusCode statusCode;

    @Builder
    public CommonResponseDto(StatusCode statusCode, String code, String message, boolean alertFlag, String requestType, T data) {
        this.statusCode = ObjectUtils.isNotEmpty(statusCode) ? statusCode : StatusCode.SUCCESS;
        this.code       = StringUtils.isNotEmpty(code) ? code : this.statusCode.getCode();
        this.message    = StringUtils.isNotEmpty(message) ? message : this.statusCode.getMessage();
        this.alertFlag  = alertFlag;
        this.requestType  = requestType;
        this.data       = data;
    }
}
