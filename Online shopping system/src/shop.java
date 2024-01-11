import java.util.ArrayList;
import java.util.Scanner;

public class shop {
    private static final ArrayList<Product> productList = new ArrayList<>();
    static int op;

    static String name;
    static String id;
    static  int price;
    static  int qty;
    static String type;
    static String brand;
    static int warrantyPeriod;
    //static String electronicsProduct;
    static String size;
    static String color;

    public static void main(String[]args){
        System.out.println("******************Welcome to Online shopping System******************");
        System.out.println("----------------------------------------------------------------------");

        while (true){
            //display console menu
            System.out.println("\n");
            System.out.println("Please Select an Option");
            System.out.println("1.Add a new product");
            System.out.println("2.Delete a product");
            System.out.println("3.Print the list of the products");
            System.out.println("4.Save in a file");
            System.out.println("5.Exit");
            System.out.println("______________________________________________________________");
            while (true){
                while (true){
                    try {
                        Scanner input = new Scanner(System.in);
                        //select an option from console menu
                        System.out.print("Select an option:- ");
                        op = input.nextInt();

                        break;
                    }
                    catch (Exception e ){
                        System.out.println("please enter the valid option!" );
                    }
                }if(op>0 && op<6){
                    break;
                }else{
                    System.out.println("enter valid option");
                }
            }
            if (op==5){
                System.out.println("Thank You.");
                System.out.println("------------------------Good Bye----------------------");
                break;
            }
            switch (op){
                case 1:
                    add_product();
                    break;
                case 2:
                    deleteProduct();
                    break;
                case 3:
                    //print_list();
                    break;
                case 4:
                    //save_file();
                    break;
            }
        }


    }

    //------------------method add product-------------------
    static void add_product() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product ID: ");
         //id = Integer.parseInt(scanner.nextLine());
        id = scanner.nextLine();

        System.out.print("Enter product name: ");
        name = scanner.nextLine();

        System.out.print("Enter price: ");
        price = (int) scanner.nextDouble();

        System.out.print("Enter quantity: ");
        qty = scanner.nextInt();

        System.out.print("Product type (E for Electronics, C for Clothing): ");
        type = scanner.next();

        if(type.equalsIgnoreCase("E")) {
            // Create Electronics product
            System.out.print("Enter brand: ");
            brand = scanner.next();

            System.out.print("Enter warranty period(Enter the number of months): ");
            warrantyPeriod = scanner.nextInt();

            Electronics e = new Electronics(id, name, price, qty, brand, warrantyPeriod);
            productList.add(e);

        } else if(type.equalsIgnoreCase("C")) {
            // Create Clothing product
            System.out.print("Enter size: ");
            String size = scanner.next();

            System.out.print("Enter color: ");
            String color = scanner.next();

            Clothing c = new Clothing(id, name, price, qty,size,color);
            productList.add(c);

        }

        System.out.println("Product added!");

    }




    //------------------method delete product------------------
    public static void deleteProduct(){

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter product ID to delete: ");
            //id = Integer.parseInt(scanner.nextLine());
            id = scanner.nextLine();


            for(Product p: productList) {
                if(p.getID().equals(id)) {
                    productList.remove(p);
                    System.out.println("Deleted:");
                    System.out.println(p);
                    System.out.println("Total products: " + productList.size());
                    return;
                }
            }

            System.out.println("Product not found!");

        }


    private static class Electronics extends Product {
        public Electronics(String id, String name, double price, int qty, String brand, int warranty) {

        }
    }

    private static class Clothing extends Product {
        public Clothing(String id, String name, double price, int qty, String size, String color) {

        }
    }
}

    // Assume you have a Product class or its subclasses (Electronics, Clothing) defined
    class Product {
        /*private int id;
        private String name;
        private int price;
        private int quantity;

        /*Product() {
        }*/

        public Object getID() {
            return null;
        }

        // Constructors, getters, and setters

        // Other methods if needed

    }
