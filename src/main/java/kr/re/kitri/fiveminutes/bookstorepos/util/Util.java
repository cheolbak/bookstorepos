package kr.re.kitri.fiveminutes.bookstorepos.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;

public class Util {

    private Util() { }

    public static int currentWeekOfMonth(LocalDate date, DayOfWeek baseDayOfWeek) {
        LocalDate firstDayOfMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate baseDayAsWeekInFirstDay = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(baseDayOfWeek));
        int subtractDays = baseDayAsWeekInFirstDay.until(firstDayOfMonth).getDays();
        LocalDate result = date.plusDays(subtractDays);
        return result.isAfter(date.with(TemporalAdjusters.lastDayOfMonth()))
                ? date.get(ChronoField.ALIGNED_WEEK_OF_MONTH)
                : result.get(ChronoField.ALIGNED_WEEK_OF_MONTH);
    }

    // 이미지 리사이징 참조: https://huskdoll.tistory.com/826
    public static BufferedImage resizeImage(BufferedImage originalImage, int maxWidth, int maxHeight) {
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();

        double ratio = (double) maxWidth / (double) originalWidth;

        if ((double) maxHeight / (double) originalHeight < ratio) {
            ratio = (double) maxHeight / (double) originalHeight;
        }

        int resizedWidth = (int) (originalWidth * ratio);
        int resizedHeight = (int) (originalHeight * ratio);
        Image image = originalImage.getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(resizedWidth, resizedHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g = resizedImage.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return resizedImage;
    }
}
