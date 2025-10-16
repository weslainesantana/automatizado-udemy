package automatizado.test;

import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import automatizado.page.UdemyPO;

public class UdemyTest extends BaseTest {
    
    private static UdemyPO udemyPage;

    @BeforeClass
    public static void preperarTestes(){
        udemyPage = new UdemyPO(driver); 
    }

    @Test
    public void TC001_deveSerPossivelPesquisarCursosDeJava() throws InterruptedException {
        udemyPage.pesquisarCurso("Java");
        Thread.sleep(8000);
        
        boolean temResultados = udemyPage.verificarSeHaResultados();
        udemyPage.obterQuantidadeCursosEncontrados();
        udemyPage.obterTituloPrimeiroCurso();
        
        assertTrue(temResultados || driver.getCurrentUrl().contains("search"));
    }

    @Test
    public void TC002_deveSerPossivelPesquisarCursosDeWebDevelopment() throws InterruptedException {
        udemyPage.pesquisarCurso("Web Development");
        Thread.sleep(8000);
        
        boolean temResultados = udemyPage.verificarSeHaResultados();
        udemyPage.obterQuantidadeCursosEncontrados();
        udemyPage.obterTituloPrimeiroCurso();
        
        assertTrue(temResultados || driver.getCurrentUrl().contains("search"));
    }

    @Test
    public void TC003_deveVerificarSeInputPesquisaEstaFuncionando() throws InterruptedException {
        udemyPage.pesquisarCurso("Python");
        Thread.sleep(5000);
        
        String urlAtual = driver.getCurrentUrl();
        
        assertTrue(urlAtual.contains("search") || urlAtual.contains("q="));
    }
}