package com.thinhlh.mi_learning_backend.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinhlh.mi_learning_backend.exceptions.ConversionException;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public abstract class StringHelper {
    private static final String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
    private static final String numbers = "0123456789";

    public static String randomString(int length) {
        var alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

        var sb = new StringBuilder();
        var random = new Random();

        for (int i = 0; i < length; i++) {
            var index = random.nextInt(alphaNumeric.length());

            var randomChar = alphaNumeric.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();

    }

    public static <T> T mapRequestBodyToObject(HttpServletRequest request, Class<T> clazz) {
        var sb = new StringBuilder();

        BufferedReader reader = null;
        try {
            reader = request.getReader();
            reader.lines().forEach(s -> {
                sb.append(s).append(" ");
            });
            var jsonString = sb.toString();

            return new ObjectMapper().readValue(jsonString, clazz);

        } catch (IOException e) {
            throw new ConversionException("Unable to convert request to class " + clazz.getName());
        }
    }
}
