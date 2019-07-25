import org.hexworks.zircon.api.TileColors;
import org.hexworks.zircon.api.Tiles;
import org.hexworks.zircon.api.data.Tile;

import java.awt.image.BufferedImage;

public class ASCIIConverter {
    public BufferedImage getImageToProcess() {
        return imageToProcess;
    }

    private BufferedImage imageToProcess;
    private Tile processedImage;

    public void setImageToProcess(BufferedImage img) {
        imageToProcess = img;
    }

    public Tile getProcessedImage() {
        return processedImage;
    }

    GetColors colors = new GetColors(imageToProcess);
    int[][] pic = colors.pic();
    int[][] red = colors.colorRed();
    int[][] green = colors.colorGreen();
    int[][] blue = colors.colorBlue();

    public void process() {
        //the main function that does it's magic and turns the input image into the ASCII representation
Tile [] tiles= new Tile[5];
tiles[0]= createTile('a',10,12,43);

    }

    public Tile createTile(char ch, int red, int green, int blue) {

        Tile tile = Tiles.newBuilder()
                .withBackgroundColor(TileColors.create(red - (red / 3), green - (green / 3), blue - (blue / 3)))
                .withForegroundColor(TileColors.create(red, green, blue))
                .withCharacter(ch)
                .build();
        return tile;
    }
}