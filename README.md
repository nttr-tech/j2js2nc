Java to JavaScript to Native Code (j2js2nc)
===========================================

JNA is a great Java library and I have used it for long time.
So I developed j2js2nc from JNA 4.1 code to support Emscripten on Nashorn (Java 8's JavaScript Engine).
 
How to use
==========

1. Write C code and save it as 'hello.c'

    #include <stdio.h>
    #include <stdlib.h>
    
    void hello(char *name) {
    	printf("Hello, %s!\n", name);
    }

2. Compile 'hello.c' to 'js/hello.js' with Emscripten on your terminal

    emcc hello.c  -s EXPORTED_FUNCTIONS="['_hello']" -o js/hello.js

3. Write Java code and run it

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

License
=======

This library is licensed under the LGPL, version 2.1 or later, and the Apache Software License, version 2.0, because JNA is licensed under them.
