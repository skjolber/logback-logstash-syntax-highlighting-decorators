package com.github.skjolber.decorators;

import com.fasterxml.jackson.core.JsonGenerator;
import com.github.skjolber.jackson.jsh.DefaultSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.JsonStreamContextListener;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;
import com.github.skjolber.jackson.jsh.SyntaxHighlightingJsonGenerator;

import net.logstash.logback.decorate.JsonGeneratorDecorator;

public class SyntaxHighlightingDecorator implements JsonGeneratorDecorator {

	private Class<? extends SyntaxHighlighter> syntaxHighlighter;

    @Override
    public JsonGenerator decorate(JsonGenerator generator) {
    	SyntaxHighlighter instance = null;
    	if(syntaxHighlighter != null) {
    		instance = createSyntaxHighlighter();
    	} else {
    		instance = new DefaultSyntaxHighlighter();
    	}
    	
    	if(instance instanceof JsonStreamContextListener) {
            return new SyntaxHighlightingJsonGenerator(generator, instance, (JsonStreamContextListener)instance);
    	}
        return new SyntaxHighlightingJsonGenerator(generator, instance);

    }

	private SyntaxHighlighter createSyntaxHighlighter() {
		try {
			return (SyntaxHighlighter)syntaxHighlighter.newInstance();
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void setSyntaxHighlighter(String syntaxHighlighter) {
		try {
			this.syntaxHighlighter = (Class<? extends SyntaxHighlighter>) Class.forName(syntaxHighlighter);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}
    
    public String getSyntaxHighlighter() {
		return syntaxHighlighter.getName();
	}
    

}
