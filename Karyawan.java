public class Karyawan {
    private String nama;
    private String nip;
    private int jamKerja;
    private static final double GAJI_PER_JAM = 30000;
    private static final double BPJS_RATE = 0.01;
    private static final double TAPERA_RATE = 0.02;

    public Karyawan(String nama, String nip, int jamKerja) {
        this.nama = nama;
        this.nip = nip;
        this.jamKerja = jamKerja;
    }

    public double hitungGajiKotor() {
        return jamKerja * GAJI_PER_JAM;
    }

    public double hitungPajakBPJS() {
        return hitungGajiKotor() * BPJS_RATE;
    }

    public double hitungPajakTAPERA() {
        return hitungGajiKotor() * TAPERA_RATE;
    }

    public double hitungGajiBersih() {
        return hitungGajiKotor() - (hitungPajakBPJS() + hitungPajakTAPERA());
    }

    @Override
    public String toString() {
        return "Nama: " + nama +
                "\nNIP: " + nip +
                "\nJam Kerja: " + jamKerja +
                "\nGaji Kotor: Rp. " + hitungGajiKotor() +
                "\nBPJS: Rp. " + hitungPajakBPJS() +
                "\nTAPERA: Rp. " + hitungPajakTAPERA() +
                "\nGaji Bersih: Rp. " + hitungGajiBersih();
    }
}
