package com.github.skjolber.decorators.factory;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.github.skjolber.jackson.jsh.AnsiSyntaxHightlight;
import com.github.skjolber.jackson.jsh.DefaultSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

import net.logstash.logback.encoder.org.apache.commons.lang.StringEscapeUtils;

public class LogbackSyntaxHighlisterFactory implements SyntaxHighlighterFactory {

	protected DefaultSyntaxHighlighter.Builder builder = DefaultSyntaxHighlighter.newBuilder();

	private String[] parseColors(String text) {
		List<String> result = new ArrayList<String>();
		
		String[] splits = text.split("\\s+");
		
		for(String split : splits) {
			String code;
			if(split.startsWith("\\")) {
				code = StringEscapeUtils.escapeJava(split);
			} else {
				code = parseAnsi(split);
			}
			
			if(!code.equals(AnsiSyntaxHightlight.CLEAR)) {
				result.add(code);
			}
		}
		
		return result.toArray(new String[result.size()]);
	}

	private String parseAnsi(String split) {
		switch(split) {
		    case "black" : return AnsiSyntaxHightlight.BLACK;
		    case "red" : return AnsiSyntaxHightlight.RED;
		    case "green" : return AnsiSyntaxHightlight.GREEN;
		    case "yellow" : return AnsiSyntaxHightlight.YELLOW;
		    case "blue" : return AnsiSyntaxHightlight.BLUE;
		    case "magenta" : return AnsiSyntaxHightlight.MAGENTA;
		    case "cyan" : return AnsiSyntaxHightlight.CYAN;
		    case "white" : return AnsiSyntaxHightlight.WHITE;
		    
		    case "blackBackground" : return AnsiSyntaxHightlight.BACKGROUND_BLACK;
		    case "redBackground" : return AnsiSyntaxHightlight.BACKGROUND_RED;
		    case "greenBackground" : return AnsiSyntaxHightlight.BACKGROUND_GREEN;
		    case "yellowBackground" : return AnsiSyntaxHightlight.BACKGROUND_YELLOW;
		    case "blueBackground" : return AnsiSyntaxHightlight.BACKGROUND_BLUE;
		    case "magentaBackground" : return AnsiSyntaxHightlight.BACKGROUND_MAGENTA;
		    case "cyanBackground" : return AnsiSyntaxHightlight.BACKGROUND_CYAN;
		    case "whiteBackground" : return AnsiSyntaxHightlight.BACKGROUND_WHITE;

		    case "highIntensity" : return AnsiSyntaxHightlight.HIGH_INTENSITY;
		    case "lowIntensity" : return AnsiSyntaxHightlight.LOW_INTENSITY;

		    case "italic" : return AnsiSyntaxHightlight.ITALIC;
		    case "underline" : return AnsiSyntaxHightlight.UNDERLINE;
		    case "blink" : return AnsiSyntaxHightlight.BLINK;

		}		
		
		throw new IllegalArgumentException("Unknown ANSI color '" + split + "'");
	}

	public void setFieldName(String fieldName) {
		builder.withField(parseColors(fieldName));
	}

	public void setBinaryValue(String binaryValue) {
		builder.withBinary(parseColors(binaryValue));
	}

	public void setBooleanValue(String booleanValue) {
		builder.withBoolean(parseColors(booleanValue));
	}

	public void setNullValue(String nullValue) {
		builder.withNull(parseColors(nullValue));
	}

	public void setNumberValue(String numberValue) {
		builder.withNumber(parseColors(numberValue));
	}

	public void setStringValue(String stringValue) {
		builder.withString(parseColors(stringValue));
	}

	public void setCurlyBrackets(String curlyBrackets) {
		builder.withCurlyBrackets(parseColors(curlyBrackets));
	}

	public void setSquareBrackets(String squareBrackets) {
		builder.withSquareBrackets(parseColors(squareBrackets));
	}

	public void setColon(String colon) {
		builder.withColon(parseColors(colon));
	}

	public void setWhitespace(String whitespace) {
		builder.withWhitespace(parseColors(whitespace));
	}

	public void setComma(String comma) {
		builder.withComma(parseColors(comma));
	}

	@Override
	public SyntaxHighlighter createSyntaxHighlighter(JsonGenerator generator) {
		return builder.build();
	}
	
}
