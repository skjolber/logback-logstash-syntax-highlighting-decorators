package com.github.skjolber.decorators.factory;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.github.skjolber.jackson.jsh.AnsiSyntaxHighlight;
import com.github.skjolber.jackson.jsh.DefaultSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.DefaultSyntaxHighlighter.Builder;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

import net.logstash.logback.encoder.org.apache.commons.lang.StringEscapeUtils;

/**
 * Configurable syntax highlighter factory.
 * <br><br>
 * <table summary="" style="border: 1px solid">
 * 	<thead>
 * 		<tr>
 * 			<th>Key</th>
 * 			<th>Name</th>
 * 		</tr>
 * 	</thead>
 * 	<tbody>
 * 		<tr>
 * 			<td>fieldName</td>
 * 			<td>Field name</td>
 * 		</tr>
 * 		<tr>
 * 			<td>binaryValue</td>
 * 			<td>Binary value</td>
 * 		</tr>
 * 		<tr>
 * 			<td>booleanValue</td>
 * 			<td>Boolean value</td>
 * 		</tr>
 * 		<tr>
 * 			<td>nullValue</td>
 * 			<td>Null value</td>
 * 		</tr>
 * 		<tr>
 * 			<td>numberValue</td>
 * 			<td>Number value</td>
 * 		</tr>
 * 		<tr>
 * 			<td>stringValue</td>
 * 			<td>Textual value</td>
 * 		</tr>
 * 		<tr>
 * 			<td>curlyBrackets</td>
 * 			<td>Object start / end</td>
 * 		</tr>
 * 		<tr>
 * 			<td>squareBrackets</td>
 * 			<td>Array start / end</td>
 * 		</tr>
 * 		<tr>
 * 			<td>colon</td>
 * 			<td>Field separator</td>
 * 		</tr>
 * 		<tr>
 * 			<td>whitespace</td>
 * 			<td>Whitespace</td>
 * 		</tr>
 * 		<tr>
 * 			<td>comma</td>
 * 			<td>Field entry separator</td>
 * 		</tr>
 * 		<tr>
 * 			<td>background</td>
 * 			<td>Background color for all fields</td>
 * 		</tr>
 * 	</tbody>
 * </table>
 * 
 * 
 */

public class ConfigurableSyntaxHighlighterFactory implements SyntaxHighlighterFactory {

	protected DefaultSyntaxHighlighter.Builder builder;
	
	public ConfigurableSyntaxHighlighterFactory() {
		this(DefaultSyntaxHighlighter.newBuilder());
	}
	
	public ConfigurableSyntaxHighlighterFactory(Builder builder) {
		this.builder = builder;
	}

	protected static String[] parseColors(String text) {
		List<String> result = new ArrayList<String>();
		
		String[] splits = text.split("\\s+");
		
		for(String split : splits) {
			String code = parseColor(split);
			
			if(!code.equals(AnsiSyntaxHighlight.CLEAR)) {
				result.add(code);
			}
		}
		
		return result.toArray(new String[result.size()]);
	}

	protected static String parseColor(String split) {
		String code;
		if(split.startsWith("\\")) {
			code = StringEscapeUtils.escapeJava(split);
		} else {
			code = parseAnsi(split);
		}
		return code;
	}

	protected static String parseAnsi(String split) {
		switch(split) {
		    case "black" : return AnsiSyntaxHighlight.BLACK;
		    case "red" : return AnsiSyntaxHighlight.RED;
		    case "green" : return AnsiSyntaxHighlight.GREEN;
		    case "yellow" : return AnsiSyntaxHighlight.YELLOW;
		    case "blue" : return AnsiSyntaxHighlight.BLUE;
		    case "magenta" : return AnsiSyntaxHighlight.MAGENTA;
		    case "cyan" : return AnsiSyntaxHighlight.CYAN;
		    case "white" : return AnsiSyntaxHighlight.WHITE;
		    case "default" : return AnsiSyntaxHighlight.DEFAULT;
		    
		    case "blackBackground" : return AnsiSyntaxHighlight.BACKGROUND_BLACK;
		    case "redBackground" : return AnsiSyntaxHighlight.BACKGROUND_RED;
		    case "greenBackground" : return AnsiSyntaxHighlight.BACKGROUND_GREEN;
		    case "yellowBackground" : return AnsiSyntaxHighlight.BACKGROUND_YELLOW;
		    case "blueBackground" : return AnsiSyntaxHighlight.BACKGROUND_BLUE;
		    case "magentaBackground" : return AnsiSyntaxHighlight.BACKGROUND_MAGENTA;
		    case "cyanBackground" : return AnsiSyntaxHighlight.BACKGROUND_CYAN;
		    case "whiteBackground" : return AnsiSyntaxHighlight.BACKGROUND_WHITE;
		    case "defaultBackground" : return AnsiSyntaxHighlight.BACKGROUND_DEFAULT;

		    case "highIntensity" : return AnsiSyntaxHighlight.HIGH_INTENSITY;
		    case "lowIntensity" : return AnsiSyntaxHighlight.LOW_INTENSITY;

		    case "italic" : return AnsiSyntaxHighlight.ITALIC;
		    case "underline" : return AnsiSyntaxHighlight.UNDERLINE;
		    case "blink" : return AnsiSyntaxHighlight.BLINK;
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

	public void setBackground(String background) {
		builder.withBackground(parseColor(background));
	}

	@Override
	public SyntaxHighlighter createSyntaxHighlighter(JsonGenerator generator) {
		return builder.build();
	}
	
}
