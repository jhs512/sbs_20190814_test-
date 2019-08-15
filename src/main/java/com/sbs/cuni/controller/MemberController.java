package com.sbs.cuni.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.cuni.dto.Member;
import com.sbs.cuni.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;

	@RequestMapping("member/login")
	public String showLogin() {
		return "member/login";
	}

	@RequestMapping("member/findInfo")
	public String findInfo() {
		return "member/findInfo";
	}

	@RequestMapping("member/doLogin")
	public String doLogin(@RequestParam Map<String, Object> param, HttpSession session, Model model) {
		Map<String, Object> rs = memberService.login(param);

		String resultCode = (String) rs.get("resultCode");
		String msg = (String) rs.get("msg");

		model.addAttribute("alertMsg", msg);

		String redirectUrl = (String) param.get("redirectUrl");

		if (redirectUrl == null || redirectUrl.length() == 0) {
			redirectUrl = "/member/myPage";
		}

		if (resultCode.startsWith("S-")) {
			model.addAttribute("redirectUrl", redirectUrl);
			session.setAttribute("loginedMemberId", rs.get("loginedMemberId"));
		} else {
			model.addAttribute("historyBack", true);
		}

		return "common/redirect";
	}

	@RequestMapping("member/join")
	public String showJoin() {
		return "member/join";
	}

	@RequestMapping("member/doJoin")
	public String doJoin(Model model, @RequestParam Map<String, Object> param, HttpSession session) {

		Map<String, Object> rs = memberService.doubleCheck(param);

		String resultCode = (String) rs.get("resultCode");

		String redirectUrl = (String) param.get("redirectUrl");

		if (redirectUrl == null || redirectUrl.length() == 0) {
			redirectUrl = "/member/login";
		}

		String msg = (String) rs.get("msg");

		model.addAttribute("alertMsg", msg);

		if (resultCode.startsWith("S-")) {
			model.addAttribute("redirectUrl", redirectUrl);
		} else {
			model.addAttribute("historyBack", true);
		}

		return "common/redirect";

	}

	@RequestMapping("member/doLogout")
	public String doLogout(HttpSession session, Model model) {
		session.removeAttribute("loginedMemberId");
		model.addAttribute("alertMsg", "로그아웃 되었습니다.");
		model.addAttribute("redirectUrl", "/member/login");
		return "common/redirect";
	}

	@RequestMapping("member/myPage")
	public String myPage(Model model, HttpSession session) {
		long loginedMemberId = (long) session.getAttribute("loginedMemberId");
		Member member = memberService.getOne(loginedMemberId);
		model.addAttribute("member", member);
		return "member/myPage";
	}

	@RequestMapping("member/modify")
	public String modify(Model model, HttpSession session) {
		long loginedMemberId = (long) session.getAttribute("loginedMemberId");
		Member member = memberService.getOne(loginedMemberId);
		model.addAttribute("member", member);
		return "member/modify";
	}

	@RequestMapping("/member/doModify")
	@ResponseBody
	public String doModify(Model model, @RequestParam Map<String, Object> param, HttpSession session) {
		long loginedMemberId = (long) session.getAttribute("loginedMemberId");
		param.put("id", loginedMemberId);

		Map<String, Object> updateRs = memberService.update(param);

		StringBuilder sb = new StringBuilder();

		sb.append("<script>");

		String msg = (String) updateRs.get("msg");

		sb.append("alert('" + msg + "');");

		if (((String) updateRs.get("resultCode")).startsWith("S-")) {
			sb.append("location.replace('./myPage');");
		} else {
			sb.append("history.back();");
		}

		sb.append("</script>");

		return sb.toString();
	}

	@RequestMapping("member/confirm")
	public String confirm(@RequestParam Map<String, Object> param, Model model) {
		Map<String, Object> rs = memberService.updateAuthStatus(param);
		String msg = (String) rs.get("msg");
		String resultCode = (String) rs.get("resultCode");

		model.addAttribute("alertMsg", msg);

		String redirectUrl = "/member/login";
		model.addAttribute("redirectUrl", redirectUrl);

		return "common/redirect";
	}

	@RequestMapping("member/doSecession")
	public String secession(@RequestParam Map<String, Object> param, Model model, HttpSession session) {
		long loginedMemberId = (long) session.getAttribute("loginedMemberId");
		param.put("id", loginedMemberId);
		Map<String, Object> rs = memberService.updateDelStatus(param);
		session.removeAttribute("loginedMemberId");

		String msg = (String) rs.get("msg");
		String resultCode = (String) rs.get("resultCode");

		model.addAttribute("alertMsg", msg);

		String redirectUrl = "/member/login";
		model.addAttribute("redirectUrl", redirectUrl);

		return "common/redirect";
	}

	@RequestMapping("member/doSearchId")
	public String doSearchId(@RequestParam Map<String, Object> param, HttpSession session, Model model) {
		Map<String, Object> rs = memberService.searchId(param);

		String resultCode = (String) rs.get("resultCode");

		String msg = (String) rs.get("msg");

		model.addAttribute("alertMsg", msg);

		String redirectUrl = (String) param.get("redirectUrl");

		if (redirectUrl == null || redirectUrl.length() == 0) {
			redirectUrl = "/member/login";
		}

		if (resultCode.startsWith("S-")) {
			model.addAttribute("redirectUrl", redirectUrl);
			session.setAttribute("loginedMemberId", rs.get("loginedMemberId"));
		} else {
			model.addAttribute("historyBack", true);
		}

		return "common/redirect";
	}

	@RequestMapping("member/doSearchPw")
	public String doSearchPw(@RequestParam Map<String, Object> param, HttpSession session, Model model) {
		Map<String, Object> rs = memberService.searchPw(param);

		String resultCode = (String) rs.get("resultCode");

		String msg = (String) rs.get("msg");

		model.addAttribute("alertMsg", msg);

		String redirectUrl = (String) param.get("redirectUrl");

		if (redirectUrl == null || redirectUrl.length() == 0) {
			redirectUrl = "/member/login";
		}

		if (resultCode.startsWith("S-")) {
			model.addAttribute("redirectUrl", redirectUrl);
			session.setAttribute("loginedMemberId", rs.get("loginedMemberId"));
		} else {
			model.addAttribute("historyBack", true);
		}

		return "common/redirect";
	}
}