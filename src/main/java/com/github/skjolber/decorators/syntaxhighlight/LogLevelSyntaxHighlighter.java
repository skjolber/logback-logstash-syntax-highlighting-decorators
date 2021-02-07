package com.github.skjolber.decorators.syntaxhighlight;

import com.github.skjolber.jackson.jsh.AnsiSyntaxHighlight;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

public class LogLevelSyntaxHighlighter extends DelegateSyntaxHighlighter {

	public static final String TRACE = AnsiSyntaxHighlight.build(AnsiSyntaxHighlight.RED);
	public static final String DEBUG = AnsiSyntaxHighlight.build(AnsiSyntaxHighlight.RED);
	public static final String INFO = AnsiSyntaxHighlight.build(AnsiSyntaxHighlight.RED);
	public static final String WARN = AnsiSyntaxHighlight.build(AnsiSyntaxHighlight.BACKGROUND_YELLOW, AnsiSyntaxHighlight.WHITE);
	public static final String ERROR = AnsiSyntaxHighlight.build(AnsiSyntaxHighlight.BACKGROUND_RED, AnsiSyntaxHighlight.WHITE);
	public static final String MESSAGE = AnsiSyntaxHighlight.build(AnsiSyntaxHighlight.HIGH_INTENSITY, AnsiSyntaxHighlight.RED);

	protected boolean logLevelField;
	protected String trace;
	protected String debug;
	protected String info;
	protected String warn;
	protected String error;

	protected boolean messageField;
	protected String message;
	
	public LogLevelSyntaxHighlighter(SyntaxHighlighter delegate, String trace, String debug, String info, String warn, String error, String message) {
		super(delegate);
		
		this.trace = trace;
		this.debug = debug;
		this.info = info;
		this.warn = warn;
		this.error = error;
		
		this.message = message;
	}

	public LogLevelSyntaxHighlighter(SyntaxHighlighter delegate) {
		this(delegate, TRACE, DEBUG, INFO, WARN, ERROR, MESSAGE);
	}

	@Override
	public String forPretty() {
		return this.delegate.forPretty();
	}

	@Override
	public String forFieldName(String value) {
		this.logLevelField = "level".equals(value);
		this.messageField = "message".equals(value);
		
		return super.forFieldName(value);
	}
	
	@Override
	public String forString(String string) {
		if(logLevelField) {
			logLevelField = false;
			
			if(string != null) {
				switch (string) {
					case "TRACE":
						return trace;
					case "DEBUG":
						return debug;
					case "INFO":
						return info;
					case "WARN":
						return warn;
					case "ERROR":
						return error;
				}
			}
		} else if(messageField) {
			messageField = false;
			
			return message;
		}
		return super.forString(string);
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getWarn() {
		return warn;
	}

	public void setWarn(String warn) {
		this.warn = warn;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
}
