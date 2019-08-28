import org.hexworks.zircon.api.CP437TilesetResources;
import org.hexworks.zircon.api.Positions;
import org.hexworks.zircon.api.TileColors;
import org.hexworks.zircon.api.Tiles;
import org.hexworks.zircon.api.data.Tile;
import org.hexworks.zircon.api.tileset.Tileset;
import org.hexworks.zircon.internal.tileset.SwingTilesetLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class SaveImage  {

    private int[][] red;
    private int[][] green;
    private int[][] blue;
    private char[][] chars;
    private int x,y;

    public SaveImage(int[][] red, int[][] green, int[][] blue, char[][] chars, int x, int y) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.chars = chars;
        this.x =x;
        this.y =y;
    }



    Tile[][] imageForSave = new Tile[x][y];

    SwingTilesetLoader loader = new SwingTilesetLoader();
    final Tileset<Graphics2D> tileset = loader.loadTilesetFrom(CP437TilesetResources.rexPaint16x16());
   // final BufferedImage image = new BufferedImage(800, 600, BufferedImage.TRANSLUCENT);

      BufferedImage image = new BufferedImage(x * tileset.getHeight(), y * tileset.getWidth(), BufferedImage.TRANSLUCENT);

    Graphics2D graphics = image.createGraphics();

    public void paint() throws Exception {
        fillArray();
        for (int i = 0; i < red.length; i++) {
            for (int j = 0; j < red[i].length; j++) {
                tileset.drawTile(imageForSave[i][j], graphics, Positions.create(i, j));

            }
        }
        File file = new File("TestImage3.png");
        ImageIO.write(image, "png", file);
      //  ImageIO.write(saveImage, "png", new File("./output_image.png"));

    }

    public void fillArray() {

        for (int i = 0; i < imageForSave.length; i++) {
            for (int j = 0; j < imageForSave[i].length; j++) {
                imageForSave[i][j] = createTile(chars[i][j], red[i][j], green[i][j], blue[i][j]);
            }
        }

    }

    public Tile createTile(char ch, int red, int green, int blue) {


        return Tiles.newBuilder()
                .withBackgroundColor(TileColors.create(red - (red / 3), green - (green / 3), blue - (blue / 3)))
                .withForegroundColor(TileColors.create(red, green, blue))
                .withCharacter(ch)
                .build();
    }


}
