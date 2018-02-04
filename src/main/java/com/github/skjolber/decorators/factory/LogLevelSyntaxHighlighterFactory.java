package com.github.skjolber.decorators.factory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.github.skjolber.decorators.syntaxhighlight.LogLevelSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.DefaultSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

public class LogLevelSyntaxHighlighterFactory extends ConfigurableSyntaxHighlighterFactory {

	protected SyntaxHighlighter cachedSyntaxHighlighter;
	
	public LogLevelSyntaxHighlighterFactory() {
		builder = DefaultSyntaxHighlighter.newBuilderWithDefaultColors();
	}
	
	@Override
	public SyntaxHighlighter createSyntaxHighlighter(JsonGenerator generator) {
		if(cachedSyntaxHighlighter == null) {
			cachedSyntaxHighlighter = super.createSyntaxHighlighter(generator);
		}
		return new LogLevelSyntaxHighlighter(cachedSyntaxHighlighter);
	}

}
