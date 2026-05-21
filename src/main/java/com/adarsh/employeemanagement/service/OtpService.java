package com.adarsh.employeemanagement.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class OtpService {

    private Map<String, String> otpStorage =
            new HashMap<>();

    private Set<String> verifiedEmails =
            new HashSet<>();

    public String generateOtp(
            String email) {

        Random random = new Random();

        int otp =
                100000 + random.nextInt(900000);

        String generatedOtp =
                String.valueOf(otp);

        otpStorage.put(
                email,
                generatedOtp);

        System.out.println(
                "Generated OTP: "
                + generatedOtp);

        return generatedOtp;
    }

    public boolean verifyOtp(
            String email,
            String otp) {

        String storedOtp =
                otpStorage.get(email);

        boolean isValid =
                storedOtp != null
                && storedOtp.equals(otp);

        if (isValid) {

            verifiedEmails.add(email);
        }

        return isValid;
    }

    public boolean isEmailVerified(
            String email) {

        return verifiedEmails.contains(email);
    }
}