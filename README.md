# Online Shopping Cart System

A simple console-based shopping cart application developed in Java. This project demonstrates the usage of `ArrayList` and `HashMap` to manage product listings and shopping cart contents.

## Features

- **View Available Items**: Browse a list of products with their prices.
- **Add Item to Cart**: Select items by ID and specify quantities to add to your cart.
- **View Cart**: See a summary of items in your cart, including individual prices, quantities, and subtotals.
- **Calculate Total**: Get the grand total of all items currently in the cart.

## How to Run

1. **Compile the Java file:**
   ```bash
   javac ShoppingCartSystem.java
   ```

2. **Run the application:**
   ```bash
   java ShoppingCartSystem
   ```

## Data Structures Used

- **ArrayList**: Used to maintain an ordered list of available items for display and selection.
- **HashMap**:
  - Used to store item prices (`Item Name` -> `Price`).
  - Used to track cart quantities (`Item Name` -> `Quantity`).