package edu.eou.cs.cs380;

public interface Cipher {
    public String encrypt (String stringToEncrypt, String key);
    public String decrypt (String stringToDecrypt, String key);
}
