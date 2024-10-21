package manage.book.catalog.service;

import org.springframework.stereotype.Service;

@Service
public class IsbnValidation {

    public boolean isValid(String isbn) {
        if (isbn == null) {
            return false;
        }

        isbn = isbn.replaceAll("-", "").replaceAll(" ", "");

        if (isbn.length() == 10) {
            return isValidIsbn10(isbn);
        } else if (isbn.length() == 13) {
            return isValidIsbn13(isbn);
        }

        return false;
    }

    private boolean isValidIsbn10(String isbn) {
        if (!isbn.matches("\\d{9}[\\dXx]")) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (isbn.charAt(i) - '0') * (10 - i);
        }

        char lastChar = isbn.charAt(9);
        sum += (lastChar == 'X' || lastChar == 'x') ? 10 : (lastChar - '0');

        return sum % 11 == 0;
    }

    private boolean isValidIsbn13(String isbn) {
        if (!isbn.matches("\\d{13}")) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 13; i++) {
            int digit = isbn.charAt(i) - '0';
            sum += (i % 2 == 0) ? digit : digit * 3;
        }

        return sum % 10 == 0;
    }

}



