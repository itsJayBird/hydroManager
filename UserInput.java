package hadesHydroMgr;

public class UserInput extends Storage {

    public void mainMenu() {
        boolean end = false;
        pickUsr();
        showMenu();
        do {
            String input = takeString();
            input = input.toUpperCase();
            switchMenu(input);
            if(input.contains("CLS")==true) end = true;
        } while(end == false);
    }

    private void showMenu() {
        System.out.println("Hello " + user + "!\n\n"
                + "-----------------------\n"
                + "   Red Star - RS\n"
                + "  Blue Star - BLS\n"
                + "  Shipments - Ship\n"
                + " View Stats - View\n"
                + "  Main Menu - Menu\n"
                + "    Log Out - Out\n"
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
            addToRS(a, b, user);
            System.out.print("\033[H\033[2J");  
            System.out.flush();  
            System.out.println("\nWhat next?");
            break;
        case "BLS":
            System.out.println("Enter Hydro before");
            a = takeNum();
            System.out.println("Enter Hydro after");
            b = takeNum();
            addToBLS(a, b, user);
            System.out.print("\033[H\033[2J");  
            System.out.flush();  
            System.out.println("\nWhat next?");
            break;
        case "SHIP":
            System.out.println("Enter Hydro before");
            a = takeNum();
            System.out.println("Enter Hydro after");
            b = takeNum();
            addToShipments(a, b, user);
            System.out.print("\033[H\033[2J");  
            System.out.flush();  
            System.out.println("\nWhat next?");
            break;
        case "VIEW":
            System.out.print("\033[H\033[2J");  
            System.out.flush();  
            System.out.println(viewStats());
            System.out.println("\nWhat next?");
            break;
        case "MENU":
            showMenu();
            break;
        case "OUT":
            pickUsr();
            showMenu();
            break;
        default:
            System.out.println("Incorrect selection, to get back to the main menu type "
                             + "\"Menu\", otherwise type the command you wish to use");
        }
    }
}
