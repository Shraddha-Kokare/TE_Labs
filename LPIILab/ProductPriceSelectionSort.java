import java.util.Scanner;

class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

public class ProductPriceSelectionSort {

    // Selection Sort based on product price
    static void selectionSort(Product[] products) {
        int n = products.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (products[j].price < products[minIndex].price) {
                    minIndex = j;
                }
            }

            // Swap products
            Product temp = products[minIndex];
            products[minIndex] = products[i];
            products[i] = temp;
        }
    }

    static void displayProducts(Product[] products) {
        System.out.println("--------------------------------");
        System.out.printf("%-15s %-10s%n", "Product", "Price");
        System.out.println("--------------------------------");

        for (Product p : products) {
            System.out.printf("%-15s Rs.%-10.2f%n", p.name, p.price);
        }
        System.out.println("--------------------------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of products: ");
        int n = sc.nextInt();
        sc.nextLine();

        Product[] products = new Product[n];

        for (int i = 0; i < n; i++) {
            System.out.print("\nEnter product name: ");
            String name = sc.nextLine();

            System.out.print("Enter product price: ");
            double price = sc.nextDouble();
            sc.nextLine();

            products[i] = new Product(name, price);
        }

        System.out.println("\nProducts Before Sorting:");
        displayProducts(products);

        selectionSort(products);

        System.out.println("\nProducts After Sorting (Low to High Price):");
        displayProducts(products);

        sc.close();
    }
}
