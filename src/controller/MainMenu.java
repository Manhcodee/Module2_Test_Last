package controller;

import model.*;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private static PhoneManager phoneManager = new PhoneManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addPhone();
                    break;
                case 2:
                    deletePhone();
                    break;
                case 3:
                    phoneManager.displayAllPhones();
                    break;
                case 4:
                    searchPhone();
                    break;
                case 5:
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình. Tạm biệt!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("-- CHƯƠNG TRÌNH QUẢN LÝ ĐIỆN THOẠI --");
        System.out.println("1. Thêm mới điện thoại");
        System.out.println("2. Xóa điện thoại");
        System.out.println("3. Xem danh sách điện thoại");
        System.out.println("4. Tìm kiếm điện thoại");
        System.out.println("5. Thoát");
        System.out.print("Chọn chức năng: ");
    }

    private static void addPhone() {
        System.out.print("Nhập ID: ");
        String id = scanner.nextLine();
        System.out.print("Nhập tên điện thoại: ");
        String name = scanner.nextLine();
        System.out.print("Nhập giá bán: ");
        double price = scanner.nextDouble();
        System.out.print("Nhập số lượng: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();  // consume newline
        System.out.print("Nhập nhà sản xuất: ");
        String manufacturer = scanner.nextLine();

        System.out.print("Chọn loại điện thoại (1. Chính hãng, 2. Xách tay): ");
        int phoneType = scanner.nextInt();
        scanner.nextLine();  // consume newline

        if (phoneType == 1) {
            System.out.print("Nhập thời gian bảo hành (tháng): ");
            int warrantyTime = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nhập phạm vi bảo hành (Trong nước/Quốc tế): ");
            String warrantyScope = scanner.nextLine();
            phoneManager.addPhone(new OfficialPhone(id, name, price, quantity, manufacturer, warrantyTime, warrantyScope));
        } else if (phoneType == 2) {
            System.out.print("Nhập quốc gia xách tay: ");
            String country = scanner.nextLine();
            System.out.print("Nhập trạng thái (Mới/Đã qua sử dụng): ");
            String status = scanner.nextLine();
            phoneManager.addPhone(new ImportedPhone(id, name, price, quantity, manufacturer, country, status));
        }
    }

    private static void deletePhone() {
        System.out.print("Nhập ID điện thoại cần xóa: ");
        String id = scanner.nextLine();
        Phone phone = phoneManager.findPhoneById(id);
        if (phone != null) {
            System.out.println("Bạn có chắc chắn muốn xóa điện thoại này không? (y/n)");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                boolean success = phoneManager.deletePhone(id);
                if (success) {
                    System.out.println("Điện thoại đã được xóa.");
                } else {
                    System.out.println("Không thể xóa điện thoại.");
                }
            }
        } else {
            System.out.println("Điện thoại không tìm thấy.");
        }
    }

    private static void searchPhone() {
        System.out.print("Nhập tên điện thoại để tìm kiếm: ");
        String name = scanner.nextLine();
        List<Phone> result = phoneManager.searchPhoneByName(name);
        if (result.isEmpty()) {
            System.out.println("Không tìm thấy điện thoại với tên: " + name);
        } else {
            for (Phone phone : result) {
                phone.displayInfo();
                System.out.println("------------------------------------------------");
            }
        }
    }
}
