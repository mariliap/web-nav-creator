package main.java;

/**
 * Created by Marilia on 07/02/2018.
 */
public class AcaoDecorator extends Acao{

    private Acao ultimaAcaoD = null;


    public AcaoDecorator(Acao acaoDASerIncrementada) {
        this.ultimaAcaoD = acaoDASerIncrementada;
    }

    @Override
    public void executarOperacaoPrincipal() {
        this.ultimaAcaoD.executarOperacaoPrincipal();
    }
}
