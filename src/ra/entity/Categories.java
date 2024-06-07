package ra.entity;

import ra.run.ShopManagement;

import java.util.Scanner;

public class Categories {
    private int catalogId;
    private String catalogName;
    private String description;
    private boolean catalogStatus;

    public Categories() {
    }

    public Categories(int catalogId, String catalogName, String description, boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.description = description;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public void inputData(Scanner scanner) {
        this.catalogId = inputCatalogId();
        this.catalogName = inputCatalogName(scanner);
        this.description = inputDescription(scanner);
        this.catalogStatus = inputStatus(scanner);
    }

    public int inputCatalogId() {
        if (ShopManagement.indexCatalog == 0) {
            return 1;
        } else {
            int maxId = ShopManagement.arrCategories[0].getCatalogId();
            for (int i = 1; i < ShopManagement.indexCatalog; i++) {
                if (maxId < ShopManagement.arrCategories[i].getCatalogId()) {
                    maxId = ShopManagement.arrCategories[i].getCatalogId();
                }
            }
            return maxId + 1;
        }
    }

    public String inputCatalogName(Scanner scanner) {
        System.out.println("Nhập vào tên danh mục:");
        do {
            String catalogName = scanner.nextLine();
            if (catalogName.length() <= 50) {
                boolean isExist = false;
                for (int i = 0; i < ShopManagement.indexCatalog; i++) {
                    if (ShopManagement.arrCategories[i].getCatalogName().equals(catalogName)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Tên danh mục đã tồn tại, vui lòng nhập lại");
                } else {
                    return catalogName;
                }
            } else {
                System.err.println("Tên danh mục không được vượt quá 50 ký tự, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputDescription(Scanner scanner) {
        System.out.println("Nhập vào mô tả danh mục:");
        return scanner.nextLine();
    }

    public boolean inputStatus(Scanner scanner) {
        System.out.println("Nhập vào trạng thái danh mục:");
        do {
            String status = scanner.nextLine();
            if (status.equals("true") || status.equals("false")) {
                return Boolean.parseBoolean(status);
            } else {
                System.err.println("Trạng thái chỉ nhận giá trị true | false, vui lòng nhập lại");
            }
        } while (true);
    }

    public void displayData() {
        System.out.printf("Mã DM: %d - Tên DM: %s - Mô tả: %s - Trạng thái: %s\n",
                this.catalogId, this.catalogName, this.description, this.catalogStatus ? "Hoạt động" : "Không hoạt động");
    }
}
