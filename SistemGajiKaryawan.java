import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

// Abstraction: Abstract class Orang
abstract class Orang {
    private String nama;
    private String nip;

    public Orang(String nama, String nip) {
        this.nama = nama;
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public String getNip() {
        return nip;
    }

    public abstract double hitungGaji();
}

// Encapsulation: Class Jabatan
class Jabatan {
    private String namaJabatan;
    private double gajiPerJam;
    private int tingkatJabatan;

    public Jabatan(String namaJabatan, double gajiPerJam, int tingkatJabatan) {
        this.namaJabatan = namaJabatan;
        this.gajiPerJam = gajiPerJam;
        this.tingkatJabatan = tingkatJabatan;
    }

    public String getNamaJabatan() {
        return namaJabatan;
    }

    public double getGajiPerJam() {
        return gajiPerJam;
    }

    public int getTingkatJabatan() {
        return tingkatJabatan;
    }
}

// Inheritance: Class Pegawai extends Orang
class Pegawai extends Orang {
    private Jabatan jabatan;
    private int jamKerja;
    private double pajak;

    public Pegawai(String nama, String nip, Jabatan jabatan, int jamKerja, double pajak) {
        super(nama, nip);
        this.jabatan = jabatan;
        this.jamKerja = jamKerja;
        this.pajak = pajak;
    }

    public Jabatan getJabatan() {
        return jabatan;
    }

    public int getJamKerja() {
        return jamKerja;
    }

    public double hitungNominalPajak() {
        double gajiKotor = jamKerja * jabatan.getGajiPerJam();
        return gajiKotor * pajak;
    }

    @Override
    public double hitungGaji() {
        double gajiKotor = jamKerja * jabatan.getGajiPerJam();
        return gajiKotor - hitungNominalPajak();
    }
}

// Main Program
public class SistemGajiKaryawan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Jabatan> daftarJabatan = new ArrayList<>();
        ArrayList<Pegawai> daftarPegawai = new ArrayList<>();

        System.out.println("Hallo Bos, Waktunya Gajian!");

        System.out.print("Masukkan nama perusahaan: ");
        String namaPerusahaan = scanner.nextLine();

        System.out.print("Masukkan pendapatan perusahaan dalam satu bulan: ");
        double pendapatanPerusahaan = scanner.nextDouble();
        scanner.nextLine();

        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Tambah Jabatan");
            System.out.println("2. Tambah Pegawai");
            System.out.println("3. Lihat dan Hitung Gaji Pegawai");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama jabatan: ");
                    String namaJabatan = scanner.nextLine();

                    System.out.print("Masukkan gaji per jam: ");
                    double gajiPerJam = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Masukkan tingkat jabatan (1-10): ");
                    int tingkatJabatan = scanner.nextInt();
                    scanner.nextLine();

                    if (tingkatJabatan < 1 || tingkatJabatan > 10) {
                        System.out.println("Tingkat jabatan tidak valid. Masukkan angka antara 1 hingga 10.");
                        break;
                    }

                    daftarJabatan.add(new Jabatan(namaJabatan, gajiPerJam, tingkatJabatan));
                    System.out.println("Jabatan berhasil ditambahkan.");
                    break;

                case 2:
                    if (daftarJabatan.isEmpty()) {
                        System.out.println("Belum ada jabatan yang ditambahkan. Tambahkan jabatan terlebih dahulu.");
                        break;
                    }

                    System.out.print("Masukkan nama pegawai: ");
                    String namaPegawai = scanner.nextLine();

                    System.out.print("Masukkan NIP pegawai: ");
                    String nip = scanner.nextLine();

                    System.out.println("Pilih jabatan:");
                    for (int i = 0; i < daftarJabatan.size(); i++) {
                        Jabatan jabatan = daftarJabatan.get(i);
                        System.out.println((i + 1) + ". " + jabatan.getNamaJabatan() + " (Tingkat " + jabatan.getTingkatJabatan() + ")");
                    }

                    System.out.print("Pilih nomor jabatan: ");
                    int nomorJabatan = scanner.nextInt();
                    scanner.nextLine();

                    if (nomorJabatan < 1 || nomorJabatan > daftarJabatan.size()) {
                        System.out.println("Jabatan tidak valid.");
                        break;
                    }

                    Jabatan jabatanDipilih = daftarJabatan.get(nomorJabatan - 1);

                    System.out.print("Masukkan jam kerja: ");
                    int jamKerja = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Pilih pajak:");
                    System.out.println("1. Tidak Ada Pajak");
                    System.out.println("2. BPJS 1%");
                    System.out.println("3. TAPERA 2%");
                    System.out.println("4. Pajak Custom");
                    System.out.print("Pilih nomor pajak: ");
                    int pilihanPajak = scanner.nextInt();
                    scanner.nextLine();

                    double pajak = 0.0;
                    switch (pilihanPajak) {
                        case 1:
                            pajak = 0.0;
                            break;
                        case 2:
                            pajak = 0.01;
                            break;
                        case 3:
                            pajak = 0.02;
                            break;
                        case 4:
                            System.out.print("Masukkan besar pajak (dalam persen): ");
                            double customPajak = scanner.nextDouble();
                            scanner.nextLine();
                            pajak = customPajak / 100;
                            break;
                        default:
                            System.out.println("Pilihan pajak tidak valid. Menggunakan default: Tidak Ada Pajak.");
                    }

                    daftarPegawai.add(new Pegawai(namaPegawai, nip, jabatanDipilih, jamKerja, pajak));
                    System.out.println("Pegawai berhasil ditambahkan.");
                    break;

                case 3:
                    if (daftarPegawai.isEmpty()) {
                        System.out.println("Belum ada pegawai yang ditambahkan.");
                        break;
                    }

                    double totalGaji = 0.0;
                    System.out.println("\nDaftar Pegawai dan Gaji:");
                    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
                    for (Pegawai pegawai : daftarPegawai) {
                        double gaji = pegawai.hitungGaji();
                        double nominalPajak = pegawai.hitungNominalPajak();
                        totalGaji += gaji;
                        System.out.println("Nama: " + pegawai.getNama());
                        System.out.println("NIP: " + pegawai.getNip());
                        System.out.println("Jabatan: " + pegawai.getJabatan().getNamaJabatan() + " (Tingkat " + pegawai.getJabatan().getTingkatJabatan() + ")");
                        System.out.println("Jam Kerja: " + pegawai.getJamKerja());
                        System.out.println("Gaji: " + currencyFormat.format(gaji));
                        System.out.println("Nominal Pajak: " + currencyFormat.format(nominalPajak));
                        System.out.println("----------------------");
                    }

                    double sisaPendapatan = pendapatanPerusahaan - totalGaji;
                    System.out.println("Total Gaji Semua Pegawai: " + currencyFormat.format(totalGaji));
                    System.out.println("Sisa Pendapatan Perusahaan: " + currencyFormat.format(sisaPendapatan));
                    break;

                case 4:
                    running = false;
                    System.out.println("Terima kasih telah menggunakan sistem gaji karyawan.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }

        scanner.close();
    }
}
