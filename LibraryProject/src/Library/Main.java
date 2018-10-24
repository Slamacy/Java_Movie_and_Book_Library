package Library;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * @authors
 * Oisin Murphy - D00191700
 * Patricia Bere - D00193593
 */

public class Main {
    static Scanner in = new Scanner(System.in);
    static Library library = new Library();
    static Users users = new Users();
    static ArrayList<Items> history = new ArrayList();
    static int strikes = 0;
    static String username, password, filepath;
    
    public static void main(String[] args) throws IOException {
        loadStartup();
        login();
        int option = 0;
        boolean done = false;
        System.out.println("\nPlease select an option, 0 to end.");
        do {
            do {
                try {
                    System.out.println("Welcome to DkIT Movie & Book library!");
                    System.out.println("*************************************");
                    System.out.println("*   1. View all in library.         *");
                    System.out.println("*   2. View books only.             *");
                    System.out.println("*   3. View movies only.            *");
                    System.out.println("*   4. Search.                      *");
                    System.out.println("*   5. Recommended.                 *");
                    System.out.println("*   6. Account History.             *");
                    System.out.println("*   7. Rate item.                   *");
                    System.out.println("*   8. Rent item.                   *");
                    System.out.println("*   9. ADMIN LOGIN                  *");
                    System.out.println("*   0. Exit.                        *");
                    System.out.println("*************************************");
                    option = in.nextInt();
                    done = true;
                    if(option == 1) {
                        viewAll();
                    }
                    else if(option == 2) {
                        viewBooks();
                    }
                    else if(option == 3) {
                        viewMovies();
                    }
                    else if(option == 4) {
                        search();
                    }
                    else if(option == 5) {
                        recommended();
                    }
                    else if(option == 6) {
                        rentHistory();
                    }
                    else if(option == 7) {
                        rateItem();
                    }
                    else if(option == 8) {
                        rentItem();
                    }
                    else if(option == 9) {
                        admin();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\nPlease input either 1 or 2.");
                    done = false;
                    in.next();
                }
            } while (done == false);
        } while(option != 0);   
    }
    
    public static void loadStartup() {
        String type, name, genre, creator, description;
        int releaseYear, rating;
        float fee;
        try {
            Scanner read = new Scanner(new File("items.txt"));
            while (read.hasNextLine()) {
                String line = read.nextLine();
                StringTokenizer st = new StringTokenizer(line, ";");
                type = st.nextToken();
                name = st.nextToken();
                genre = st.nextToken();
                creator = st.nextToken();
                description = st.nextToken();
                releaseYear = Integer.parseInt(st.nextToken());
                fee = Float.parseFloat(st.nextToken());
                if (type.equals("Book")) {
                    int pageCount = Integer.parseInt(st.nextToken());
                    rating = Integer.parseInt(st.nextToken());
                    library.addToLib(new Book(type, name, genre, creator, description, releaseYear, fee, pageCount, rating));
                }
                else if (type.equals("Movie")) {
                    String[] actors = new String[3];
                    for (int i = 0; i < 3; i++) {
                        actors[i] = st.nextToken();
                    }
                    String runningTime = st.nextToken();
                    rating = Integer.parseInt(st.nextToken());
                    library.addToLib(new Movie(type, name, genre, creator, description, releaseYear, fee, actors, runningTime, rating));
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            Scanner read = new Scanner(new File("users.txt"));
            while (read.hasNextLine()) {
                String line = read.nextLine();
                StringTokenizer st = new StringTokenizer(line, ";");
                username = st.nextToken();
                password = st.nextToken();
                filepath = st.nextToken();
                users.add(new User(username, password, filepath));
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        //BOOK LOAD CONFIG = type;name;genre;creator;description;releaseYear;fee;pageCount
        //MOVIE LOAD CONFIG = type;name;genre;creator;description;releaseYear;fee;actors;runningTime
        //USER LOAD CONFIG = username;password;filepath
    }
    
    public static void save() {
        PrintWriter print;
        try {
            print = new PrintWriter(new File("items.txt"));
            for(Items i : library.getArray()) {
               if(i.getType().equalsIgnoreCase("Book")) {
                   Book b = (Book)i;
                   print.println(b.getType() + ";" + b.getName() + ";" + b.getGenre() + ";" + b.getCreator() + ";" + b.getDescription() + ";" + b.getReleaseYear() + ";" + b.getFee() + ";" + b.getPageCount() + ";" + b.getRating());
               }
               else if(i.getType().equalsIgnoreCase("Movie")) {
                   Movie m = (Movie)i;
                   print.println(m.getType() + ";" + m.getName() + ";" + m.getGenre() + ";" + m.getCreator() + ";" + m.getDescription() + ";" + m.getReleaseYear() + ";" + m.getFee() + ";" + m.getActor(0) + ";" + m.getActor(1) + ";" + m.getActor(2) + ";" + m.getRunningTime()+ ";" + m.getRating());
               }
            }
            print.flush();
            print.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void viewAll() {
        System.out.println("\n*************************************");
        System.out.println("*   LIST OF ALL ITEMS IN LIBRARY    *");
        System.out.println("*************************************");
        int j = 1;
        for (int i = 0; i < library.getArray().size(); i++) {
            System.out.println(j + ".");
            System.out.println(library.get(i).toString() + "\nRating : " + library.get(i).getRating());
            System.out.println("\n*************************************");
            j++;
        }
    }
    
    public static void viewBooks() {
        System.out.println("\n*************************************");
        System.out.println("*   LIST OF ALL BOOKS IN LIBRARY    *");
        System.out.println("*************************************");
        int j = 1;
        for (int i = 0; i < library.getArray().size(); i++) {
            if (library.get(i).getType().equalsIgnoreCase("Book")) {
                System.out.println(j + ".");
                System.out.println(library.get(i).toString() + "\nRating : " + library.get(i).getRating());
                System.out.println("\n*************************************");
                j++;
            }
        }
    }
    
    public static void viewMovies() {
        System.out.println("\n*************************************");
        System.out.println("*   LIST OF ALL MOVIES IN LIBRARY   *");
        System.out.println("*************************************");
        int j = 1;
        for (int i = 0; i < library.getArray().size(); i++) {
            if (library.get(i).getType().equalsIgnoreCase("Movie")) {
                System.out.println(j + ".");
                System.out.println(library.get(i).toString() + "\nRating : " + library.get(i).getRating());
                System.out.println("\n*************************************");
                j++;
            }
        }
    }
    
    public static void admin() {
        int option = 0;
        boolean done = false;
        do {
            do {
                try {
                    System.out.println("\nPlease select an option, 0 to end.");
                    System.out.println("*************************************");
                    System.out.println("*   1. Edit library item.           *");
                    System.out.println("*   2. Add library item.            *");
                    System.out.println("*   3. Remove library item.         *");
                    System.out.println("*   0. Return.                      *");
                    System.out.println("*************************************");
                    option = in.nextInt();
                    done = true;
                } catch (InputMismatchException e) {
                    System.out.println("\nPlease input either 1 or 2.");
                    done = false;
                    in.next();
                }
            } while (done == false);
            if(option == 1) {
                editItem();
            }
            else if(option == 2) {
                addItem();
            }
            else if(option == 3) {
                removeItem();
            }
        } while(option != 0);   
    }
    
    public static void login() throws IOException {
        String line, inusername = "", inpassword = "", userFile = "", reqUsername = "";
        boolean validUser = false;
        int option = 0;
        do {
            try {
                System.out.println("Do you have an account on this system?");
                System.out.println("Please enter the numbers 1 or 2");
                System.out.println("**************************************");
                System.out.println("*   1. Yes.                          *");
                System.out.println("*   2. No.                           *");
                System.out.println("**************************************");
                option = in.nextInt();
                if (option == 2) {
                    createUser();
                    System.out.println("Your account has been created!");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nPlease input either 1 or 2.");
                in.next();
            }
        } while (option != 1);
        try {
            Scanner login = new Scanner(new File("users.txt"));
            System.out.print("Please input your username: ");
            in.nextLine();
            inusername = in.nextLine();
            while (login.hasNextLine()) {
                line = login.nextLine();
                StringTokenizer st = new StringTokenizer(line, ";");
                reqUsername = st.nextToken();
                if (reqUsername.equalsIgnoreCase(inusername)) {
                    validUser = true;
                    String passwordNeeded = st.nextToken();
                    boolean isEqual = false;
                    while (isEqual == false) {
                        System.out.print("\nPlease enter password: ");
                        inpassword = in.nextLine();
                        if (inpassword.equals(passwordNeeded)) {
                            isEqual = true;
                            userFile = st.nextToken();
                        }
                        else {
                            System.out.println("PASSWORD IS INCORRECT, PLEASE TRY AGAIN.");
                        }
                    }
                }
            }
            if (!validUser) {
                System.out.println("This user does not exist, please try again or make a new user.\n");
                login();
            }
            else {
                loadUser(inusername, userFile);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void loadUser(String username, String userFile) {
        try {
            Scanner file = new Scanner(new File(userFile));
            String line, type, name, state;
            while(file.hasNextLine()) {
                line = file.nextLine();
                StringTokenizer st = new StringTokenizer(line, ";");
                strikes = Integer.parseInt(st.nextToken());
                type = st.nextToken();
                name = st.nextToken();
                state = st.nextToken();
                if (state.equalsIgnoreCase("Late")) {
                    strikes++;
                }
                for (Items i: library.getArray()) {
                    if (type.equals(i.getType()) && name.equals(i.getName())) {
                        i.setState(state);
                        history.add(i);
                    }
                }
                //System.out.println(history); TESTING
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void rentHistory() {
        int j = 1;
        System.out.println("Here is your account history.");
        System.out.println("*****************************");
        for (Items i: history) {
            System.out.println(j + ". " + i.getType() + ": " + i.getName() + " - " + i.getState());
            j++;
        }
        System.out.println("");
    }
    
    public static void createUser() throws IOException {
        in.nextLine();
        String username, password, line, fName;
        System.out.println("Please enter your desired username.");
        username = in.nextLine();
        System.out.println("Please enter your desired password.");
        password = in.nextLine();
        fName = username + ".txt";
        line = username.toLowerCase() + ";" + password + ";" + fName;
        File file = new File(fName);
        file.createNewFile();
        FileWriter write = new FileWriter("users.txt", true);
        write.write("\n" + line);
        write.close();
    }
    
    public static void editItem() {
        System.out.println("\nPlease select a movie / book you would like to edit.");
        int choice = 0, choice2 = 1, option = 0;
        boolean done = false;
        do {
            try {
                for (int i = 0; i < library.getSize(); i++) {
                    System.out.println(i + ": " + library.get(i).getType() + " - " + library.get(i).getName());
                }
                choice = in.nextInt();
                done = true;
            } catch (InputMismatchException me) {
                System.out.println("\nPlease input a number denoted in the menu.\n");
                done = false;
                in.next();
            }
        } while (done == false);
        done = false;
        do {
            do {
                try {
                    System.out.println(library.getArray().get(choice).toString());
                    System.out.println("\nWhat would you like to edit?");
                    System.out.println("****************************");
                    System.out.println("*   1. Type.               * " + library.get(choice).getType());
                    System.out.println("*   2. Name.               * " + library.get(choice).getName());
                    System.out.println("*   3. Genre.              * " + library.get(choice).getGenre());
                    System.out.println("*   4. Creator.            * " + library.get(choice).getCreator());
                    System.out.println("*   5. Description.        * " + library.get(choice).getDescription());
                    System.out.println("*   6. Release Year.       * " + library.get(choice).getReleaseYear());
                    System.out.println("*   7. Fee.                * " + library.get(choice).getFee());
                    if (library.getArray().get(choice).getType().equalsIgnoreCase("Book")) {
                        System.out.println("*   8. Page Count.         * " + library.getBook(choice).getPageCount());
                    }
                    else if (library.getArray().get(choice).getType().equalsIgnoreCase("Movie")) {
                        System.out.println("*   8. Actors.             * " + library.getMovie(choice).getActors());
                        System.out.println("*   9. Running Time        * " + library.getMovie(choice).getRunningTime());
                    }
                    System.out.println("*   0. EXIT.               *");
                    System.out.println("****************************");
                    option = in.nextInt();
                    done = true;
                } catch (InputMismatchException e) {
                    System.out.println("\nPlease input a number denoted in the menu.\n");
                    done = false;
                    in.next();
                }
            } while (done == false);
            if (option == 1) {
                System.out.println("Input what you would like to replace this with.");
                in.nextLine();
                String type = in.nextLine();
                library.setType(choice, type);
            }
            else if (option == 2) {
                System.out.println("Input what you would like to replace this with.");
                in.nextLine();
                String name = in.nextLine();
                library.setName(choice, name);
            }
            else if (option == 3) {
                System.out.println("Input what you would like to replace this with.");
                in.nextLine();
                String genre = in.nextLine();
                library.setGenre(choice, genre);
            }
            else if (option == 4) {
                System.out.println("Input what you would like to replace this with.");
                in.nextLine();
                String creator = in.nextLine();
                library.setCreator(choice, creator);
            }
            else if (option == 5) {
                System.out.println("Input what you would like to replace this with.");
                in.nextLine();
                String desc = in.nextLine();
                library.setDescription(choice, desc);
            }
            else if (option == 6) {
                System.out.println("Input what you would like to replace this with.");
                int releaseYear = in.nextInt();
                library.setReleaseYear(choice, releaseYear);
            }
            else if (option == 7) {
                System.out.println("Input what you would like to replace this with.");
                float fee = in.nextFloat();
                library.setFee(choice, fee);
            }
            if (library.get(choice).getType().equalsIgnoreCase("Book")) {
                if (option == 8) {
                    System.out.println("Input what you would like to replace this with.");
                    int pageCount = in.nextInt();
                    library.setPageCount(choice, pageCount);
                }
            }
            
            else if (library.get(choice).getType().equalsIgnoreCase("Movie")) {
                if (option == 8) {
                    System.out.println("Input what you would like to replace this with.");
                    in.nextLine();
                    String[] actors = new String[3];
                    actors[0] = in.nextLine();
                    actors[1] = in.nextLine();
                    actors[2] = in.nextLine();
                    library.setActors(choice, actors);
                }
                else if (option == 9) {
                    System.out.println("Input what you would like to replace this with.");
                    String runningTime = in.nextLine();
                    library.setRunningTime(choice, runningTime);
                }
            }
            System.out.println("Have you completed your edits?\nInput 0 if you have to return to the menu.");
            choice2 = in.nextInt();
            //add save method 
        } while (choice2 != 0);
        save();
    }
    
    public static void addItem() { //extend this out to full functionality
        String type = "";
        String name, creator, genre, description, runningTime;
        int pageCount, releaseYear, rating = 0;
        float fee;
        boolean done = false;
        String[] actors;
        int option = 0;
        do {
            do {
                try {
                    System.out.println("Would you like to add a movie or a book?");
                    System.out.println("*****************************************");
                    System.out.println("*   1. Movie.                           *");
                    System.out.println("*   2. Book.                            *");
                    System.out.println("*   0. Cancel.                          *");
                    System.out.println("*****************************************\n");
                    option = in.nextInt();
                    done = true;
                } catch (InputMismatchException e) {
                    System.out.println("\nPlease input either 1 or 2.");
                    done = false;
                    in.next();
                }
            } while (done == false);
            if (option == 1) {
                type = "Movie";
            }
            else if (option == 2){
                type = "Book";
            }
            else {
                admin();
            }
            in.nextLine();
            System.out.println("Please enter the name of the " + type.toLowerCase() + ".");
            name = in.nextLine();
            System.out.println("Please enter the genre of the " + type.toLowerCase() + ".");
            genre = in.nextLine();
            System.out.println("Please enter the creator of the " + type.toLowerCase() + ".");
            creator = in.nextLine();
            System.out.println("Please enter the description of the " + type.toLowerCase() + ".");
            description = in.nextLine();
            System.out.println("Please enter the release year of the " + type.toLowerCase() + ".");
            releaseYear = in.nextInt();
            System.out.println("Please enter the fee of the " + type.toLowerCase() + ".");
            fee = in.nextFloat();
            if(type.equalsIgnoreCase("Book")){
                System.out.println("Please enter the page count of the " + type.toLowerCase() + ".");
                pageCount = in.nextInt();
                Book newBook = new Book(type, name, genre, creator, description, releaseYear, fee, pageCount, 0);
                System.out.println(newBook);
                library.addToLib(newBook);
                save();
            }
            if(type.equalsIgnoreCase("Movie")) {
                System.out.println("Please enter three actors that star in this " + type.toLowerCase() + ".");
                in.nextLine();
                String [] tempActors = new String [3];
                tempActors[0] = in.nextLine();
                tempActors[1] = in.nextLine();
                tempActors[2] = in.nextLine();
                System.out.println("Please enter the running time of the " + type.toLowerCase() + ".");
                runningTime = in.nextLine();
                Movie newMovie = new Movie(type, name, genre, creator, description, releaseYear, fee, tempActors, runningTime, 0);
                System.out.println(newMovie);
                library.addToLib(newMovie);
                save();
            }
            System.out.println("");
        } while (option != 0);
    }

    public static void removeItem() {
        library.getArray();
        System.out.println("Please select a movie / book you would like to remove.");
        int j, choice = 0, choice2 = 0;
        boolean done = false;
        while (done == false) {
            j = 0;
            try {
                for (Items i: library.getArray()) {
                    System.out.println(j + ": " + i.getName());
                    j++;
                }
                choice = in.nextInt();
                done = true;
            } catch (InputMismatchException e) {
                System.out.println("\nPlease input a number denoted in the menu.\n");
                done = false;
                in.next();
            }
        }
        try {
            System.out.println("Are you sure you want to remove " + library.get(choice).getName() + "?");
            System.out.println("*********************************");
            System.out.println("*   1. Yes.                     *");
            System.out.println("*   2. No.                      *");
            System.out.println("*********************************");
            choice2 = in.nextInt();
        } catch (InputMismatchException e) {
                System.out.println("\nPlease input either 1 or 2.");
                in.next();
        }
        if (choice2 == 1) {
            library.getArray().remove(choice);
            save();
        }
        else {
            admin();
        }
    }
    
    public static void search() {
        int choice = 0;
        do {
            try {
                System.out.println("\nWould you like to sort items by...");
                System.out.println("************************************");
                System.out.println("*            1. Name.              *");
                System.out.println("*            2. Type.              *");
                System.out.println("*            3. Year asc.          *");
                System.out.println("*            4. Year desc.         *");
                System.out.println("*            5. Rating asc.        *");
                System.out.println("*            6. Rating desc.       *");
                System.out.println("*            7. EXIT               *");
                System.out.println("************************************");
                choice = in.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Sorted by Name : ");
                        Collections.sort(library.getArray(), new NameComparator());
                        viewAllSorted();
                        break;
                    case 2:    
                       System.out.println("Sorted by Type : ");
                        Collections.sort(library.getArray(), new TypeComparator());
                        viewAllSorted();
                        break;
                    case 3:   
                        System.out.println("Sorted by Year asc :");
                        Collections.sort(library.getArray(), new AscYearComparator());
                        viewAllSorted();
                        break;
                    case 4:
                        System.out.println("Sorted by Year desc :");
                        Collections.sort(library.getArray(), new DescYearComparator());
                        viewAllSorted();
                        break;
                    case 5:
                        System.out.println("Sorted by Rating asc :");
                        Collections.sort(library.getArray(), new AscRatingComparator());
                        viewAllSorted();
                        break;
                    case 6:
                        System.out.println("Sorted by Rating desc :");
                        Collections.sort(library.getArray(), new DescRatingComparator());
                        viewAllSorted();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nPlease input a number denoted in the menu.");
                in.next();
            }
        } while (choice != 7);
    }
    
    public static void viewAllSorted() {
        System.out.println("\n**************************************");
        System.out.println("*   LIST OF SORTED ITEMS IN LIBRARY    *");
        System.out.println("****************************************");
        int j = 1;
        for (int i = 0; i < library.getArray().size(); i++) {
            System.out.println(j + ".");
            System.out.println(library.get(i).toString() + "\nRating : " + library.get(i).getRating());
            System.out.println("\n****************************************");
            j++;
        }
    }
    
    public static void rentItem() throws IOException {
        System.out.println("\nPlease select a movie / book you would like to rent.");
        for (int i = 0; i < library.getSize(); i++) {
            System.out.println(i + ": " + library.get(i).getType() + " - " + library.get(i).getName());
        }
        int choice2 = 0;
        int choice = in.nextInt();
        System.out.println("\n" + library.get(choice).toString());
        while (choice2 != 1 || choice2 != 2) {
            try {
                System.out.println("\nWould you like to rent this item?");
                System.out.println("*********************************");
                System.out.println("*   1. Yes.                     *");
                System.out.println("*   2. No.                      *");
                System.out.println("*********************************");
                choice2 = in.nextInt();
                if (choice2 == 1) {
                    history.add(library.get(choice));
                    saveUser(choice);
                    break;
                }
                else if (choice2 == 2) {
                    rentItem();
                }
                else {
                    System.out.println("Please enter either 1 or 2.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nPlease input either 1 or 2.");
                in.next();
            }
        } 
    }
    
    public static void saveUser(int choice) throws IOException {
        try {
            FileWriter write = new FileWriter(filepath, true);
            for (Items i : history) {
                write.write(strikes + ";" + i.getType() + ";" + i.getName() + ";" + "Rented");
                write.close();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void rateItem() {
        System.out.println("\nPlease select a movie / book you would like to rate.");
        int j = 0;
        String pad = " ";
        for (Items i: library.getArray()) {
            String padding = "";
            for (int k = 0; k < 25 - i.getName().length(); k++) {
                padding += pad;
            }
            System.out.println(j + ": " + i.getName() + " - rating: " + padding + i.getRating());
            j++;
        }
        int choice = in.nextInt();
        int rating = -1;
        while (rating < 0 || rating > 5) {
            System.out.println("Please input your rating out of 5 (whole numbers).");
            rating = in.nextInt();
            //if (rating != 0 || rating != 1 || rating != 2 || rating != 3 || rating != 4 || rating != 5) {
            //    System.out.println("Please input your rating as a whole number from 0 to 5.\n");
            //    rateItem();
            //}
            library.get(choice).addRating(rating);
            library.get(choice).calculateRatings();
        }
    }
    
    public static void recommended() {
        ArrayList<Items> recommended = new ArrayList();
        for (Items i : library.getArray()) {
            // i is every Item in library arraylist
            for (Items j : history) {
                // j is every Item in history arraylist (generated from user file)
                if (!recommended.contains(i)) {
                    if (j.getCreator().equalsIgnoreCase(i.getCreator())) {
                        recommended.add(i);
                    }
                    else if (j.getGenre().equalsIgnoreCase(i.getGenre())) {
                        recommended.add(i);
                    }
                    else if (i.getRating() > 3) {
                        recommended.add(i);
                    }
                }
            }
        }
        System.out.println("Recommended movies / books");
        System.out.println("**************************");
        int j = 0;
        String pad = " ";
        for (Items i: recommended) {
            String padding = "";
            for (int k = 0; k < 25 - i.getName().length(); k++) {
                padding += pad;
            }
            System.out.println(j + ": " + i.getName() + " - rating: " + padding + i.getRating());
            j++;
        }
        System.out.println("**************************\n");
    }
}