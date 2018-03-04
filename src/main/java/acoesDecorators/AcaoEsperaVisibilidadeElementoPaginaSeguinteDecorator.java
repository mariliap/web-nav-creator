package acoesDecorators;

import acoesBasics.Acao;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Marilia on 07/02/2018.
 */
public class AcaoEsperaVisibilidadeElementoPaginaSeguinteDecorator extends AcaoDecorator {

    public AcaoEsperaVisibilidadeElementoPaginaSeguinteDecorator(Acao acaoDASerIncrementada) {
        super(acaoDASerIncrementada);
    }

    @Override
    public void executarOperacaoPrincipal() {

        super.executarOperacaoPrincipal();

        System.out.print("\nAcaoEsperaVisibilidadeElementoPaginaSeguinte > ");
        WebDriverWait wait = new WebDriverWait(getAcaoASerIncrementada().getDriver(), 20);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("ImgAppBanner")));
    }
}
