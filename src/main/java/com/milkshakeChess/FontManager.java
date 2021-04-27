package com.milkshakeChess;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class FontManager {

    public static Font avengerTitleFont;
    public static Font debrosee;
    public static Font kgTenK1;
    public static Font kgTenK2;

    public FontManager() {
        try {
            initFonts();
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public static Font getResizedFont(Font font, float size) {
        return font.deriveFont(size);
    }

    private void initFonts() throws IOException, FontFormatException {
        byte[] fontSrc = Objects.requireNonNull(FontManager.class.getResourceAsStream("/fonts/Subway.ttf")).readAllBytes();
        InputStream fontInput = new ByteArrayInputStream(fontSrc);

        avengerTitleFont = Font.createFont(Font.TRUETYPE_FONT, fontInput);
        avengerTitleFont = avengerTitleFont.deriveFont(30F);

        fontSrc = Objects.requireNonNull(FontManager.class.getResourceAsStream("/fonts/Debrosee.ttf")).readAllBytes();
        fontInput = new ByteArrayInputStream(fontSrc);

        debrosee = Font.createFont(Font.TRUETYPE_FONT, fontInput);
        debrosee = debrosee.deriveFont(30F);

        fontSrc = Objects.requireNonNull(FontManager.class.getResourceAsStream("/fonts/KgTenThousand1.ttf")).readAllBytes();
        fontInput = new ByteArrayInputStream(fontSrc);

        kgTenK1 = Font.createFont(Font.TRUETYPE_FONT, fontInput);
        kgTenK1 = kgTenK1.deriveFont(30F);

        fontSrc = Objects.requireNonNull(FontManager.class.getResourceAsStream("/fonts/KgTenThousand2.ttf")).readAllBytes();
        fontInput = new ByteArrayInputStream(fontSrc);

        kgTenK2 = Font.createFont(Font.TRUETYPE_FONT, fontInput);
        kgTenK2 = kgTenK2.deriveFont(30F);
    }

}
