emcc hello.c  -s EXPORTED_FUNCTIONS="['_hello', '_allocate', '_get', '_set']" -o ..\js\hello.js
