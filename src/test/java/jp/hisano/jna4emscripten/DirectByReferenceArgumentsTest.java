/* Copyright (c) 2009 Timothy Wall, All Rights Reserved
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * <p/>
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.  
 */
package jp.hisano.jna4emscripten;

import jp.hisano.jna4emscripten.ptr.ByteByReference;
import jp.hisano.jna4emscripten.ptr.DoubleByReference;
import jp.hisano.jna4emscripten.ptr.FloatByReference;
import jp.hisano.jna4emscripten.ptr.IntByReference;
import jp.hisano.jna4emscripten.ptr.LongByReference;
import jp.hisano.jna4emscripten.ptr.NativeLongByReference;
import jp.hisano.jna4emscripten.ptr.PointerByReference;
import jp.hisano.jna4emscripten.ptr.ShortByReference;

/** Exercise a range of native methods.
 *
 * @author twall@users.sf.net
 */
public class DirectByReferenceArgumentsTest extends ByReferenceArgumentsTest {

    public static class DirectTestLibrary implements TestLibrary {

        public native void incrementInt8ByReference(ByteByReference b);
        public native void incrementInt16ByReference(ShortByReference s);
        public native void incrementInt32ByReference(IntByReference i);
        public native void incrementNativeLongByReference(NativeLongByReference i);
        public native void incrementInt64ByReference(LongByReference l);
        public native void complementFloatByReference(FloatByReference f);
        public native void complementDoubleByReference(DoubleByReference d);
        public native void setPointerByReferenceNull(PointerByReference p);

        static {
            Native.register("testlib");
        }
    }

    protected void setUp() {
        lib = new DirectTestLibrary();
    }
    
    public static void main(java.lang.String[] argList) {
        junit.textui.TestRunner.run(DirectByReferenceArgumentsTest.class);
    }
    
}
