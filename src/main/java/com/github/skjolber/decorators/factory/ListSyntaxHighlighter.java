package com.github.skjolber.decorators.factory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.github.skjolber.jackson.jsh.AnsiSyntaxHightlight;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

public class ListSyntaxHighlighter implements SyntaxHighlighter {

	protected static final String CLEAR_START = AnsiSyntaxHightlight.ESC_START + AnsiSyntaxHightlight.CLEAR;
	protected static final String RESET_COMMAND = AnsiSyntaxHightlight.CLEAR + AnsiSyntaxHightlight.SEPERATOR;
	
	protected List<SyntaxHighlighter> list;
	
	public ListSyntaxHighlighter(List<SyntaxHighlighter> list) {
		this.list = list;
	}
	
	public List<SyntaxHighlighter> getList() {
		return list;
	}
	
	protected void append(String ansi, StringBuilder builder) {
		if(!ansi.startsWith(AnsiSyntaxHightlight.ESC_START)) {
			throw new IllegalArgumentException("Highlighter strings must start with '\\u001B['");
		}
		if(!ansi.endsWith(AnsiSyntaxHightlight.ESC_END)) {
			throw new IllegalArgumentException("Highlighter strings end with '" + AnsiSyntaxHightlight.ESC_END + "'");
		}
		
		if(ansi.length() > AnsiSyntaxHightlight.ESC_START.length() + AnsiSyntaxHightlight.ESC_END.length()) {
			// at least one code
			if(ansi.charAt(2) == '0') {
				if(ansi.charAt(3) == 'm') {
					// ignore clear
					return;
				} else if(ansi.charAt(3) == ';') {
					// ignore clear start
					builder.append(ansi, AnsiSyntaxHightlight.ESC_START.length() + 1, ansi.length() - AnsiSyntaxHightlight.ESC_END.length());
					return;
				}
			}
			builder.append(AnsiSyntaxHightlight.SEPERATOR);
			builder.append(ansi, AnsiSyntaxHightlight.ESC_START.length(), ansi.length() - AnsiSyntaxHightlight.ESC_END.length());
		}
	}

	@Override
	public String forCurlyBrackets() {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forCurlyBrackets(), builder);
		}
		builder.append(AnsiSyntaxHightlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forSquareBrackets() {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forSquareBrackets(), builder);
		}
		builder.append(AnsiSyntaxHightlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forColon() {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forColon(), builder);
		}
		builder.append(AnsiSyntaxHightlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forComma() {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forComma(), builder);
		}
		builder.append(AnsiSyntaxHightlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forWhitespace() {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forWhitespace(), builder);
		}
		builder.append(AnsiSyntaxHightlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forFieldName(String value) {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forFieldName(value), builder);
		}
		builder.append(AnsiSyntaxHightlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forNumber(int value) {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forNumber(value), builder);
		}
		builder.append(AnsiSyntaxHightlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forNumber(double value) {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forNumber(value), builder);
		}
		builder.append(AnsiSyntaxHightlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forNumber(long value) {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forNumber(value), builder);
		}
		builder.append(AnsiSyntaxHightlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forNumber(BigInteger v) {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forNumber(v), builder);
		}
		builder.append(AnsiSyntaxHightlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forNumber(BigDecimal v) {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forNumber(v), builder);
		}
		builder.append(AnsiSyntaxHightlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forNumber(String encodedValue) {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forNumber(encodedValue), builder);
		}
		builder.append(AnsiSyntaxHightlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forString(String string) {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forString(string), builder);
		}
		builder.append(AnsiSyntaxHightlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forBinary() {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forBinary(), builder);
		}
		builder.append(AnsiSyntaxHightlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forBoolean(boolean value) {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forBoolean(value), builder);
		}
		builder.append(AnsiSyntaxHightlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forNull() {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forNull(), builder);
		}
		if(builder.length() > AnsiSyntaxHightlight.CLEAR.length()) {
			builder.setLength(builder.length() - 1);
		}
		builder.append(AnsiSyntaxHightlight.ESC_END);
		return builder.toString();
	}

}
