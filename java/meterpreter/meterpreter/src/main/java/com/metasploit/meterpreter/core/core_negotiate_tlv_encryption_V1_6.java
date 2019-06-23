package com.metasploit.meterpreter.core;

import com.metasploit.meterpreter.Meterpreter;
import com.metasploit.meterpreter.TLVPacket;
import com.metasploit.meterpreter.TLVType;

/*
import javax.xml.bind.DatatypeConverter;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
 */

public class core_negotiate_tlv_encryption_V1_6 extends core_negotiate_tlv_encryption {

    /*
    public static PublicKey getPublicKey(String base64PublicKey){
        PublicKey publicKey = null;
        try{
            byte[] decoded = DatatypeConverter.parseBase64Binary(base64PublicKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }
     */

    public int execute(Meterpreter meterpreter, TLVPacket request, TLVPacket response) throws Exception {
        String rsaPubKey = request.getStringValue(TLVType.TLV_TYPE_RSA_PUB_KEY);
        System.err.println("rsa pub key" + rsaPubKey);
//        PublicKey publicKey = getPublicKey(rsaPubKey);
//        System.err.println("rsa pub key" + publicKey.toString());
//        int keyType = request.getIntValue(TLVType.TLV_TYPE_SYM_KEY_TYPE);
//        byte [] key = request.getRawValue(TLVType.TLV_TYPE_SYM_KEY);
//        byte [] enckey = request.getRawValue(TLVType.TLV_TYPE_ENC_SYM_KEY);
        return ERROR_SUCCESS;
    }
}
