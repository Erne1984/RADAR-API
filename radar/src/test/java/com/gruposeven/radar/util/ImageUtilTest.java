package com.gruposeven.radar.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;

import java.util.zip.DataFormatException;

import org.junit.Test;

import com.gruposeven.radar.exception.SometingWentWrongException;

public class ImageUtilTest {

    @Test
    public void testCompressAndDecompressImage() throws SometingWentWrongException, DataFormatException {
        byte[] originalData = "This is a test string".getBytes();
        byte[] compressedData = ImageUtil.compressImage(originalData);
        byte[] decompressedData = ImageUtil.decompressImage(compressedData);

        assertArrayEquals(originalData, decompressedData);
    }

    @Test
    public void testCompressImageWithException() {
        assertThrows(SometingWentWrongException.class, () -> {
            byte[] data = new byte[0];
            ImageUtil.compressImage(data);
        });
    }

    @Test
    public void testDecompressImageWithException() {
        assertThrows(SometingWentWrongException.class, () -> {
            byte[] data = new byte[0];
            ImageUtil.decompressImage(data);
        });
    }
}
