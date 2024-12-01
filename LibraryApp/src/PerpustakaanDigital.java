import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PerpustakaanDigital {
    public static void main(String[] args) {
        String csvFile = "data/Rak 1.csv";
        String line;
        String delimiter = ",";
        List<Buku> bukuList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Membaca file baris demi baris
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                if (values.length == 3) {
                    bukuList.add(new Buku(values[0], Integer.parseInt(values[1]), values[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n==== MENU ====");
            System.out.println("(1) Tampilkan Data Asli");
            System.out.println("(2) Urutkan Data Berdasarkan Judul (Selection Sort)");
            System.out.println("(3) Urutkan Data Berdasarkan Tahun Terbit (Insertion Sort)");
            System.out.println("(41) Cari Buku Berdasarkan Judul");
            System.out.println("(42) Cari Buku Berdasarkan Tahun Terbit");
            System.out.println("(0) Keluar");
            System.out.print("Masukkan pilihan: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (pilihan) {
                case 1:
                    System.out.println("\n======== DATA ASLI =======================");
                    printList(bukuList);
                    break;

                case 2:
                    selectionSortByJudul(bukuList);
                    System.out.println("\n======== DATA DIURUTKAN BERDASARKAN JUDUL (Selection Sort) =======================");
                    printList(bukuList);
                    break;

                case 3:
                    insertionSortByTahun(bukuList);
                    System.out.println("\n======== DATA DIURUTKAN BERDASARKAN TAHUN TERBIT (Insertion Sort) =======================");
                    printList(bukuList);
                    break;

                case 41:
                    System.out.print("Masukkan judul buku yang ingin dicari: ");
                    String judul = scanner.nextLine();
                    cariBukuByJudul(bukuList, judul);
                    break;

                case 42:
                    System.out.print("Masukkan tahun terbit buku yang ingin dicari: ");
                    int tahun = scanner.nextInt();
                    cariBukuByTahun(bukuList, tahun);
                    break;

                case 0:
                    System.out.println("Keluar dari program.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid, coba lagi.");
            }
        } while (pilihan != 0);

        scanner.close();
    }

    // Fungsi untuk mencetak daftar buku
    private static void printList(List<Buku> bukuList) {
        for (Buku buku : bukuList) {
            System.out.println(buku);
        }
    }

    // Selection Sort untuk mengurutkan berdasarkan judul
    private static void selectionSortByJudul(List<Buku> bukuList) {
        int n = bukuList.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (bukuList.get(j).judul.compareToIgnoreCase(bukuList.get(minIndex).judul) < 0) {
                    minIndex = j;
                }
            }
            Buku temp = bukuList.get(minIndex);
            bukuList.set(minIndex, bukuList.get(i));
            bukuList.set(i, temp);
        }
    }

    // Insertion Sort untuk mengurutkan berdasarkan tahun terbit
    private static void insertionSortByTahun(List<Buku> bukuList) {
        int n = bukuList.size();
        for (int i = 1; i < n; i++) {
            Buku key = bukuList.get(i);
            int j = i - 1;

            while (j >= 0 && bukuList.get(j).tahunTerbit > key.tahunTerbit) {
                bukuList.set(j + 1, bukuList.get(j));
                j--;
            }
            bukuList.set(j + 1, key);
        }
    }

    // Cari buku berdasarkan judul
    private static void cariBukuByJudul(List<Buku> bukuList, String judul) {
        boolean found = false;
        for (Buku buku : bukuList) {
            if (buku.judul.equalsIgnoreCase(judul)) {
                System.out.println("Buku ditemukan: " + buku);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Buku tidak ditemukan :(");
        }
    }

    // Cari buku berdasarkan tahun terbit
    private static void cariBukuByTahun(List<Buku> bukuList, int tahun) {
        boolean found = false;
        for (Buku buku : bukuList) {
            if (buku.tahunTerbit == tahun) {
                System.out.println("Buku ditemukan: " + buku);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Tidak ada buku dengan tahun terbit: " + tahun);
        }
    }
}