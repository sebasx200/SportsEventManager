package com.ces3.project.ces3project.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

public class UtilMethods {

    public static JsonObject getParamsFromBody(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            sb.append(line + "\n");
            line = reader.readLine();
        }
        reader.close();
        return JsonParser.parseString(sb.toString()).getAsJsonObject();
    }

    public static BigInteger generateUniqueBigInteger(int bitLength) {
        SecureRandom random = new SecureRandom();
        Set<BigInteger> generatedNumbers = new HashSet<>();
        BigInteger number;
        do {
            number = new BigInteger(bitLength, random);
        } while (generatedNumbers.contains(number));

        generatedNumbers.add(number);
        return number;
    }
}
