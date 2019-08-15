package com.sbs.cuni.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Member {
	private int id;
	private String regDate;
	private String loginId;
	private String loginPw;
	private String email;
	private String name;
	private String emailAuthKey;
	private boolean emailAuthStatus;
	private boolean delStatus;
	private int permissionLevel;
}