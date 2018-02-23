package acoesBasics;

import acoesVisitors.AcaoVisitor;
import elementos.Elemento;

/**
 * Created by Marilia on 07/02/2018.
 */
//http://www.baeldung.com/jackson-inheritance

//@JsonTypeInfo(
//        use = JsonTypeInfo.Id.NAME,
//        include = JsonTypeInfo.As.PROPERTY,
//        property = "type")
//@JsonSubTypes({
//        @Type(value = AcaoClicarBotao.class, name = "clicarBotao"),
//        @Type(value = AcaoValidarInformacao.class, name = "validarInformacao"),
//        @Type(value = AcaoPreencherCampo.class, name = "preencherCampo"),
//        @Type(value = AcaoSelecionarItem.class, name = "selecionarItem")
//})
//@Entity
public abstract class Acao {

    private Elemento elemento;
    private Acao proxima;

    public Elemento getElemento() {
        return elemento;
    }

    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }

    public Acao construir(){
       return this.elemento.getPagina().decorY(this);
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

    public abstract void executarOperacaoPrincipal();

    public abstract Acao accept(AcaoVisitor av);
}
