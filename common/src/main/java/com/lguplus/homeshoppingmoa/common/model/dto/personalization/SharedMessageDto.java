package com.lguplus.homeshoppingmoa.common.model.dto.personalization;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(name = "SharedMessageDto", description = "공유 메시지 DTO")
public class SharedMessageDto {

    // mac 주소
    private String macAddress;

    // 가입자 번호
    private String subNo;

    // 공유하려는 상품 ID
    private long mainProductId;

    // 수신자 전화번호
    private String recvCtn;

    // 메시지 제목
    private String msgTitle;

    // 메시지 내용
    private String msgContent;

    @Builder
    public SharedMessageDto(String macAddress, String subNo, long mainProductId, String recvCtn, String msgTitle, String msgContent) {
        this.macAddress = macAddress;
        this.subNo = subNo;
        this.mainProductId = mainProductId;
        this.recvCtn = recvCtn;
        this.msgTitle = msgTitle;
        this.msgContent = msgContent;
    }
}
