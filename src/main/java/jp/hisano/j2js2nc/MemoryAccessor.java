package jp.hisano.j2js2nc;

interface MemoryAccessor {
    void setByte(long addr, byte value);
    void setShort(long addr, short value);
    void setInt(long addr, int value);
    
    byte getByte(long addr);
    short getShort(long addr);
    int getInt(long addr);
}