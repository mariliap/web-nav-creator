package acoesBasics;

import acoesVisitors.VisitableAcao;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import commons.GenericEntity;
import elementos.Elemento;
import org.openqa.selenium.WebDriver;

import javax.persistence.*;

/**
 * Created by Marilia on 07/02/2018.
 */
//http://www.baeldung.com/jackson-inheritance

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @Type(value = AcaoClicarBotao.class, name = "clicarBotao"),
        @Type(value = AcaoValidarInformacao.class, name = "validarInformacao"),
        @Type(value = AcaoPreencherCampo.class, name = "preencherCampo"),
        @Type(value = AcaoSelecionarItem.class, name = "selecionarItem"),
        @Type(value = AcaoIrPara.class, name = "irPara")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Acao_Type")
public abstract class Acao extends GenericEntity implements VisitableAcao{

    @ManyToOne
    private Elemento elemento;

    @Transient
    private Acao proxima;

    @Transient
    private WebDriver driver;

    public abstract void executarOperacaoPrincipal();

    public Elemento getElemento() {
        return elemento;
    }

    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public Acao construir(){
//       return this.elemento.getPagina().decorY(this);
        return this.elemento.decorar(this);
    }

    public void adicionar(Acao acao) {
        if (proxima == null) {
            proxima = acao.construir();
        } else {
            proxima.adicionar(acao);
        }
    }

    public void executar(){

        executarOperacaoPrincipal();

        if (proxima != null) {
            proxima.executar();
        }
    }



}
