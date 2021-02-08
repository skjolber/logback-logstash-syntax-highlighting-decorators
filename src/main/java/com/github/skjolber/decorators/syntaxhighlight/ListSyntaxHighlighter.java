package com.github.skjolber.decorators.syntaxhighlight;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.github.skjolber.jackson.jsh.highlighter.AnsiSyntaxHighlight;
import com.github.skjolber.jackson.jsh.highlighter.SyntaxHighlighter;

public class ListSyntaxHighlighter implements SyntaxHighlighter {

	protected static final String CLEAR_START = AnsiSyntaxHighlight.ESC_START + AnsiSyntaxHighlight.CLEAR;
	protected static final String RESET_COMMAND = AnsiSyntaxHighlight.CLEAR + AnsiSyntaxHighlight.SEPERATOR;
	
	protected List<SyntaxHighlighter> list;
	
	public ListSyntaxHighlighter(List<SyntaxHighlighter> list) {
		this.list = list;
	}
	
	public List<SyntaxHighlighter> getList() {
		return list;
	}
	
	protected void append(String ansi, StringBuilder builder) {
		if(!ansi.startsWith(AnsiSyntaxHighlight.ESC_START)) {
			throw new IllegalArgumentException("Highlighter strings must start with '\\u001B['");
		}
		if(!ansi.endsWith(AnsiSyntaxHighlight.ESC_END)) {
			throw new IllegalArgumentException("Highlighter strings end with '" + AnsiSyntaxHighlight.ESC_END + "'");
		}
		
		if(ansi.length() > AnsiSyntaxHighlight.ESC_START.length() + AnsiSyntaxHighlight.ESC_END.length()) {
			// at least one code
			if(ansi.charAt(2) == '0') {
				if(ansi.charAt(3) == 'm') {
					// ignore clear
					return;
				} else if(ansi.charAt(3) == ';') {
					// ignore clear start
					builder.append(ansi, AnsiSyntaxHighlight.ESC_START.length() + 1, ansi.length() - AnsiSyntaxHighlight.ESC_END.length());
					return;
				}
			}
			builder.append(AnsiSyntaxHighlight.SEPERATOR);
			builder.append(ansi, AnsiSyntaxHighlight.ESC_START.length(), ansi.length() - AnsiSyntaxHighlight.ESC_END.length());
		}
	}

	@Override
	public String forCurlyBrackets() {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forCurlyBrackets(), builder);
		}
		builder.append(AnsiSyntaxHighlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forSquareBrackets() {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forSquareBrackets(), builder);
		}
		builder.append(AnsiSyntaxHighlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forColon() {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forColon(), builder);
		}
		builder.append(AnsiSyntaxHighlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forComma() {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forComma(), builder);
		}
		builder.append(AnsiSyntaxHighlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forPretty() {
		// pretty is not applicable here, so just ignore
		return "";
	}

	@Override
	public String forWhitespace() {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forWhitespace(), builder);
		}
		builder.append(AnsiSyntaxHighlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forFieldName(String value) {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forFieldName(value), builder);
		}
		builder.append(AnsiSyntaxHighlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forNumber(int value) {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forNumber(value), builder);
		}
		builder.append(AnsiSyntaxHighlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forNumber(double value) {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forNumber(value), builder);
		}
		builder.append(AnsiSyntaxHighlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forNumber(long value) {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forNumber(value), builder);
		}
		builder.append(AnsiSyntaxHighlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forNumber(BigInteger v) {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forNumber(v), builder);
		}
		builder.append(AnsiSyntaxHighlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forNumber(BigDecimal v) {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forNumber(v), builder);
		}
		builder.append(AnsiSyntaxHighlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forNumber(String encodedValue) {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forNumber(encodedValue), builder);
		}
		builder.append(AnsiSyntaxHighlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forString(String string) {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forString(string), builder);
		}
		builder.append(AnsiSyntaxHighlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forBinary() {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forBinary(), builder);
		}
		builder.append(AnsiSyntaxHighlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forBoolean(boolean value) {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forBoolean(value), builder);
		}
		builder.append(AnsiSyntaxHighlight.ESC_END);
		return builder.toString();
	}

	@Override
	public String forNull() {
		StringBuilder builder = new StringBuilder();
		builder.append(CLEAR_START);
		for(SyntaxHighlighter h : list) {
			append(h.forNull(), builder);
		}
		if(builder.length() > AnsiSyntaxHighlight.CLEAR.length()) {
			builder.setLength(builder.length() - 1);
		}
		builder.append(AnsiSyntaxHighlight.ESC_END);
		return builder.toString();
	}

}
