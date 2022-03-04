package pro.gsilva.catalogo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pro.gsilva.catalogo.util.PopulaDados;

@SpringBootTest
class CatalogoApplicationTests {

	@Autowired
	PopulaDados populaDados;

	@Test
	void contextLoads() {
		populaDados.cadastrarMusica();
	}
}
