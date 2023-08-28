package com.github.skjolber.decorators.factory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.github.skjolber.jackson.jsh.SyntaxHighlighter;

/**
 * Factory interface for {@linkplain SyntaxHighlighter}. <br>
 * <br>
 * Instances of the factory are required to be thread safe, the generated syntax
 * highlighters not.
 */

public interface SyntaxHighlighterFactory {

	/**
	 * Create {@linkplain SyntaxHighlighter} for coloring the input generator.
	 * 
	 * @param generator generator for creating the returned highlighter
	 * 
	 * @return highlighter a newly created syntax highlighter
	 */

	SyntaxHighlighter createSyntaxHighlighter(JsonGenerator generator);
}
