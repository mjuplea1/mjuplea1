package com.lguplus.homeshoppingmoa.common.sec;

import com.lguplus.homeshoppingmoa.common.sec.model.CustomUserDetails;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

	@SneakyThrows
    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) {
        final String userInputPassword = (String) authentication.getCredentials();

        CustomUserDetails customUser = (CustomUserDetails) this.userDetailsService.loadUserByUsername(username);
        log.debug("> userInputPassword: {}, customUser.getPassword(): {}", userInputPassword, customUser.getPassword());

        if (!this.passwordEncoder.matches(userInputPassword, customUser.getPassword())) {
            throw new BadCredentialsException(messages.getMessage("badCredentials", "NOT_MATCH"));
        }

        return new CustomUserDetails(customUser.getUsername(), customUser.getName(), customUser.getAuthorities());
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) {
		//
    }

}
