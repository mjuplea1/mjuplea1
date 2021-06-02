package com.lguplus.homeshoppingmoa.common.sec.ncas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lguplus.homeshoppingmoa.common.model.stb.dto.request.Payload;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

public class NcasUserDetails implements UserDetails {

	private static final long serialVersionUID = -5040562823453196756L;

	@Getter
	private final String username;

	@JsonIgnore
	@Getter
	private String password;

	@Getter
	private final Set<GrantedAuthority> authorities;

	@Getter
	private final Payload payload;

	public NcasUserDetails(String username, Payload payload, Collection<? extends GrantedAuthority> authorities) {
		this.username = username;
		this.payload = payload;
		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
	}

	public NcasUserDetails(String username, String password, Payload payload, Collection<? extends GrantedAuthority> authorities) {
		this(username, payload, authorities);
		this.password = password;
    }

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * 권한정보 정렬
	 *
	 * @param authorities
	 * @return
	 */
	private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
		SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(new AuthorityComparator());

		for (GrantedAuthority grantedAuthority : authorities) {
			Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
			sortedAuthorities.add(grantedAuthority);
		}

		return sortedAuthorities;
	}

	private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {

		private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

		@Override
		public int compare(GrantedAuthority g1, GrantedAuthority g2) {
			if (g2.getAuthority() == null) {
				return -1;
			}
			if (g1.getAuthority() == null) {
				return 1;
			}

			return g1.getAuthority().compareTo(g2.getAuthority());
		}

	}

}
