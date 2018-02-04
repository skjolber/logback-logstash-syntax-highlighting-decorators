package com.github.skjolber.decorators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerator;
import com.github.skjolber.decorators.factory.ListSyntaxHighlighter;
import com.github.skjolber.decorators.factory.ListSyntaxHighlighterFactory;
import com.github.skjolber.decorators.factory.SyntaxHighlighterFactory;
import com.github.skjolber.jackson.jsh.AnsiSyntaxHightlight;
import com.github.skjolber.jackson.jsh.DefaultSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

public class ListSyntaxHighlighterFactoryTest {

	@Test
	public void testSingle() {
		ListSyntaxHighlighterFactory factory = new ListSyntaxHighlighterFactory();
		
		factory.addSyntaxHighlighterFactory(new SyntaxHighlighterFactory() {

			@Override
			public SyntaxHighlighter createSyntaxHighlighter(JsonGenerator generator) {
				return DefaultSyntaxHighlighter.newBuilder().withComma(AnsiSyntaxHightlight.GREEN).build();
			}
			
		});
		
		SyntaxHighlighter syntaxHighlighter = factory.createSyntaxHighlighter(null);
		assertFalse(syntaxHighlighter instanceof ListSyntaxHighlighter);
		assertEquals(AnsiSyntaxHightlight.build(AnsiSyntaxHightlight.GREEN), syntaxHighlighter.forComma());
	}

	@Test
	public void testMultiple() {
		SyntaxHighlightingDecorator factory = new SyntaxHighlightingDecorator();
		
		String[] colors = new String[]{AnsiSyntaxHightlight.RED, AnsiSyntaxHightlight.GREEN};
		
		for(final String str : colors) {
			factory.addSyntaxHighlighterFactory(new SyntaxHighlighterFactory() {
	
				@Override
				public SyntaxHighlighter createSyntaxHighlighter(JsonGenerator generator) {
					return DefaultSyntaxHighlighter.newBuilder().withComma(str).build();
				}
			});
		}
		
		SyntaxHighlighter syntaxHighlighter = factory.createSyntaxHighlighter(null);
		assertTrue(syntaxHighlighter instanceof ListSyntaxHighlighter);
		assertEquals(AnsiSyntaxHightlight.build(AnsiSyntaxHightlight.RED, AnsiSyntaxHightlight.GREEN), syntaxHighlighter.forComma());
	}
}
