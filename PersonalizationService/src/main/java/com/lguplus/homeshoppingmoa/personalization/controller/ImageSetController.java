package com.lguplus.homeshoppingmoa.personalization.controller;

import com.lguplus.homeshoppingmoa.common.model.stb.dto.request.StbInfo;
import com.lguplus.homeshoppingmoa.common.sec.ncas.model.NcasUserDetails;
import com.lguplus.homeshoppingmoa.common.sec.ncas.model.NcasUserDetailsHelper;
import com.lguplus.homeshoppingmoa.config.swagger.OpenApiConfig;
import com.lguplus.homeshoppingmoa.personalization.controller.dto.*;
import com.lguplus.homeshoppingmoa.personalization.model.entity.SettopBox;
import com.lguplus.homeshoppingmoa.personalization.service.ImageSetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "이미지노출설정 API", description = "")
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/api/stb/v1/imgset", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class ImageSetController {

    private final ImageSetService imageSetService;

    @Operation(
            summary = "이미지노출설정 조회",
            description = "이미지노출설정값을 조회한다.",
            security = {
                    @SecurityRequirement(name = OpenApiConfig.HEADER_NAME_AUTHORIZATION)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "success", content = @Content(schema = @Schema(implementation = ResponseImageSet.class)))
            }
    )
    @PostMapping
    public <U extends NcasUserDetails> ResponseEntity<ResponseImageSet> imageSet (@AuthenticationPrincipal U principal, RequestRootBody<?> requestRootBody) {
        StbInfo stbUserInfo = principal.getPayload().getParam();

        SettopBox settopBox = imageSetService.getImageSettingValue(stbUserInfo.getMacAddress(), stbUserInfo.getSubNo());
        return ResponseEntity.ok(ResponseImageSet.create(settopBox.getStbDisplayYn().value()));
    }

    @Operation(
            summary = "이미지노출설정 ON",
            description = "이미지노출설정 상태값을 노출로 변경한다",
            security = {
                    @SecurityRequirement(name = OpenApiConfig.HEADER_NAME_AUTHORIZATION)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "success", content = @Content(schema = @Schema(implementation = ResponseImageSet.class)))
            }
    )
    @PostMapping("/on")
    public <U extends NcasUserDetails> ResponseEntity<ResponseImageSet> imageSetOn (@AuthenticationPrincipal U principal, RequestRootBody<?> requestRootBody) {
        StbInfo stbUserInfo = principal.getPayload().getParam();

        SettopBox settopBox = imageSetService.updateImageSettingValueTurnOn(stbUserInfo.getMacAddress(), stbUserInfo.getSubNo());
        return ResponseEntity.ok(ResponseImageSet.create(settopBox.getStbDisplayYn().value()));
    }

    @Operation(
            summary = "이미지노출설정 OFF",
            description = "이미지노출설정 상태값을 비노출로 변경한다",
            security = {
                    @SecurityRequirement(name = OpenApiConfig.HEADER_NAME_AUTHORIZATION)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "success", content = @Content(schema = @Schema(implementation = ResponseImageSet.class)))
            }
    )
    @PostMapping("/off")
    public <U extends NcasUserDetails> ResponseEntity<ResponseImageSet> imageSetOff (@AuthenticationPrincipal U principal, RequestRootBody<?> requestRootBody) {
        StbInfo stbUserInfo = principal.getPayload().getParam();

        SettopBox settopBox = imageSetService.updateImageSettingValueTurnOff(stbUserInfo.getMacAddress(), stbUserInfo.getSubNo());
        return ResponseEntity.ok(ResponseImageSet.create(settopBox.getStbDisplayYn().value()));
    }

}
