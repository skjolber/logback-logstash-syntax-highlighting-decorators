package com.github.skjolber.decorators.syntaxhighlight;

import com.github.skjolber.jackson.jsh.AnsiSyntaxHightlight;
import com.github.skjolber.jackson.jsh.DefaultSyntaxHighlighter;

public class LogLevelSingleSyntaxHighlighter extends DefaultSyntaxHighlighter {

	private static final String WARN = AnsiSyntaxHightlight.build(AnsiSyntaxHightlight.BACKGROUND_YELLOW, AnsiSyntaxHightlight.WHITE);
	private static final String ERROR = AnsiSyntaxHightlight.build(AnsiSyntaxHightlight.BACKGROUND_RED, AnsiSyntaxHightlight.HIGH_INTENSITY, AnsiSyntaxHightlight.WHITE);

	private boolean isFieldName;

	@Override
	public String forFieldName(String value) {
		this.isFieldName = true;
		
		return super.forFieldName(value);
	}
	
	@Override
	public String forString(String string) {
		if(isFieldName) {
			isFieldName = false;
			
			if(string != null) {
				if(string.equals("WARN")) {
					return WARN;
				} else if(string.equals("ERROR")) {
					return ERROR;
				}
			}
		}
		return super.forString(string);
	}
}
