package com.github.skjolber.decorators;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.github.skjolber.decorators.factory.ListJsonStreamContextListener;
import com.github.skjolber.decorators.factory.ListSyntaxHighlighterFactory;
import com.github.skjolber.decorators.factory.LogLevelSyntaxHighlighterFactory;
import com.github.skjolber.decorators.factory.SyntaxHighlighterFactory;
import com.github.skjolber.decorators.syntaxhighlight.ListSyntaxHighlighter;
import com.github.skjolber.jackson.jsh.JsonStreamContextListener;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;
import com.github.skjolber.jackson.jsh.SyntaxHighlightingJsonGenerator;

import net.logstash.logback.decorate.JsonGeneratorDecorator;

public class SyntaxHighlightingDecorator extends ListSyntaxHighlighterFactory implements JsonGeneratorDecorator {

	protected SyntaxHighlighterFactory defaultSyntaxHighlighterFactory;
	protected boolean prettyPrint = true;

	public SyntaxHighlightingDecorator(SyntaxHighlighterFactory defaultSyntaxHighlighterFactory) {
		this.defaultSyntaxHighlighterFactory = defaultSyntaxHighlighterFactory;
	}

	public SyntaxHighlightingDecorator() {
		this(new LogLevelSyntaxHighlighterFactory());
	}

	@Override
	public JsonGenerator decorate(JsonGenerator generator) {
		SyntaxHighlighter instance = createSyntaxHighlighter(generator);

		// check whether we need to add context listeners too
		if (instance instanceof ListSyntaxHighlighter) {
			ListSyntaxHighlighter list = (ListSyntaxHighlighter) instance;

			List<JsonStreamContextListener> listeners = new ArrayList<>(factories.size());
			for (SyntaxHighlighter h : list.getList()) {
				if (h instanceof JsonStreamContextListener) {
					listeners.add((JsonStreamContextListener) h);
				}
			}
			if (!listeners.isEmpty()) {
				if (listeners.size() == 1) {
					return new SyntaxHighlightingJsonGenerator(generator, instance, listeners.get(0), prettyPrint);
				}
				return new SyntaxHighlightingJsonGenerator(generator, instance,
						new ListJsonStreamContextListener(listeners), prettyPrint);
			}
		}

		if (instance instanceof JsonStreamContextListener) {
			return new SyntaxHighlightingJsonGenerator(generator, instance, (JsonStreamContextListener) instance,
					prettyPrint);
		}
		return new SyntaxHighlightingJsonGenerator(generator, instance, prettyPrint);

	}

	@Override
	public SyntaxHighlighter createSyntaxHighlighter(JsonGenerator generator) {
		if (factories.isEmpty()) {
			return createDefaultSyntaxHighlighter(generator);
		}
		return super.createSyntaxHighlighter(generator);
	}

	protected SyntaxHighlighter createDefaultSyntaxHighlighter(JsonGenerator generator) {
		return defaultSyntaxHighlighterFactory.createSyntaxHighlighter(generator);
	}

	public void addSyntaxHighlighterFactory(SyntaxHighlighterFactory factory) {
		factories.add(factory);
	}

	public void setPrettyPrint(String prettyPrint) {
		this.prettyPrint = Boolean.parseBoolean(prettyPrint);
	}

}
