package src.com.datarun;

import java.io.*;
import java.util.*;

public class Database {
    private String fileName = "MainDatabase.txt";

    public void tampilkanData() throws IOException {
        System.out.println("\t                    Data Kendaraan");
        System.out.println("==================================================================");
        try (BufferedReader bufferInput = new BufferedReader(new FileReader(fileName))) {
            System.out.println("| ID  | Merk       | Tipe       | Plat Nomor   | Pengguna     |");
            System.out.println("------------------------------------------------------------------");

            String data;
            while ((data = bufferInput.readLine()) != null) {
                StringTokenizer stringToken = new StringTokenizer(data, ",");
                System.out.printf("| %-3s ", stringToken.nextToken());
                System.out.printf("| %-10s ", stringToken.nextToken());
                System.out.printf("| %-10s ", stringToken.nextToken());
                System.out.printf("| %-12s ", stringToken.nextToken());
                System.out.printf("| %-10s   |", stringToken.nextToken());
                System.out.println();
            }
            System.out.println("------------------------------------------------------------------");
        } catch (FileNotFoundException e) {
            System.err.println("Database tidak ditemukan, silahkan tambah data terlebih dahulu.");
        }
    }

    public void tambahData() throws IOException {
        try (BufferedWriter bufferedOutput = new BufferedWriter(new FileWriter(fileName, true))) {
            Scanner input = new Scanner(System.in);
            System.out.println();
            System.out.print("Masukkan Merk Kendaraan: ");
            String merkKendaraan = input.nextLine();
            System.out.println();
            System.out.print("Masukkan Tipe Kendaraan: ");
            String tipeKendaraan = input.nextLine();
            System.out.println();
            System.out.print("Masukkan Plat Nomor: ");
            String platNomor = input.nextLine();
            System.out.println();
            System.out.print("Masukkan Nama Pengguna: ");
            String pengguna = input.nextLine();
            System.out.println();

            int idKendaraan = getLastID() + 1;
            String idKendaraanStr = String.valueOf(idKendaraan);

            bufferedOutput.write(idKendaraanStr + "," + merkKendaraan + "," + tipeKendaraan + "," + platNomor + "," + pengguna);
            bufferedOutput.newLine();
        }
    }

    private int getLastID() throws IOException {
        int lastID = 0;
        try (BufferedReader bufferInput = new BufferedReader(new FileReader(fileName))) {
            String data;
            while ((data = bufferInput.readLine()) != null) {
                StringTokenizer stringToken = new StringTokenizer(data, ",");
                String id = stringToken.nextToken();
                int currentID = Integer.parseInt(id);
                lastID = Math.max(lastID, currentID);
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR!");
        }
        return lastID;
    }

    public void cariData() throws IOException {
        try (BufferedReader bufferInput = new BufferedReader(new FileReader(fileName))) {
            Scanner input = new Scanner(System.in);
            System.out.print("Masukkan ID Kendaraan: ");
            String idKendaraan = input.nextLine();

            String data;
            boolean found = false;
            while ((data = bufferInput.readLine()) != null) {
                StringTokenizer stringToken = new StringTokenizer(data, ",");
                String currentID = stringToken.nextToken();

                if (currentID.equals(idKendaraan)) {
                    System.out.println();
                    System.out.println("Data Kendaraan Ditemukan:");
                    System.out.println("ID Kendaraan: " + currentID);
                    System.out.println("Merk Kendaraan: " + stringToken.nextToken());
                    System.out.println("Tipe Kendaraan: " + stringToken.nextToken());
                    System.out.println("Plat Nomor: " + stringToken.nextToken());
                    System.out.println("Nama Pengguna: " + stringToken.nextToken());
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Data dengan ID " + idKendaraan + " tidak ditemukan!");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Database tidak ditemukan, silahkan tambah data terlebih dahulu.");
        }
    }

    public void hapusData() throws IOException {
        File tempFile = new File("MainDatabase.tmp");
        try (BufferedReader bufferInput = new BufferedReader(new FileReader(fileName));
             BufferedWriter bufferOutput = new BufferedWriter(new FileWriter(tempFile))) {

            Scanner input = new Scanner(System.in);
            System.out.print("Masukkan ID Kendaraan yang ingin dihapus: ");
            String idKendaraan = input.nextLine();

            String data;
            boolean found = false;
            while ((data = bufferInput.readLine()) != null) {
                StringTokenizer stringToken = new StringTokenizer(data, ",");
                String currentID = stringToken.nextToken();

                if (!currentID.equals(idKendaraan)) {
                    bufferOutput.write(data);
                    bufferOutput.newLine();
                } else {
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Data tidak ditemukan!");
            } else {
                System.out.println("Data berhasil dihapus!");
            }
        }

        File oldFile = new File(fileName);
       if (oldFile.delete()) {
            urutkanID(tempFile);
            tempFile.renameTo(oldFile);
        }
    }

    private void urutkanID(File file) throws IOException {
        File tempFileRenamed = new File("MainDatabaseRenamed.tmp");
        try (BufferedReader bufferInput = new BufferedReader(new FileReader(file));
            BufferedWriter bufferOutput = new BufferedWriter(new FileWriter(tempFileRenamed))) {

            String data;
            int idBaru = 1;

            while ((data = bufferInput.readLine()) != null) {
                StringTokenizer stringToken = new StringTokenizer(data, ",");
                String idLama = stringToken.nextToken();
                String merkKendaraan = stringToken.nextToken();
                String tipeKendaraan = stringToken.nextToken();
                String platNomor = stringToken.nextToken();
                String pengguna = stringToken.nextToken();

                bufferOutput.write(idBaru + "," + merkKendaraan + "," + tipeKendaraan + "," + platNomor + "," + pengguna);
                bufferOutput.newLine();
                idBaru++;
                }
            }

                file.delete();
                tempFileRenamed.renameTo(file);
    }
}