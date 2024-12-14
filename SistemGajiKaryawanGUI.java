import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

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

    public Jabatan(String namaJabatan, double gajiPerJam) {
        this.namaJabatan = namaJabatan;
        this.gajiPerJam = gajiPerJam;
    }

    public String getNamaJabatan() {
        return namaJabatan;
    }

    public double getGajiPerJam() {
        return gajiPerJam;
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

// Main GUI Application
public class SistemGajiKaryawanGUI {
    private JFrame frame;
    private JTextArea txtTerminal;
    private ArrayList<Jabatan> daftarJabatan = new ArrayList<>();
    private ArrayList<Pegawai> daftarPegawai = new ArrayList<>();
    private double pendapatanPerusahaan;

    public SistemGajiKaryawanGUI() {
        frame = new JFrame("Sistem Gaji Karyawan");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Top Panel with Image
        JPanel panelTop = new JPanel(new BorderLayout());
        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon("src/logo3.png"); // Update this with the actual path to your image
        imageLabel.setIcon(icon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panelTop.add(imageLabel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel panelButtons = new JPanel(new GridLayout(2, 3, 10, 10)); // 2 rows, 3 columns
        JButton btnSetPendapatan = new JButton("Set Pendapatan Perusahaan");
        JButton btnTambahJabatan = new JButton("Tambah Jabatan");
        JButton btnTambahPegawai = new JButton("Tambah Pegawai");
        JButton btnLihatGaji = new JButton("Lihat Gaji");
        JButton btnClearOutput = new JButton("Clear Output");
        JButton btnResetData = new JButton("Reset Data");

        btnSetPendapatan.addActionListener(e -> setPendapatanPerusahaan());
        btnTambahJabatan.addActionListener(e -> tambahJabatan());
        btnTambahPegawai.addActionListener(e -> tambahPegawai());
        btnLihatGaji.addActionListener(e -> lihatGaji());
        btnClearOutput.addActionListener(e -> clearOutput());
        btnResetData.addActionListener(e -> resetData());

        panelButtons.add(btnSetPendapatan);
        panelButtons.add(btnTambahJabatan);
        panelButtons.add(btnTambahPegawai);
        panelButtons.add(btnLihatGaji);
        panelButtons.add(btnClearOutput);
        panelButtons.add(btnResetData);

        // Terminal Output Area
        txtTerminal = new JTextArea(15, 50);
        txtTerminal.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtTerminal);

        frame.add(panelTop, BorderLayout.NORTH);
        frame.add(panelButtons, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void setPendapatanPerusahaan() {
        JTextField txtPendapatan = new JTextField();

        Object[] message = {
            "Pendapatan Perusahaan:", txtPendapatan
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Set Pendapatan Perusahaan", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                pendapatanPerusahaan = Double.parseDouble(txtPendapatan.getText());
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
                txtTerminal.append("Pendapatan perusahaan berhasil diset: " + currencyFormat.format(pendapatanPerusahaan) + "\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Masukkan data yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void tambahJabatan() {
        JTextField txtNamaJabatan = new JTextField();
        JTextField txtGajiPerJam = new JTextField();

        Object[] message = {
            "Nama Jabatan:", txtNamaJabatan,
            "Gaji Per Jam:", txtGajiPerJam
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Tambah Jabatan", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String namaJabatan = txtNamaJabatan.getText();
                double gajiPerJam = Double.parseDouble(txtGajiPerJam.getText());

                daftarJabatan.add(new Jabatan(namaJabatan, gajiPerJam));
                txtTerminal.append("Jabatan berhasil ditambahkan: " + namaJabatan + "\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Masukkan data yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void tambahPegawai() {
        if (daftarJabatan.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Tambahkan jabatan terlebih dahulu!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTextField txtNamaPegawai = new JTextField();
        JTextField txtNIPPegawai = new JTextField();
        JTextField txtJamKerja = new JTextField();
        JComboBox<String> comboJabatan = new JComboBox<>();
        JTextField txtPajak = new JTextField();

        for (Jabatan jabatan : daftarJabatan) {
            comboJabatan.addItem(jabatan.getNamaJabatan());
        }

        Object[] message = {
            "Nama Pegawai:", txtNamaPegawai,
            "NIP Pegawai:", txtNIPPegawai,
            "Jam Kerja:", txtJamKerja,
            "Pilih Jabatan:", comboJabatan,
            "Pajak (dalam persen):", txtPajak
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Tambah Pegawai", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String namaPegawai = txtNamaPegawai.getText();
                String nip = txtNIPPegawai.getText();
                int jamKerja = Integer.parseInt(txtJamKerja.getText());
                double pajak = Double.parseDouble(txtPajak.getText()) / 100;

                int selectedIndex = comboJabatan.getSelectedIndex();
                Jabatan jabatanDipilih = daftarJabatan.get(selectedIndex);

                Pegawai pegawai = new Pegawai(namaPegawai, nip, jabatanDipilih, jamKerja, pajak);
                daftarPegawai.add(pegawai);

                txtTerminal.append("Pegawai berhasil ditambahkan: " + namaPegawai + " dengan jabatan " + jabatanDipilih.getNamaJabatan() + "\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Masukkan data yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void lihatGaji() {
        if (daftarPegawai.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Belum ada pegawai yang ditambahkan.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        txtTerminal.append("\nDaftar Gaji Pegawai:\n");

        double totalGaji = 0.0;
        for (Pegawai pegawai : daftarPegawai) {
            double gaji = pegawai.hitungGaji();
            totalGaji += gaji;
            txtTerminal.append("Nama: " + pegawai.getNama() + "\n");
            txtTerminal.append("NIP: " + pegawai.getNip() + "\n");
            txtTerminal.append("Jabatan: " + pegawai.getJabatan().getNamaJabatan() + "\n");
            txtTerminal.append("Jam Kerja: " + pegawai.getJamKerja() + "\n");
            txtTerminal.append("Gaji: " + currencyFormat.format(gaji) + "\n");
            txtTerminal.append("----------------------\n");
        }

        double sisaPendapatan = pendapatanPerusahaan - totalGaji;
        txtTerminal.append("\nTotal Gaji Semua Pegawai: " + currencyFormat.format(totalGaji) + "\n");
        txtTerminal.append("Sisa Pendapatan Perusahaan: " + currencyFormat.format(sisaPendapatan) + "\n");
    }

    private void clearOutput() {
        txtTerminal.setText("");
    }

    private void resetData() {
        daftarJabatan.clear();
        daftarPegawai.clear();
        txtTerminal.append("Data pegawai dan jabatan berhasil direset.\n");
    }

    public static void main(String[] args) {
        new SistemGajiKaryawanGUI();
    }
}
