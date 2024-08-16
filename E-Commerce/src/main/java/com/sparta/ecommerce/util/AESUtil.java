package com.sparta.ecommerce.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtil {

    // AES 알고리즘을 사용
    private static final String ALGORITHM = "AES";
    // 암호화에 사용할 비밀 키 (16바이트 고정 길이)
    private static final String SECRET_KEY = "1234567890123456"; // 실제 환경에서는 더 복잡하고 안전한 키를 사용해야 합니다.

    /*
     * 주어진 문자열을 AES 알고리즘을 사용하여 암호화합니다.
     * @param value 암호화할 문자열
     * @return 암호화된 문자열 (Base64 인코딩)
     * @throws Exception 암호화 중 발생할 수 있는 예외
     */
    public static String encrypt(String value) throws Exception {
        // 비밀 키 생성
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);

        // 암호화 모드로 설정된 Cipher 객체 생성
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        // 입력된 값을 암호화하고 Base64로 인코딩하여 반환
        byte[] encryptedValue = cipher.doFinal(value.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedValue);
    }

    /*
     * AES 알고리즘을 사용하여 암호화된 문자열을 복호화합니다.
     * @param value 복호화할 암호화된 문자열 (Base64 인코딩)
     * @return 복호화된 원래의 문자열
     * @throws Exception 복호화 중 발생할 수 있는 예외
     */
    public static String decrypt(String value) throws Exception {
        // 비밀 키 생성
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);

        // 복호화 모드로 설정된 Cipher 객체 생성
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);

        // Base64로 디코딩된 값을 복호화하여 반환
        byte[] decryptedValue = cipher.doFinal(Base64.getDecoder().decode(value));
        return new String(decryptedValue, "UTF-8");
    }
}
