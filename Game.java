import java.util.Scanner;

public class Game {
    private Room currentRoom;

    public Game() {
        createRooms();
    }

    private void createRooms() {
        Room hall = new Room("di aula besar, penuh dengan lampu gantung");
        Room kitchen = new Room("di dapur yang bau dengan tumpukan piring kotor");
        Room library = new Room("di perpustakaan, dipenuhi dengan buku tua dan debu");
        Room garden = new Room("di taman yang indah dengan bunga bermekaran");

        hall.setExit("utara", library);
        hall.setExit("timur", kitchen);
        hall.setExit("selatan", garden);

        kitchen.setExit("barat", hall);
        library.setExit("selatan", hall);
        garden.setExit("utara", hall);

        currentRoom = hall; 
    }

    public void play() {
        printWelcome();

        Scanner scanner = new Scanner(System.in);
        boolean finished = false;
        while (!finished) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] words = input.split(" ");
            String command = words[0];

            switch (command) {
                case "pergi":
                    if (words.length > 1) {
                        goRoom(words[1]);
                    } else {
                        System.out.println("Pergi ke mana?");
                    }
                    break;
                case "lihat":
                    lookAround();
                    break;
                case "keluar":
                    System.out.println("Terima kasih telah bermain. Sampai jumpa!");
                    finished = true;
                    break;
                default:
                    System.out.println("Perintah tidak dikenal.");
            }
        }

        scanner.close();
    }

    private void printWelcome() {
        System.out.println("Selamat datang di World of Zuul!");
        System.out.println("Ketik 'pergi [arah]', 'lihat', atau 'keluar' untuk bermain.");
        System.out.println();
        lookAround();
    }

    private void lookAround() {
        System.out.println("Kamu sekarang berada " + currentRoom.getDescription());
        System.out.println("Pintu keluar: " + currentRoom.getExits());
    }

    private void goRoom(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("Tidak ada pintu ke arah itu!");
        } else {
            currentRoom = nextRoom;
            lookAround();
        }
    }
}
