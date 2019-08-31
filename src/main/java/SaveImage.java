import org.hexworks.zircon.api.CP437TilesetResources;
import org.hexworks.zircon.api.Positions;
import org.hexworks.zircon.api.TileColors;
import org.hexworks.zircon.api.Tiles;
import org.hexworks.zircon.api.data.Tile;
import org.hexworks.zircon.api.tileset.Tileset;
import org.hexworks.zircon.internal.tileset.SwingTilesetLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SaveImage {

    private int[][] red;
    private int[][] green;
    private int[][] blue;
    private char[][] chars;
    int x, y;

    public SaveImage(int[][] red, int[][] green, int[][] blue, char[][] chars, int x, int y) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.chars = chars;
        this.x = x;
        this.y = y;
    }

    public void paint() throws IOException {
        Tile[][] imageForSave = new Tile[x][y];
        fillArray(imageForSave);
        // you create an image and the corresponding graphics


        // you load the tileset you want
        SwingTilesetLoader loader = new SwingTilesetLoader();
        final Tileset<Graphics2D> tileset = loader.loadTilesetFrom(CP437TilesetResources.rexPaint16x16());
        final BufferedImage image = new BufferedImage(x * tileset.getWidth(), y * tileset.getHeight(), BufferedImage.TRANSLUCENT);
        final Graphics2D graphics = image.createGraphics();
        for (int i = 0; i < x - 1; i++) {
            for (int j = 0; j < y - 1; j++) {
                tileset.drawTile(imageForSave[i][j], graphics, Positions.create(i, j));
            }
        }


        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("*.png", "png"));
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                ImageIO.write(image, "png", new File(file.getAbsolutePath() + ".png"));
            } catch (IOException ex) {
                System.out.println("Failed to save image!");
            }
        } else {
            System.out.println("No file chosen!");
        }
    }


    public void fillArray(Tile[][] imageForSave) {

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
