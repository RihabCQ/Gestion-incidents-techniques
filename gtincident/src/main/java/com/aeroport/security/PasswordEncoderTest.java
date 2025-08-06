package com.aeroport.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println("admin123 => " + encoder.encode("admin123"));
        System.out.println("admin456 => " + encoder.encode("admin456"));
        System.out.println("agent123 => " + encoder.encode("agent123"));
        System.out.println("agent124 => " + encoder.encode("agent124"));
        System.out.println("tech123 => " + encoder.encode("tech123"));
        System.out.println("tech124 => " + encoder.encode("tech124"));
        System.out.println("tech456 => " + encoder.encode("tech456"));
    }
}
