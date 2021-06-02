package com.lguplus.homeshoppingmoa.common.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(name = "SampleMemberDto", description = "샘플멤버 DTO")
@ToString
public class SampleMemberDto implements Serializable {

	private static final long serialVersionUID = 6263036439789234390L;

	@Schema(name = "seq", description = "샘플멤버 일련번호")
	private long seq;

	@Schema(name = "username", description = "아이디", maxLength = 20)
	private String username;

	@Schema(name = "password", description = "비밀번호")
	private String password;

	@Schema(name = "name", description = "이름")
	private String name;

	@Schema(name = "authorities", description = "권한", defaultValue = "ROLE_OPERATOR", allowableValues = { "ROLE_OPERATOR", "ROLE_SUPERADMIN" })
	private String authorities;

	@Schema(name = "regDt", description = "등록 일시")
	private LocalDateTime regDt;

	@Schema(name = "lastLoginDt", description = "마지막 로그인 일시")
	private LocalDateTime lastLoginDt;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@Schema(name = "attachFiles", description = "첨부파일 목록", nullable = true)
	private List<AttachFileDto> attachFiles = new ArrayList<>();

}
