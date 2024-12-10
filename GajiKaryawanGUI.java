import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GajiKaryawanGUI extends JFrame {
    private JTextField tfNama, tfNIP, tfJamKerja, tfPendapatanUsaha;
    private JTextArea taOutput;
    private JButton btnTambah, btnHitung;
    private ArrayList<Karyawan> daftarKaryawan = new ArrayList<>();
    private double pendapatanUsaha;

    public GajiKaryawanGUI() {
        // Frame settings
        setTitle("Sistem Gaji Karyawan");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel atas (form input)
        JPanel panelInput = new JPanel(new GridLayout(5, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createTitledBorder("Data Karyawan"));

        tfNama = new JTextField();
        tfNIP = new JTextField();
        tfJamKerja = new JTextField();
        tfPendapatanUsaha = new JTextField();

        panelInput.add(new JLabel("Nama:"));
        panelInput.add(tfNama);
        panelInput.add(new JLabel("NIP:"));
        panelInput.add(tfNIP);
        panelInput.add(new JLabel("Jam Kerja:"));
        panelInput.add(tfJamKerja);
        panelInput.add(new JLabel("Pendapatan Usaha:"));
        panelInput.add(tfPendapatanUsaha);

        btnTambah = new JButton("Tambah Karyawan");
        btnHitung = new JButton("Hitung Gaji");
        panelInput.add(btnTambah);
        panelInput.add(btnHitung);

        add(panelInput, BorderLayout.NORTH);

        // Panel tengah (output area)
        taOutput = new JTextArea();
        taOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taOutput);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Output"));
        add(scrollPane, BorderLayout.CENTER);

        // Action Listeners
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nama = tfNama.getText();
                    String nip = tfNIP.getText();
                    int jamKerja = Integer.parseInt(tfJamKerja.getText());

                    if (pendapatanUsaha == 0) {
                        pendapatanUsaha = Double.parseDouble(tfPendapatanUsaha.getText());
                    }

                    Karyawan karyawan = new Karyawan(nama, nip, jamKerja);
                    daftarKaryawan.add(karyawan);

                    taOutput.append("Karyawan ditambahkan: \n" + karyawan + "\n\n");
                    clearInputFields();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Masukkan data dengan format yang benar!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnHitung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double totalGaji = 0;
                for (Karyawan karyawan : daftarKaryawan) {
                    totalGaji += karyawan.hitungGajiBersih();
                }
                pendapatanUsaha -= totalGaji;

                taOutput.append("\n=== Rekap Gaji Karyawan ===\n");
                for (Karyawan karyawan : daftarKaryawan) {
                    taOutput.append(karyawan + "\n\n");
                }
                taOutput.append("Sisa Pendapatan Usaha: Rp. " + pendapatanUsaha + "\n");
            }
        });
    }

    private void clearInputFields() {
        tfNama.setText("");
        tfNIP.setText("");
        tfJamKerja.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GajiKaryawanGUI gui = new GajiKaryawanGUI();
            gui.setVisible(true);
        });
    }
}
