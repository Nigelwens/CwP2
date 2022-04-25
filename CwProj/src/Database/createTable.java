/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;



public class createTable {

    public static void main(String[] args) {
        
        createPush(conversation());
        createPush(user());
        createPush(person());
        createPush(log());
        createPush(highlight_words());
        createPush(match_conv_word());
        
    }
    
    private static String conversation()
    {
        String createString = "CREATE TABLE Conversation (\n" +
                        "    converstion_id    INTEGER      PRIMARY KEY AUTOINCREMENT\n" +
                        "                                   NOT NULL\n" +
                        "                                   UNIQUE,\n" +
                        "    level             VARCHAR (2)  NOT NULL,\n" +
                        "    context           VARCHAR (50) NOT NULL,\n" +
                        "    sub_context       VARCHAR (50) NOT NULL,\n" +
                        "    grammer_structure VARCHAR (50),\n" +
                        "    language          VARCHAR (20) NOT NULL\n" +
                        ");";
        
        return createString;
    }
    
    private static String user()
    {
        String createString = "CREATE TABLE User (\n" +
                        "    user_id         INTEGER      PRIMARY KEY AUTOINCREMENT\n" +
                        "                                 NOT NULL\n" +
                        "                                 UNIQUE,\n" +
                        "    conversation_id INTEGER      REFERENCES Conversation (converstion_id),\n" +
                        "    type            VARCHAR (15) NOT NULL,\n" +
                        "    name            VARCHAR (25) NOT NULL,\n" +
                        "    email           VARCHAR (35) UNIQUE\n" +
                        "                                 NOT NULL,\n" +
                        "    title           VARCHAR (4),\n" +
                        "    password        VARCHAR (20) NOT NULL\n" +
                        ");";
        
        return createString;
    }
    
    
    private static String person()
    {
        String createString = "CREATE TABLE Person (\n" +
                        "    person_id       INTEGER       PRIMARY KEY AUTOINCREMENT\n" +
                        "                                  NOT NULL\n" +
                        "                                  UNIQUE,\n" +
                        "    conversation_id INTEGER       REFERENCES Conversation (converstion_id),\n" +
                        "    a_sentence      VARCHAR (200) NOT NULL,\n" +
                        "    b_sentence      VARCHAR (200) NOT NULL,\n" +
                        "    sentence_order  INTEGER (15)  NOT NULL\n" +
                        ");";
        
        return createString;
    }
    
    
    private static String log()
    {
        String createString = "CREATE TABLE Log (\n" +
                        "    log_id          INTEGER       PRIMARY KEY AUTOINCREMENT\n" +
                        "                                  UNIQUE\n" +
                        "                                  NOT NULL,\n" +
                        "    user_id         INTEGER       REFERENCES User (user_id) \n" +
                        "                                  NOT NULL,\n" +
                        "    conversation_id INTEGER       REFERENCES Conversation (converstion_id) \n" +
                        "                                  NOT NULL,\n" +
                        "    datetime        DATETIME      NOT NULL,\n" +
                        "    status          VARCHAR (15)  NOT NULL,\n" +
                        "    notes           VARCHAR (250) \n" +
                        ");";
        
        return createString;
    }
    
    private static String match_conv_word()
    {
        String createString = "CREATE TABLE Match_conv_word (\n" +
                        "    conversation_id  REFERENCES Conversation (converstion_id) \n" +
                        "                     NOT NULL\n" +
                        "                     UNIQUE,\n" +
                        "    highlight_id     REFERENCES Highlight_words (highlight_id) \n" +
                        "                     NOT NULL\n" +
                        "                     UNIQUE\n" +
                        ");";
        
        return createString;
    }
    
    private static String highlight_words()
    {
        String createString = "CREATE TABLE Highlight_words (\n" +
                        "    highlight_id    INTEGER      PRIMARY KEY AUTOINCREMENT\n" +
                        "                                 UNIQUE\n" +
                        "                                 NOT NULL,\n" +
                        "    english_word    VARCHAR (50),\n" +
                        "    translated_word VARCHAR (50) \n" +
                        ");";
        
        return createString;
    }
    
    private static void createPush(String createString)
    {
        Connection con = connectDB.getConnection();
        Statement stmt = null;
        
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(createString);
            con.commit();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
        }
    }
    
    
}


