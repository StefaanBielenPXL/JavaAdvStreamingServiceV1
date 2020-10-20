package be.pxl.ja.streamingservice.model;

import java.text.ParseException;

public class CreditCardNumber {
    private static final int LENGTH = 16;
    private static final int CVC_LENGTH = 3;

    private CreditCardType type;
    private String cardNumber;
    private String cvc;

    public CreditCardNumber(String cardNumber, String cvc) {
        cardNumber = removeBlanks(cardNumber);
        if (cardNumber.length() != LENGTH || cvc.length() != CVC_LENGTH) {
            throw new IllegalArgumentException("Lengte van de invoer is onjuist!");
        }
        if (!isNumeric(cardNumber) || !isNumeric(cvc)) {
            throw new IllegalArgumentException("Geen geldig nummer ingegeven!");
        }

        this.cardNumber = cardNumber;
        this.cvc = cvc;
        this.type = getType();
    }

    private boolean isNumeric(String cvc) {
        try {
            Integer.parseInt(cvc);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    private String removeBlanks(String text) {
        return text.replaceAll("\\s","");
    }

    public CreditCardType getType() {
        if (cardNumber.startsWith("4")) {
            return CreditCardType.VISA;
        } else if (cardNumber.startsWith("5")){
            return CreditCardType.MASTERCARD;
        }
        return null;
    }

    public String getCvc() {
        return cvc;
    }

    public String getNumber() {
        return cardNumber;
    }
}
