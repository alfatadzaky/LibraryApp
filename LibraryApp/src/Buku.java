class Buku {
    String judul;
    int tahunTerbit;
    String kategori;

    public Buku(String judul, int tahunTerbit, String kategori) {
        this.judul = judul;
        this.tahunTerbit = tahunTerbit;
        this.kategori = kategori;
    }

    @Override
    public String toString() {
        return judul + " | " + tahunTerbit + " | " + kategori;
    }
}