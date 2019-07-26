import net.coobird.thumbnailator.Thumbnails;
import org.hexworks.zircon.api.TileColors;
import org.hexworks.zircon.api.Tiles;
import org.hexworks.zircon.api.data.Tile;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ASCIIConverter {

    int panelX = GUI.getPanelX();
    int panelY = GUI.getPanelY();

    public BufferedImage getImageToProcess() {
        return imageToProcess;
    }


    private BufferedImage imageToProcess;
    private Tile processedImage;

    public void setImageToProcess(BufferedImage img) {
        imageToProcess = img;
    }

    public BufferedImage getImageToRender() {
        return imageToRender;
    }


    private BufferedImage imageToRender;

    {
        try {
            imageToRender = resize(imageToProcess, panelY, panelX);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    GetColors colors = new GetColors(imageToProcess);
    int[][] pic = colors.pic();
    int[][] red = colors.colorRed();
    int[][] green = colors.colorGreen();
    int[][] blue = colors.colorBlue();

    GetASCIIchar asciiChar = new GetASCIIchar(pic);
    char[][] chars = asciiChar.getChar();

    GetColors renderColors = new GetColors(imageToRender);
    int[][] rpic = renderColors.pic();
    int[][] rred = renderColors.colorRed();
    int[][] rgreen = renderColors.colorGreen();
    int[][] rblue = renderColors.colorBlue();


    GetASCIIchar asciiCharR = new GetASCIIchar(rpic);
    char[][] rchars = asciiChar.getChar();

    public Tile[][] convertForRender() {
        Tile[][] imageToRender = new Tile[panelY][panelX];
        for (int i = 0; i < panelY; i++) {
            for (int j = 0; j < panelX; j++) {
                imageToRender[i][j] = createTile(rchars[i][j], rred[i][j], rgreen[i][j], rblue[i][j]);

            }
        }
        return imageToRender;
    }

    public ASCIIPixel[][] convertForSave() {
        int height = imageToProcess.getHeight();
        int width = imageToProcess.getWidth();
        ASCIIPixel[][] pixels = new ASCIIPixel[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                    pixels[i][j] = new ASCIIPixel(red[i][j],green[i][j],blue[i][j], chars[i][j]);
            }
        }
        return pixels;
    }

    public Tile createTile(char ch, int red, int green, int blue) {

        return Tiles.newBuilder()
                .withBackgroundColor(TileColors.create(red - (red / 3), green - (green / 3), blue - (blue / 3)))
                .withForegroundColor(TileColors.create(red, green, blue))
                .withCharacter(ch)
                .build();
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) throws IOException {
        return Thumbnails.of(img).size(newW, newH).asBufferedImage();
    }
}