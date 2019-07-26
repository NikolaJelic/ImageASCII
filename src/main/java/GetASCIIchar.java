public class GetASCIIchar {
    private int[][] pic;

    public GetASCIIchar(int[][] pic) {
        this.pic = pic;
    }

    public char[][] getChar() {
        char[][] aChar = new char[pic.length][pic[0].length];
        for (int i = 0; i < pic.length; i++) {
            for (int j = 0; j < pic[i].length; j++) {


                if (pic[i][j] >= 255) {
                    aChar[i][j] = '$';
                } else if (pic[i][j] >= 250) {
                    aChar[i][j] = '@';
                } else if (pic[i][j] >= 245) {
                    aChar[i][j] = 'B';
                } else if (pic[i][j] >= 240) {
                    aChar[i][j] = '%';

                } else if (pic[i][j] >= 235) {
                    aChar[i][j] = '8';

                } else if (pic[i][j] >= 230) {
                    aChar[i][j] = 'W';

                } else if (pic[i][j] >= 225) {
                    aChar[i][j] = 'M';

                } else if (pic[i][j] >= 220) {
                    aChar[i][j] = '#';

                } else if (pic[i][j] >= 215) {
                    aChar[i][j] = '*';

                } else if (pic[i][j] >= 210) {
                    aChar[i][j] = '0';

                } else if (pic[i][j] >= 205) {
                    aChar[i][j] = 'a';

                } else if (pic[i][j] >= 200) {
                    aChar[i][j] = 'h';

                } else if (pic[i][j] >= 195) {
                    aChar[i][j] = 'k';

                } else if (pic[i][j] >= 190) {
                    aChar[i][j] = 'b';

                } else if (pic[i][j] >= 185) {
                    aChar[i][j] = 'p';

                } else if (pic[i][j] >= 180) {
                    aChar[i][j] = 'd';

                } else if (pic[i][j] >= 175) {
                    aChar[i][j] = 'Z';

                } else if (pic[i][j] >= 170) {
                    aChar[i][j] = 'O';

                } else if (pic[i][j] >= 165) {
                    aChar[i][j] = 'Q';

                } else if (pic[i][j] >= 160) {
                    aChar[i][j] = 'J';

                } else if (pic[i][j] >= 155) {
                    aChar[i][j] = 'L';

                } else if (pic[i][j] >= 150) {
                    aChar[i][j] = 'C';

                } else if (pic[i][j] >= 145) {
                    aChar[i][j] = 'U';

                } else if (pic[i][j] >= 140) {
                    aChar[i][j] = 'Y';

                } else if (pic[i][j] >= 135) {
                    aChar[i][j] = 'X';

                } else if (pic[i][j] >= 130) {
                    aChar[i][j] = 'z';

                } else if (pic[i][j] >= 125) {
                    aChar[i][j] = 'c';
                } else if (pic[i][j] >= 120) {
                    aChar[i][j] = 'u';
                } else if (pic[i][j] >= 115) {
                    aChar[i][j] = 'n';
                } else if (pic[i][j] >= 110) {
                    aChar[i][j] = 'v';
                } else if (pic[i][j] >= 105) {
                    aChar[i][j] = 'x';
                } else if (pic[i][j] >= 100) {
                    aChar[i][j] = 'r';
                } else if (pic[i][j] >= 95) {
                    aChar[i][j] = 'j';
                } else if (pic[i][j] >= 90) {
                    aChar[i][j] = 'f';
                } else if (pic[i][j] >= 85) {
                    aChar[i][j] = 't';
                } else if (pic[i][j] >= 80) {

                    aChar[i][j] = '/';
                } else if (pic[i][j] >= 75) {
                    aChar[i][j] = '\\';
                } else if (pic[i][j] >= 70) {
                    aChar[i][j] = '|';
                } else if (pic[i][j] >= 65) {
                    aChar[i][j] = '(';
                } else if (pic[i][j] >= 60) {
                    aChar[i][j] = '}';
                } else if (pic[i][j] >= 55) {
                    aChar[i][j] = '1';
                } else if (pic[i][j] >= 50) {
                    aChar[i][j] = '[';
                } else if (pic[i][j] >= 45) {
                    aChar[i][j] = '-';
                } else if (pic[i][j] >= 40) {
                    aChar[i][j] = '_';
                } else if (pic[i][j] >= 35) {
                    aChar[i][j] = '?';
                } else if (pic[i][j] >= 30) {
                    aChar[i][j] = '+';
                } else if (pic[i][j] >= 25) {
                    aChar[i][j] = '~';
                } else if (pic[i][j] >= 20) {
                    aChar[i][j] = '!';
                } else if (pic[i][j] >= 15) {
                    aChar[i][j] = ':';
                } else if (pic[i][j] >= 10) {
                    aChar[i][j] = ';';
                } else if (pic[i][j] >= 5) {
                    aChar[i][j] = ',';
                } else if (pic[i][j] >= 0) {
                    aChar[i][j] = '.';
                }
            }

        }
        return aChar;
    }
}
