import java.util.*;
import java.io.*;

public class InventoryManager {
    private List<Item> items = new ArrayList<>();
    private int nextId = 1;

    // Add item
    public void addItem(Item item) {
        items.add(item);
        System.out.println("Item added.");
    }

public int getNextId() {
    return nextId++;
}

    // Display all items
    public void displayAllItems() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        for (Item item : items) {
            System.out.println(item);
        }
    }

    // Search by name
    public Item searchItemByName(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    // Update item by ID
    public boolean updateItem(int id, String newName, int newQty, double newPrice) {
        for (Item item : items) {
            if (item.getId() == id) {
                item.setName(newName);
                item.setQuantity(newQty);
                item.setPrice(newPrice);
                return true;
            }
        }
        return false;
    }

    // Delete item by ID
    public boolean deleteItem(int id) {
        return items.removeIf(item -> item.getId() == id);
    }

    // Save to file
    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Item item : items) {
                writer.println(item.toFileString());
            }
            System.out.println("Inventory saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    // Load from file


public void loadFromFile(String filename) {
    items.clear();
    nextId = 1; // reset ID counter
    try (Scanner fileScanner = new Scanner(new File(filename))) {
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            Item item = Item.fromFileString(line);
            items.add(item);

            // Ensure nextId is greater than any existing ID
            if (item.getId() >= nextId) {
                nextId = item.getId() + 1;
            }
        }
        System.out.println("Inventory loaded from file.");
    } catch (FileNotFoundException e) {
        System.out.println("File not found. Starting with empty inventory.");
    }
}


//    public void loadFromFile(String filename) {
 //       items.clear();
  //      try (Scanner fileScanner = new Scanner(new File(filename))) {
   //         while (fileScanner.hasNextLine()) {
    //            String line = fileScanner.nextLine();
     //           Item item = Item.fromFileString(line);
      //          items.add(item);
       //     }
        //    System.out.println("Inventory loaded from file.");
      //  } catch (FileNotFoundException e) {
       //     System.out.println("File not found. Starting with empty inventory.");
      //  }
   // }
}
