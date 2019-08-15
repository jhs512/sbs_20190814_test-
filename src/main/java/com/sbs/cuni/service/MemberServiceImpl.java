package com.sbs.cuni.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.groovy.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sbs.cuni.dao.MemberDao;
import com.sbs.cuni.dto.Member;
import com.sbs.cuni.handler.MailHandler;
import com.sbs.cuni.util.CUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
	@Value("${custom.emailSender}")
	private String emailSender;
	@Value("${custom.emailSenderName}")
	private String emailSenderName;

	@Autowired
	private MemberDao memberDao;
	@Autowired
	private JavaMailSender sender;

	@Override
	public Map<String, Object> login(Map<String, Object> param) {
		Member loginedMember = (Member) memberDao.getMatchedOne(param);

		String msg = null;
		String resultCode = null;

		long loginedMemberId = 0;

		if (loginedMember == null) {
			resultCode = "F-1";
			msg = "일치하는 회원이 없습니다.";

			return Maps.of("resultCode", resultCode, "msg", msg);
		}

		if (loginedMember.isEmailAuthStatus() == false) {
			resultCode = "F-2";
			msg = "이메일 인증을 진행해주세요.";

			return Maps.of("resultCode", resultCode, "msg", msg);
		}

		loginedMemberId = loginedMember.getId();

		resultCode = "S-1";
		msg = "로그인 되었습니다.";

		return Maps.of("resultCode", resultCode, "msg", msg, "loginedMemberId", loginedMemberId);
	}

	@Override
	public Member getOne(long loginedMemberId) {
		return memberDao.getOne(loginedMemberId);
	}

	public Map<String, Object> doubleCheck(Map<String, Object> param) {
		int count = memberDao.doubleCheck(param);

		String msg = null;
		String resultCode = null;

		if (count <= 0) {
			param.put("authKey", CUtil.getTempKey());
			memberDao.addMember(param);
			MailHandler mail;
			try {
				mail = new MailHandler(sender);
				mail.setFrom(emailSender, emailSenderName);
				mail.setTo((String) param.get("email"));
				mail.setSubject("회원가입 이메일인증");
				mail.setText(new StringBuffer().append("<h1>이메일인증 메일</h1>")
						.append("<a href='http://localhost:8083/member/confirm?email=")
						.append((String) param.get("email")).append("&authKey=").append((String) param.get("authKey"))
						.append("' target='_blank'> 누르시면 메일 인증 페이지로 이동됩니다. </a>").toString());
				mail.send();
				msg = "회원가입에 성공했습니다.";
				resultCode = "S-2";
			} catch (Exception e) {
				msg = "회원가입에 실패했습니다.";
				resultCode = "F-2";
				e.printStackTrace();
			}
		} else {
			msg = "회원가입에 실패했습니다.";
			resultCode = "F-2";
		}

		return Maps.of("msg", msg, "resultCode", resultCode);
	}

	public Map<String, Object> searchId(Map<String, Object> param) {
		Member member = memberDao.searchId(param);
		System.out.println(member.getLoginId());
		String msg = null;
		String resultCode = null;
		MailHandler mail;
		try {
			mail = new MailHandler(sender);
			mail.setFrom(emailSender, emailSenderName);
			mail.setTo((String) param.get("email"));
			mail.setSubject("회원님의 아이디가 발송되었습니다.");
			mail.setText(new StringBuffer().append("<h1>아이디는 " + member.getLoginId() + " 입니다.</h1>").toString());
			mail.send();
			msg = "메일이 발송되었습니다.";
			resultCode = "S-2";
		} catch (Exception e) {
			msg = "메일 발송에 실패했습니다.";
			resultCode = "F-2";
			e.printStackTrace();
		}
		return Maps.of("msg", msg, "resultCode", resultCode);
	}

	public Map<String, Object> searchPw(Map<String, Object> param) {
		Member member = memberDao.searchPw(param);
		String msg = null;
		String resultCode = null;
		MailHandler mail;
		try {
			mail = new MailHandler(sender);
			mail.setFrom(emailSender, emailSenderName);
			mail.setTo((String) param.get("email"));
			mail.setSubject("회원님의 비밀번호가 발송되었습니다");
			mail.setText(new StringBuffer().append("<h1>비밀번호는 " + member.getLoginPw() + " 입니다.</h1>").toString());
			mail.send();
			msg = "메일이 발송되었습니다.";
			resultCode = "S-2";
		} catch (Exception e) {
			msg = "메일 발송에 실패했습니다.";
			resultCode = "F-2";
			e.printStackTrace();
		}
		return Maps.of("msg", msg, "resultCode", resultCode);
	}

	public Map<String, Object> update(Map<String, Object> args) {

		Map<String, Object> rs = new HashMap<String, Object>();

		memberDao.update(args);

		rs.put("resultCode", "S-1");

		rs.put("msg", "회원정보가 수정되었습니다.");

		return rs;
	}

	public Map<String, Object> updateAuthStatus(Map<String, Object> param) {
		Member member = memberDao.getEmailMember(param);
		String msg = "";
		String resultCode = "";
		if (member == null) {
			msg = "업데이트에 실패했습니다.";
			resultCode = "F-5";
		} else {
			memberDao.updateAuthStatus(param);
			msg = "업데이트에 성공했습니다.";
			resultCode = "S-5";
		}

		return Maps.of("msg", msg, "resultCode", resultCode);
	}

	public Map<String, Object> updateDelStatus(Map<String, Object> param) {

		Map<String, Object> rs = new HashMap<String, Object>();

		memberDao.updateDelStatus(param);

		rs.put("resultCode", "S-1");
		rs.put("msg", "탈퇴 되었습니다.");

		return rs;
	}

	@Override
	public boolean isMasterMember(long loginedMemberId) {
		Member member = memberDao.getOne(loginedMemberId);
		return member.getPermissionLevel() > 1;
	}

}