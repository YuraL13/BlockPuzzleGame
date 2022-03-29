package com.company.service;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ScoreServiceJDBC {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    //password
    private static final String PASSWORD = "0992011088lev";

    private long Time;

//    public static void main(String[] args) throws SQLException {
//
//        try( var connection =  DriverManager.getConnection(URL, USER, PASSWORD); var statement = connection.createStatement()){
//            ResultSet resultSet =  statement.executeQuery("select p.player, 'On level ' || l.level_id || ' score: ' || l.score from players p join levelplayerscore l on p.player_id = l.player_id\n");
//            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
//
//            while (resultSet.next()) {
//                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
//                    String columnValue = resultSet.getString(i);
//                    System.out.println(columnValue);
//                }
//            }
//        }
//
//        var sql = "INSERT INTO score (player, level, score) VALUES ('val', 0, 0);";
//
//        try( var connection =  DriverManager.getConnection(URL, USER, PASSWORD); var statement = connection.createStatement()){
//            var result = statement.executeUpdate(sql);
//        }
//
//    }

    /**
     * Starts timer
     */
    public void startTimer(){
        Time = System.nanoTime();
    }

    /**
     * Stops timer
     * @return time in seconds
     */
    public long stopTimer(){
        Time = System.nanoTime() - Time;
        return Time/1000000000;
    }

    public int countScore(long time, int  basicScore){

        if(time < 10){
            return Math.round(basicScore *2);
        }
        else if(time < 60){
            return (int)Math.round(basicScore *1.5);
        }
        else if(time < 120){
            return (int)Math.round(basicScore *1.25);
        }
        else return basicScore;

    }

    /**
     * Adds score to databasee
     */
    public void addScoreToDB(String player, long score, int level) throws SQLException{
        var sql = "INSERT INTO SCORE (player, level, score) VALUES ('" + player + "'," + (level-1) + "," + score + ");";

        try( var connection =  DriverManager.getConnection(URL, USER, PASSWORD); var statement = connection.createStatement()){
            var result = statement.executeUpdate(sql);
        }

        //To get kinda valid output if needed try using ROLLUP()!!!

    }

}
