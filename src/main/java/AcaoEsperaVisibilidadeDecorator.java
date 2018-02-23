/**
 * Created by Marilia on 07/02/2018.
 */
public class AcaoEsperaVisibilidadeDecorator extends AcaoDecorator {

    public AcaoEsperaVisibilidadeDecorator(Acao acaoDASerIncrementada) {
        super(acaoDASerIncrementada);
    }

    @Override
    public void executarOperacaoPrincipal() {

        System.out.print("\nAcaoEsperaVisibilidadeDecorator > ");
        super.executarOperacaoPrincipal();
    }
}
