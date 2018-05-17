package edu.eou.cs.cs380;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class CipherTest {
    @Test
    public void testEncrypt1() throws Exception {
        Cipher cipher = new CipherImpl();
        String result = cipher.encrypt("cs380 is awesome", "bagofbones");
        assertEquals("ds380 og fxsfsef", result);
    }

    @Test
    public void testDecrypt1() throws Exception {
        Cipher cipher = new CipherImpl();
        String result = cipher.decrypt("lbcd uiqvh njohy oygncvh vg", "Puck");
        assertEquals("what fools these mortals be", result);
    }


}


