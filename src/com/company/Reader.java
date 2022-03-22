package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {
    private List<String> listPieces;
    private int level;

    public Reader(int level){
        listPieces = new ArrayList<>();
        this.level = level;
    }

    public List<String> readLevelFromFile(){
        try{
            String fileName = "C:\\Users\\yural\\Desktop\\Mine\\Study TUKE\\Code\\Block Puzzle\\src\\com\\company\\levels\\level" + level + ".txt";
            File file = new File(fileName);
            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()){
                String data = scan.nextLine();
                listPieces.add(data);
            }
            scan.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred. File does not exist");
            e.printStackTrace();
        }

        return listPieces;
    }

}
