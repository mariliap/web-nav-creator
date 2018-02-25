import acoesBasics.Acao;
import acoesBasics.AcaoVazia;
import acoesVisitors.PaginaAcoesVisitor;
import elementos.Elemento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hsqldb");
        EntityManager em = emf.createEntityManager();

        //PaginaAcoesVisitor pagina1 = new PaginaGenenericaAcoesVisitor();
        //PaginaAcoesVisitor pagina2 = new TestingDecoratorsAcoesVisitor();

        PaginaAcoesVisitor pagina1 = em.createQuery("SELECT b FROM PaginaAcoesVisitor b where b.id = 1", PaginaAcoesVisitor.class).getSingleResult();
        PaginaAcoesVisitor pagina2 = em.createQuery("SELECT b FROM PaginaAcoesVisitor b where b.id = 2", PaginaAcoesVisitor.class).getSingleResult();

        Elemento elemento1 = em.createQuery("SELECT e FROM Elemento e where e.id = 1", Elemento.class).getSingleResult();
        Elemento elemento2 = em.createQuery("SELECT e FROM Elemento e where e.id = 2", Elemento.class).getSingleResult();
        Elemento elemento3 = em.createQuery("SELECT e FROM Elemento e where e.id = 3", Elemento.class).getSingleResult();
        Elemento elemento4 = em.createQuery("SELECT e FROM Elemento e where e.id = 4", Elemento.class).getSingleResult();

        List<Acao> listaAcoes = em.createQuery("SELECT a FROM Acao a", Acao.class).getResultList();

        em.close();
        emf.close();

//        Elemento pagl_elemento1 = new Elemento();
//        pagl_elemento1.setNome("P1E1");
//        pagl_elemento1.setVisitor(pagina1);
//
//        Elemento pagl_elemento2 = new Elemento();
//        pagl_elemento2.setVisitor(pagina1);
//        pagl_elemento2.setNome("P1E2");
//
//        Elemento pagl_elemento3 = new Elemento();
//        pagl_elemento3.setVisitor(pagina1);
//        pagl_elemento3.setNome("P1E3");
//
//        Elemento pag2_elemento1 = new Elemento();
//        pag2_elemento1.setNome("P2E1");
//        pag2_elemento1.setVisitor(pagina2);


//        List<Acao> listaAcoes = new ArrayList<>();
//
//        Acao acao1 = new AcaoPreencherCampo();
//        acao1.setElemento(elemento1);
//        Acao acao1_1 = new AcaoClicarBotao();
//        acao1_1.setElemento(elemento1);
//
//        Acao acao2 = new AcaoPreencherCampo();
//        acao2.setElemento(elemento2);
//        Acao acao2_1 = new AcaoClicarBotao();
//        acao2_1.setElemento(elemento2);
//
//        Acao acao3 = new AcaoPreencherCampo();
//        acao3.setElemento(elemento3);
//        Acao acao3_1 = new AcaoClicarBotao();
//        acao3_1.setElemento(elemento3);
//
//        Acao acao4 = new AcaoPreencherCampo();
//        acao4.setElemento(elemento4);
//        Acao acao4_1 = new AcaoClicarBotao();
//        acao4_1.setElemento(elemento4);

//        Acao acao5 = new AcaoSelecionarItem();
//        acao5.setElemento(pagl_elemento1);
//        Acao acao6 = new AcaoValidarInformacao();
//        acao6.setElemento(pagl_elemento1);
//        Acao acao7 = new AcaoValidarInformacao();
//        acao7.setElemento(pagl_elemento1);
//        Acao acao8 = new AcaoValidarInformacao();
//        acao8.setElemento(pagl_elemento1);

//        listaAcoes.add(acao1);
//        listaAcoes.add(acao1_1);
//        listaAcoes.add(acao2);
//        listaAcoes.add(acao2_1);
//        listaAcoes.add(acao3);
//        listaAcoes.add(acao3_1);
//        listaAcoes.add(acao4);
//        listaAcoes.add(acao4_1);
//        listaAcoes.add(acao5);
//        listaAcoes.add(acao6);
//        listaAcoes.add(acao7);
//        listaAcoes.add(acao8);



        Acao teste = new AcaoVazia();
        for (Acao acao : listaAcoes) {
            teste.adicionar(acao);
        }
        teste.executar();

    }
}
