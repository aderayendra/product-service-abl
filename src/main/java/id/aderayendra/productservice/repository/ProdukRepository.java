package id.aderayendra.productservice.repository;

import id.aderayendra.productservice.model.Produk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdukRepository extends JpaRepository<Produk, String> {
}
