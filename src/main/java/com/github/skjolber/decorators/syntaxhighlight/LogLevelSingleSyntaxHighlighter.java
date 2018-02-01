package com.github.skjolber.decorators.syntaxhighlight;

import com.github.skjolber.jackson.jsh.AnsiSyntaxHightlight;
import com.github.skjolber.jackson.jsh.DefaultSyntaxHighlighter;

public class LogLevelSingleSyntaxHighlighter extends DefaultSyntaxHighlighter {

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
					return AnsiSyntaxHightlight.SANE + AnsiSyntaxHightlight.BACKGROUND_YELLOW + AnsiSyntaxHightlight.WHITE;
				} else if(string.equals("ERROR")) {
					return AnsiSyntaxHightlight.SANE + AnsiSyntaxHightlight.BACKGROUND_RED + AnsiSyntaxHightlight.HIGH_INTENSITY + AnsiSyntaxHightlight.WHITE;
				}

			}
		}
		return super.forString(string);
	}
}
