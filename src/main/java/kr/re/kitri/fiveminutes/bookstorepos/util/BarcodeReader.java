package kr.re.kitri.fiveminutes.bookstorepos.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.GenericMultipleBarcodeReader;
import com.google.zxing.multi.MultipleBarcodeReader;

import java.awt.image.BufferedImage;
import java.util.*;

public class BarcodeReader {

    private static final Map<DecodeHintType,Object> HINTS;
    private static final Map<DecodeHintType,Object> HINTS_PURE;

    static {
        HINTS = Map.of(DecodeHintType.TRY_HARDER, Boolean.TRUE,
                       DecodeHintType.POSSIBLE_FORMATS, EnumSet.allOf(BarcodeFormat.class));
        HINTS_PURE = new EnumMap<>(HINTS);
        HINTS_PURE.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
    }

    public static String read(BufferedImage image) {
        List<Result> results = new ArrayList<>();

        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new GlobalHistogramBinarizer(source));
        Reader reader = new MultiFormatReader();
        try {
            MultipleBarcodeReader multiReader = new GenericMultipleBarcodeReader(reader);
            Result[] resultArr = multiReader.decodeMultiple(bitmap, HINTS);
            if (resultArr.length != 0) {
                results.addAll(Arrays.asList(resultArr));
            }
        } catch (ReaderException ignore) { }

        if (results.isEmpty()) {
            try {
                Result theResult = reader.decode(bitmap, HINTS_PURE);
                if (theResult != null) {
                    results.add(theResult);
                }
            }
            catch (ReaderException ignore) { }
        }

        if (results.isEmpty()) {
            try {
                Result theResult = reader.decode(bitmap, HINTS);
                if (theResult != null) {
                    results.add(theResult);
                }
            }
            catch (ReaderException ignore) { }
        }

        if (results.isEmpty()) {
            try {
                BinaryBitmap hybridBitmap = new BinaryBitmap(new HybridBinarizer(source));
                Result theResult = reader.decode(hybridBitmap);
                if (theResult != null) {
                    results.add(theResult);
                }
            }
            catch (ReaderException ignore) { }
        }

        return results.isEmpty()
                ? UUID.randomUUID().toString()
                : results.get(0).getText();
    }
}
