package com.quanomous;

import android.util.Base64;
import org.json.JSONArray;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

public class Quanomous {

    public static String decode(String data) {
        try {
            byte[] bytes = Base64.decode(data, Base64.NO_WRAP);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            try (GZIPInputStream gis = new GZIPInputStream(bais);
                 ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                byte[] buf = new byte[8192];
                for (int r; (r = gis.read(buf)) != -1; ) baos.write(buf, 0, r);
                return baos.toString(StandardCharsets.UTF_8.name());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONArray parse(String jsonText) {
        try {
            return new JSONArray(jsonText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}