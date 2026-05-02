package id.aderayendra.productservice.controller;

import id.aderayendra.productservice.model.Produk;
import id.aderayendra.productservice.service.ProdukService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produk")
@RequiredArgsConstructor
public class ProdukController {

    private final ProdukService produkService;

    @GetMapping
    public List<Produk> getAllProduk() {
        return produkService.getAllProduk();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produk> getProdukById(@PathVariable String id) {
        return produkService.getProdukById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Produk createProduk(@RequestBody Produk produk) {
        return produkService.saveProduk(produk);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Produk> updateProduk(@PathVariable String id, @RequestBody Produk produkDetails) {
        try {
            return ResponseEntity.ok(produkService.updateProduk(id, produkDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteProduk(@PathVariable String id) {
        try {
            produkService.deleteProduk(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
