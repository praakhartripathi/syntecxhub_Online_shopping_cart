import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ShoppingCartSystem {

    // HashMap to store the price of each item (Item Name -> Price)
    private static HashMap<String, Double> itemPrices = new HashMap<>();
    
    // HashMap to store the quantity of items in the cart (Item Name -> Quantity)
    private static HashMap<String, Integer> cartQuantities = new HashMap<>();
    
    // ArrayList to maintain an ordered list of available items for display selection
    private static ArrayList<String> itemList = new ArrayList<>();
    
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeStore();
        
        System.out.println("=========================================");
        System.out.println("   Welcome to the Online Shopping Cart   ");
        System.out.println("=========================================");

        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    displayAvailableItems();
                    break;
                case "2":
                    addItemToCart();
                    break;
                case "3":
                    viewCart();
                    break;
                case "4":
                    calculateTotal();
                    break;
                case "5":
                    System.out.println("\nThank you for using our Shopping Cart. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("\n[!] Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void initializeStore() {
        // Populating the store with some dummy data
        addItemToStore("Laptop", 999.99);
        addItemToStore("Smartphone", 499.50);
        addItemToStore("Headphones", 79.99);
        addItemToStore("Keyboard", 45.00);
        addItemToStore("Mouse", 25.00);
        addItemToStore("Monitor", 150.00);
    }

    private static void addItemToStore(String name, double price) {
        itemPrices.put(name, price);
        itemList.add(name);
    }

    private static void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. View Available Items");
        System.out.println("2. Add Item to Cart");
        System.out.println("3. View Cart");
        System.out.println("4. Calculate Total Price");
        System.out.println("5. Exit");
    }

    private static void displayAvailableItems() {
        System.out.println("\n--- Available Items ---");
        System.out.printf("%-5s %-20s %-10s%n", "ID", "Item Name", "Price");
        System.out.println("---------------------------------------");
        
        for (int i = 0; i < itemList.size(); i++) {
            String itemName = itemList.get(i);
            Double price = itemPrices.get(itemName);
            System.out.printf("%-5d %-20s $%.2f%n", (i + 1), itemName, price);
        }
    }

    private static void addItemToCart() {
        displayAvailableItems();
        System.out.print("\nEnter the ID of the item you want to add: ");
        
        try {
            int itemIndex = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (itemIndex >= 0 && itemIndex < itemList.size()) {
                String selectedItem = itemList.get(itemIndex);
                
                System.out.print("Enter quantity for " + selectedItem + ": ");
                int quantity = Integer.parseInt(scanner.nextLine());
                
                if (quantity > 0) {
                    int currentQty = cartQuantities.getOrDefault(selectedItem, 0);
                    cartQuantities.put(selectedItem, currentQty + quantity);
                    System.out.println("\n[+] Added " + quantity + " x " + selectedItem + " to your cart.");
                } else {
                    System.out.println("\n[!] Quantity must be greater than 0.");
                }
            } else {
                System.out.println("\n[!] Invalid Item ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n[!] Invalid input. Please enter numbers only.");
        }
    }

    private static void viewCart() {
        System.out.println("\n--- Your Shopping Cart ---");
        if (cartQuantities.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.printf("%-20s %-10s %-10s %-10s%n", "Item", "Price", "Qty", "Subtotal");
        System.out.println("-------------------------------------------------------");
        
        double grandTotal = 0.0;
        
        for (String itemName : cartQuantities.keySet()) {
            int quantity = cartQuantities.get(itemName);
            double price = itemPrices.get(itemName);
            double subtotal = price * quantity;
            grandTotal += subtotal;
            
            System.out.printf("%-20s $%-9.2f %-10d $%.2f%n", itemName, price, quantity, subtotal);
        }
        
        System.out.println("-------------------------------------------------------");
        System.out.printf("%-42s $%.2f%n", "Total Amount:", grandTotal);
    }

    private static void calculateTotal() {
        if (cartQuantities.isEmpty()) {
            System.out.println("\nYour cart is empty. Total is $0.00");
        } else {
            double total = 0.0;
            for (String itemName : cartQuantities.keySet()) {
                total += itemPrices.get(itemName) * cartQuantities.get(itemName);
            }
            System.out.printf("\nTotal Price of items in cart: $%.2f%n", total);
        }
    }
}