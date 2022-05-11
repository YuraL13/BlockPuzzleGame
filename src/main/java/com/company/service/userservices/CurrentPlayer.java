package com.company.service.userservices;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface CurrentPlayer {
    String player = null;

    String getPlayer() throws FileNotFoundException;
    void setPlayer(String player) throws IOException;

}
