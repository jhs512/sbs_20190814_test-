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
public class Board {
	private long id;
	private String regDate;
	private String name;
	private Map<String, String> extra;
}