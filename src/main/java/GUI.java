import org.hexworks.zircon.api.*;
import org.hexworks.zircon.api.component.Button;
import org.hexworks.zircon.api.component.Panel;
import org.hexworks.zircon.api.data.Size;
import org.hexworks.zircon.api.data.Tile;
import org.hexworks.zircon.api.graphics.BoxType;
import org.hexworks.zircon.api.grid.TileGrid;
import org.hexworks.zircon.api.resource.TilesetResource;
import org.hexworks.zircon.api.screen.Screen;
import org.hexworks.zircon.internal.component.renderer.NoOpComponentRenderer;

import java.awt.*;

import static org.hexworks.zircon.api.ComponentDecorations.box;
import static org.hexworks.zircon.api.ComponentDecorations.shadow;
import static org.hexworks.zircon.api.uievent.ComponentEventType.ACTIVATED;

public class GUI {
    private static final TilesetResource TILESET = CP437TilesetResources.rexPaint16x16();
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static double columns = screenSize.getWidth() / TILESET.getWidth() / 1.2;
    static double rows = screenSize.getHeight() / TILESET.getHeight() / 1.2;
    static Size terminalSize = Sizes.create((int) columns, (int) rows);

    private final static TileGrid tileGrid = SwingApplications.startTileGrid(AppConfigs.newConfig()
            .withDefaultTileset(TILESET)
            .withSize(terminalSize)
            .withDebugMode(true)
            .withTitle("ImageASCII")
            .build());

    public static TileGrid getTileGrid() {
        return tileGrid;
    }

    private static int panelX = (int) (columns - (columns / 6));
    private static int panelY = (int) rows;

    public static int getPanelX() {
        return panelX;
    }

    public static int getPanelY() {
        return panelY;
    }

    private static Panel panelImage = Components.panel()
            .withDecorations(box(BoxType.LEFT_RIGHT_DOUBLE, "Image"), shadow())
            .withPosition(0, 0)
            .withComponentRenderer(new NoOpComponentRenderer())
            .withSize(Sizes.create(panelX, panelY))
            .build();

    public static Panel getPanelImage() {
        return panelImage;
    }

    public void setPanelImage(Panel panelImage) {
        this.panelImage = panelImage;
    }

    public void GUIrender() {


        TileGrid tileGrid = getTileGrid();
        Screen screen = Screens.createScreenFor(tileGrid);
        int panelX = getPanelX();
        int panelY = getPanelY();
        System.out.println(panelX + "X");
        System.out.println(panelY + "Y");
        int optionsX = (int) (columns / 6);
        int optionsY = (int) rows;
        Panel panelOptions = Components.panel()
                .withDecorations(box(BoxType.LEFT_RIGHT_DOUBLE, "Options"), shadow())
                .withPosition(panelX, 0)
                .withSize(Sizes.create(optionsX, panelY))
                .build();
        Button openButton = Components.button()
                .withText("New Image")
                .withPosition(Positions.create(0, 2))
                .build();

        Button workButton = Components.button()
                .withText("Apply")
                .withPosition(Positions.create(0, 4))
                .build();
        Button saveButton = Components.button()
                .withText("Save")
                .withPosition(Positions.create(0, optionsY - 5))
                .build();
        ASCIIConverter asciiConverter = new ASCIIConverter();
        Panel panelImage = getPanelImage();
        openButton.handleComponentEvents(ACTIVATED, (event) -> {
            //  System.exit(0);
            try {
                asciiConverter.setImageToProcess(FilePicker.openFileChooser());

            } catch (Exception e) {

            }
            asciiConverter.process();
            Tile[][] tiles = asciiConverter.tilesToRender;
            for (int i = 0; i < panelY; i++) {
                for (int j = 0; j < panelX; j++) {
                    panelImage.setTileAt(
                            Positions.create(i, j),
                            tiles[i][j]
                    );
                }
            }
            return UIEventResponses.processed();
        });



        panelOptions.addComponent(openButton);
        panelOptions.addComponent(workButton);
        panelOptions.addComponent(saveButton);
        screen.addComponent(panelImage);
        screen.addComponent(panelOptions);
        screen.applyColorTheme(ColorThemes.newBuilder()
                .withAccentColor(TileColors.fromString("#ec407a"))
                .withPrimaryForegroundColor(TileColors.fromString("#d81b60"))
                .withSecondaryForegroundColor(TileColors.fromString("#ec407a"))
                .withPrimaryBackgroundColor(TileColors.fromString("#00001c"))
                .withSecondaryBackgroundColor(TileColors.fromString("#00001c"))
                .build());
        screen.display();

    }

}
