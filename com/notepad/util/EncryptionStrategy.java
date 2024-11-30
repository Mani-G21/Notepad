package com.notepad.util;

public interface EncryptionStrategy {
    String encrypt(String plainText);

    String decrypt(String cipherText);
}
