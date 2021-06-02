package com.lguplus.homeshoppingmoa.personalization.controller;

import com.lguplus.homeshoppingmoa.common.model.stb.dto.request.StbInfo;
import com.lguplus.homeshoppingmoa.common.sec.ncas.model.NcasUserDetails;
import com.lguplus.homeshoppingmoa.config.swagger.OpenApiConfig;
import com.lguplus.homeshoppingmoa.personalization.controller.dto.RequestRootBody;
import com.lguplus.homeshoppingmoa.personalization.controller.dto.RequestShareMessage;
import com.lguplus.homeshoppingmoa.personalization.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "공유문자 발송요청 API", description = "")
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/api/stb/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class MessageController {

    private final MessageService messageService;

    @Operation(
            summary = "공유문자 발송요청",
            description = "공유문자를 발송요청한다.",
            security = {
                    @SecurityRequirement(name = OpenApiConfig.HEADER_NAME_AUTHORIZATION)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "success")
            }
    )
    @PostMapping("/message/send")
    public <U extends NcasUserDetails> void sendMessage(@AuthenticationPrincipal U principal, RequestRootBody<RequestShareMessage> requestRootBody) {
        StbInfo stbUserInfo = (StbInfo) principal.getPayload().getParam();
        RequestShareMessage requestShareMessage = requestRootBody.getParam();

        messageService.sendSharedMessage(stbUserInfo.getMacAddress(), stbUserInfo.getSubNo(), requestShareMessage);
    }

}
