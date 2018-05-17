package edu.eou.cs.cs380;

class CipherImpl implements Cipher {
    private int cipherGrid[][] = new int[26][26];
    public CipherImpl() {
        int asciiHolder = 65;
        for (int i = 0; i < 26; i++){
            for (int j = 0; j < 26; j++){
                cipherGrid[i][j] = asciiHolder;
                asciiHolder++;
                if (asciiHolder > 90) {
                    asciiHolder = 65;
                }
            }
            asciiHolder ++;
        }
    }
    @Override
    public String decrypt(String stringToDecrypt, String key) {
        int i = 0;
        int j = 0;
        String decrypt = "";
        while (i < stringToDecrypt.length()) {
            while (Character.isDigit(stringToDecrypt.charAt(i)) || stringToDecrypt.charAt(i) == 32) {
                decrypt = decrypt.concat(Character.toString(stringToDecrypt.charAt(i)));
                i++;
            }
            int charToInt = (int) stringToDecrypt.charAt(i);
            if (Character.isLetterOrDigit(stringToDecrypt.charAt(i))) {
                charToInt = charToInt - 32;
            }
            int kCharToInt = (int) key.charAt(j);
            if (kCharToInt > 90) {
                kCharToInt = kCharToInt - 32;
            }
            int l = 0;
            while (cipherGrid[l][0] != kCharToInt) {
                l++;
            }
            int k = 0;
            while (cipherGrid[l][k] != charToInt) {
                k++;
            }
            char cipher = (char) cipherGrid[0][k];
            if (Character.isLowerCase(stringToDecrypt.charAt(i))) {
                cipher = Character.toLowerCase(cipher);
            }
            decrypt = decrypt.concat(Character.toString(cipher));
            i++;
            j++;
            if (j >= key.length()) {
                j = 0;
            }
        }
        return decrypt;
    }
    @Override
    public String encrypt(String stringToEncrypt, String key) {
        int i = 0;
        int j = 0;
        String encrypt  = "";
        while (i < stringToEncrypt.length()) {
            while (Character.isDigit(stringToEncrypt.charAt(i)) || stringToEncrypt.charAt(i) == 32) {
                encrypt = encrypt.concat(Character.toString(stringToEncrypt.charAt(i)));
                i++;
            }
            if(Character.isLetterOrDigit(stringToEncrypt.charAt(i))){

            }
            int charToInt = (int)stringToEncrypt.charAt(i);
            if (Character.isLowerCase(stringToEncrypt.charAt(i))){ charToInt = charToInt - 32;}
            int kCharToInt = (int)key.charAt(j);
            if (Character.isLowerCase(key.charAt(j))) {kCharToInt = kCharToInt - 32;}
            int l = 0;
            while (cipherGrid[l][0] != charToInt) {
                l++;
            }
            int k = 0;
            while (cipherGrid[0][k] != kCharToInt) {
                k++;
            }
            char cipher = (char) cipherGrid[l][k];
            if(Character.isLowerCase(stringToEncrypt.charAt(i))){
                cipher = Character.toLowerCase(cipher);
            }
            encrypt = encrypt.concat(Character.toString(cipher));
            i++;
            j++;
            if (j >= key.length()) {
                j = 0;
            }
        }
        return encrypt;
    }

    public static void main (String args []) {
        CipherImpl cipher = new CipherImpl();
        cipher.encrypt("test", "KGB");
    }
}