package com.notepad.util;

public class CeaserCipher implements EncryptionStrategy {
    private final int shift;
    public CeaserCipher(int shift){
        this.shift = shift;
    }

    @Override
    public String encrypt(String plainText){
        StringBuffer encyrypted = new StringBuffer();
        for(char c: plainText.toCharArray()){
            char shifted = (char)(c + shift);
            encyrypted.append(shifted);
        }
        return encyrypted.toString();
    }

    @Override
    public String decrypt(String cipherText){
        StringBuffer decrypted = new StringBuffer();
        for(char c: cipherText.toCharArray()){
            char shifted = (char)(c - shift);
            decrypted.append(shifted);
        }
        return decrypted.toString();
    }
}
