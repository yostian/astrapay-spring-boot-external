# 📝 Notes API – Spring Boot

Aplikasi REST API sederhana untuk mengelola catatan (notes) menggunakan Spring Boot.

## 🚀 Cara Menjalankan Aplikasi

1. Clone repository ini:
   git clone https://github.com/username/notes-api.git
   cd notes-api

2. Jalankan aplikasi:
   Jika menggunakan Maven Wrapper:
   ./mvnw spring-boot:run

   Atau jika menggunakan Maven biasa:
   mvn spring-boot:run

3. Akses aplikasi di browser atau Postman:
   http://localhost:8000/notes

   (Ganti port jika berbeda di file application.properties)

## 🔌 Daftar Endpoint

| Method | Endpoint       | Deskripsi                     |
|--------|----------------|-------------------------------|
| GET    | /notes         | Mengambil semua catatan       |
| POST   | /notes         | Menambahkan catatan baru      |
| DELETE | /notes/{id}    | Menghapus catatan berdasarkan ID |

## 📥 Contoh Request

### POST /notes
Request:
{
  "title": "Note 1",
  "content": "Isi Note 1"
}

Response:
{
  "data": {
    "id": 1,
    "title": "Note 1",
    "content": "Isi Note 1"
  },
  "message": "Note created successfully"
}

### DELETE /notes/{id}
Response:
{
  "message": "Note deleted successfully"
}

## ✅ Validasi

- Field `title` dan `content` wajib diisi.
- Jika input tidak valid, response akan berupa 400 Bad Request beserta pesan kesalahan.

## 📚 Swagger (jika tersedia)

Akses dokumentasi API di:
http://localhost:8000/swagger-ui/

## 🛠 Teknologi yang Digunakan

- Java 17
- Spring Boot
- Spring Web
- Lombok
- Maven