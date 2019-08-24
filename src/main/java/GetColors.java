import java.awt.*;
import java.awt.image.BufferedImage;

public class GetColors {
    private BufferedImage image;
    int height;
    int width;
    int [][] pic;
    int [][] cRed,cBlue,cGreen;


    public GetColors(BufferedImage image){
        this.image = image;
         height = image.getHeight();
         width = image.getWidth();
         pic = new int[width][height];
         cRed = new int [width][height];
         cGreen = new int [width][height];
        cBlue = new int [width][height];

    }




    public  int[][] pic() {




        Color color;
        int red, green, blue;

        for (int i = 0; i < width-1; i++) {
            for (int j = 0; j < height-1; j++) {

                color = new Color(image.getRGB(i, j));
                red = color.getRed();
                blue = color.getBlue();
                green = color.getGreen();


                pic[i][j] = (red + blue + green) / 3;
            }
        }

        return pic;

    }
    public int[][] colorRed(){




        Color color;
        int red;

        for (int i = 0; i < width-1; i++) {
            for (int j = 0; j < height-1; j++) {
                color = new Color(image.getRGB(i, j));
                red = color.getRed();


                cRed[i][j] = red;
            }
        }


        return  cRed;

    }

    public int[][] colorGreen(){



        Color color;
        int  green;

        for (int i = 0; i < width-1; i++) {
            for (int j = 0; j < height-1; j++) {
                color = new Color(image.getRGB(i, j));

                green = color.getGreen();


                cGreen[i][j] = green;
            }
        }


        return  cGreen;

    }

    public int[][] colorBlue(){




        Color color;
        int blue;

        for (int i = 0; i < width-1; i++) {
            for (int j = 0; j < height-1; j++) {
                color = new Color(image.getRGB(i, j));

                blue = color.getBlue();



                cBlue[i][j] = blue;
            }
        }


        return  cBlue;

    }

}
