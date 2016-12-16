package com.json;

import java.io.IOException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public interface IFileHandle {

	public void connectJson() throws JsonIOException, JsonSyntaxException, IOException;
}
