import java.util.*;
import java.io.*;

class Product {

    private int productId;
    private String productName;
    private int quantity;
    private double price;
    private String category;

    public Product() {
    }

    public Product(int productId,
                   String productName,
                   int quantity,
                   double price,
                   String category) {

        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double inventoryValue() {
        return quantity * price;
    }

    @Override
    public String toString() {

        return productId +
                " " +
                productName +
                " Qty:" +
                quantity +
                " Price:" +
                price +
                " Category:" +
                category;
    }
}

interface InventoryManager {

    void addProduct(Product p);

    void displayProducts();

    Product searchProduct(int id);
}

abstract class StorageCost {

    public abstract double calculateStorageCost(Product p);
}

class ElectronicsStorage extends StorageCost {

    @Override
    public double calculateStorageCost(Product p) {

        return p.getQuantity() * 20.0;
    }
}

class FoodStorage extends StorageCost {

    @Override
    public double calculateStorageCost(Product p) {

        return p.getQuantity() * 10.0;
    }
}

class FurnitureStorage extends StorageCost {

    @Override
    public double calculateStorageCost(Product p) {

        return p.getQuantity() * 15.0;
    }
}

class ProductNotFoundException extends Exception {

    public ProductNotFoundException(String message) {

        super(message);
    }
}

class Warehouse implements InventoryManager {

    private ArrayList<Product> products;

    private int[][] warehouseLayout;

    private int rows;

    private int cols;

    public Warehouse(int rows, int cols) {

        this.rows = rows;
        this.cols = cols;

        products = new ArrayList<>();

        warehouseLayout = new int[rows][cols];
    }

    @Override
    public void addProduct(Product p) {

        products.add(p);
    }

    @Override
    public void displayProducts() {

        for (Product p : products) {

            System.out.println(p);
        }
    }

    @Override
    public Product searchProduct(int id) {

        for (Product p : products) {

            if (p.getProductId() == id) {

                return p;
            }
        }

        return null;
    }

    public void placeProduct(int row,
                             int col,
                             int productId) {

        warehouseLayout[row][col] = productId;
    }

    public void displayWarehouse() {

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                System.out.print(
                        warehouseLayout[i][j] + " "
                );
            }

            System.out.println();
        }
    }

    public ArrayList<Product> getProducts() {

        return products;
    }

    public int[][] getWarehouseLayout() {

        return warehouseLayout;
    }

    public double totalInventoryValue() {

        double sum = 0;

        for (Product p : products) {

            sum += p.inventoryValue();
        }

        return sum;
    }

    public Product highestPriceProduct() {

        if (products.isEmpty()) {

            return null;
        }

        Product max = products.get(0);

        for (Product p : products) {

            if (p.getPrice() > max.getPrice()) {

                max = p;
            }
        }

        return max;
    }

    public Product lowestPriceProduct() {

        if (products.isEmpty()) {

            return null;
        }

        Product min = products.get(0);

        for (Product p : products) {

            if (p.getPrice() < min.getPrice()) {

                min = p;
            }
        }

        return min;
    }
}

class WarehouseStatistics {

    public static int totalProducts(
            ArrayList<Product> products) {

        return products.size();
    }

    public static int totalQuantity(
            ArrayList<Product> products) {

        int sum = 0;

        for (Product p : products) {

            sum += p.getQuantity();
        }

        return sum;
    }

    public static double averagePrice(
            ArrayList<Product> products) {

        if (products.size() == 0) {

            return 0;
        }

        double sum = 0;

        for (Product p : products) {

            sum += p.getPrice();
        }

        return sum / products.size();
    }

    public static Map<String, Integer>
    categoryCount(
            ArrayList<Product> products) {

        Map<String, Integer> map =
                new HashMap<>();

        for (Product p : products) {

            map.put(
                    p.getCategory(),
                    map.getOrDefault(
                            p.getCategory(),
                            0
                    ) + 1
            );
        }

        return map;
    }
}

class DataLoader {

    public static ArrayList<Product>
    loadSampleProducts() {

        ArrayList<Product> list =
                new ArrayList<>();

        list.add(
                new Product(
                        101,
                        "Laptop",
                        10,
                        50000,
                        "Electronics"
                )
        );

        list.add(
                new Product(
                        102,
                        "Phone",
                        20,
                        30000,
                        "Electronics"
                )
        );

        list.add(
                new Product(
                        103,
                        "Rice",
                        50,
                        60,
                        "Food"
                )
        );

        list.add(
                new Product(
                        104,
                        "TV",
                        5,
                        45000,
                        "Electronics"
                )
        );

        list.add(
                new Product(
                        105,
                        "Milk",
                        30,
                        50,
                        "Food"
                )
        );

        list.add(
                new Product(
                        106,
                        "Chair",
                        15,
                        2500,
                        "Furniture"
                )
        );

        list.add(
                new Product(
                        107,
                        "Table",
                        10,
                        7000,
                        "Furniture"
                )
        );

        list.add(
                new Product(
                        108,
                        "Monitor",
                        12,
                        15000,
                        "Electronics"
                )
        );

        list.add(
                new Product(
                        109,
                        "Keyboard",
                        40,
                        1200,
                        "Electronics"
                )
        );

        list.add(
                new Product(
                        110,
                        "Mouse",
                        60,
                        800,
                        "Electronics"
                )
        );

        return list;
    }
}
/* =========================================================
   BST NODE
   ========================================================= */

class BSTNode {

    int productId;

    BSTNode left;
    BSTNode right;

    BSTNode(int productId) {

        this.productId = productId;
    }
}

/* =========================================================
   BINARY SEARCH TREE
   ========================================================= */

class ProductBST {

    BSTNode root;

    public void insert(int productId) {

        root = insertRecursive(root, productId);
    }

    private BSTNode insertRecursive(
            BSTNode node,
            int productId) {

        if (node == null) {

            return new BSTNode(productId);
        }

        if (productId < node.productId) {

            node.left =
                    insertRecursive(
                            node.left,
                            productId
                    );
        }
        else {

            node.right =
                    insertRecursive(
                            node.right,
                            productId
                    );
        }

        return node;
    }

    public boolean search(int id) {

        return searchRecursive(root, id);
    }

    private boolean searchRecursive(
            BSTNode node,
            int id) {

        if (node == null) {

            return false;
        }

        if (node.productId == id) {

            return true;
        }

        if (id < node.productId) {

            return searchRecursive(
                    node.left,
                    id
            );
        }

        return searchRecursive(
                node.right,
                id
        );
    }

    public void inorder() {

        inorder(root);

        System.out.println();
    }

    private void inorder(BSTNode node) {

        if (node != null) {

            inorder(node.left);

            System.out.print(
                    node.productId + " "
            );

            inorder(node.right);
        }
    }

    public void preorder() {

        preorder(root);

        System.out.println();
    }

    private void preorder(BSTNode node) {

        if (node != null) {

            System.out.print(
                    node.productId + " "
            );

            preorder(node.left);

            preorder(node.right);
        }
    }

    public void postorder() {

        postorder(root);

        System.out.println();
    }

    private void postorder(BSTNode node) {

        if (node != null) {

            postorder(node.left);

            postorder(node.right);

            System.out.print(
                    node.productId + " "
            );
        }
    }

    public int countNodes() {

        return countNodes(root);
    }

    private int countNodes(BSTNode node) {

        if (node == null) {

            return 0;
        }

        return 1
                + countNodes(node.left)
                + countNodes(node.right);
    }

    public int height() {

        return height(root);
    }

    private int height(BSTNode node) {

        if (node == null) {

            return 0;
        }

        return Math.max(
                height(node.left),
                height(node.right)
        ) + 1;
    }
}

/* =========================================================
   BINARY SEARCH
   ========================================================= */

class BinarySearchUtil {

    public static int binarySearch(
            int[] arr,
            int target) {

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {

            int mid =
                    left +
                    (right - left) / 2;

            if (arr[mid] == target) {

                return mid;
            }

            if (arr[mid] < target) {

                left = mid + 1;
            }
            else {

                right = mid - 1;
            }
        }

        return -1;
    }
}

/* =========================================================
   BUBBLE SORT
   ========================================================= */

class BubbleSort {

    public static void sort(int[] arr) {

        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0;
                 j < n - i - 1;
                 j++) {

                if (arr[j] > arr[j + 1]) {

                    int temp = arr[j];

                    arr[j] = arr[j + 1];

                    arr[j + 1] = temp;
                }
            }
        }
    }
}

/* =========================================================
   MERGE SORT
   ========================================================= */

class MergeSort {

    public static void sort(
            int[] arr,
            int left,
            int right) {

        if (left < right) {

            int mid =
                    (left + right) / 2;

            sort(arr, left, mid);

            sort(arr,
                    mid + 1,
                    right);

            merge(
                    arr,
                    left,
                    mid,
                    right
            );
        }
    }

    private static void merge(
            int[] arr,
            int left,
            int mid,
            int right) {

        int n1 =
                mid - left + 1;

        int n2 =
                right - mid;

        int[] L =
                new int[n1];

        int[] R =
                new int[n2];

        for (int i = 0; i < n1; i++) {

            L[i] =
                    arr[left + i];
        }

        for (int j = 0; j < n2; j++) {

            R[j] =
                    arr[mid + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 &&
                j < n2) {

            if (L[i] <= R[j]) {

                arr[k++] = L[i++];
            }
            else {

                arr[k++] = R[j++];
            }
        }

        while (i < n1) {

            arr[k++] = L[i++];
        }

        while (j < n2) {

            arr[k++] = R[j++];
        }
    }
}

/* =========================================================
   QUICK SORT
   ========================================================= */

class QuickSort {

    public static void sort(
            int[] arr,
            int low,
            int high) {

        if (low < high) {

            int pi =
                    partition(
                            arr,
                            low,
                            high
                    );

            sort(
                    arr,
                    low,
                    pi - 1
            );

            sort(
                    arr,
                    pi + 1,
                    high
            );
        }
    }

    private static int partition(
            int[] arr,
            int low,
            int high) {

        int pivot =
                arr[high];

        int i =
                low - 1;

        for (int j = low;
             j < high;
             j++) {

            if (arr[j] < pivot) {

                i++;

                int temp =
                        arr[i];

                arr[i] =
                        arr[j];

                arr[j] =
                        temp;
            }
        }

        int temp =
                arr[i + 1];

        arr[i + 1] =
                arr[high];

        arr[high] =
                temp;

        return i + 1;
    }
}

/* =========================================================
   COMPARABLE
   ========================================================= */

class ProductPriceComparable
        implements Comparable<ProductPriceComparable> {

    int id;
    double price;

    ProductPriceComparable(
            int id,
            double price) {

        this.id = id;
        this.price = price;
    }

    @Override
    public int compareTo(
            ProductPriceComparable other) {

        return Double.compare(
                this.price,
                other.price
        );
    }
}

/* =========================================================
   COMPARATOR
   ========================================================= */

class ProductIdComparator
        implements Comparator<Product> {

    @Override
    public int compare(
            Product p1,
            Product p2) {

        return Integer.compare(
                p1.getProductId(),
                p2.getProductId()
        );
    }
}

/* =========================================================
   COLLECTIONS DEMO
   ========================================================= */

class CollectionModule {

    public static void runCollectionsDemo() {

        Stack<Integer> stack =
                new Stack<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        if (!stack.isEmpty()) {

            stack.pop();
        }

        Queue<Integer> queue =
                new LinkedList<>();

        queue.add(100);
        queue.add(200);
        queue.add(300);

        queue.poll();

        HashSet<Integer> set =
                new HashSet<>();

        set.add(1);
        set.add(2);
        set.add(3);

        TreeSet<Integer> treeSet =
                new TreeSet<>();

        treeSet.add(50);
        treeSet.add(10);
        treeSet.add(90);

        PriorityQueue<Integer>
                priorityQueue =
                new PriorityQueue<>();

        priorityQueue.add(40);
        priorityQueue.add(10);
        priorityQueue.add(60);

        priorityQueue.poll();

        HashMap<Integer, String>
                productMap =
                new HashMap<>();

        productMap.put(
                101,
                "Laptop"
        );

        productMap.put(
                102,
                "Phone"
        );

        productMap.put(
                103,
                "Rice"
        );

        TreeMap<Integer, String>
                treeMap =
                new TreeMap<>();

        treeMap.put(
                201,
                "Warehouse-A"
        );

        treeMap.put(
                202,
                "Warehouse-B"
        );

        treeMap.put(
                203,
                "Warehouse-C"
        );
    }
}
/* =========================================================
   RECURSION MODULE
   ========================================================= */

class RecursiveAnalytics {

    public static int totalQuantity(
            ArrayList<Product> products,
            int index) {

        if (index == products.size()) {

            return 0;
        }

        return products.get(index).getQuantity()
                + totalQuantity(
                        products,
                        index + 1
                );
    }

    public static double totalInventoryValue(
            ArrayList<Product> products,
            int index) {

        if (index == products.size()) {

            return 0;
        }

        return products.get(index).inventoryValue()
                + totalInventoryValue(
                        products,
                        index + 1
                );
    }

    public static int factorial(int n) {

        if (n <= 1) {

            return 1;
        }

        return n * factorial(n - 1);
    }

    public static int fibonacci(int n) {

        if (n <= 1) {

            return n;
        }

        return fibonacci(n - 1)
                + fibonacci(n - 2);
    }

    public static int power(
            int base,
            int exp) {

        if (exp == 0) {

            return 1;
        }

        return base *
                power(
                        base,
                        exp - 1
                );
    }

    public static int gcd(
            int a,
            int b) {

        if (b == 0) {

            return a;
        }

        return gcd(
                b,
                a % b
        );
    }
}

/* =========================================================
   DYNAMIC PROGRAMMING
   ========================================================= */

class DynamicProgrammingModule {

    public static int maxProfitPath(
            int[][] profit) {

        int rows =
                profit.length;

        int cols =
                profit[0].length;

        int[][] dp =
                new int[rows][cols];

        dp[0][0] =
                profit[0][0];

        for (int i = 1;
             i < rows;
             i++) {

            dp[i][0] =
                    dp[i - 1][0]
                            + profit[i][0];
        }

        for (int j = 1;
             j < cols;
             j++) {

            dp[0][j] =
                    dp[0][j - 1]
                            + profit[0][j];
        }

        for (int i = 1;
             i < rows;
             i++) {

            for (int j = 1;
                 j < cols;
                 j++) {

                dp[i][j] =
                        profit[i][j]
                                + Math.max(
                                dp[i - 1][j],
                                dp[i][j - 1]
                        );
            }
        }

        return dp[rows - 1][cols - 1];
    }

    public static int fibonacciDP(
            int n) {

        if (n <= 1) {

            return n;
        }

        int[] dp =
                new int[n + 1];

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2;
             i <= n;
             i++) {

            dp[i] =
                    dp[i - 1]
                            + dp[i - 2];
        }

        return dp[n];
    }

    public static int knapsack(
            int W,
            int[] wt,
            int[] val,
            int n) {

        int[][] dp =
                new int[n + 1][W + 1];

        for (int i = 0;
             i <= n;
             i++) {

            for (int w = 0;
                 w <= W;
                 w++) {

                if (i == 0 || w == 0) {

                    dp[i][w] = 0;
                }
                else if (
                        wt[i - 1] <= w) {

                    dp[i][w] =
                            Math.max(
                                    val[i - 1]
                                            + dp[i - 1][
                                            w - wt[i - 1]
                                            ],
                                    dp[i - 1][w]
                            );
                }
                else {

                    dp[i][w] =
                            dp[i - 1][w];
                }
            }
        }

        return dp[n][W];
    }
}

/* =========================================================
   MATRIX OPERATIONS
   ========================================================= */

class MatrixOperations {

    public static int[][] add(
            int[][] A,
            int[][] B) {

        int rows =
                A.length;

        int cols =
                A[0].length;

        int[][] result =
                new int[rows][cols];

        for (int i = 0;
             i < rows;
             i++) {

            for (int j = 0;
                 j < cols;
                 j++) {

                result[i][j] =
                        A[i][j]
                                + B[i][j];
            }
        }

        return result;
    }

    public static int[][] multiply(
            int[][] A,
            int[][] B) {

        int rows =
                A.length;

        int cols =
                B[0].length;

        int common =
                B.length;

        int[][] result =
                new int[rows][cols];

        for (int i = 0;
             i < rows;
             i++) {

            for (int j = 0;
                 j < cols;
                 j++) {

                for (int k = 0;
                     k < common;
                     k++) {

                    result[i][j] +=
                            A[i][k]
                                    * B[k][j];
                }
            }
        }

        return result;
    }

    public static void printMatrix(
            int[][] matrix) {

        for (int[] row
                : matrix) {

            for (int value
                    : row) {

                System.out.print(
                        value + " "
                );
            }

            System.out.println();
        }
    }
}

/* =========================================================
   BACKTRACKING
   ========================================================= */

class BacktrackingModule {

    public static void generatePaths(
            int row,
            int col,
            int rows,
            int cols,
            String path) {

        if (row == rows - 1
                && col == cols - 1) {

            return;
        }

        if (row + 1 < rows) {

            generatePaths(
                    row + 1,
                    col,
                    rows,
                    cols,
                    path + "D"
            );
        }

        if (col + 1 < cols) {

            generatePaths(
                    row,
                    col + 1,
                    rows,
                    cols,
                    path + "R"
            );
        }
    }
}

/* =========================================================
   REPORT GENERATOR
   ========================================================= */

class ReportGenerator {

    public static void inventoryReport(
            Warehouse warehouse) {

        ArrayList<Product>
                products =
                warehouse.getProducts();

        System.out.println(
                "\n===== INVENTORY REPORT =====");

        System.out.println(
                "Total Products : "
                        + products.size());

        System.out.println(
                "Total Quantity : "
                        + RecursiveAnalytics
                        .totalQuantity(
                                products,
                                0));

        System.out.println(
                "Total Value : "
                        + warehouse
                        .totalInventoryValue());

        Product highest =
                warehouse
                        .highestPriceProduct();

        Product lowest =
                warehouse
                        .lowestPriceProduct();

        if (highest != null) {

            System.out.println(
                    "Highest Price Product : "
                            + highest
                            .getProductName());
        }

        if (lowest != null) {

            System.out.println(
                    "Lowest Price Product : "
                            + lowest
                            .getProductName());
        }
    }
}

/* =========================================================
   ANALYTICS ENGINE
   ========================================================= */

class AnalyticsEngine {

    public static double averagePrice(
            ArrayList<Product> products) {

        double sum = 0;

        for (Product p
                : products) {

            sum += p.getPrice();
        }

        return sum
                / products.size();
    }

    public static int maxQuantity(
            ArrayList<Product> products) {

        int max = 0;

        for (Product p
                : products) {

            max =
                    Math.max(
                            max,
                            p.getQuantity()
                    );
        }

        return max;
    }

    public static int minQuantity(
            ArrayList<Product> products) {

        int min =
                Integer.MAX_VALUE;

        for (Product p
                : products) {

            min =
                    Math.min(
                            min,
                            p.getQuantity()
                    );
        }

        return min;
    }

    public static double totalRevenue(
            ArrayList<Product> products) {

        double revenue = 0;

        for (Product p
                : products) {

            revenue +=
                    p.getPrice()
                            * p.getQuantity();
        }

        return revenue;
    }
}

/* =========================================================
   MATHEMATICAL UTILITIES
   ========================================================= */

class MathUtilities {

    public static boolean isPrime(
            int n) {

        if (n <= 1) {

            return false;
        }

        for (int i = 2;
             i * i <= n;
             i++) {

            if (n % i == 0) {

                return false;
            }
        }

        return true;
    }

    public static int lcm(
            int a,
            int b) {

        return
                (a * b)
                        /
                        RecursiveAnalytics
                                .gcd(a, b);
    }

    public static int sumArray(
            int[] arr) {

        int sum = 0;

        for (int num
                : arr) {

            sum += num;
        }

        return sum;
    }

    public static double averageArray(
            int[] arr) {

        return
                (double) sumArray(arr)
                        / arr.length;
    }
}
/* =========================================================
   FILE HANDLING MODULE
   ========================================================= */

class FileManager {

    public static void writeReport(
            String fileName,
            String content) {

        try {

            FileWriter writer =
                    new FileWriter(fileName);

            writer.write(content);

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "File Write Error"
            );
        }
    }

    public static String readReport(
            String fileName) {

        StringBuilder data =
                new StringBuilder();

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    fileName
                            )
                    );

            String line;

            while ((line = br.readLine())
                    != null) {

                data.append(line)
                        .append("\n");
            }

            br.close();

        } catch (Exception e) {

            System.out.println(
                    "File Read Error"
            );
        }

        return data.toString();
    }
}

/* =========================================================
   CUSTOM EXCEPTIONS
   ========================================================= */

class WarehouseException
        extends Exception {

    public WarehouseException(
            String message) {

        super(message);
    }
}

class InvalidQuantityException
        extends WarehouseException {

    public InvalidQuantityException(
            String message) {

        super(message);
    }
}

class InvalidPriceException
        extends WarehouseException {

    public InvalidPriceException(
            String message) {

        super(message);
    }
}

/* =========================================================
   VALIDATOR MODULE
   ========================================================= */

class ProductValidator {

    public static void validate(
            Product product)
            throws WarehouseException {

        if (product.getQuantity() < 0) {

            throw new InvalidQuantityException(
                    "Quantity Cannot Be Negative"
            );
        }

        if (product.getPrice() < 0) {

            throw new InvalidPriceException(
                    "Price Cannot Be Negative"
            );
        }
    }
}

/* =========================================================
   GENERICS MODULE
   ========================================================= */

class GenericStorage<T> {

    private ArrayList<T> list =
            new ArrayList<>();

    public void add(T item) {

        list.add(item);
    }

    public T get(int index) {

        return list.get(index);
    }

    public int size() {

        return list.size();
    }

    public void display() {

        for (T item : list) {

            System.out.println(item);
        }
    }
}

/* =========================================================
   THREAD 1
   ========================================================= */

class InventoryThread
        extends Thread {

    @Override
    public void run() {

        for (int i = 1;
             i <= 5;
             i++) {

            try {

                Thread.sleep(100);

            } catch (Exception e) {
            }
        }
    }
}

/* =========================================================
   THREAD 2
   ========================================================= */

class StatisticsThread
        extends Thread {

    @Override
    public void run() {

        for (int i = 1;
             i <= 5;
             i++) {

            try {

                Thread.sleep(100);

            } catch (Exception e) {
            }
        }
    }
}

/* =========================================================
   RUNNABLE IMPLEMENTATION
   ========================================================= */

class ReportRunnable
        implements Runnable {

    @Override
    public void run() {

        try {

            Thread.sleep(200);

        } catch (Exception e) {
        }
    }
}

/* =========================================================
   LAMBDA EXPRESSIONS
   ========================================================= */

interface StorageOperation {

    double calculate(
            int quantity,
            double rate);
}

class LambdaModule {

    public static void execute() {

        StorageOperation op =
                (qty, rate)
                        -> qty * rate;

        op.calculate(
                10,
                20
        );
    }
}

/* =========================================================
   STREAM API
   ========================================================= */

class StreamModule {

    public static double totalValue(
            ArrayList<Product> products) {

        return products
                .stream()
                .mapToDouble(
                        p -> p.getPrice()
                                * p.getQuantity()
                )
                .sum();
    }

    public static long electronicsCount(
            ArrayList<Product> products) {

        return products
                .stream()
                .filter(
                        p ->
                                p.getCategory()
                                        .equalsIgnoreCase(
                                                "Electronics"
                                        )
                )
                .count();
    }

    public static Optional<Product>
    highestPriceProduct(
            ArrayList<Product> products) {

        return products
                .stream()
                .max(
                        Comparator.comparing(
                                Product::getPrice
                        )
                );
    }
}

/* =========================================================
   PERFORMANCE MONITOR
   ========================================================= */

class PerformanceMonitor {

    private long startTime;

    public void start() {

        startTime =
                System.nanoTime();
    }

    public void stop() {

        long endTime =
                System.nanoTime();

        double executionTime =
                (endTime - startTime)
                        / 1_000_000_000.0;

        Runtime runtime =
                Runtime.getRuntime();

        runtime.gc();

        double memoryUsage =
                (runtime.totalMemory()
                        - runtime.freeMemory())
                        / (1024.0 * 1024.0);

        System.out.printf(
                "Execution Time : %.3f sec%n",
                executionTime
        );

        System.out.printf(
                "Memory Usage : %.2f MB%n",
                memoryUsage
        );
    }
}

/* =========================================================
   SERIALIZATION READY CLASS
   ========================================================= */

class SerializableProduct
        implements Serializable {

    private static final long
            serialVersionUID = 1L;

    int id;

    String name;

    public SerializableProduct(
            int id,
            String name) {

        this.id = id;
        this.name = name;
    }
}

/* =========================================================
   OBJECT FILE HANDLING
   ========================================================= */

class ObjectFileManager {

    public static void saveObject(
            SerializableProduct p,
            String fileName) {

        try {

            ObjectOutputStream out =
                    new ObjectOutputStream(
                            new FileOutputStream(
                                    fileName
                            )
                    );

            out.writeObject(p);

            out.close();

        } catch (Exception e) {
        }
    }

    public static SerializableProduct
    loadObject(
            String fileName) {

        try {

            ObjectInputStream in =
                    new ObjectInputStream(
                            new FileInputStream(
                                    fileName
                            )
                    );

            SerializableProduct p =
                    (SerializableProduct)
                            in.readObject();

            in.close();

            return p;

        } catch (Exception e) {

            return null;
        }
    }
}

/* =========================================================
   DEQUE MODULE
   ========================================================= */

class DequeModule {

    public static void execute() {

        Deque<Integer> deque =
                new ArrayDeque<>();

        deque.addFirst(10);
        deque.addLast(20);

        deque.removeFirst();
    }
}

/* =========================================================
   ENUM MODULE
   ========================================================= */

enum ProductCategory {

    ELECTRONICS,
    FOOD,
    FURNITURE,
    CLOTHING,
    MEDICAL
}

/* =========================================================
   DATE & TIME MODULE
   ========================================================= */

class DateTimeModule {

    public static String currentDate() {

        return java.time.LocalDate
                .now()
                .toString();
    }

    public static String currentTime() {

        return java.time.LocalTime
                .now()
                .toString();
    }
}

/* =========================================================
   STRING UTILITIES
   ========================================================= */

class StringUtilities {

    public static boolean palindrome(
            String str) {

        String reversed =
                new StringBuilder(str)
                        .reverse()
                        .toString();

        return str.equals(reversed);
    }

    public static int wordCount(
            String text) {

        return text.split("\\s+")
                .length;
    }
}
/* =========================================================
   GRAPH USING BFS & DFS
   ========================================================= */

class Graph {

    private int vertices;

    private LinkedList<Integer>[] adj;

    @SuppressWarnings("unchecked")
    public Graph(int vertices) {

        this.vertices = vertices;

        adj = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++) {

            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src, int dest) {

        adj[src].add(dest);
    }

    public void bfs(int start) {

        boolean[] visited =
                new boolean[vertices];

        Queue<Integer> queue =
                new LinkedList<>();

        visited[start] = true;

        queue.add(start);

        while (!queue.isEmpty()) {

            int node = queue.poll();

            for (int next : adj[node]) {

                if (!visited[next]) {

                    visited[next] = true;

                    queue.add(next);
                }
            }
        }
    }

    public void dfs(int start) {

        boolean[] visited =
                new boolean[vertices];

        dfsUtil(start, visited);
    }

    private void dfsUtil(
            int node,
            boolean[] visited) {

        visited[node] = true;

        for (int next : adj[node]) {

            if (!visited[next]) {

                dfsUtil(next, visited);
            }
        }
    }
}

/* =========================================================
   MIN HEAP
   ========================================================= */

class MinHeap {

    PriorityQueue<Integer> heap =
            new PriorityQueue<>();

    public void insert(int value) {

        heap.add(value);
    }

    public int removeMin() {

        return heap.poll();
    }

    public int size() {

        return heap.size();
    }
}

/* =========================================================
   AVL TREE
   ========================================================= */

class AVLNode {

    int key;
    int height;

    AVLNode left;
    AVLNode right;

    AVLNode(int key) {

        this.key = key;
        height = 1;
    }
}

class AVLTree {

    AVLNode root;

    int height(AVLNode n) {

        return n == null ? 0 : n.height;
    }

    int balance(AVLNode n) {

        return n == null
                ? 0
                : height(n.left)
                - height(n.right);
    }

    AVLNode rightRotate(
            AVLNode y) {

        AVLNode x = y.left;

        AVLNode t2 = x.right;

        x.right = y;

        y.left = t2;

        y.height =
                Math.max(
                        height(y.left),
                        height(y.right)
                ) + 1;

        x.height =
                Math.max(
                        height(x.left),
                        height(x.right)
                ) + 1;

        return x;
    }

    AVLNode leftRotate(
            AVLNode x) {

        AVLNode y = x.right;

        AVLNode t2 = y.left;

        y.left = x;

        x.right = t2;

        x.height =
                Math.max(
                        height(x.left),
                        height(x.right)
                ) + 1;

        y.height =
                Math.max(
                        height(y.left),
                        height(y.right)
                ) + 1;

        return y;
    }

    AVLNode insert(
            AVLNode node,
            int key) {

        if (node == null) {

            return new AVLNode(key);
        }

        if (key < node.key) {

            node.left =
                    insert(
                            node.left,
                            key
                    );
        }
        else if (key > node.key) {

            node.right =
                    insert(
                            node.right,
                            key
                    );
        }
        else {

            return node;
        }

        node.height =
                1 +
                        Math.max(
                                height(node.left),
                                height(node.right)
                        );

        int balance =
                balance(node);

        if (balance > 1
                && key < node.left.key) {

            return rightRotate(node);
        }

        if (balance < -1
                && key > node.right.key) {

            return leftRotate(node);
        }

        if (balance > 1
                && key > node.left.key) {

            node.left =
                    leftRotate(node.left);

            return rightRotate(node);
        }

        if (balance < -1
                && key < node.right.key) {

            node.right =
                    rightRotate(node.right);

            return leftRotate(node);
        }

        return node;
    }
}

/* =========================================================
   SINGLETON DESIGN PATTERN
   ========================================================= */

class WarehouseDashboard {

    private static WarehouseDashboard instance =
            new WarehouseDashboard();

    private WarehouseDashboard() {
    }

    public static WarehouseDashboard getInstance() {

        return instance;
    }

    public void initialize() {
    }
}

/* =========================================================
   MAIN CLASS
   ========================================================= */

public class Main {

    public static void main(String[] args) {

        PerformanceMonitor monitor =
                new PerformanceMonitor();

        monitor.start();

        try {

            Warehouse warehouse =
                    new Warehouse(3, 3);

            ArrayList<Product> products =
                    DataLoader.loadSampleProducts();

            for (Product p : products) {

                warehouse.addProduct(p);
            }

            warehouse.placeProduct(0,0,101);
            warehouse.placeProduct(0,1,102);
            warehouse.placeProduct(0,2,103);
            warehouse.placeProduct(1,0,104);
            warehouse.placeProduct(1,1,105);
            warehouse.placeProduct(1,2,106);

            ProductBST bst =
                    new ProductBST();

            for (Product p : products) {

                bst.insert(
                        p.getProductId()
                );
            }

            // Execute BST operations silently
            bst.search(105);
            bst.countNodes();
            bst.height();

            int[] ids = {
                    101,102,103,
                    104,105,106
            };

            BinarySearchUtil.binarySearch(
                    ids,
                    105
            );

            BubbleSort.sort(ids);

            MergeSort.sort(
                    ids,
                    0,
                    ids.length - 1
            );

            QuickSort.sort(
                    ids,
                    0,
                    ids.length - 1
            );

            RecursiveAnalytics.totalQuantity(
                    products,
                    0
            );

            RecursiveAnalytics.totalInventoryValue(
                    products,
                    0
            );

            RecursiveAnalytics.factorial(10);

            RecursiveAnalytics.fibonacci(15);

            int[][] profit = {
                    {5,8,2},
                    {4,7,6},
                    {9,3,1}
            };

            DynamicProgrammingModule.maxProfitPath(
                    profit
            );

            DynamicProgrammingModule.fibonacciDP(
                    20
            );

            int[] wt = {2,3,4,5};
            int[] val = {3,4,5,6};

            DynamicProgrammingModule.knapsack(
                    5,
                    wt,
                    val,
                    4
            );

            MatrixOperations.add(
                    new int[][]{
                            {1,2},
                            {3,4}
                    },
                    new int[][]{
                            {5,6},
                            {7,8}
                    }
            );

            MatrixOperations.multiply(
                    new int[][]{
                            {1,2},
                            {3,4}
                    },
                    new int[][]{
                            {5,6},
                            {7,8}
                    }
            );

            BacktrackingModule.generatePaths(
                    0,
                    0,
                    3,
                    3,
                    ""
            );

            InventoryThread t1 =
                    new InventoryThread();

            StatisticsThread t2 =
                    new StatisticsThread();

            Thread t3 =
                    new Thread(
                            new ReportRunnable()
                    );

            t1.start();
            t2.start();
            t3.start();

            t1.join();
            t2.join();
            t3.join();

            LambdaModule.execute();

            StreamModule.totalValue(products);

            StreamModule.electronicsCount(
                    products
            );

            StreamModule.highestPriceProduct(
                    products
            );

            GenericStorage<Product>
                    storage =
                    new GenericStorage<>();

            for (Product p : products) {

                storage.add(p);
            }

            Graph graph =
                    new Graph(5);

            graph.addEdge(0,1);
            graph.addEdge(1,2);
            graph.addEdge(2,3);
            graph.addEdge(3,4);

            graph.bfs(0);
            graph.dfs(0);

            MinHeap heap =
                    new MinHeap();

            heap.insert(50);
            heap.insert(10);
            heap.insert(30);

            heap.removeMin();

            AVLTree avl =
                    new AVLTree();

            avl.root =
                    avl.insert(
                            avl.root,
                            30
                    );

            avl.root =
                    avl.insert(
                            avl.root,
                            20
                    );

            avl.root =
                    avl.insert(
                            avl.root,
                            40
                    );

            WarehouseDashboard
                    .getInstance()
                    .initialize();

            StringUtilities.palindrome(
                    "madam"
            );

            StringUtilities.wordCount(
                    "Smart Warehouse System"
            );

            DateTimeModule.currentDate();

            DateTimeModule.currentTime();

            DequeModule.execute();

        }
        catch (Exception e) {

            // No output
        }

        monitor.stop();
    }
}
