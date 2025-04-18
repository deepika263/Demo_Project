import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        Scanner sc = new Scanner(System.in);
        String filename = "inventory.txt";

        manager.loadFromFile(filename);

        while (true) {
            System.out.println("\n--- Inventory Management ---");
            System.out.println("1. Add Item");
            System.out.println("2. View All Items");
            System.out.println("3. Search Item by Name");
            System.out.println("4. Update Item by ID");
            System.out.println("5. Delete Item by ID");
            System.out.println("6. Save & Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    int id = manager.getNextId(); // Auto-generated ID

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();
                    manager.addItem(new Item(id, name, qty, price));
                    break;

                case 2:
                    manager.displayAllItems();
                    break;

                case 3:
                    System.out.print("Enter name to search: ");
                    String searchName = sc.nextLine();
                    Item found = manager.searchItemByName(searchName);
                    if (found != null)
                        System.out.println(found);
                    else
                        System.out.println("Item not found.");
                    break;

                case 4:
                    System.out.print("Enter ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("New Quantity: ");
                    int newQty = sc.nextInt();
                    System.out.print("New Price: ");
                    double newPrice = sc.nextDouble();
                    boolean updated = manager.updateItem(updateId, newName, newQty, newPrice);
                    System.out.println(updated ? "Item updated." : "Item not found.");
                    break;

                case 5:
                    System.out.print("Enter ID to delete: ");
                    int deleteId = sc.nextInt();
                    boolean deleted = manager.deleteItem(deleteId);
                    System.out.println(deleted ? "Item deleted." : "Item not found.");
                    break;

                case 6:
                    manager.saveToFile(filename);
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
