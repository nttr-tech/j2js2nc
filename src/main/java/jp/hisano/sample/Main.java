package jp.hisano.sample;

import jp.hisano.jna4emscripten.Library;
import jp.hisano.jna4emscripten.Native;
import jp.hisano.jna4emscripten.Pointer;

public class Main {
	public interface Hello extends Library {
		void hello();
		Pointer allocate(int size);
		void set(Pointer buffer, int index, byte value);
		byte get(Pointer buffer, int index);
	}

	public static void main(String[] args) throws Exception {
		Hello library = (Hello) Native.loadLibrary("hello" , Hello.class);
		library.hello();

		Pointer buffer = library.allocate(100);
		library.set(buffer, 10, (byte)123);
		System.out.println(library.get(buffer, 10));
	}
}
