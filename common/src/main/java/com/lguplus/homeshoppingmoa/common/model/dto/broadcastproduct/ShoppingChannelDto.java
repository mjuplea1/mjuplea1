package com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(name = "ShoppingChannelDto", description = "쇼핑 채널 DTO")
public class ShoppingChannelDto {

    @Schema(name = "channelId", description = "채널 아이디")
    private long channelId;

    @Schema(name = "channelName", description = "채널 이름")
    private String channelName;

    @Schema(name = "channelServiceId", description = "채널 서비스 아이디")
    private String channelServiceId;

    @Schema(name = "channelLogoUrl", description = "채널 로고 URL")
    private String channelLogoUrl;

    @Schema(name = "channelNum", description = "채널 번호")
    private int channelNum;

    @Schema(name = "channelURL", description = "채널 API URL")
    private String channelURL;

    @Schema(name = "channelStatus", description = "채널 노출 여부")
    private String channelStatus;

    @Schema(name = "regDt", description = "등록일")
    private LocalDateTime regDt;

    @Schema(name = "updDt", description = "수정일")
    private LocalDateTime updDt;

    public ShoppingChannelDto() {
    }

    public ShoppingChannelDto(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingChannelDto)) return false;
        ShoppingChannelDto that = (ShoppingChannelDto) o;
        return channelId == that.channelId
                && Objects.equals(channelServiceId, that.channelServiceId)
                && channelNum == that.channelNum
                && Objects.equals(channelName, that.channelName)
                && Objects.equals(channelLogoUrl, that.channelLogoUrl)
                && Objects.equals(channelURL, that.channelURL)
                && Objects.equals(channelStatus, that.channelStatus)
                && Objects.equals(updDt, that.updDt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                channelId,
                channelServiceId,
                channelNum,
                channelName,
                channelLogoUrl,
                channelURL,
                channelStatus,
                updDt);
    }

}
