package com.github.skjolber.decorators.factory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.github.skjolber.decorators.syntaxhighlight.LogLevelSingleSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

public class DefaultSyntaxHighlighterFactory implements SyntaxHighlighterFactory {
	
	@Override
	public SyntaxHighlighter createSyntaxHighlighter(JsonGenerator generator) {
		return new LogLevelSingleSyntaxHighlighter();
	}

}
