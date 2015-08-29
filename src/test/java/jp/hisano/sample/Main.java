package jp.hisano.sample;

import jp.hisano.j2js2nc.Library;
import jp.hisano.j2js2nc.Native;
import jp.hisano.j2js2nc.Pointer;
import jp.hisano.j2js2nc.ptr.ByteByReference;

public class Main {
	public interface Hello extends Library {
		void hello();
		Pointer allocate(int size);
		void set(Pointer buffer, int index, byte value);
		byte get(Pointer buffer, int index);
		void print(String str);
	}

	public static void main(String[] args) throws Exception {
		Hello library = (Hello) Native.loadLibrary("hello" , Hello.class);
		library.hello();

		{
			Pointer buffer = library.allocate(100);
			library.set(buffer, 10, (byte)123);
			System.out.println(library.get(buffer, 10));
		}

		{
			ByteByReference byteByReference = new ByteByReference();
			library.set(byteByReference.getPointer(), 0, (byte)45);
			System.out.println(library.get(byteByReference.getPointer(), 0));
			System.out.println(byteByReference.getValue());
		}

		library.print("hello");
	}
}
