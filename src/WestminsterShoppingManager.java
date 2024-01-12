import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class WestminsterShoppingManager {
    private static List<product> productList = new ArrayList<>();
    //private static final ArrayList<Product> productList;
    static int op;

    static String name;
    static String id;
    static  int price;
    static  int qty;
    static String type;
    static String brand;
    static int warrantyPeriod;
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
                    print_list();
                    break;
                case 4:
                    save_file();
                    break;
            }
        }


    }

    private static void save_file() {

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

        if("E".equals(type)) {
            // Create Electronics product
            System.out.print("Enter brand: ");
            brand = scanner.next();

            System.out.print("Enter warranty period(Enter the number of months): ");
            warrantyPeriod = scanner.nextInt();
            Electronics electronics = new Electronics(id,name,price,qty,brand,warrantyPeriod);
            //Electronics e = new Electronics(id, name, price, qty, brand, warrantyPeriod);
            productList.add(electronics);
        }else if("C".equals(type)) {
            // Create Clothing product
            System.out.print("Enter size: ");
            size = scanner.next();

            System.out.print("Enter color: ");
            color = scanner.next();

            Clothing clothing = new Clothing(id, name, price, qty,size,color);
            productList.add(clothing);

        }else {
            System.out.println("Enter valid type: ");

        }

        System.out.println("Product added!");
        product Product = new product();
        productList.add(Product);

    }


    //------------------method delete product------------------
    public static void deleteProduct(){

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter product ID to delete: ");
            id = scanner.nextLine();


            for(product product:productList) {
                if (product.getId() == id) {
                    //boolean product = false;
                    productList.remove(product);
                    System.out.println("Deleted:");
                    System.out.println(product);
                    System.out.println("Total products: " + productList.size());
                    return;
                }
            }

            System.out.println("Product not found!");

    }
    //------------------method print list------------------
    public void print_list(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            productList = new ArrayList<>();
            while (true) {
                try {
                    product product = (product) ois.readObject();
                    productList.add(product);
                } catch (EOFException e) {
                    break;
                }
            }
            System.out.println("Products loaded from file: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void save_file(String fileName) {
        name = fileName;
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (product product : productList) {
                fileWriter.write(product.getId() +", "+ product.getName() +", "+ product.getPrice() +", "+ product.getQty());
            }
            System.out.println("Product List Successfully Saved..");
        } catch (IOException e) {
            System.err.println("Error massage saving to file: " + e.getMessage());
        }
    }

}