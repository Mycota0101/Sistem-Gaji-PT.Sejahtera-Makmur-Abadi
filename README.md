# Gaji In

program akan segera di push ke repo 

---

Hi there, project ini merupakan project pribadi yang berkelanjutan sekalian menyelesaikan tugas yang di berikan pada mata kuliah PBO

![image](https://github.com/user-attachments/assets/6fdf16e4-2be2-4c68-8b40-83d645c1eaac)

![image](https://github.com/user-attachments/assets/6badf8da-4889-4329-87a3-a77fd00eff7b)

---

Sistem Gaji Karyawan

1. Latar Belakang Ide
Masalah yang Diangkat: Banyak perusahaan kesulitan dalam menghitung gaji karyawan yang melibatkan berbagai elemen seperti jam kerja, pajak, dan tingkat jabatan.
Solusi yang Ditawarkan: Sistem berbasis Java untuk menghitung gaji karyawan secara otomatis, efisien, dan fleksibel sesuai dengan struktur perusahaan.
2. Tujuan Proyek
Membuat sistem sederhana yang dapat:
Mengelola data jabatan karyawan.
Menghitung gaji berdasarkan jam kerja dan tingkat jabatan.
Mengakomodasi perhitungan pajak karyawan.
Memberikan laporan pendapatan perusahaan yang tersisa setelah penggajian.
3. Prinsip Dasar PBO yang Diterapkan
a. Abstraction (Abstraksi)
Penerapan:
Dibuat abstract class Orang yang mendeklarasikan atribut umum (nama, NIP) dan metode abstrak hitungGaji() untuk implementasi spesifik pada subclass.
Manfaat: Menyembunyikan detail implementasi untuk fokus pada fungsionalitas inti.
b. Encapsulation (Enkapsulasi)
Penerapan:
Atribut di dalam class, seperti namaJabatan, gajiPerJam, dan tingkatJabatan pada class Jabatan, dibuat private.
Akses ke atribut dilakukan melalui metode getter.
Manfaat: Melindungi data dari modifikasi langsung.
c. Inheritance (Pewarisan)
Penerapan:
Class Pegawai mewarisi class Orang.
Class Pegawai menambahkan atribut spesifik seperti jamKerja dan pajak.
Manfaat: Mengurangi duplikasi kode dan meningkatkan keterbacaan.
d. Polymorphism (Polimorfisme)
Penerapan:
Class Pegawai mengimplementasikan metode abstrak hitungGaji() dari class Orang.
Manfaat: Memungkinkan metode hitungGaji() memiliki implementasi yang berbeda pada subclass.
4. Fitur Utama Sistem
a. Tambah Jabatan
Memungkinkan pengguna menambahkan jabatan dengan nama, tingkat jabatan, dan gaji per jam.
b. Tambah Pegawai
Menambahkan karyawan dengan nama, NIP, jabatan, jam kerja, dan persentase pajak.
c. Hitung Gaji
Menampilkan daftar karyawan beserta gaji bersih, pajak, dan total gaji seluruh pegawai.
d. Laporan Pendapatan
Menghitung sisa pendapatan perusahaan setelah pembayaran gaji karyawan.
5. Alur Kerja Program
Pengguna memasukkan data perusahaan.
Menambahkan jabatan dan karyawan sesuai struktur organisasi.
Program menghitung gaji bersih setiap karyawan berdasarkan:
Jam kerja.
Tingkat jabatan.
Pajak yang berlaku.
Menampilkan laporan total gaji dan sisa pendapatan perusahaan.
