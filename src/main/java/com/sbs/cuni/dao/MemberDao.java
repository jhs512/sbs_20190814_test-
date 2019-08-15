package com.sbs.cuni.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sbs.cuni.dto.Member;

@Mapper
public interface MemberDao {

	public Member getMatchedOne(Map<String, Object> param);

	public Member getOne(long loginedMemberId);

	public Member searchId(Map<String, Object> param);

	public Member searchPw(Map<String, Object> param);

	public int doubleCheck(Map<String, Object> param);

	public void addMember(Map<String, Object> param);

	public Member getEmailMember(Map<String, Object> param);

	public void update(Map<String, Object> args);

	public void updateAuthStatus(Map<String, Object> param);

	public void updateDelStatus(Map<String, Object> param);

	public void modifyPw(Map<String, Object> param);

}