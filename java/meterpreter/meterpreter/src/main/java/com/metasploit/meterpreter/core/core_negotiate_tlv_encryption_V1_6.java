package com.metasploit.meterpreter.core;

import com.metasploit.meterpreter.Meterpreter;
import com.metasploit.meterpreter.TLVPacket;
import com.metasploit.meterpreter.TLVType;

import javax.xml.bind.DatatypeConverter;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

public class core_negotiate_tlv_encryption_V1_6 extends core_negotiate_tlv_encryption {

    private static final String PUBLIC_KEY_BEGIN = "-----BEGIN PUBLIC KEY-----\n";
    private static final String PUBLIC_KEY_END = "-----END PUBLIC KEY-----\n";

    public static PublicKey getPublicKey(String base64PublicKey) throws Exception {
        if (base64PublicKey.startsWith(PUBLIC_KEY_BEGIN)) {
            base64PublicKey = base64PublicKey.substring(PUBLIC_KEY_BEGIN.length());
        }
        if (base64PublicKey.endsWith(PUBLIC_KEY_END)) {
            base64PublicKey = base64PublicKey.substring(0, base64PublicKey.length() - PUBLIC_KEY_END.length());
        }
        byte[] decoded = DatatypeConverter.parseBase64Binary(base64PublicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    public static byte[] encryptWithKey(byte[] key, byte[] data) {
        return null;
    }

    public static byte[] decryptWithKey(byte[] key, byte[] data) {
        return null;
    }

    public int execute(Meterpreter meterpreter, TLVPacket request, TLVPacket response) throws Exception {
        String rsaPubKey = request.getStringValue(TLVType.TLV_TYPE_RSA_PUB_KEY);
        PublicKey publicKey = getPublicKey(rsaPubKey);
//        int keyType = request.getIntValue(TLVType.TLV_TYPE_SYM_KEY_TYPE);
//        byte [] key = request.getRawValue(TLVType.TLV_TYPE_SYM_KEY);
//        byte [] enckey = request.getRawValue(TLVType.TLV_TYPE_ENC_SYM_KEY);
        return ERROR_SUCCESS;
    }
}
