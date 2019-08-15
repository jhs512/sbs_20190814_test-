package com.sbs.cuni.dto;

import java.util.Map;

import org.springframework.web.util.HtmlUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ArticleReply {
	private long id;
	private String regDate;
	private long articleId;
	private long memberId;
	private long boardId;
	private String body;
	private Map<String, String> extra;

	public String getBodyForPrint() {
		String bodyForPrint = HtmlUtils.htmlEscape(body);
		bodyForPrint = bodyForPrint.replace("\n", "<br>");

		return bodyForPrint;
	}
}