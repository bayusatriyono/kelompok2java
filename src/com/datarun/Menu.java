package src.com.datarun;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private Database database;

    public Menu(Database database) {
        this.database = database;
    }
    public void displayMenu() throws IOException, InterruptedException  {
        Scanner input = new Scanner(System.in);
        boolean ulang = true;

        while (ulang) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            ClearScreen.clearScreen();
            System.out.println("===============================================");
            System.out.println("\t Selamat Datang Di Aplikasi");
            System.out.println("\t Management Kendaraan Dinas ");
            System.out.println("\t    PT. NUSA BUANA ABADI");
            System.out.println("===============================================");
            System.out.println("1. Tampilkan Data Kendaraan");
            System.out.println("2. Tambah Data Kendaraan");
            System.out.println("3. Cari Data Kendaraan");
            System.out.println("4. Hapus Data Kendaraan");
            System.out.println("5. Keluar");
            System.out.print("Masukkan Pilihan: ");
            String pilihan = input.next();

            switch (pilihan) {
                case "1":
                    database.tampilkanData();
                    break;
                case "2":
                    database.tambahData();
                    break;
                case "3":
                    database.cariData();
                    break;
                case "4":
                    database.hapusData();
                    break;
                case "5":
                    System.out.println("Terima kasih telah menggunakan aplikasi ini!");
                    return;
                default:
                    System.err.println("Pilihan tidak ditemukan!");
                    break;
            }
            System.out.println();
            System.out.print("Apakah Anda Ingin Melanjutkan? (y/n): ");
            ulang = input.next().equalsIgnoreCase("y");
            System.out.println();
        }
    }
}