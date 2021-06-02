package com.lguplus.homeshoppingmoa.common.sec.model;

import com.lguplus.homeshoppingmoa.common.constants.CommonConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class CustomUserDetailsHelper {

    private static final long JWT_EXP = 3; // 단위: 시간
    private static final String JWT_SECRET = "7w!z%C*F-JaNdRgUjXn2r5u8x/A?D(G+KbPeShVmYp3s6v9y$B&E)H@McQfTjWnZ";
    // JWT Header
    private static final String TOKEN_TYPE = "JWT";
	private static final String TOKEN_ISSUER = "홈쇼핑모아.uplus.com";
	private static final String TOKEN_SUBJECT = "홈쇼핑모아운영자";

    private final CustomUserDetails systemUser = new CustomUserDetails("system", "시스템", Collections.singletonList(new SimpleGrantedAuthority("ROLE_SYSTEM")), 0L);

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
    }

	private Jws<Claims> getJwsClaims(String authorization) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getSecretKey())
                .build()
                .parseClaimsJws(authorization.replace(CommonConstants.TOKEN_PREFIX, ""));
    }

    /**
     * Claims 반환
     *
     * @param accessToken
     * @return
     */
    private Claims getClaimsFromAccessToken(String accessToken) {
        return this.getJwsClaims(accessToken).getBody();
    }

    /**
	 * JWT 토큰 반환
	 *
	 * @param customUser
	 * @return
	 */
	public String generateJwt(CustomUserDetails customUser) {
        Date expDate = Date.from(LocalDateTime.now().plusHours(JWT_EXP).atZone(ZoneId.of("Asia/Seoul")).toInstant());
        return this.generateJwt(customUser, expDate);
    }

    /**
     * 시스템용 JWT 토큰 반환
     *
     * @return
     */
    public String generateJwt4System() {
        Date expDate = Date.from(LocalDateTime.now().plusHours(24).atZone(ZoneId.of("Asia/Seoul")).toInstant());
        return this.generateJwt(this.systemUser, expDate);
    }

    private String generateJwt(CustomUserDetails customUser, Date expDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(CommonConstants.DEFAULT_DATE_FORMAT, Locale.KOREA);
        log.debug("> token expired date: {}", sdf.format(expDate));

        List<String> roles = customUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        Long accountId = (customUser.getAccountId() == null) ? 0L : customUser.getAccountId();

        return Jwts.builder()
                .signWith(this.getSecretKey(), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", TOKEN_TYPE)
                .setId(UUID.randomUUID().toString())
                .setIssuer(TOKEN_ISSUER)
                .setSubject(TOKEN_SUBJECT)
                .setExpiration(expDate)
                .setAudience(customUser.getUsername())
                .setIssuedAt(new Date())
                .claim("rol", roles)
                .claim("name", customUser.getName())
                .claim("accountId", accountId)
                .compact();
    }

    /**
	 * JWT에서 CustomUserDetails 반환
	 *
	 * @param token
	 * @return
	 */
	public CustomUserDetails getSimpleUser(String token) {
        Claims claims = this.getClaimsFromAccessToken(token);

        String username = (String) claims.get("aud");
        String name = (String) claims.get("name");
        Long accountId = Long.parseLong(String.valueOf(claims.get("accountId")));
        List<GrantedAuthority> authorities = ((List<?>) claims.get("rol")).stream().map(authority -> new SimpleGrantedAuthority((String) authority)).collect(Collectors.toList());

		return new CustomUserDetails(username, name, authorities, accountId);
    }

}
