package com.lguplus.homeshoppingmoa.personalization.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lguplus.homeshoppingmoa.common.model.stb.dto.request.Payload;
import com.lguplus.homeshoppingmoa.common.sec.ncas.model.NcasUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NcasAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private final NcasAuthService ncasAuthService;
	private final ObjectMapper objectMapper;
    private final SettopBoxService settopBoxService;

	@SneakyThrows
    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) {
		final Payload payload = (Payload) authentication.getPrincipal();
		return this.ncasAuthService.loadUserByUsername(this.objectMapper.writeValueAsString(payload));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) {
	    Payload payload = ((NcasUserDetails) userDetails).getPayload();
	    this.settopBoxService.saveOrUpdate(payload);
    }

}
