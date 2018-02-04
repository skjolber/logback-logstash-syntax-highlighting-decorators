# logback-logstash-more-decorators
Additional `Decorator`s for [logstash-logback-encoder]

  * Syntax highlighting (and pretty printing)
  * Special field handling
    * Level: warn and error
    * Message: highlight

The primary use-case for this tool is coloring console output during testing. For productions setups, rather configure a encoder without a `Decorator` and use a proper visualization tool like [Kibana]. 

## License
[Apache 2.0]

# Obtain
The project is based on [Maven] and is available at central Maven repository.

```xml
<dependency>
    <groupId>com.github.skjolber.logback-logstash-more-decorators</groupId>
    <artifactId>logback-logstash-more-decorators</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

# Usage
The highlighters are instances of [JsonGeneratorDecorator]. Configuration would look something like

```xml
<appender name="STDOUT_JSON" class="ch.qos.logback.core.ConsoleAppender">
	<encoder class="net.logstash.logback.encoder.LogstashEncoder">
		<!-- add pretty-printing and syntax highlighting for testing -->
		<jsonGeneratorDecorator class="com.github.skjolber.decorators.SyntaxHighligtingDecorator"/>
	</encoder>
</appender>
```

If you prefer to configure the colors yourself, supply an instance of `SyntaxHighlighterFactory`, and configure

```
<jsonGeneratorDecorator class="com.github.skjolber.decorators.SyntaxHighlightingDecorator">
	<syntaxHighlighterFactory class="com.example.MySyntaxHighlighterFactory"/>
</jsonGeneratorDecorator>
```

Then create instances of `SyntaxHighlighter` on `SyntaxHighlighterFactory.createSyntaxHighlighter(..)`. 

Use [conditional processing] to differentiate between different environments.

## Details
The `SyntaxHighlightingDecorator` supports a list of `syntaxHighlighterFactory` elements. The resulting list of `SyntaxHighlighter` ANSI colors are appended in natural order. Highlighters not wanting to contribute to the current JSON event are expected to return ANSI clear/reset .

Use `LogbackSyntaxHighlighterFactory` for your own colors; for the syntax designators:

| Key | Name |
|-----|------|
| fieldName | Field name |
| binaryValue | Binary value |
| booleanValue | Boolean value |
| nullValue| Null value |
| numberValue| Number value |
| stringValue| Textual value |
| curlyBrackets| Object start / end |
| squareBrackets| Array start / end |
| colon| Field separator |
| whitespace| Whitespace |
| comma| Field entity separator |

use the following ANSI keys (with space seperators):

| Key | Text color |
| ----- | ----------- |
| black | Black text |
|red | Red text | 
|green | Green text |
|yellow | Yellow text |
|blue | Blue text |
|magenta | Magneta text |
|cyan | Cyan text |
|white | White text |

| Key | Background color |
| ----- | ----------- |
|blackBackground | Black |
|redBackground | Red |
|greenBackground | Green |
|yellowBackground | Yellow |
|blueBackground | Blue |
|magentaBackground | Magneta |
|cyanBackground | Cyan | 
|whiteBackground | White |

| Key | Style |
| ----- | ----------- |
|highIntensity | High intensity (bold? |
|lowIntensity | Low instensity |
|italic | Italic
|underline | Underline
|blink| Blink |


# History

 - [1.0.0]: Initial version

[Apache 2.0]:          		http://www.apache.org/licenses/LICENSE-2.0.html
[issue-tracker]:       		https://github.com/skjolber/logback-logstash-more-decorators/issues
[Maven]:                	http://maven.apache.org/
[1.0.0]:					https://github.com/skjolber/logback-logstash-more-decorators/releases/tag/logback-logstash-more-decorators-1.0.0
[jackson-syntax-highlight]:	https://github.com/skjolber/jackson-syntax-highlight
[Jackson]:					https://github.com/FasterXML/jackson
[ANSI]:						https://en.wikipedia.org/wiki/ANSI_escape_code
[JSON]:						https://no.wikipedia.org/wiki/JSON
[JsonGeneratorDecorator]:	https://github.com/logstash/logstash-logback-encoder/blob/master/src/main/java/net/logstash/logback/decorate/JsonGeneratorDecorator.java
[logstash-logback-encoder]:	https://github.com/logstash/logstash-logback-encoder
[Kibana]:                   https://www.elastic.co/products/kibana
[conditional processing]:	https://logback.qos.ch/manual/configuration.html#conditional

