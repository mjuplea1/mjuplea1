package com.lguplus.homeshoppingmoa.common.sec.ncas.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lguplus.homeshoppingmoa.common.constants.CommonConstants;
import com.lguplus.homeshoppingmoa.common.model.stb.dto.request.Payload;
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
public class NcasUserDetailsHelper {

    private static final long JWT_EXP = 3; // 단위: 시간
    private static final String JWT_SECRET = "7w!z%C*F-JaNdRgUjXn2r5u8x/A?D(G+KbPeShVmYp3s6v9y$B&E)H@McQfTjWnZ";
    // JWT Header
    private static final String TOKEN_TYPE = "JWT";
	private static final String TOKEN_ISSUER = "홈쇼핑모아.uplus.com";
	private static final String TOKEN_SUBJECT = "홈쇼핑모아세톱박스";

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
	 * @param ncasUser
	 * @return
	 */
	public String generateJwt(NcasUserDetails ncasUser) {
        Date expDate = Date.from(LocalDateTime.now().plusHours(JWT_EXP).atZone(ZoneId.of("Asia/Seoul")).toInstant());
        return this.generateJwt(ncasUser, expDate);
    }

    private String generateJwt(NcasUserDetails ncasUser, Date expDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(CommonConstants.DEFAULT_DATE_FORMAT, Locale.KOREA);
        log.debug("> token expired date: {}", sdf.format(expDate));

        List<String> roles = ncasUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        return Jwts.builder()
                .signWith(this.getSecretKey(), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", TOKEN_TYPE)
                .setId(UUID.randomUUID().toString())
                .setIssuer(TOKEN_ISSUER)
                .setSubject(TOKEN_SUBJECT)
                .setExpiration(expDate)
                .setAudience(ncasUser.getUsername())
                .setIssuedAt(new Date())
                .claim("rol", roles)
                .claim("payload", ncasUser.getPayload())
                .compact();
    }

    /**
	 * JWT에서 NcasUserDetails 반환
	 *
	 * @param token
	 * @return
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public NcasUserDetails getSimpleUser(String token) {
        Claims claims = this.getClaimsFromAccessToken(token);
        String username = (String) claims.get("aud");

        Map<String, Map> map = (LinkedHashMap) claims.get("payload");
        Payload payload = new ObjectMapper().convertValue(map, Payload.class);

        List<GrantedAuthority> authorities = ((List<?>) claims.get("rol")).stream().map(authority -> new SimpleGrantedAuthority((String) authority)).collect(Collectors.toList());

        return new NcasUserDetails(username, payload, authorities);
    }

}
