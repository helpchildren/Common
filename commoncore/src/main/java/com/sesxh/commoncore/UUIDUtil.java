/**
 * Copyright (c)  https://github.com/zhaohuatai
 *
 * contact z_huatai@qq.com
 *  
 */
package com.sesxh.commoncore;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class UUIDUtil {
	
	/**
     * All possible chars for representing a number as a String
     * copy from mica：https://github.com/lets-mica/mica/blob/master/mica-core/src/main/java/net/dreamlu/mica/core/utils/NumberUtil.java#L113
     */
    private final static byte[] DIGITS = {
        '0', '1', '2', '3', '4', '5',
        '6', '7', '8', '9', 'a', 'b',
        'c', 'd', 'e', 'f', 'g', 'h',
        'i', 'j', 'k', 'l', 'm', 'n',
        'o', 'p', 'q', 'r', 's', 't',
        'u', 'v', 'w', 'x', 'y', 'z',
        'A', 'B', 'C', 'D', 'E', 'F',
        'G', 'H', 'I', 'J', 'K', 'L',
        'M', 'N', 'O', 'P', 'Q', 'R',
        'S', 'T', 'U', 'V', 'W', 'X',
        'Y', 'Z'
    };

    /**
     * 生成uuid，采用 jdk 9 的形式，优化性能
     * copy from mica：https://github.com/lets-mica/mica/blob/master/mica-core/src/main/java/net/dreamlu/mica/core/utils/StringUtil.java#L335
     * <p>
     * 关于mica uuid生成方式的压测结果，可以参考：https://github.com/lets-mica/mica-jmh/wiki/uuid
     *
     * @return UUID
     */
    public static String fastUUID() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        long lsb = random.nextLong();
        long msb = random.nextLong();
        byte[] buf = new byte[32];
        formatUnsignedLong(lsb, buf, 20, 12);
        formatUnsignedLong(lsb >>> 48, buf, 16, 4);
        formatUnsignedLong(msb, buf, 12, 4);
        formatUnsignedLong(msb >>> 16, buf, 8, 4);
        formatUnsignedLong(msb >>> 32, buf, 0, 8);
        return new String(buf, StandardCharsets.UTF_8);
    }

    /**
     * copy from mica：https://github.com/lets-mica/mica/blob/master/mica-core/src/main/java/net/dreamlu/mica/core/utils/StringUtil.java#L348
     */
    private static void formatUnsignedLong(long val, byte[] buf, int offset, int len) {
        int charPos = offset + len;
        int radix = 1 << 4;
        int mask = radix - 1;
        do {
            buf[--charPos] = DIGITS[((int) val) & mask];
            val >>>= 4;
        } while (charPos > offset);
    }
	
	public static String uuid() {
//		return getUUID();
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
 
//    public static String base64Uuid() {
//        UUID uuid = UUID.randomUUID();
//        return base64Uuid(uuid);
////    	return base64Uuid(uuid());
//    }
 
//    protected static String base64Uuid(UUID uuid) {
//
//        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
//        bb.putLong(uuid.getMostSignificantBits());
//        bb.putLong(uuid.getLeastSignificantBits());
//        return Base64.getUrlEncoder().encodeToString(bb.array());
//    }
 
//    public static String encodeBase64Uuid(String uuidString) {
//        UUID uuid = UUID.fromString(uuidString);
//        return base64Uuid(uuid);
//    }
 
//    public static String decodeBase64Uuid(String compressedUuid) {
//
//        byte[] byUuid = Base64.getUrlDecoder().decode(compressedUuid);
//
//        ByteBuffer bb = ByteBuffer.wrap(byUuid);
//        UUID uuid = new UUID(bb.getLong(), bb.getLong());
//        return uuid.toString();
//    }
 
//    public static String base58Uuid() {
//        UUID uuid = UUID.randomUUID();
//        return base58Uuid(uuid);
//    }
 
//    protected static String base58Uuid(UUID uuid) {
//
//        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
//        bb.putLong(uuid.getMostSignificantBits());
//        bb.putLong(uuid.getLeastSignificantBits());
//
//        return Base58Util.encode(bb.array());
//    }
 
//    public static String encodeBase58Uuid(String uuidString) {
//        UUID uuid = UUID.fromString(uuidString);
//        return base58Uuid(uuid);
//    }
 
//    public static String decodeBase58Uuid(String base58uuid) {
//        byte[] byUuid = Base58Util.decode(base58uuid);
//        ByteBuffer bb = ByteBuffer.wrap(byUuid);
//        UUID uuid = new UUID(bb.getLong(), bb.getLong());
//        return uuid.toString();
//    }
    
//	public static void main(String[] args) {
//		for(int i=0;i<100;i++){
//			String s=UUIDUtil.base58Uuid();
//			System.out.println(s);
//			System.out.println(s.length());
//		}
//		System.out.println("---------------------------");
//		for(int i=0;i<100;i++){
//			String s=UUIDUtil.base64Uuid();
//			System.out.println(s);
//			System.out.println(s.length());
//		}
//	}
}
