import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class WestminsterShoppingManager implements ShoppingManager {
    private static List<Product> productList = new ArrayList<>();

    private static final String filePath="dataStore.txt";
    private WestminsterOnlineShoppingSystemGUI gui;


    // implement this method due to request in assignment pdf. Otherwise this should be moved to WestminsterShoppingSystem Class
    public void startConsoleApplication() {
        System.out.println("******************Welcome to Online shopping System******************");
        System.out.println("----------------------------------------------------------------------");
        this.getProductsFromStore();

        while (true) {
            //display console menu
            System.out.println("\n");
            System.out.println("Please Select an Option");
            System.out.println("1.Add a new product");
            System.out.println("2.Delete a product");
            System.out.println("3.Print the list of the products");
            System.out.println("4.Save in a file");
            System.out.println("5.Open GUI");
            System.out.println("6.Exit");
            System.out.println("______________________________________________________________");

            System.out.print("Enter an option:");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    this.getInputAndAddProduct();
                    break;
                case 2:
                    this.getInputAndDeleteProduct();
                    break;

                case 3:
                    this.printProductList();
                    break;

                case 4:
                    this.saveToFile();
                    break;
                case 5:
                    this.openGUI();
                    break;
                case 6:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private void openGUI() {
        if (gui == null) {
            gui = new WestminsterOnlineShoppingSystemGUI(this, new ShoppingCart(), this.getProductList());

        } else {
            System.out.println("GUI is already open!");
        }
    }

    // File data store standard -> Id,Name,Type,quantity,price-brand,warrentyPeriod or size,color
    @Override
    public void saveToFile() {
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(filePath))) {
            for (Product item : productList) {
                String record;
                if(item instanceof Clothing){
                    record=String.format("%s,%s,%s,%s,%s-%s,%s\n",
                            item.getId(),
                            item.getName(),
                            item.getProductCategory(),
                            item.getAvailableQty(),
                            item.getPrice(),
                            ((Clothing) item).getSize(),
                            ((Clothing) item).getColor()
                    );
                }else{
                    record=String.format("%s,%s,%s,%s,%s-%s,%s\n",
                            item.getId(),
                            item.getName(),
                            item.getProductCategory(),
                            item.getAvailableQty(),
                            item.getPrice(),
                            ((Electronics) item).getBrand(),
                            ((Electronics) item).getWarrantyPeriod()
                    );
                }

                buffer.write(record);
            }
            System.out.println("Successfully written product details to " + filePath);
        } catch (IOException exception) {
            System.err.println("An error occurred during file writing: " + exception.getMessage());
        }
    }

    @Override
    public void printProductList() {
        System.out.println("------ Product List ------");

        Collections.sort(productList, (Product p1, Product p2) -> p1.getId().compareTo(p2.getId()));

        for (Product product : productList) {

            System.out.println("Product Id : "+ product.getId());
            System.out.println("Product Name : "+ product.getName());
            System.out.println("Product price : "+ product.getPrice());
            System.out.println("Product Type : "+ product.getProductCategory().toUpperCase());

            if(product instanceof Clothing){
                System.out.println("Product Size : "+ ((Clothing) product).getSize());
                System.out.println("Product Color : "+ ((Clothing) product).getColor());
            }
            if(product instanceof Electronics){
                System.out.println("Product Brand : "+ ((Electronics) product).getBrand());
                System.out.println("Product Warranty Period : "+ ((Electronics) product).getWarrantyPeriod());
            }

            System.out.println("");
            System.out.println("");
        }
    }

    // File data store standard -> Id,Name,Type,quantity,price-brand,warrantyPeriod or size,color
    @Override
    public void getProductsFromStore() {
        try {
            // Read all lines from a file as a List of Strings
            List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);

            // Print each line
            for (String line : lines) {
                if(line.isEmpty()){
                    continue;
                }
                String commonProperties=line.split("-")[0];
                String specificProperties=line.split("-")[1];

                String[] commonPropsList=commonProperties.split(",");
                String[] specificPropsList=specificProperties.split(",");

                String id=commonPropsList[0];
                String name=commonPropsList[1];
                String type=commonPropsList[2];
                String quantity=commonPropsList[3];
                String price=commonPropsList[4];

                if(type.equals("Clothing")){
                    String size=specificPropsList[0];
                    String color=specificPropsList[1];
                    Clothing clothObj=new Clothing(id,name,Double.parseDouble(price),Integer.parseInt(quantity),size,color);
                    this.addProduct(clothObj);
                } else if (type.equals("Electronics")) {
                    String brand=specificPropsList[0];
                    String warrantyPeriod=specificPropsList[1];
                    Electronics electronicObj=new Electronics(id,name,Double.parseDouble(price),Integer.parseInt(quantity),brand,Integer.parseInt(warrantyPeriod));
                    this.addProduct(electronicObj);
                }
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error occurred while reading the file: " + e.getMessage());
        }
    }


    private void getInputAndAddProduct(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product ID: ");
        //id = Integer.parseInt(scanner.nextLine());
        String id = scanner.nextLine();

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        int qty = getValidQuantity();

        double price =getValidPrice();

        String type = getValidProductType();

        if("E".equals(type)) {
            System.out.print("Enter brand: ");
            String brand = scanner.next();

            int warrantyPeriod = getValidWarrantyPeriod();
            Electronics electronics = new Electronics(id,name,price,qty,brand,warrantyPeriod);
            this.addProduct(electronics);

        }else if("C".equals(type)) {
            System.out.print("Enter size: ");
            String size = scanner.next();

            System.out.print("Enter color: ");
            String color = scanner.next();

            Clothing clothing = new Clothing(id, name, price, qty,size,color);
            this.addProduct(clothing);

        }
        System.out.println("Product added!");
    }

    private String getValidProductType() {
        Scanner scanner = new Scanner(System.in);
        String type="";
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Product type (E for Electronics, C for Clothing): ");
            type = scanner.next();
            if("E".equals(type)) {
               validInput=true;
            }else if("C".equals(type)) {
               validInput=true;
            }else {
                System.out.println("Invalid type.Enter valid type.");
            }
        }
        return type;
    }

    private int getValidQuantity() {
        Scanner scanner = new Scanner(System.in);
        int qty = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter quantity: ");
            try {
                qty = scanner.nextInt();
                if (qty < 0) {
                    System.out.println("Quantity cannot be negative. Please enter a valid integer.");
                } else {
                    validInput = true; // Input is valid, exit the loop
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input before next attempt
            }
        }
        return qty;
    }

    private int getValidWarrantyPeriod() {
        Scanner scanner = new Scanner(System.in);
        int period = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter warranty period(Enter the number of months): ");
            try {
                period = scanner.nextInt();
                if (period < 0) {
                    System.out.println("Warranty period cannot be negative. Please enter a valid integer.");
                } else {
                    validInput = true; // Input is valid, exit the loop
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input before next attempt
            }
        }
        return period;
    }

    private double getValidPrice() {
        Scanner scanner = new Scanner(System.in);
        double price = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter price: ");
            try {
                price = scanner.nextDouble();
                if (price < 0) {
                    System.out.println("Price cannot be negative. Please enter a valid amount.");
                } else {
                    validInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid amount.");
                scanner.next(); // Clear the invalid input before next attempt
            }
        }
        return price;
    }

    private void getInputAndDeleteProduct() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter product id to delete:");
        String productId = scanner.nextLine();
        this.deleteProduct(productId);
        System.out.println("Product deleted. Product id: " + productId);
    }

    @Override
    public void addProduct(Product product) {
        productList.add(product);
    }

    @Override
    public void deleteProduct(String productID) {
        List<Product> remainingProducts = new ArrayList<>();
        for (Product product : productList) {

            if (!product.getId().equals(productID)) {
                remainingProducts.add(product);
            }
        }

        productList = remainingProducts;
    }

    @Override
    public Product getProductByID(String productId) {
        Optional<Product> selectedProduct= productList.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst();
        if(selectedProduct.isEmpty()){
            throw new NoSuchElementException("No product found for given ID: "+productId);
        }else{
            return selectedProduct.get();
        }
    }

    @Override
    public List<Product> getProductsByType(String type) {
        if (productList == null) {
            return new ArrayList<>();
        }

        return productList.stream()
                .filter(p -> type.equalsIgnoreCase("All") || p.getProductCategory().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public List<Product> getProductList() {
        return productList;
    }

    private static void save_file() {

    }





    //------------------method print list------------------
    public void print_list(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            productList = new ArrayList<>();
            while (true) {
                try {
                    Product product = (Product) ois.readObject();
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

}