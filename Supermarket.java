import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

// Kelas Barang
class Barang {
    protected String kodeBarang;
    protected String namaBarang;
    protected int hargaBarang;

    public Barang(String kodeBarang, String namaBarang, int hargaBarang) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
    }
}

// Kelas Transaksi yang Menggunakan Inheritance
class Transaksi extends Barang {
    private int jumlahBeli;
    private int total;

    public Transaksi(String kodeBarang, String namaBarang, int hargaBarang, int jumlahBeli) {
        super(kodeBarang, namaBarang, hargaBarang);
        if (jumlahBeli <= 0) {
            throw new IllegalArgumentException("Jumlah beli harus lebih dari 0.");
        }
        this.jumlahBeli = jumlahBeli;
        this.total = hitungTotal();
    }

    private int hitungTotal() {
        return this.hargaBarang * this.jumlahBeli;
    }

    public int getJumlahBeli() {
        return jumlahBeli;
    }

    public int getTotal() {
        return total;
    }
}

// Kelas Main
public class Supermarket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Login system
        boolean isLoginSuccessful = false;
        while (!isLoginSuccessful) {
            System.out.println("+-----------------------------------------------+");
            System.out.print("Username : ");
            String username = scanner.nextLine();

            System.out.print("Password : ");
            String password = scanner.nextLine();

            System.out.print("Captcha (12345) : ");
            String captcha = scanner.nextLine();

            // Simple login validation
            if ("ganteng".equalsIgnoreCase(username) && "098".equals(password) && "12345".equals(captcha)) {
                isLoginSuccessful = true;
                System.out.println("Login berhasil!");
            } else {
                System.out.println("Login gagal, silakan coba lagi.");
            }
        }

        // Display welcome message and current date
        System.out.println("\nSelamat Datang di Supermarket Ganteng");
        Date date = new Date(); // Current date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println("Tanggal dan Waktu : " + dateFormat.format(date));
        System.out.println("+-----------------------------------------------+");

        try {
            // Input data transaksi
            System.out.print("Masukkan No Faktur: ");
            String noFaktur = scanner.nextLine();

            System.out.print("Masukkan Kode Barang: ");
            String kodeBarang = scanner.nextLine();

            System.out.print("Masukkan Nama Barang: ");
            String namaBarang = scanner.nextLine();

            System.out.print("Masukkan Harga Barang: ");
            int hargaBarang = scanner.nextInt();

            System.out.print("Masukkan Jumlah Beli: ");
            int jumlahBeli = scanner.nextInt();

            // Membuat objek transaksi
            Transaksi transaksi = new Transaksi(kodeBarang, namaBarang, hargaBarang, jumlahBeli);

            // Display transaction details
            System.out.println("+-----------------------------------------------+");
            System.out.println("No. Faktur : " + noFaktur);
            System.out.println("Kode Barang : " + transaksi.kodeBarang.toUpperCase());
            System.out.println("Nama Barang : " + transaksi.namaBarang.toUpperCase());
            System.out.println("Harga Barang : Rp" + transaksi.hargaBarang);
            System.out.println("Jumlah Beli : " + transaksi.getJumlahBeli());
            System.out.println("TOTAL : Rp" + transaksi.getTotal());
            System.out.println("+-----------------------------------------------+");

            // Input nama kasir
            scanner.nextLine(); // Consume newline
            System.out.print("Kasir : ");
            String namaKasir = scanner.nextLine();

            System.out.println("Kasir : " + namaKasir.toUpperCase());
            System.out.println("+-----------------------------------------------+");
        } catch (IllegalArgumentException e) {
            System.err.println("Kesalahan: " + e.getMessage());
        } catch (java.util.InputMismatchException e) {
            System.err.println("Kesalahan: Input harus berupa angka yang valid.");
        } catch (Exception e) {
            System.err.println("Terjadi kesalahan: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
