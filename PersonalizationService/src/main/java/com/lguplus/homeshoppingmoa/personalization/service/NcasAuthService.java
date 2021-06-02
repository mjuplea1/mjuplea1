package com.lguplus.homeshoppingmoa.personalization.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lguplus.homeshoppingmoa.common.model.dto.RequestNcasAuthDto;
import com.lguplus.homeshoppingmoa.common.model.dto.ResponseNcasAuthDto;
import com.lguplus.homeshoppingmoa.common.model.stb.dto.request.Payload;
import com.lguplus.homeshoppingmoa.common.model.stb.dto.request.StbInfo;
import com.lguplus.homeshoppingmoa.common.sec.ncas.model.NcasUserDetails;
import com.lguplus.homeshoppingmoa.common.wrapper.CommonModel;
import com.lguplus.homeshoppingmoa.personalization.rest.NcasServiceApiClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor
public class NcasAuthService implements UserDetailsService {

    private final ObjectMapper objectMapper;
    // https://www.notion.so/32a2cf72237548aeaa0ee7ad0e30d4ff
    private final NcasServiceApiClient ncasServiceApiClient;

    @Transactional
    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String payloadJson) {
        Payload<StbInfo> payload = this.objectMapper.readValue(payloadJson, Payload.class);

        RequestNcasAuthDto ncasAuthDto = new RequestNcasAuthDto(payload.getParam().getMemberNo(), payload.getParam().getSubNo(), payload.getParam().getMacAddress());

        boolean authFlag = false;
        try {
            CommonModel<ResponseNcasAuthDto> ncasResult = this.ncasServiceApiClient.find(ncasAuthDto);
            authFlag = ncasResult.getResult().isAuthFlag();
        }
        catch (RuntimeException re) {
            log.error("NCas 연동 오류", re);
        }

        if (!authFlag) {
            throw new UsernameNotFoundException("MemberNo : (" + payload.getParam().getMemberNo()
                    + ") SubNo : (" + payload.getParam().getSubNo()
                    + ") MacAddr : (" + payload.getParam().getMacAddress()
                    + "), not exists.");
        }

        return new NcasUserDetails(payload.getParam().getMacAddress(), payload, Collections.singletonList(new SimpleGrantedAuthority("ROLE_NCAS_USER")));
    }

}
