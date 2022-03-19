package com.company;

import java.awt.*;
import java.nio.channels.Pipe;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        Game game = new Game(1);
        game.gameLoop();

    }


}
