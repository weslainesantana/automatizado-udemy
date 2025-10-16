package automatizado.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UdemyPO extends BasePO {

    @FindBy(name = "q")
    public WebElement inputPesquisa;

    @FindBy(css = "input[data-testid='search-input']")
    public WebElement inputPesquisaAlternativo;

    @FindBy(css = "h1")
    public WebElement headerResultados;

    @FindBy(css = "[data-purpose*='course']")
    public java.util.List<WebElement> cardsCursos;

    @FindBy(css = "button[aria-label*='Fechar']")
    public WebElement botaoFecharPopup;

    public UdemyPO(WebDriver driver) {
        super(driver);
    }
    
    public void pesquisarCurso(String textoCurso){
        aguardarCarregamento();
        
        WebElement input = inputPesquisa;
        try {
            if (!input.isDisplayed()) {
                input = inputPesquisaAlternativo;
            }
        } catch (Exception e) {
            input = inputPesquisaAlternativo;
        }
        
        input.clear();
        input.sendKeys(textoCurso);
        input.sendKeys(Keys.ENTER);
    }

    public int obterQuantidadeCursosEncontrados(){
        try {
            Thread.sleep(5000);
            return cardsCursos.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public String obterTituloPrimeiroCurso(){
        try {
            if(!cardsCursos.isEmpty()) {
                return cardsCursos.get(0).getText();
            }
        } catch (Exception e) {
        }
        return "Nenhum curso encontrado";
    }

    public boolean verificarSeHaResultados(){
        try {
            return !cardsCursos.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    private void aguardarCarregamento(){
        try {
            Thread.sleep(3000);
            wait.until(ExpectedConditions.elementToBeClickable(inputPesquisa));
            
            try {
                if(botaoFecharPopup.isDisplayed()) {
                    botaoFecharPopup.click();
                    Thread.sleep(2000);
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
    }
}