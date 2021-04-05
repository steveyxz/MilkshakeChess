package com.milkshakeChess.objects.startScreen;

import com.milkshakeChess.Game;
import com.milkshakeChess.enums.id.SideID;
import com.milkshakeChess.models.screenObjects.StartScreenObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

//This is a purely decorative pawn (or maybe not...)
public class FakePawn extends StartScreenObject {

    private final SideID sideID;

    public FakePawn(int x, int y, int rotation, SideID sideID) {
        super(x, y, rotation);
        this.sideID = sideID;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

        Image img = Game.whitePawnIMG;
        if (sideID == SideID.Black) {
            img = Game.blackPawnIMG;
        }

        BufferedImage image = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = image.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        double rads = Math.toRadians(rotation);
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
        AffineTransform at = new AffineTransform();
        at.translate(w / 2F, h / 2F);
        at.rotate(rads, 0, 0);
        at.translate(-image.getWidth() / 2F, -image.getHeight() / 2F);
        AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image, rotatedImage);

        g.drawImage(rotatedImage, x, y, width, height, null);
    }
}
