package com.dobudobu.perpustakaan.Converter;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import java.security.Key;
import java.util.Base64;

public class StringAttreibuteConverter implements AttributeConverter<String, String> {

    private static final String AES = "AES";

    private static final byte[] encryptedKey = "iWiLlSaVeyOurDaY".getBytes();

    private final Cipher encryiptCipher;

    private final Cipher decryptCipher;

    public StringAttreibuteConverter() throws Exception{
        Key key = new SecretKeySpec(encryptedKey, AES);
        encryiptCipher = Cipher.getInstance(AES);
        encryiptCipher.init(Cipher.ENCRYPT_MODE, key);
        decryptCipher = Cipher.getInstance(AES);
        decryptCipher.init(Cipher.DECRYPT_MODE, key);
    }

    @Override
    public String convertToDatabaseColumn(String s) {
        try {
            return Base64.getEncoder().encodeToString(encryiptCipher.doFinal(s.getBytes()));
        }catch (Exception e){
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String convertToEntityAttribute(String s) {
        try {
            return new String(decryptCipher.doFinal(Base64.getDecoder().decode(s)));
        }catch (Exception e){
            throw new IllegalArgumentException(e);
        }
    }
}
