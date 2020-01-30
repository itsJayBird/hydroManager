package hadesHydroMgr;

import java.util.Scanner;

public class UserInput extends Storage {

    public void mainMenu() {
        String input = "";
        do {
            showMenu();
            input = takeString();
            input = input.toUpperCase();
            switchMenu(input);
        } while(input.contains("CLS")==false);
    }

    private void showMenu() {
        System.out.println("-----------------------\n"
                + "   Red Star - RS\n"
                + "  Blue Star - BLS\n"
                + "  Shipments - Ship\n"
                + " View Stats - View\n"
                + "      Close - Cls\n"
                + "-----------------------");
    }

    private void switchMenu(String in) {
        int a = 0;
        int b = 0;
        switch(in) {
        case "RS":
            System.out.println("Enter Hydro before");
            a = takeNum();
            System.out.println("Enter Hydro after");
            b = takeNum();
            addToRS(a, b);
            break;
        case "BLS":
            System.out.println("Enter Hydro before");
            a = takeNum();
            System.out.println("Enter Hydro after");
            b = takeNum();
            addToBLS(a, b);
            break;
        case "SHIP":
            System.out.println("Enter Hydro before");
            a = takeNum();
            System.out.println("Enter Hydro after");
            b = takeNum();
            addToShipments(a, b);
            break;
        case "VIEW":
            System.out.println(viewStats());
            break;
        }
    }
    private String takeString() {
        // takes and returns string input
        @SuppressWarnings("resource")
        Scanner in = new Scanner(System.in);
        in.useDelimiter("\\s");
        String input = in.next();
        return input;   
    }
    private int takeNum() {
        // takes and returns int input
        @SuppressWarnings("resource")
        Scanner in = new Scanner(System.in);
        int input = in.nextInt();
        return input;   
    }
}
