package com.github.skjolber.decorators.syntaxhighlight;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

public class FieldSyntaxHighlighter implements SyntaxHighlighter {

	private final LogLevelSingleSyntaxHighlighter base;
	private final SyntaxHighlighter message;
	private final String fieldName;

	private SyntaxHighlighter delegate;

	public FieldSyntaxHighlighter(LogLevelSingleSyntaxHighlighter base, String fieldName, LogLevelSingleSyntaxHighlighter value) {
		this.base = base;
		this.fieldName = fieldName;
		this.message = value;
		
		this.delegate = base;
	}

	@Override
	public String forCurlyBrackets() {
		return delegate.forCurlyBrackets();
	}

	@Override
	public String forSquareBrackets() {
		return delegate.forSquareBrackets();
	}

	@Override
	public String forColon() {
		return delegate.forColon();
	}

	@Override
	public String forComma() {
		return delegate.forComma();
	}

	@Override
	public String forWhitespace() {
		return delegate.forWhitespace();
	}

	@Override
	public String forFieldName(String value) {
		if(fieldName.equals(value)) {
			this.delegate = message;
		} else {
			this.delegate = base;
		}
		return delegate.forFieldName(value);
	}

	@Override
	public String forNumber(int value) {
		return delegate.forNumber(value);
	}

	@Override
	public String forString(String value) {
		return delegate.forString(value);
	}

	@Override
	public String forBinary() {
		return delegate.forBinary();
	}

	@Override
	public String forBoolean(boolean value) {
		return delegate.forBoolean(value);
	}

	@Override
	public String forNull() {
		return delegate.forNull();
	}

	@Override
	public String forNumber(double value) {
		return delegate.forNumber(value);
	}

	@Override
	public String forNumber(long value) {
		return delegate.forNumber(value);
	}

	@Override
	public String forNumber(BigInteger v) {
		return delegate.forNumber(v);
	}

	@Override
	public String forNumber(BigDecimal v) {
		return delegate.forNumber(v);
	}

	@Override
	public String forNumber(String encodedValue) {
		return delegate.forNumber(encodedValue);
	}

}
