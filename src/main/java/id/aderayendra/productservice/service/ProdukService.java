package id.aderayendra.productservice.service;

import id.aderayendra.productservice.model.Produk;
import id.aderayendra.productservice.repository.ProdukRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdukService {

    private final ProdukRepository produkRepository;

    public List<Produk> getAllProduk() {
        return produkRepository.findAll();
    }

    public Optional<Produk> getProdukById(String id) {
        return produkRepository.findById(id);
    }

    public Produk saveProduk(Produk produk) {
        return produkRepository.save(produk);
    }

    public Produk updateProduk(String id, Produk produkDetails) {
        Produk produk = produkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produk not found with id: " + id));

        produk.setNama(produkDetails.getNama());
        produk.setKategori(produkDetails.getKategori());
        produk.setHarga(produkDetails.getHarga());
        produk.setStok(produkDetails.getStok());

        return produkRepository.save(produk);
    }

    public void deleteProduk(String id) {
        Produk produk = produkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produk not found with id: " + id));
        produkRepository.delete(produk);
    }
}
