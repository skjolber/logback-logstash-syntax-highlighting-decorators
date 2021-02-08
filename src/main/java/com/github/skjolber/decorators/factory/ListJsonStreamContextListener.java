package com.github.skjolber.decorators.factory;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonStreamContext;
import com.github.skjolber.jackson.jsh.highlighter.JsonStreamContextListener;

public class ListJsonStreamContextListener implements JsonStreamContextListener {

	protected List<JsonStreamContextListener> listeners;

	public ListJsonStreamContextListener(List<JsonStreamContextListener> listeners) {
		this.listeners = listeners;
	}
	
	public ListJsonStreamContextListener() {
		this(new ArrayList<JsonStreamContextListener>());
	}

	@Override
	public void startObject(JsonStreamContext outputContext) {
		for(JsonStreamContextListener listener : listeners) {
			listener.startObject(outputContext);
		}
	}

	@Override
	public void endObject(JsonStreamContext outputContext) {
		for(JsonStreamContextListener listener : listeners) {
			listener.endObject(outputContext);
		}
	}

	@Override
	public void startArray(JsonStreamContext outputContext) {
		for(JsonStreamContextListener listener : listeners) {
			listener.startArray(outputContext);
		}
	}

	@Override
	public void endArray(JsonStreamContext outputContext) {
		for(JsonStreamContextListener listener : listeners) {
			listener.endArray(outputContext);
		}
	}
	
}
