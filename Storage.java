package hadesHydroMgr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

abstract class Storage {
    private int[][] RS = new int[2][0];
    private int[][] BLS = new int[2][0];
    private int[][] shipments = new int[2][0];
    String user;

    void pickUsr() {
        System.out.println("Hello there! Who's logging in?\n"
                         + "Enter Username: ");
        String usr = takeString();
        user = usr;
        File checkSave = new File(user);
        boolean exists = checkSave.exists();
        if(exists==false) {
            RS = new int[2][0];
            BLS = new int[2][0];
            shipments = new int[2][0];
            saveStats(RS, BLS, shipments);
        }
        openStats();
    }
    
    void addToRS(int before, int after,String user) {
        // check if a file exists
        File checkSave = new File(user);
        boolean exists = checkSave.exists();
        if(exists==false) saveStats(RS, BLS, shipments);
        openStats();
        // then create temp array to hold new values
        int[][] temp = new int[2][RS[0].length + 1];
        for(int i = 0; i < RS[0].length; i++) {
            temp[0][i] = RS[0][i]; // adds old values to new array
            temp[1][i] = RS[1][i];
        }
        temp[0][RS[0].length] = before;
        temp[1][RS[1].length] = after;
        RS = temp; // overwrites old array
        saveStats(RS, BLS, shipments); // save to memory
    }
    void addToBLS(int before, int after, String user) {
        // check if a file exists
        File checkSave = new File(user);
        boolean exists = checkSave.exists();
        if(exists==false) saveStats(RS, BLS, shipments);
        openStats();
        // then create temp array to hold new values
        int[][] temp = new int[2][BLS[0].length + 1];
        for(int i = 0; i < BLS[0].length; i++) {
            temp[0][i] = BLS[0][i]; // adds old values to new array
            temp[1][i] = BLS[1][i];
        }
        temp[0][BLS[0].length] = before;
        temp[1][BLS[1].length] = after;
        BLS = temp; // overwrites old array
        saveStats(RS, BLS, shipments); // save to memory
    }
    void addToShipments(int before, int after, String user) {
        // check if a file exists
        File checkSave = new File(user);
        boolean exists = checkSave.exists();
        if(exists==false) saveStats(RS, BLS, shipments);
        openStats();
        // then create temp array to hold new values
        int[][] temp = new int[2][shipments[0].length + 1];
        for(int i = 0; i < shipments[0].length; i++) {
            temp[0][i] = shipments[0][i]; // adds old values to new array
            temp[1][i] = shipments[1][i]; 
        }
        temp[0][shipments[0].length] = before;
        temp[1][shipments[1].length] = after;
        shipments = temp; // overwrites old array
        saveStats(RS, BLS, shipments); // save to memory
    }
    private int findAverage(int[][] temp) {
        int a = 0;
        int b = 0;
        for(int i = 0; i < temp[0].length; i++) {
            a = a + temp[0][i];
            b = b + temp[1][i];
        }
        int avg = (a / temp[0].length) - (b / temp[0].length);
        return avg;
    }
    String viewStats() {
        // check if a file exists
        File checkSave = new File(user);
        boolean exists = checkSave.exists();
        if(exists==false) saveStats(RS, BLS, shipments);
        openStats();
        String stats = "";
        boolean rsError = false;
        boolean blsError = false;
        boolean shipError = false;
        stats += "Total Runs : Average Hydro Per Run\n";
        try {
            findAverage(RS);
        }
        catch(ArithmeticException e) {
            rsError = true;
        }
        try {
            findAverage(BLS);
        }
        catch(ArithmeticException e) {
            blsError = true;
        }
        try {
            findAverage(shipments);
        }
        catch(ArithmeticException e) {
            shipError = true;
        }
        if(rsError==false) stats += "Red Star " + RS[0].length + " : " + findAverage(RS) + "\n";
        if(blsError==false) stats += "Blue Star " + BLS[0].length + " : " + findAverage(BLS) + "\n";
        if(shipError==false) stats += "Shipments " + shipments[0].length + " : " + findAverage(shipments);
        return stats;
    }
    String takeString() {
        // takes and returns string input
        @SuppressWarnings("resource")
        Scanner in = new Scanner(System.in);
        in.useDelimiter("\\s");
        String input = in.next();
        return input;   
    }
    int takeNum() {
        // takes and returns int input
        @SuppressWarnings("resource")
        Scanner in = new Scanner(System.in);
        int input = in.nextInt();
        return input;   
    }
    void saveStats(int[][] rs, int[][] bls, int[][] ship) {
        try {
            FileOutputStream saveFile = new FileOutputStream(user); // creates save file
            ObjectOutputStream save = new ObjectOutputStream(saveFile); // objects to put into save file
            save.writeObject(rs); // saving objects
            save.writeObject(bls);
            save.writeObject(ship);
            save.close();
        } catch(Exception exc) {
            exc.printStackTrace(); // If there was an error, print the info.
        }
    }
    void openStats() {      
        try {
            FileInputStream saveFile = new FileInputStream(user); // open save file
            ObjectInputStream save = new ObjectInputStream(saveFile); // objects to pull from save file
            RS = (int[][]) save.readObject(); // restoring objects
            BLS = (int[][]) save.readObject();
            shipments = (int[][]) save.readObject();
            save.close();
        } catch(Exception exc){
            exc.printStackTrace(); // If there was an error, print the info.
        }
    }
}
