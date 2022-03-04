package pro.gsilva.catalogo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pro.gsilva.catalogo.model.Categoria;
import pro.gsilva.catalogo.repository.CategoriaRepository;
import pro.gsilva.catalogo.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public Page<Categoria> findAll(Pageable pageable) {
		return categoriaRepository.findAll(pageable);
	}

	@Override
	public List<Categoria> findAll() {
		return this.categoriaRepository.findAll();
	}

	@Override
	public Optional<Categoria> findById(long id) {
		return this.categoriaRepository.findById(id);
	}

	@Override
	public Categoria save(Categoria categoria) {
		return this.categoriaRepository.save(categoria);
	}

	@Override
	public void excluir(long id) {
		this.categoriaRepository.deleteById(id);
	}
}
