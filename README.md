# logback-logstash-more-decorators
Additional `Decorators` for [logstash-logback-encoder].

Features:
  * Syntax highlighting (and pretty printing)
  * Special field handling
    * Level: warn and error

The primary use-case for this tool is coloring console output during local development. For productions setups, rather configure a encoder without a `Decorator` and apply proper visualization tools like [Kibana]. 

Use [conditional processing] to differentiate between different environments.

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
Add a [JsonGeneratorDecorator]:

```xml
<appender name="STDOUT_JSON" class="ch.qos.logback.core.ConsoleAppender">
    <encoder class="net.logstash.logback.encoder.LogstashEncoder">
        <!-- add pretty-printing and syntax highlighting for testing -->
        <jsonGeneratorDecorator class="com.github.skjolber.decorators.SyntaxHighligtingDecorator"/>
    </encoder>
</appender>
```

The default decorator is aware of the log-level and highlights `WARN` and `ERROR` with yellow and red background color. 

## Custom colors
Define your own colors using `LogLevelSingleSyntaxHighlighter`:

```xml
<jsonGeneratorDecorator class="com.github.skjolber.decorators.SyntaxHighligtingDecorator"/>
    <syntaxHighlighterFactory class="com.github.skjolber.decorators.factory.LogLevelSingleSyntaxHighlighter">
        <stringValue>blue</stringValue>
        <numberValue>black highIntensity</numberValue>
        <fieldName>red</fieldName>
        <binaryValue>yellowBackground</binaryValue>
        <booleanValue>cyan</booleanValue>
        <nullValue>black</nullValue>
        <curlyBrackets>green</curlyBrackets>
        <squareBrackets>green</squareBrackets>
        <colon>green</colon>
        <whitespace>green</whitespace>
        <comma>green</comma>
    </syntaxHighlighterFactory>
</jsonGeneratorDecorator>
 ```

and space-separated foreground, background and style keys.

### Foreground color
| Key | Text color |
| ----- | ----------- |
| black | Black text |
| red | Red text | 
| green | Green text |
| yellow | Yellow text |
| blue | Blue text |
| magenta | Magneta text |
| cyan | Cyan text |
| white | White text |

### Background color
| Key | Background color |
| ----- | ----------- |
| blackBackground | Black |
| redBackground | Red |
| greenBackground | Green |
| yellowBackground | Yellow |
| blueBackground | Blue |
| magentaBackground | Magneta |
| cyanBackground | Cyan | 
| whiteBackground | White |
 
### Style
| Key | Style |
| ----- | ----------- |
| highIntensity | High intensity (bold) |
| lowIntensity | Low instensity |
| italic | Italic
| underline | Underline
| blink| Blink |


## Details
The `SyntaxHighlightingDecorator` supports a list of `<syntaxHighlighterFactory>` elements. The colors are applied in natural order. Highlighters not wanting to contribute to the current JSON event are expected to return a full ANSI clear/reset.

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

