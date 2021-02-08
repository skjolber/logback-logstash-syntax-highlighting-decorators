package com.github.skjolber.decorators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.github.skjolber.decorators.factory.SyntaxHighlighterFactory;
import com.github.skjolber.decorators.syntaxhighlight.ListSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.highlighter.SyntaxHighlighter;

public class SyntaxHighlightingDecoratorTest {

	@Test
	public void testSingle() throws IOException {
		SyntaxHighlightingDecorator decorator = new SyntaxHighlightingDecorator();
		
		final SubtreeJsonStreamContextListener listener = new SubtreeJsonStreamContextListener();
		decorator.addSyntaxHighlighterFactory(generator -> listener);
		
		StringWriter writer = new StringWriter();
		JsonGenerator vanilla = new JsonFactory().createGenerator(writer);
		
		SyntaxHighlighter syntaxHighlighter = decorator.createSyntaxHighlighter(vanilla);
		assertFalse(syntaxHighlighter instanceof ListSyntaxHighlighter);
		
		// check that the json stream context part is wired correctly
		JsonGenerator decorated = decorator.decorate(vanilla);
		decorated.writeStartObject(); 
		assertEquals(1, listener.getLevel());
		decorated.writeEndObject();
		assertEquals(0, listener.getLevel());
	}
	
	@Test
	public void testMultiple() throws IOException {
		SyntaxHighlightingDecorator decorator = new SyntaxHighlightingDecorator();
		
		final SubtreeJsonStreamContextListener first = new SubtreeJsonStreamContextListener();
		decorator.addSyntaxHighlighterFactory(generator -> first);
		final SubtreeJsonStreamContextListener second = new SubtreeJsonStreamContextListener(false);
		decorator.addSyntaxHighlighterFactory(generator -> second);
		
		StringWriter writer = new StringWriter();
		JsonGenerator vanilla = new JsonFactory().createGenerator(writer);
		
		SyntaxHighlighter syntaxHighlighter = decorator.createSyntaxHighlighter(vanilla);
		assertTrue(syntaxHighlighter instanceof ListSyntaxHighlighter);
		
		// check that the json stream context part is wired correctly
		JsonGenerator decorated = decorator.decorate(vanilla);
		decorated.writeStartObject(); 
		assertEquals(1, first.getLevel());
		assertEquals(1, second.getLevel());
		assertEquals("true", first.forPretty());
		assertEquals("false", second.forPretty());
		decorated.writeEndObject();
		assertEquals(0, first.getLevel());
		assertEquals(0, second.getLevel());
		assertEquals("true", first.forPretty());
		assertEquals("false", second.forPretty());
	}
}
