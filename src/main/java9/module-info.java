module com.github.skjolber.decorators {
	exports com.github.skjolber.decorators;
	exports com.github.skjolber.decorators.syntaxhighlight;
	exports com.github.skjolber.decorators.factory;

	requires com.fasterxml.jackson.core;
	requires com.github.skjolber.jackson.jsh;
	requires logstash.logback.encoder;
	requires org.apache.commons.text;
	requires org.slf4j;
}