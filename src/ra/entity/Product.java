package ra.entity;

import ra.run.ShopManagement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Product {
    private String productId;
    private String productName;
    private float price;
    private String description;
    private Date created;
    private int catalogId;
    private int productStatus;

    public Product() {
    }

    public Product(String productId, String productName, float price, String description, Date created, int catalogId, int productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.created = created;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public void inputData(Scanner scanner) {
        this.productId = inputProductId(scanner);
        this.productName = inputProductName(scanner);
        this.price = inputPrice(scanner);
        this.description = inputDescription(scanner);
        this.created = inputCreated(scanner);
        this.catalogId = inputCatalogId(scanner);
        this.productStatus = inputProductStatus(scanner);
    }

    public String inputProductId(Scanner scanner) {
        String productIdRegex = "[CSA]\\w{3}";
        System.out.println("Nhập vào mã danh mục:");
        do {
            String productId = scanner.nextLine();
            if (Pattern.matches(productIdRegex, productId)) {
                boolean isExist = false;
                for (int i = 0; i < ShopManagement.indexProduct; i++) {
                    if (ShopManagement.arrProducts[i].getProductId().equals(productId)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Mã sản phẩm đã tồn tại, vui lòng nhập lại");
                } else {
                    return productId;
                }
            } else {
                System.err.println("Mã sản phẩm gồm 4 ký tự bắt đầu là A|S|C, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputProductName(Scanner scanner) {
        System.out.println("Nhập vào tên sản phẩm:");
        do {
            String productName = scanner.nextLine();
            if (productName.length() >= 10 && productName.length() <= 50) {
                boolean isExist = false;
                for (int i = 0; i < ShopManagement.indexProduct; i++) {
                    if (ShopManagement.arrProducts[i].getProductName().equals(productName)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Tên danh mục đã tồn tại, vui lòng nhập lại");
                } else {
                    return productName;
                }
            } else {
                System.err.println("Tên danh mục có từ 10-50 ký tự, vui lòng nhập lại");
            }
        } while (true);
    }

    public float inputPrice(Scanner scanner) {
        System.out.println("Nhập vào giá sản phẩm:");
        do {
            float price = Float.parseFloat(scanner.nextLine());
            if (price > 0) {
                return price;
            } else {
                System.err.println("Giá sản phẩm lớn hơn 0, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputDescription(Scanner scanner) {
        System.out.println("Nhập vào mô tả sản phẩm:");
        return scanner.nextLine();
    }

    public Date inputCreated(Scanner scanner) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Nhập vào ngày nhập sản phẩm:");
        do {
            try {
                Date created = sdf.parse(scanner.nextLine());
                return created;
            } catch (Exception ex) {
                System.err.println("Định dạng ngày nhập là dd/MM/yyyy, vui lòng nhập lại");
            }
        } while (true);
    }

    public int inputCatalogId(Scanner scanner) {
        System.out.println("Chọn danh mục của sản phẩm:");
        do {
            for (int i = 0; i < ShopManagement.indexCatalog; i++) {
                System.out.printf("%d. %s\n", (i + 1), ShopManagement.arrCategories[i].getCatalogName());
            }
            System.out.print("Chọn danh mục:");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice > 0 && choice <= ShopManagement.indexCatalog) {
                return ShopManagement.arrCategories[choice - 1].getCatalogId();
            } else {
                System.err.println("Bạn chưa chọn đúng danh mục, vui lòng chọn lại");
            }
        } while (true);
    }

    public int inputProductStatus(Scanner scanner) {
        System.out.println("Chọn trạng thái sản phẩm:");
        do {
            System.out.println("1. Đang bán");
            System.out.println("2. Hết hàng");
            System.out.println("3. Không bán");
            System.out.print("Lựa chọn trạng thái:");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice > 0 && choice < 4) {
                return choice - 1;
            } else {
                System.err.println("Bạn chọn chưa đúng trạng thái sản phẩm, vui lòng nhập lại");
            }
        } while (true);
    }

    public void displayData() {
        System.out.printf("Mã SP: %s - Tên SP: %s - Giá: %.1f - Ngày nhập: %s\n",
                this.productId, this.productName, this.price, this.created);
        System.out.printf("Mô tả: %s - Trạng thái: %s\n",
                this.description, (this.productStatus == 0) ? "Đang bán" : ((this.productStatus == 1) ? "Hết hàng" : "Không bán"));
    }
}
