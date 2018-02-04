package com.github.skjolber.decorators.syntaxhighlight;

import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

public class FieldSyntaxHighlighter extends DelegateSyntaxHighlighter {

	private final SyntaxHighlighter base;
	private final SyntaxHighlighter field;
	private final String fieldName;

	public FieldSyntaxHighlighter(SyntaxHighlighter base, String fieldName, SyntaxHighlighter value) {
		super(base);
		this.base = base;
		this.fieldName = fieldName;
		this.field = value;
	}

	@Override
	public String forFieldName(String value) {
		if(fieldName.equals(value)) {
			this.delegate = field;
		} else {
			this.delegate = base;
		}
		return super.forFieldName(value);
	}

}
