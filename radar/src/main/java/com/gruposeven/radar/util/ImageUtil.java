package com.gruposeven.radar.util;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import com.gruposeven.radar.exception.SometingWentWrongException;

public class ImageUtil {
    
    private ImageUtil() { }

    public static byte[] compressImage(byte[] data) throws SometingWentWrongException {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception e) {
            throw new SometingWentWrongException();
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data) throws SometingWentWrongException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception exception) {
            throw new SometingWentWrongException();
        }
        return outputStream.toByteArray();
    }
}
