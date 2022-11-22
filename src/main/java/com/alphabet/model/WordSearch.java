package com.alphabet.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WordSearch implements IWordSearch {

    enum Directions {
        LEFT2RIGHT(0), RIGHT2LEFT(1), TOP2BOTTOM(2), BOTTOM2TOP(3), DIAGONAL2BOTTOM(4), DIAGONAL2TOP(5);

        private final int value;

        private Directions(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    // 80 spanish noun words
    private static final List<String> WORD_LIST = List.of("abrelatas", "pelusa", "aire", "escritura", "periferia",
            "amor", "escuela", "perro", "esfera", "pilar", "animal", "esquina", "piscina", "facilidad", "planta",
            "asiento", "favor", "pomelo", "galleta", "posavasos", "barco", "posibilidad", "guitarra", "programa",
            "bondad", "hoja", "puerta", "idea", "quejido", "bufanda", "joroba", "cable", "juguete", "ropa",
            "calculadora", "julio", "silla", "carpeta", "libros", "sonido", "cartera", "suciedad", "casco", "loro",
            "suerte", "celular", "luz", "sustancia", "cerradura", "manantial", "tarima", "mano", "mausoleo",
            "televidente", "mesa", "tierra", "ciruela", "tigre", "ciudad", "trabajo", "claridad", "moneda", "clavel",
            "mueble", "nariz", "competencia", "utensilio", "computadora", "notas", "vaso", "cuaderno", "ventana",
            "cuerda", "pantalla", "vidrio", "curiosidad", "parlante", "dedo", "pasto", "visita");

    private List<String> words;
    private int width;
    private int high;
    private char[][] soup;
    private boolean left2right;
    private boolean right2left;
    private boolean top2bottom;
    private boolean bottom2top;
    private boolean diagonal2bottom;
    private boolean diagonal2top;

    // directions{0:left2right, 1:right2left, 2:top2bottom, 3:bottom2top,
    // 4:diagonal2bottom}
    private List<Integer> directions;

    public WordSearch() {
        super();
        this.width = 15;
        this.high = 15;
        this.soup = new char[this.high][this.width];
        this.left2right = true;
        this.right2left = false;
        this.top2bottom = true;
        this.bottom2top = false;
        this.diagonal2bottom = false;
        this.diagonal2top = false;
        this.directions = List.of(Directions.LEFT2RIGHT.getValue(), Directions.TOP2BOTTOM.getValue());
        this.words = generateRandomWords(10);
    }

    public WordSearch(int width, int high, boolean left2right, boolean right2left, boolean top2bottom,
            boolean bottom2top, boolean diagonal1, boolean diagonal2, int numberOfWords) {
        super();
        this.width = width;
        this.high = high;
        this.soup = new char[this.high][this.width];
        this.left2right = left2right;
        this.right2left = right2left;
        this.top2bottom = top2bottom;
        this.bottom2top = bottom2top;
        this.diagonal2bottom = diagonal1;
        this.diagonal2top = diagonal2;
        this.directions = getDirections();
        this.words = generateRandomWords(numberOfWords);
    }

    private List<Integer> getDirections() {
        List<Integer> dirs = new ArrayList<>();
        if (this.left2right) {
            dirs.add(Directions.LEFT2RIGHT.getValue());
        }
        if (this.right2left) {
            dirs.add(Directions.RIGHT2LEFT.getValue());
        }
        if (this.top2bottom) {
            dirs.add(Directions.TOP2BOTTOM.getValue());
        }
        if (this.bottom2top) {
            dirs.add(Directions.BOTTOM2TOP.getValue());
        }
        if (this.diagonal2bottom) {
            dirs.add(Directions.DIAGONAL2BOTTOM.getValue());
        }
        if (this.diagonal2top) {
            dirs.add(Directions.DIAGONAL2TOP.getValue());
        }
        return dirs;
    }

    private List<String> generateRandomWords(int numberOfWords) {
        List<String> words = new ArrayList<>();
        for (char[] row : this.soup) {
            Arrays.fill(row, ' ');
        }
        Random rand = new Random();
        while (numberOfWords > 0) {
            String word = WORD_LIST.get(rand.nextInt(WORD_LIST.size()));
            int direction = this.directions.get(rand.nextInt(this.directions.size()));
            int xStart = rand.nextInt(this.high);
            int yStart = rand.nextInt(this.width);
            if (!words.contains(word) && addSoupWords(this.soup, word, xStart, yStart, direction)) {
                words.add(word);
                numberOfWords -= 1;
            }

        }
        // random fill
        Random r = new Random();
        for (int i = 0; i < this.high; i++) {
            for (int j = 0; j < this.width; j++) {
                if (soup[i][j] == ' ') {
                    soup[i][j] = (char) (r.nextInt(26) + 'a');
                }
            }
        }

        return words;
    }

    private boolean addSoupWords(char[][] soup, String word, int xStart, int yStart, int direction) {
        // check border
        int xChange = 0;
        int yChange = 0;
        if (direction == Directions.LEFT2RIGHT.getValue() || direction == Directions.DIAGONAL2BOTTOM.getValue()) {
            yChange = 1;
        }
        if (direction == Directions.RIGHT2LEFT.getValue() || direction == Directions.DIAGONAL2TOP.getValue()) {
            yChange = -1;
        }

        if (direction == Directions.TOP2BOTTOM.getValue() || direction == Directions.DIAGONAL2BOTTOM.getValue()) {
            xChange = 1;
        }
        if (direction == Directions.RIGHT2LEFT.getValue() || direction == Directions.DIAGONAL2TOP.getValue()) {
            xChange = -1;
        }
        int wordLength = word.length();
        int xEnd = xStart + wordLength * xChange;
        int yEnd = yStart + wordLength * yChange;

        if (xEnd < 0 || xEnd >= this.high) {
            return false;
        }
        if (yEnd < 0 || yEnd >= this.width) {
            return false;
        }

        // check colisions
        int xPos = xStart;
        int yPos = yStart;
        for (int i = 0; i < wordLength; i++) {
            if (soup[xPos][yPos] != ' ' && soup[xPos][yPos] != word.charAt(i)) {
                return false;
            }
            xPos += xChange;
            yPos += yChange;
        }

        // insert word
        xPos = xStart;
        yPos = yStart;
        for (int i = 0; i < wordLength; i++) {
            soup[xPos][yPos] = word.charAt(i);
            xPos += xChange;
            yPos += yChange;
        }

        return true;
    }

    public List<String> getWords() {
        return words;
    }

    public boolean setWords(int startRow, int startColumn, int endRow, int endColumn) {
        // validar imput

        // get direcction
        int r = (int) Math.signum(endRow - startRow);
        int c = (int) Math.signum(endColumn - startColumn);
        Directions d;
        if (r == 0) {
            if (c > 0) {
                d = Directions.LEFT2RIGHT;
            } else {
                d = Directions.RIGHT2LEFT;
            }
        } else if (r > 0) {
            if (c > 0) {
                d = Directions.DIAGONAL2BOTTOM;
            } else {
                d = Directions.TOP2BOTTOM;
            }
        } else {
            if (c < 0) {
                d = Directions.DIAGONAL2TOP;
            } else {
                d = Directions.BOTTOM2TOP;
            }
        }

        // extract word
        // insert word
        int xPos = startRow;
        int yPos = startColumn;
        char letter;
        String w = "";
        while (xPos != endRow || yPos != endColumn) {
            letter = soup[xPos][yPos];
            w += letter;
            xPos += r;
            yPos += c;
        }
        letter = soup[xPos][yPos];
        w += letter;

        // verify word in words
        if (!this.words.contains(w)) {
            return false;
        }

        // modify soup
        xPos = startRow;
        yPos = startColumn;
        while (xPos != endRow || yPos != endColumn) {
            soup[xPos][yPos] -= 32;
            xPos += r;
            yPos += c;
        }
        soup[xPos][yPos] -= 32;

        return true;

    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        for (char[] x : this.soup) {
            for (char y : x) {
                result.append(y + " ");
            }
            result.append("\n");
        }
        return result.toString();
    }

}
