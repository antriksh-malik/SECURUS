import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;



public class WritingImages {

    public static void main( String[] args ){
        BufferedImage image = null;
        try {

            //you can either use URL or File for reading image using ImageIO
            File imagefile = new File("C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\Securus.png");
            image = ImageIO.read(imagefile);

            BufferedImage resized = resize(image, 300, 300);

            File output = new File("C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\Securus.png");
            ImageIO.write(resized, "png", output);
            //ImageIO Image write Example in Java
            BufferedImage image1= ImageIO.read(new File("C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\Securus.png"));
            for (int i = 1; i <=10; i++) {
                String name = "C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\test\\r1\\s" + i + ".PGM";
                ImageIO.write(image1, "pnm", new File(name));
            }
            for (int i = 1; i <=10; i++) {
                String name = "C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\test\\r2\\s" + i + ".PGM";
                ImageIO.write(image1, "pnm", new File(name));
            }
            for (int i = 1; i <=10; i++) {
                String name = "C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\test\\r3\\s" + i + ".PGM";
                ImageIO.write(image1, "pnm", new File(name));
            }
            for (int i = 1; i <=10; i++) {
                String name = "C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\test\\r4\\s" + i + ".PGM";
                ImageIO.write(image1, "pnm", new File(name));
            }
            for (int i = 1; i <=10; i++) {
                String name = "C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\test\\r5\\s" + i + ".PGM";
                ImageIO.write(image1, "pnm", new File(name));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Success");
    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
}