package pro.gsilva.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pro.gsilva.catalogo.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
