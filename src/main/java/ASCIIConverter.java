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


    public void setImageToProcess(BufferedImage img) {
        imageToProcess = img;
    }

    public BufferedImage getImageToRender() {
        return imageToRender;
    }


    private BufferedImage imageToRender;


    GetColors colors;
    int[][] pic;
    int[][] red;
    int[][] green;
    int[][] blue;

    GetASCIIchar asciiChar;
    char[][] chars;

    GetColors renderColors;
    int[][] rpic;
    int[][] rred;
    int[][] rgreen;
    int[][] rblue;

    GetASCIIchar asciiCharR;
    char[][] rchars;

    Tile[][] tilesToRender = new Tile[panelY][panelX];

    public void renderImg() {
        try {
            imageToRender = resize(imageToProcess, panelY, panelX);
        } catch (IOException e) {
            e.printStackTrace();


        }
    }

    public void process() {
renderImg();
       //  tilesToRender= new Tile[panelY][panelX];
        colors = new GetColors(imageToProcess);
        pic = colors.pic();
        red = colors.colorRed();
        green = colors.colorGreen();
        blue = colors.colorBlue();
        asciiChar = new GetASCIIchar(pic);
        chars = asciiChar.getChar();

        renderColors = new GetColors(imageToRender);
        rpic = renderColors.pic();
        rred = renderColors.colorRed();
        rgreen = renderColors.colorGreen();
        rblue = renderColors.colorBlue();

        asciiCharR = new GetASCIIchar(rpic);
        rchars = asciiChar.getChar();
        GetASCIIchar asciiCharR = new GetASCIIchar(rpic);
        char[][] rchars = asciiChar.getChar();
        for (int i = 0; i < panelY - 1; i++) {
            for (int j = 0; j < panelX - 1; j++) {
                tilesToRender[i][j] = createTile(rchars[i][j], rred[i][j], rgreen[i][j], rblue[i][j]);
            }
        }
    }

  /*  public Tile[][] convertForRender() {

        for (int i = 0; i < panelY; i++) {
            for (int j = 0; j < panelX; j++) {
                tilesToRender[i][j] = createTile(rchars[i][j], rred[i][j], rgreen[i][j], rblue[i][j]);

            }
        }
        return tilesToRender;
    }*/

    public ASCIIPixel[][] convertForSave() {
        int height = imageToProcess.getHeight();
        int width = imageToProcess.getWidth();
        ASCIIPixel[][] pixels = new ASCIIPixel[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixels[i][j] = new ASCIIPixel(red[i][j], green[i][j], blue[i][j], chars[i][j]);
            }
        }
        return pixels;
    }

    public Tile createTile(char ch, int red, int green, int blue) {

        Tile tile = Tiles.newBuilder()
                .withBackgroundColor(TileColors.create(red - (red / 3), green - (green / 3), blue - (blue / 3)))
                .withForegroundColor(TileColors.create(red, green, blue))
                .withCharacter(ch)
                .build();
        return tile;
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) throws IOException {
        return Thumbnails.of(img).size(newW, newH).asBufferedImage();
    }
}