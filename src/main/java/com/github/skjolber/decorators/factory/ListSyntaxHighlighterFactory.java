package com.github.skjolber.decorators.factory;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

public class ListSyntaxHighlighterFactory implements SyntaxHighlighterFactory {

	protected SyntaxHighlighterFactory defaultSyntaxHighlighterFactory;
	
	protected List<SyntaxHighlighterFactory> factories = new ArrayList<>();

	public ListSyntaxHighlighterFactory() {
		this(new DefaultSyntaxHighlighterFactory());
	}
	
	public ListSyntaxHighlighterFactory(SyntaxHighlighterFactory defaultSyntaxHighlighterFactory) {
		this.defaultSyntaxHighlighterFactory = defaultSyntaxHighlighterFactory;
	}

	public void addSyntaxHighlighterFactory(SyntaxHighlighterFactory factory) {
		factories.add(factory);
	}
	
	public SyntaxHighlighter createSyntaxHighlighter(JsonGenerator generator) {
		
		if(factories.isEmpty()) {
			return createDefaultSyntaxHighlighter(generator);
		}
		
		if(factories.size() == 1) {
			return factories.get(0).createSyntaxHighlighter(generator);
		}
		
		List<SyntaxHighlighter> highlighterList = new ArrayList<>(factories.size());
		
		for(SyntaxHighlighterFactory factory : factories) {
			highlighterList.add(factory.createSyntaxHighlighter(generator));
		}
		
		return new ListSyntaxHighlighter(highlighterList);
	}

	protected SyntaxHighlighter createDefaultSyntaxHighlighter(JsonGenerator generator) {
		return defaultSyntaxHighlighterFactory.createSyntaxHighlighter(generator);
	}

}
