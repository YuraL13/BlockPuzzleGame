package com.company.service.userservices;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CurrentPlayerImp implements CurrentPlayer{

    String player;
    String fileName = "src\\main\\resources\\CurrentPlayer\\player.txt";
    File file = new File(fileName);

    @Override
    public String getPlayer() throws FileNotFoundException {
        Scanner scan = new Scanner(file);

        if(scan.hasNextLine()){
            player = scan.nextLine();
        }

        System.out.println(player + " - LOGIN BEEN READ");
        scan.close();
        return player;
    }

    @Override
    public void setPlayer(String player) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(player);
        fw.close();
    }
}
