package com.metasploit.meterpreter.stdapi;

import com.metasploit.stage.Config;
import com.metasploit.stage.ConfigParser;

import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLClassLoader;
import java.net.URLConnection;

public class ConfigParserTest extends TestCase {

    private byte[] loadResourceAsByteArray(String resource) throws IOException {
//        InputStream in = getClass().getResourceAsStream(resource);
        FileInputStream in = new FileInputStream(resource);
        assertNotNull(in);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        for (;;) {
            int nread = in.read(buffer);
            if (nread <= 0) {
                break;
            }
            byteArrayOutputStream.write(buffer, 0, nread);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public void testParseTcpConfig() throws Exception {
        String tcpConfig = "/Users/Timothy_Wright/dev/git/metasploit-payloads/java/meterpreter/shared/src/test/resources/config.bin";
        byte[] configBytes = loadResourceAsByteArray(tcpConfig);
        Config config = ConfigParser.parseConfig(configBytes);
        assertNotNull(config);
        assertEquals(604800000, config.session_expiry);
        assertEquals(1, config.transportConfigList.size());
        assertEquals("tcp://192.168.11.111:4444", config.transportConfigList.get(0).url);
        assertEquals(300000, config.transportConfigList.get(0).comm_timeout);
        assertEquals(3600000, config.transportConfigList.get(0).retry_total);
        assertEquals(10000, config.transportConfigList.get(0).retry_wait);
    }

    public void testParseHttpConfig() throws Exception {
        String tcpConfig = "/Users/Timothy_Wright/dev/git/metasploit-payloads/java/meterpreter/shared/src/test/resources/httpconfig.bin";
        byte[] configBytes = loadResourceAsByteArray(tcpConfig);
        Config config = ConfigParser.parseConfig(configBytes);
        assertNotNull(config);
        assertEquals(604800000, config.session_expiry);
        assertEquals(1, config.transportConfigList.size());
        assertEquals("http://192.168.32.131:4444/zo84lXtq3Vzfatx5hty21AdgaS-vO9jTRi9iHeyRwChCoA4BpNm0NXpPNtQA965zCkt7UxBIuNm1jMZVJM3V3REyn1/", config.transportConfigList.get(0).url);
        assertEquals(300000, config.transportConfigList.get(0).comm_timeout);
        assertEquals(3600000, config.transportConfigList.get(0).retry_total);
        assertEquals(10000, config.transportConfigList.get(0).retry_wait);
    }

    public void testParseHttpConfigs() throws Exception {
        String tcpConfig = "/Users/Timothy_Wright/dev/git/metasploit-framework/bin";
        byte[] configBytes = loadResourceAsByteArray(tcpConfig);
        Config config = ConfigParser.parseConfig(configBytes);
        assertNotNull(config);
        assertEquals(604800000, config.session_expiry);
        assertEquals(1, config.transportConfigList.size());
//        assertEquals("http://192.168.32.131:4444/zo84lXtq3Vzfatx5hty21AdgaS-vO9jTRi9iHeyRwChCoA4BpNm0NXpPNtQA965zCkt7UxBIuNm1jMZVJM3V3REyn1/", config.transportConfigList.get(0).url);
        assertEquals(300000, config.transportConfigList.get(0).comm_timeout);
        assertEquals(3600000, config.transportConfigList.get(0).retry_total);
        assertEquals(10000, config.transportConfigList.get(0).retry_wait);
    }

}
