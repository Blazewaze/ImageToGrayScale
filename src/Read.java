import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Read {
    public void ConvertImage(String path) {
        try {

            File file = new File(path);
            BufferedImage image = ImageIO.read(file);
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage bwImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixelColor = image.getRGB(x, y);


                    int red = (pixelColor >> 16) & 0xff;
                    int green = (pixelColor >> 8) & 0xff;
                    int blue = pixelColor & 0xff;


                    int gray = (int)(0.299 * red + 0.587 * green + 0.114 * blue);
                    int newPixelColor = (gray << 16) | (gray << 8) | gray; // RGB format
                    bwImage.setRGB(x, y, newPixelColor);
                }
            }

            // Save the black and white image
            ImageIO.write(bwImage, "jpg", new File("path/to/your/output_bw_image.jpg"));

            System.out.println("Black and white filter applied successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
