package fr.lp.ic.contact.validators;

import java.util.regex.Pattern;

public final class EmailValidatorUtils {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9]{1,25}@[a-zA-Z0-9]{1,25}\\.[a-zA-Z0-9]{1,5}$";
    private static final Pattern EMAIL_PATTERN_MATCHER = Pattern.compile(EMAIL_PATTERN);

    private EmailValidatorUtils(){

    }


    public static boolean validate(String email) {
        return EMAIL_PATTERN_MATCHER.matcher(email).matches();
    }
}
