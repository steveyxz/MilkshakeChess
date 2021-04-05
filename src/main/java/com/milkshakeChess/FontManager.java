package com.milkshakeChess;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class FontManager {

    public static Font avengerTitleFont;

    public FontManager() {
        try {
            initFonts();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }

    private void initFonts() throws IOException, FontFormatException {
        byte[] fontSrc = Objects.requireNonNull(FontManager.class.getResourceAsStream("/fonts/Subway.ttf")).readAllBytes();
        InputStream fontInput = new ByteArrayInputStream(fontSrc);

        avengerTitleFont = Font.createFont(Font.TRUETYPE_FONT, fontInput);
        avengerTitleFont = avengerTitleFont.deriveFont(30F);
    }

    public static Font getResizedFont(Font font, float size) {
        return font.deriveFont(size);
    }

}
