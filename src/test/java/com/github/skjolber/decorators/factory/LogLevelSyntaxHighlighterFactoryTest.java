package com.github.skjolber.decorators.factory;

import static org.junit.Assert.assertTrue;

import java.io.StringWriter;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.github.skjolber.decorators.factory.LogLevelSyntaxHighlighterFactory.Level;
import com.github.skjolber.decorators.syntaxhighlight.LogLevelSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

public class LogLevelSyntaxHighlighterFactoryTest {
	
	@Test
	public void testLogLevelSyntaxHighlighterFactory() throws Exception {
		StringWriter writer = new StringWriter();

		LogLevelSyntaxHighlighterFactory factory = new LogLevelSyntaxHighlighterFactory();
		factory.setMessage("black");
		
		Level level = new LogLevelSyntaxHighlighterFactory.Level();
		level.setDebug("black");
		level.setInfo("blue");
		level.setWarning("yellow");
		level.setError("red");
		
		factory.setLevel(level);
		
		JsonGenerator vanilla = new JsonFactory().createGenerator(writer);

		SyntaxHighlighter syntaxHighlighter = factory.createSyntaxHighlighter(vanilla);
		assertTrue(syntaxHighlighter instanceof LogLevelSyntaxHighlighter);

	}

}
