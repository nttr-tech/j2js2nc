package jp.hisano.sample;

import jp.hisano.j2js2nc.Library;
import jp.hisano.j2js2nc.Native;

public class Main {
    public interface Hello extends Library {
        void hello(String name);
    }

    public static void main(String[] args) throws Exception {
        Hello library = (Hello) Native.loadLibrary("hello", Hello.class);
        library.hello("j2js2nc");
    }
}
