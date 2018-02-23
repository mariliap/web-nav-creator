

import acoesBasics.*;
import elementos.Elemento;
import paginas.Pagina;
import paginas.PaginaEdicaoParceiro;
import paginas.PaginaGenerica;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marilia on 07/02/2018.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

//        List<AcaoD> listaAcoes = new ArrayList<>();
//        AcaoD acaoD = new AcaoDVazia();
//        listaAcoes.adicionar(acaoD);

//        acaoD = new AcaoPreencherCampoDecorator(acaoD);
//        acaoD.executar();
//
//        acaoD = new ChainResponsability.AcaoEsperaVisibilidadeDecorator(acaoD);
//        acaoD.executar();
//
//        acaoD = new AcaoValidarInformacaoDecorator(acaoD);
//        acaoD.executar();

//        acaoD = acaoD.compor(new AcaoPreencherCampoDecorator());
//
//        acaoD = acaoD.compor(new ChainResponsability.AcaoEsperaVisibilidadeDecorator());
//
//        acaoD = acaoD.compor(new AcaoValidarInformacaoDecorator());
//
//        acaoD.executar();


//        Acao acaoX = new AcaoPreencherCampo();
//        acaoX.adicionar(new AcaoClicarBotao());
//        acaoX.adicionar(new AcaoSelecionarItem());
//        acaoX.adicionar(new AcaoValidarInformacao());
//        acaoX.executar();


        Pagina pagina1 = new PaginaEdicaoParceiro();

        Elemento pagl_elemento1 = new Elemento();
        pagl_elemento1.setPagina(pagina1);
        pagl_elemento1.setNome("P1E1");

        Elemento pagl_elemento2 = new Elemento();
        pagl_elemento2.setPagina(pagina1);
        pagl_elemento2.setNome("P1E2");

        Elemento pagl_elemento3 = new Elemento();
        pagl_elemento3.setPagina(pagina1);
        pagl_elemento3.setNome("P1E3");

        PaginaGenerica pagina2 = new PaginaGenerica();

        Elemento pag2_elemento1 = new Elemento();
        pag2_elemento1.setPagina(pagina2);
        pag2_elemento1.setNome("P2E1");


        List<Acao> listaAcoes = new ArrayList<>();

        Acao acao1 = new AcaoPreencherCampo();
        acao1.setElemento(pagl_elemento1);
        Acao acao1_1 = new AcaoClicarBotao();
        acao1_1.setElemento(pagl_elemento1);

        Acao acao2 = new AcaoPreencherCampo();
        acao2.setElemento(pagl_elemento2);
        Acao acao2_1 = new AcaoClicarBotao();
        acao2_1.setElemento(pagl_elemento2);

        Acao acao3 = new AcaoPreencherCampo();
        acao3.setElemento(pagl_elemento3);
        Acao acao3_1 = new AcaoClicarBotao();
        acao3_1.setElemento(pagl_elemento3);

        Acao acao4 = new AcaoPreencherCampo();
        acao4.setElemento(pag2_elemento1);
        Acao acao4_1 = new AcaoClicarBotao();
        acao4_1.setElemento(pag2_elemento1);

        Acao acao5 = new AcaoSelecionarItem();
        acao5.setElemento(pagl_elemento1);
        Acao acao6 = new AcaoValidarInformacao();
        acao6.setElemento(pagl_elemento1);
        Acao acao7 = new AcaoValidarInformacao();
        acao7.setElemento(pagl_elemento1);
        Acao acao8 = new AcaoValidarInformacao();
        acao8.setElemento(pagl_elemento1);
        listaAcoes.add(acao1);
        listaAcoes.add(acao1_1);
        listaAcoes.add(acao2);
        listaAcoes.add(acao2_1);
        listaAcoes.add(acao3);
        listaAcoes.add(acao3_1);
        listaAcoes.add(acao4);
        listaAcoes.add(acao4_1);
        listaAcoes.add(acao5);
        listaAcoes.add(acao6);
        listaAcoes.add(acao7);
        listaAcoes.add(acao8);

        Pagina pagAtual = null;
        Acao teste = new AcaoVazia();
        for (Acao acao : listaAcoes) {
//            if(pagAtual == null){
//                pagAtual = acao.getElemento().getPagina();
//            } else if(!pagAtual.equals(acao.getElemento().getPagina())){
//                acao = new AcaoEsperaVisibilidadeDecorator(acao);
//            }
            teste.adicionar(acao);
        }
        teste.executar();

    }
}
