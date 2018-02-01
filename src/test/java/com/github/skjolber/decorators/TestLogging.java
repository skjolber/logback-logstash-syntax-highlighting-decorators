package com.github.skjolber.decorators;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogging {

	private final static Logger logger = LoggerFactory.getLogger(TestLogging.class);

    @Test 
    public void testLogger() {
    	logger.trace("Trace message");
    	logger.debug("Debug message");
    	logger.info("Info message");
    	logger.warn("Warn message");
    	logger.error("Error message");
    }
}
