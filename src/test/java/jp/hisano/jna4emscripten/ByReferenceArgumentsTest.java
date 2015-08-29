/* Copyright (c) 2007 Timothy Wall, All Rights Reserved
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
import junit.framework.TestCase;

/** Exercise a range of native methods.
 *
 * @author twall@users.sf.net
 */
public class ByReferenceArgumentsTest extends TestCase {

    public static interface TestLibrary extends Library {

        void incrementInt8ByReference(ByteByReference b);
        void incrementInt16ByReference(ShortByReference s);
        void incrementInt32ByReference(IntByReference i);
        void incrementNativeLongByReference(NativeLongByReference l);
        void incrementInt64ByReference(LongByReference l);
        void complementFloatByReference(FloatByReference f);
        void complementDoubleByReference(DoubleByReference d);
        void setPointerByReferenceNull(PointerByReference p);
    }

    TestLibrary lib;
    protected void setUp() {
        lib = (TestLibrary)Native.loadLibrary("testlib", TestLibrary.class);
    }
    
    protected void tearDown() {
        lib = null;
    }

    public void testByteByReference() {
        ByteByReference bref = new ByteByReference();
        lib.incrementInt8ByReference(bref);
        assertEquals("Byte argument not modified", 1, bref.getValue());
    }
    public void testShortByReference() {
        ShortByReference sref = new ShortByReference();
        lib.incrementInt16ByReference(sref);
        assertEquals("Short argument not modified", 1, sref.getValue());
    }
    public void testIntByReference() {
        IntByReference iref = new IntByReference();
        lib.incrementInt32ByReference(iref);
        assertEquals("Int argument not modified", 1, iref.getValue());
    }
    public void testNativeLongByReference() {
        NativeLongByReference iref = new NativeLongByReference();
        lib.incrementNativeLongByReference(iref);
        assertEquals("Native long argument not modified",
                     new NativeLong(1), iref.getValue());
    }
    public void testLongByReference() {
        LongByReference lref = new LongByReference();
        lib.incrementInt64ByReference(lref);
        assertEquals("Long argument not modified", 1, lref.getValue());
    }
    public void testFloatByReference() {
        FloatByReference fref = new FloatByReference(1f);
        lib.complementFloatByReference(fref);
        assertEquals("Float argument not modified", -1f, fref.getValue(), 0.0);
    }
    public void testDoubleByReference() {
        DoubleByReference dref = new DoubleByReference(1d);
        lib.complementDoubleByReference(dref);
        assertEquals("Int argument not modified", -1d, dref.getValue(), 0.0);
    }
    public void testPointerByReference() {
        PointerByReference pref = new PointerByReference();
        assertNull("Default pointer should be null", pref.getValue());
        pref = new PointerByReference(new Memory(16));
        assertNotNull("Explicit pointer should not be null", pref.getValue());
        lib.setPointerByReferenceNull(pref);
        assertNull("Default pointer should be NULL after call", pref.getValue());
    }
    
    public static void main(java.lang.String[] argList) {
        junit.textui.TestRunner.run(ByReferenceArgumentsTest.class);
    }
    
}
