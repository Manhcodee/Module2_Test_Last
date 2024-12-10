package util;

import model.Mobile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {
    private static final String FILE_PATH = "data/mobiles.csv";

    private static void ensureDirectoryExists() {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public static List<Mobile> readMobiles() {
        List<Mobile> mobiles = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                try {
                    int id = Integer.parseInt(parts[0]); // id là số
                    String name = parts[1];  // name là chuỗi
                    double price = Double.parseDouble(parts[2]); // price là số
                    int quantity = Integer.parseInt(parts[3]); // quantity là số
                    String manufacturer = parts[4]; // manufacturer là chuỗi

                    // Kiểm tra số trường để phân biệt AuthenticMobile và HandedMobile
                    if (parts.length == 7) {
                        // Nếu các trường 6 và 7 là số, chúng ta coi đây là AuthenticMobile
                        try {
                            int warrantyTime = Integer.parseInt(parts[5]);  // warrantyTime là số
                            String warrantyRange = parts[6]; // warrantyRange là chuỗi
                            mobiles.add(new model.AuthenticMobile(id, name, price, quantity, manufacturer, warrantyTime, warrantyRange));
                        } catch (NumberFormatException e) {
                            // Nếu không phải số ở trường warrantyTime, thì nó là HandedMobile
                            String importedCountry = parts[5]; // importedCountry là chuỗi
                            String status = parts[6]; // status là chuỗi
                            mobiles.add(new model.HandedMobile(id, name, price, quantity, manufacturer, importedCountry, status));
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Bỏ qua dòng có lỗi định dạng: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return mobiles;
    }

    public static void writeMobile(Mobile mobile) {
        ensureDirectoryExists();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(mobile.toCSV());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Khong tim thay file: " + e.getMessage());
        }
    }

    public static void overwriteMobiles(List<Mobile> mobiles) {
        ensureDirectoryExists();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Mobile mobile : mobiles) {
                bw.write(mobile.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Khong tim thay file: " + e.getMessage());
        }
    }
}