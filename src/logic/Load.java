/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template fileString, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javafx.stage.FileChooser;

/**
 * load game from the selected fileString
 *
 * @author kobra
 */
public class Load {

    GUIConnector gui;
    GUIToGame game;

    /**
     * for loading the given parameter into game
     *
     * @param gui
     * @param game
     * @return
     * @throws IOException
     */
    public GUIToGame loadFile(GUIConnector gui, GUIToGame game) throws IOException {
        this.gui = gui;
        this.game = game;
        String line = "";
        String fileString = "";
        FileReader fr = null;
        BufferedReader br = null;
        InputStreamReader sr = null;
        Scanner sc = null;

        System.out.println("input stream in the file");
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose your load file");

        File selectedFile = null;

        if (selectedFile == null) {
            selectedFile = chooser.showOpenDialog(null);
        }

        try {

            fr = new FileReader(selectedFile.getPath());
            sr = new InputStreamReader(System.in);

            // work
            br = new BufferedReader(fr);
            sc = new Scanner(sr);
            while ((line = br.readLine()) != null) {
                fileString = fileString + line + "\n";
            }
            System.out.println("load filed" + fileString);

        } finally {
            // close
            if (sc != null) {
                sc.close();
            }
            if (br != null) {
                br.close();
            }
            if (sr != null) {
                sr.close();
            }
            if (fr != null) {
                fr.close();
            }
        }

        return loadAllFileString(fileString);
    }

    /**
     * change given string , read line by line of all parts such as bags,
     * fields, nextbox, current box, current player and current domino. Every
     * parts are converted to their format and in last step return them.
     *
     * @param fileString
     * @return
     */
    private GUIToGame loadAllFileString(String fileString) {
        int numOfPlayers = 0;
        String lines[] = fileString.split("\n");

        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("field")) {
                numOfPlayers++;
            }
        }
        List<Tiles> tiles = new LinkedList<>();
        List<Domino> dominos = new LinkedList<>();
        String bag = "";
        String bank = "";
        int length = 0;
        Domino tempDom;
        Domino[] currBox = new Domino[numOfPlayers];
        Domino[] nextBox = new Domino[numOfPlayers];
        Field[] cardFields = new Field[numOfPlayers];
        String[] fields = new String[numOfPlayers];
        int[] currId = new int[numOfPlayers];
        int[] nextId = new int[numOfPlayers];
        Tiles tempTile;

        if (fileString != null && numOfPlayers == 4) {
            String temp = "";
            for (int i = 0; i < lines.length; i++) {
                if (lines[i].contains("bag")) {
                    length = (lines[i + 1].length() + 1) / 5; //for length inside the bag for iterating
                    // System.out.println(length);
                    int m = 0;
                    for (int j = 0; j < length; j++) {
                        for (int k = m; k < m + 4; k++) {
                            bag = bag + lines[i + 1].charAt(k);
                        }
                        m = m + 5;
                    }
                }
            }//System.out.println("bag" + bag);
            int s = 0;//reading every four char counter
            String tempBag = "";//store every four char inside this
            Tiles.fill(tiles);
            for (int i = 0; i < length; i++) {
                for (int j = s; j < s + 4; j++) {
                    tempBag = tempBag + bag.charAt(j);

                }
                tempTile = Tiles.getTiles(tiles, tempBag);

                tiles.remove(tempTile);
                dominos.add(new Domino(tempTile));
                s = s + 4;

                tempBag = "";
            }
            for (int i = 0; i < lines.length; i++) {
                // for current box
                if (lines[i].contains("bank")) {
                    tiles.clear();
                    length = (lines[i + 1].length() + 2) / 8; //for length inside the bag for iterating
                    int m = 0;

                    int n = numOfPlayers - length;
                    //System.out.println("n "+n);
                    for (int j = 0; j < length; j++) {
                        for (int k = m + 2; k < m + 6; k++) {
                            bank = bank + lines[i + 1].charAt(k);
                            if (j < n) {
                                currId[j] = -1;
                                currId[j + n] = convertToInt(lines[i + 1].charAt(m));

                            }

                        }
                        Tiles.fill(tiles);

                        tempTile = Tiles.getTiles(tiles, bank);
                        currBox[j] = new Domino(tempTile);
                        bank = "";
                        m = m + 8;
                    }
                    // for next box
                    tiles.clear();
                    length = (lines[i + 2].length() + 2) / 8; //for length inside the bag for iterating
                    m = 0;
                    for (int j = 0; j < length; j++) {
                        for (int k = m + 2; k < m + 6; k++) {
                            bank = bank + lines[i + 2].charAt(k);
                            nextId[j] = convertToInt(lines[i + 2].charAt(m));
                        }
                        Tiles.fill(tiles);
                        tempTile = Tiles.getTiles(tiles, bank);
                        nextBox[j] = new Domino(tempTile);
                        bank = "";
                        m = m + 8;
                    }
                }

            }

            int k = 0;
            String t = "";
            for (int i = 0; i < lines.length; i++) {

                if (lines[i].contains("field")) {
                    int brdLen = lines[i + 1].length();

                    length = (lines[i + 1].length() + 1) / 3;
                    for (int j = 0; j < length; j++) {
                        t = t + lines[i + j + 1] + "\n";
                    }
                    fields[k] = t;

                    t = "";

                    k++;
                }
            }
            for (int i = 0; i < cardFields.length; i++) {
                cardFields[i] = new Field(fields[i]);
//                System.out.println(cardFields[i].toString());
            }

            this.game = new Game(this.gui, cardFields, currBox, nextBox, currId, nextId, dominos);

        }
        return this.game;
    }

    /**
     * convert given char to int
     *
     * @param s is given char
     * @return int value of char
     */
    private int convertToInt(char s) {
        int last = 0;
        switch (s) {
            case '1':
                last = 1;
                break;
            case '2':
                last = 2;
                break;
            case '3':
                last = 3;
                break;
            case '0':
                last = 0;
                break;
            default:
                last = -1;
                break;
        }
        return last;
    }

}
