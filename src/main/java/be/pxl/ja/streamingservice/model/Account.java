package be.pxl.ja.streamingservice.model;

import be.pxl.ja.streamingservice.exception.TooManyProfilesException;
import be.pxl.ja.streamingservice.util.PasswordUtil;

import java.util.Collections;
import java.util.List;

public class Account {
    public final int MINIMUM_PASSWORD_STRENGTH = 5;
    private String email;
    private String password;
    private PaymentInfo paymentInfo;
    private StreamingPlan streamingPlan;
    private List<Profile> profiles;

    public Account(String email, String password) {
        setEmail(email);
        setPassword(password);
        profiles.add(new Profile("profile1"));
        streamingPlan = StreamingPlan.BASIC;
    }

    public void addProfile(Profile profile) {
        if (streamingPlan.getNumberOfScreens() <= profiles.size()) {
            throw new TooManyProfilesException("Kan geen profielen meer toevoegen, maximum bereikt.");
        }
        profiles.add(profile);
    }

    public List<Profile> getProfiles() {
        Collections.sort(profiles);
        return profiles;
    }

    public void setEmail(String email) {
        if (email == null || email.equals("")) {
            throw new IllegalArgumentException("Veld email mag niet null of leeg zijn.");
        }
        this.email = email;
    }

    public void setPassword(String password) {
        if (password == null || password.equals("")) {
            throw new IllegalArgumentException("Veld password mag niet null of leeg zijn.");
        }
        if (PasswordUtil.calculateStrength(password) < MINIMUM_PASSWORD_STRENGTH) {
            throw new IllegalArgumentException("Paswoord is niet sterk genoeg.");
        }
        this.password = password;
    }

    public int getNumberOfProfiles() {
        return profiles.size();
    }

    public String getEmail() {
        return email;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }
}
