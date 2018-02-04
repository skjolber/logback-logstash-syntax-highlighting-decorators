package com.github.skjolber.decorators.factory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

public interface SyntaxHighlighterFactory {

	SyntaxHighlighter createSyntaxHighlighter(JsonGenerator generator);
}
