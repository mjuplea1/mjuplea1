package com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@Schema(name = "ShoppingChannelLogoDto", description = "쇼핑채널 로고 Image file")
public class ShoppingChannelLogoDto {

    @Schema(name = "CHANNEL_LOGO_ORG_NAME", description = "원본 파일명")
    private String channelLogoOrgName;

    @Schema(name = "CHANNEL_LOGO_MIME", description = "mime 타입")
    private String channelLogoMime;

    @Schema(name = "CHANNEL_LOGO_SIZE", description = "로고 사이즈")
    private long channelLogoSize;

    @Schema(name = "url", description = "로고 URL")
    private String channelLogoUrl;

    public ShoppingChannelLogoDto(String orgName, String mime, long size) {
        this.channelLogoOrgName = orgName;
        this.channelLogoMime = mime;
        this.channelLogoSize = size;
    }

    public static ShoppingChannelLogoDto of(MultipartFile mf) {
        return new ShoppingChannelLogoDto(mf.getOriginalFilename(), mf.getContentType(), mf.getSize());
    }
}
