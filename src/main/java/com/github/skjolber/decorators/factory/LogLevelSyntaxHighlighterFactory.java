package com.github.skjolber.decorators.factory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.github.skjolber.decorators.syntaxhighlight.LogLevelSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.AnsiSyntaxHighlight;
import com.github.skjolber.jackson.jsh.DefaultSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

public class LogLevelSyntaxHighlighterFactory extends ConfigurableSyntaxHighlighterFactory {

	public static class Level {
		
		private String trace = LogLevelSyntaxHighlighter.TRACE;
		private String debug = LogLevelSyntaxHighlighter.DEBUG;
		private String info = LogLevelSyntaxHighlighter.INFO;
		private String warning = LogLevelSyntaxHighlighter.WARN;
		private String error = LogLevelSyntaxHighlighter.ERROR;
		
		public void setTrace(String value) {
			this.trace = AnsiSyntaxHighlight.build(parseColors(value));
		}
		
		public void setDebug(String value) {
			this.debug = AnsiSyntaxHighlight.build(parseColors(value));
		}
		
		public void setInfo(String value) {
			this.info = AnsiSyntaxHighlight.build(parseColors(value));
		}
		
		public void setWarning(String value) {
			this.warning = AnsiSyntaxHighlight.build(parseColors(value));
		}
		
		public void setError(String value) {
			this.error = AnsiSyntaxHighlight.build(parseColors(value));
		}
	}
	
	protected SyntaxHighlighter cachedSyntaxHighlighter;
	
	protected Level level = new Level();
	protected String message = LogLevelSyntaxHighlighter.MESSAGE;
	
	public LogLevelSyntaxHighlighterFactory() {
		builder = DefaultSyntaxHighlighter.newBuilderWithDefaultColors();
	}
	
	@Override
	public SyntaxHighlighter createSyntaxHighlighter(JsonGenerator generator) {
		if(cachedSyntaxHighlighter == null) {
			cachedSyntaxHighlighter = super.createSyntaxHighlighter(generator);
		}
		return new LogLevelSyntaxHighlighter(cachedSyntaxHighlighter, level.trace, level.debug, level.info, level.warning, level.error, message);
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public void setMessage(String value) {
		this.message = AnsiSyntaxHighlight.build(parseColors(value));
	}

}
