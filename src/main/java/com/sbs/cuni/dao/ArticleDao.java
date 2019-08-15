package com.sbs.cuni.dao;

import java.util.List;
import java.util.Map;

import org.apache.groovy.util.Maps;
import org.apache.ibatis.annotations.Mapper;

import com.sbs.cuni.dto.Article;
import com.sbs.cuni.dto.ArticleReply;
import com.sbs.cuni.dto.Board;

@Mapper
public interface ArticleDao {
	public List<Article> getList(Map<String, Object> args);

	public int getCount(Map<String, Object> args);

	public List<ArticleReply> getReplies(Map<String, Object> args);

	public Article getOne(Map<String, Object> args);

	public ArticleReply getReply(Map<String, Object> args);

	default public ArticleReply getReply(long id) {
		return getReply(Maps.of("id", id));
	}

	public Board getBoard(Object id);

	public Article getBoardId(Map<String, Object> args);

	public void add(Map<String, Object> args);

	public void addReply(Map<String, Object> args);

	public void modify(Map<String, Object> args);

	public void modifyReply(Map<String, Object> args);

	public void delete(long id);

	public void deleteReplies(long articleId);

	public void deleteReply(Map<String, Object> param);
}