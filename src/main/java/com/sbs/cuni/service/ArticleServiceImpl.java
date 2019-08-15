package com.sbs.cuni.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.groovy.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.cuni.dao.ArticleDao;
import com.sbs.cuni.dto.Article;
import com.sbs.cuni.dto.ArticleReply;
import com.sbs.cuni.dto.Board;
import com.sbs.cuni.util.CUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
	public static final int LIST_ITEMS_COUNT_IN_A_PAGE = 5;

	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private MemberService memberService;

	private int _getCount(Map<String, Object> args) {
		return articleDao.getCount(args);
	}

	private List<Article> _getList(Map<String, Object> args) {
		if (args.containsKey("extra__repliesCount") && (boolean) args.containsKey("extra__repliesCount") == true) {
			args.put("leftJoin__articleReply", true);
			args.put("groupBy__articleId", true);
		}

		int page = 1;
		if (args.containsKey("page")) {
			String pageStr = (String) args.get("page");
			page = Integer.parseInt(pageStr);
		}

		args.put("limitOffset", LIST_ITEMS_COUNT_IN_A_PAGE * (page - 1));
		args.put("limit", LIST_ITEMS_COUNT_IN_A_PAGE);

		return articleDao.getList(args);
	}

	@Override
	public Map<String, Object> getPagedList(Map<String, Object> param) {
		Map<String, Object> rs = new HashMap<>();

		int totalItemsCount = _getCount(param);
		int lastPage = (int) Math.ceil(totalItemsCount / (double) ArticleServiceImpl.LIST_ITEMS_COUNT_IN_A_PAGE);

		rs.put("page", CUtil.getAsInt(param.get("page")));
		rs.put("lastPage", lastPage);

		List<Article> list = _getList(param);

		rs.put("list", list);

		return rs;
	}

	@Override
	public List<Article> getList(Map<String, Object> args) {
		return _getList(args);
	}

	@Override
	public int getCount(Map<String, Object> args) {
		return _getCount(args);
	}

	public List<ArticleReply> getReplies(Map<String, Object> args) {
		return articleDao.getReplies(args);
	}

	public Article getOne(Map<String, Object> args) {
		return articleDao.getOne(args);
	}

	public ArticleReply getReply(Map<String, Object> args) {
		return articleDao.getReply(args);
	}

	public Article getBoardId(Map<String, Object> args) {
		return articleDao.getBoardId(args);
	}

	public long add(Map<String, Object> args) {
		articleDao.add(args);

		return CUtil.getAsLong(args.get("id"));
	}

	public long addReply(Map<String, Object> args) {
		articleDao.addReply(args);

		return CUtil.getAsLong(args.get("id"));
	}

	public Map<String, Object> modify(Map<String, Object> args) {

		Map<String, Object> rs = new HashMap<String, Object>();

		articleDao.modify(args);

		long id = CUtil.getAsLong(args.get("id"));

		rs.put("resultCode", "S-1");
		rs.put("msg", id + "번 게시물이 수정되었습니다.");

		return rs;
	}

	public Map<String, Object> updateReply(Map<String, Object> args) {

		ArticleReply reply = articleDao.getReply((long)args.get("id"));
		
		String msg = "";
		String resultCode = "";
		
		if (reply == null) {
			msg = "존재하지 않는 댓글 정보";
			resultCode = "F-4";
		} else if (reply.getMemberId() != (long)args.get("memberId")) {
			msg = "권한이 없습니다.";
			resultCode = "F-4";
		} else {
			articleDao.modifyReply(args);
			msg = "댓글이 수정되었습니다.";
			resultCode = "S-4";
		}

		return Maps.of("msg", msg, "resultCode", resultCode);
	}

	public Map<String, Object> delete(Map<String, Object> args) {

		Map<String, Object> rs = new HashMap<String, Object>();

		long id = (long) args.get("id");

		articleDao.delete(id);

		articleDao.deleteReplies(id);

		rs.put("resultCode", "S-1");
		rs.put("msg", id + "번 게시물이 삭제되었습니다.");

		return rs;
	}

	public ArticleReply getReply(long id) {
		return getReply(Maps.of("id", id));
	}

	public Map<String, Object> deleteReply(Map<String, Object> param) {

		ArticleReply articleReply = articleDao.getReply(CUtil.getAsLong(param.get("id")));

		long memberId = (long) param.get("loginedMemberId");

		String msg = "";
		String resultCode = "";

		if (articleReply == null) {
			msg = "존재하지 않는 댓글 정보";
			resultCode = "F-4";
		} else if (articleReply.getMemberId() != memberId) {
			msg = "권한이 없습니다.";
			resultCode = "F-4";
		} else {
			articleDao.deleteReply(param);
			msg = "삭제했습니다.";
			resultCode = "S-4";
		}

		return Maps.of("msg", msg, "resultCode", resultCode);
	}

	@Override
	public Map<String, Object> checkModifyPermmision(long id, long loginedMemberId) {
		Article article = articleDao.getOne(Maps.of("id", id));

		if (article == null) {
			return Maps.of("resultCode", "F-1", "msg", "존재하지 않는 게시물 입니다.");
		}

		if (memberService.isMasterMember(loginedMemberId)) {
			return Maps.of("resultCode", "S-1", "msg", "마스터회원은 모든 게시물을 수정할 수 있습니다.");
		}

		if (article.getMemberId() == loginedMemberId) {
			return Maps.of("resultCode", "S-2", "msg", "게시물의 소유자는 해당 게시물을 수정 할 수 있습니다.");
		}

		return Maps.of("resultCode", "F-2", "msg", "권한이 없습니다.");
	}

	@Override
	public Board getBoard(long id) {
		return articleDao.getBoard(id);
	}

}