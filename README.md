# Gaji In

v2.0.0-alpha

---

Hi there, project ini merupakan project pribadi yang berkelanjutan sekalian menyelesaikan tugas yang di berikan pada mata kuliah PBO


![image](https://github.com/user-attachments/assets/6badf8da-4889-4329-87a3-a77fd00eff7b)

![Screenshot 2024-12-14 025700](https://github.com/user-attachments/assets/68590af0-e195-4719-8fab-0f177240d19e)


---

# **Sistem Gaji Karyawan**

## **Deskripsi Proyek**
Sistem Gaji Karyawan adalah aplikasi berbasis Java yang dirancang untuk membantu perusahaan dalam menghitung gaji karyawan secara otomatis berdasarkan jam kerja, tingkat jabatan, dan pajak. Proyek ini dibuat sebagai tugas akhir mata kuliah Pemrograman Berorientasi Objek (PBO).

---

## **Fitur Utama**
1. **Tambah Jabatan**: Memungkinkan pengguna menambahkan jabatan dengan nama, tingkat jabatan, dan gaji per jam.
2. **Tambah Pegawai**: Menambahkan karyawan dengan nama, NIP, jabatan, jam kerja, dan persentase pajak.
3. **Hitung Gaji**: Menampilkan daftar karyawan beserta gaji bersih, pajak, dan total gaji seluruh pegawai.
4. **Laporan Pendapatan**: Menghitung sisa pendapatan perusahaan setelah pembayaran gaji karyawan.

---

## **Prinsip Dasar PBO yang Diterapkan**
1. **Abstraction (Abstraksi)**:
   - Menggunakan abstract class `Orang` untuk mendeklarasikan atribut umum (nama, NIP) dan metode abstrak `hitungGaji()`.
   - Fokus pada fungsionalitas utama tanpa memperlihatkan detail implementasi.
   
2. **Encapsulation (Enkapsulasi)**:
   - Atribut dalam class seperti `namaJabatan`, `gajiPerJam`, dan `tingkatJabatan` dibuat private.
   - Menggunakan getter dan setter untuk mengontrol akses ke atribut.
   
3. **Inheritance (Pewarisan)**:
   - Class `Pegawai` mewarisi class `Orang` untuk menambahkan atribut spesifik seperti `jamKerja` dan `pajak`.
   - Membantu mengurangi duplikasi kode.

4. **Polymorphism (Polimorfisme)**:
   - Class `Pegawai` mengimplementasikan metode abstrak `hitungGaji()` dari class `Orang`.
   - Memungkinkan metode yang sama memiliki implementasi yang berbeda.

---

## **Struktur Relasi Tabel**
1. **Tabel `Jabatan`**:
   - Menyimpan informasi tentang jabatan seperti nama jabatan, gaji per jam, dan tingkat jabatan.
   - **Primary Key**: `id_jabatan`

2. **Tabel `Pegawai`**:
   - Menyimpan data pegawai seperti nama, NIP, jam kerja, pajak, dan id jabatan.
   - **Primary Key**: `id_pegawai`
   - **Foreign Key**: `id_jabatan`

**Relasi**: 
- **One-to-Many**: Satu jabatan dapat dimiliki oleh banyak pegawai.

---

## **Alur Kerja Program**
1. Pengguna memasukkan data perusahaan.
2. Menambahkan jabatan dan karyawan sesuai struktur organisasi.
3. Program menghitung gaji bersih setiap karyawan berdasarkan:
   - Jam kerja.
   - Tingkat jabatan.
   - Pajak yang berlaku.
4. Menampilkan laporan total gaji dan sisa pendapatan perusahaan.

---

## **Cuplikan Kode**
### **Abstraction pada Class `Orang`**
```java
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
```

link bloger 
https://278hafidzpanjiasharii.blogspot.com/2024/12/eas.html
