package jp.hisano.sample;

import java.io.InputStreamReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import jp.hisano.jna4emscripten.Library;
import jp.hisano.jna4emscripten.Pointer;

public class Main {
	public interface Hello extends Library {
		Pointer allocate(int size);
		void set(Pointer buffer, int index, byte value);
		byte get(Pointer buffer, int index);
	}

	public static void main(String[] args) throws Exception {
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		engine.eval(new InputStreamReader(Main.class.getResourceAsStream("/js/hello.js")));

		Invocable invocable = (Invocable) engine;
		invocable.invokeFunction("_hello");

		Object buffer = invocable.invokeFunction("_allocate", 100);
		invocable.invokeFunction("_set", buffer, 10, 123);
		Object result = invocable.invokeFunction("_get", buffer, 10);
		System.out.println(result);
	}
}
