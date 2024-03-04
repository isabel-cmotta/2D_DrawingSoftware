import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.Enumeration;
import java.awt.Graphics;

import javax.swing.*;  

@SuppressWarnings("serial")
/**
 * Cria a interface com o usuario (GUI)
 * 
 * @author Cinthia Alves Barreto, Isabel Cavalcante Motta, Isabella Rubio Venancio
 * @version 14082023
 */
class Gui extends JFrame {
    // Tipo Atual de primitivo
    private TipoPrimitivo tipoAtual = TipoPrimitivo.NENHUM;

    // Cor atual
    private Color corAtual = Color.BLACK;

    // Espessura atual do primitivo
    private int espAtual = 5;

    // Componentes de GUI
    // barra de menu (inserir componente)
    private JToolBar barraComandos = new JToolBar();

    // mensagens
    private JLabel msg = new JLabel("Msg: ");

    // Painel de desenho
    private PainelDesenho areaDesenho = new PainelDesenho(msg, tipoAtual, corAtual, 10);

    // Botoes
    private JButton jbPonto = new JButton("Ponto");
    private JButton jbReta = new JButton("Reta");
    private JButton jbRetangulo = new JButton("Retangulo");
    private JButton jbCirculo = new JButton("Circulo");
    private JButton jbTriangulo = new JButton("Triangulo");
    private JButton jbMandala = new JButton("Mandala");
    private JButton jbLimpar = new JButton("Limpar");
    private JButton jbCtrlz = new JButton("Ctrl z");
    private JButton jbApagarED = new JButton("Limpar ED");
    private JButton jbRedesenhar = new JButton("Redesenhar");
    private JButton jbSelecionar = new JButton("Selecionar");

    private JButton jbSalvar = new JButton("Salvar");
    private JButton jbLer = new JButton("Ler Arquivo");
    private JMenuItem figura;  
    private JMenu menu_selecionar =new JMenu("Selecionar");  
    //private JMenu menu_se =new JMenu("Selecionar");  
    private JMenu menu_transformacao_geometrica =new JMenu("Tranformação Geométrica");  

    private JMenu menu_retas=new JMenu("Retas");  
    private JMenu menu_pontos=new JMenu("Pontos");  
    private JMenu menu_retangulos=new JMenu("Retangulos");  
    private JMenu menu_triangulos=new JMenu("Triangulos"); 
    private JMenu menu_circulos=new JMenu("Circulos");  
    private JMenu menu_mandalas=new JMenu("Mandalas"); 

    private JMenu menu_triangulos_tg =new JMenu("Triangulos"); 

    private JButton jbCor = new JButton("Cor");
    private JButton jbSair = new JButton("Sair");

    // Entrada (slider) para definir espessura dos primitivos
    private JLabel jlEsp = new JLabel("   Espessura: " + String.format("%-5s", 1));
    private JSlider jsEsp = new JSlider(1, 50, 1);

    private JMenuBar mb=new JMenuBar();  

    /**
     * Constroi a GUI
     *
     * @param larg largura da janela
     * @param alt altura da janela
     */
    public Gui(int larg, int alt) {

        /**
         * Definicoes de janela
         */
        super("Desenhador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(larg, alt);
        setVisible(true);
        setResizable(false);
        repaint(); 

        // i1=new JMenuItem("Item 1");  
        // i2=new JMenuItem("Item 2");  
        // i3=new JMenuItem("Item 3");  
        // i4=new JMenuItem("Item 4");  
        // i5=new JMenuItem("Item 5");  
        // menu.add(i1); menu.add(i2); menu.add(i3);  
        // submenu.add(i4); submenu.add(i5);  

        //setJMenuBar(mb);  

        //barraComandos.add(mb);
        JMenuItem menuItem;

        barraComandos.add(jbPonto);
        barraComandos.add(jbReta);
        barraComandos.add(jbRetangulo);
        barraComandos.add(jbTriangulo);
        barraComandos.add(jbCirculo);
        barraComandos.add(jbMandala);
        barraComandos.add(jbLimpar); // Botao de Limpar
        barraComandos.add(jbCtrlz); // Botao de Limpar
        barraComandos.add(jbApagarED); // Botao de Limpar
        barraComandos.add(jbRedesenhar); // Botao de Limpar
        //barraComandos.add(jbSelecionar);
        barraComandos.add(jbSalvar);
        barraComandos.add(jbLer);

        // adiciona os componentes com os respectivos layouts

        menu_selecionar.add(menu_pontos);  
        menu_selecionar.add(menu_retas); 
        menu_selecionar.add(menu_retangulos);  
        menu_selecionar.add(menu_triangulos); 
        menu_selecionar.add(menu_circulos);  
        menu_selecionar.add(menu_mandalas); 
        mb.add(menu_selecionar);  
        
        mb.add(menu_transformacao_geometrica);
        
        barraComandos.add(mb);
        barraComandos.add(jbCor); // Botao de Cores

        barraComandos.add(jlEsp); // Label para espessura
        barraComandos.add(jsEsp);    // Slider para espacamento
        areaDesenho.setEsp(espAtual); // define a espessura inicial
        barraComandos.add(jbSair); // Botao de Cores

        add(barraComandos, BorderLayout.NORTH);                
        add(areaDesenho, BorderLayout.CENTER);                
        add(msg, BorderLayout.SOUTH);

        areaDesenho.removeAll();
        repaint(); 
        
        Graphics g = getGraphics();

        // Adicionando os componentes

        // Adiciona "tratador" ("ouvidor") de eventos para 
        // cada componente
        jbPonto.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.PONTO;
                    areaDesenho.setTipo(tipoAtual);

            });   
        jbReta.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.RETA;
                    areaDesenho.setTipo(tipoAtual);

            });
        jbRetangulo.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.RETANGULO;
                    areaDesenho.setTipo(tipoAtual);
            });
        jbMandala.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.MANDALA;
                    areaDesenho.setTipo(tipoAtual);
            });
        jbTriangulo.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.TRIANGULO;
                    areaDesenho.setTipo(tipoAtual);
            });
        jbCirculo.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.CIRCULO;
                    areaDesenho.setTipo(tipoAtual);
            });    
        jbRedesenhar.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.ED_RECONSTRUIDA;
                    areaDesenho.setTipo(tipoAtual);
                    repaint();

            });
        jbLimpar.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.LIMPA;
                    areaDesenho.setTipo(tipoAtual);   
                    //areaDesenho.removeAll();
                    //Color.WHITE;

                    jsEsp.setValue(1); // inicia slider (necessario para limpar ultimo primitivo da tela) 
                    repaint(); 
            });
        jbCtrlz.addActionListener(e -> {

                    repaint();  
                    tipoAtual = TipoPrimitivo.REMOVER_ULTIMO;
                    areaDesenho.setTipo(tipoAtual);   
                    //areaDesenho.removeAll();
                    //Color.WHITE;
                    adicionaFigurasMenu();
            });
        jbApagarED.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.APAGA_ED;
                    areaDesenho.setTipo(tipoAtual);   
                    //areaDesenho.removeAll();
                    //Color.WHITE;
                    areaDesenho.removeAll();
                    jsEsp.setValue(1); // inicia slider (necessario para limpar ultimo primitivo da tela) 
                    repaint(); 
            });
        jbCor.addActionListener(e -> {
                    Color c = JColorChooser.showDialog(null, "Escolha uma cor", msg.getForeground()); 
                    if (c != null){ 
                        corAtual = c; // pega do chooserColor 
                    }
                    areaDesenho.setCorAtual(corAtual); // cor atual
            });  
        jsEsp.addChangeListener(e -> {
                    espAtual = jsEsp.getValue();
                    jlEsp.setText("   Espessura: " + String.format("%-5s", espAtual));
                    areaDesenho.setEsp(espAtual);        
            });       
        jbSelecionar.addActionListener(e -> {
                    tipoAtual = TipoPrimitivo.SELECIONAR;
                    areaDesenho.setTipo(tipoAtual);

            });
        jbSalvar.addActionListener(e -> {
                    areaDesenho.salvarEstruturaDeDados(larg, alt);
            });
        jbLer.addActionListener(e -> {
                    
                    tipoAtual = TipoPrimitivo.LEITURA;
                    areaDesenho.setTipo(tipoAtual);
                    repaint();
                    areaDesenho.lerArquivo(g);
            });
        menu_selecionar.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e){
                    adicionaFigurasMenu();
                    tipoAtual = TipoPrimitivo.SELECIONAR;
                    repaint();
                    areaDesenho.setTipo(tipoAtual);
                    
                }
                
            });
            
        menu_transformacao_geometrica.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e){
                    adicionaFigurasMenu();
                    tipoAtual = TipoPrimitivo.SELECIONAR;
                    repaint();
                    areaDesenho.setTipo(tipoAtual);
                    
                }
                
            });

        menu_pontos.addActionListener(e -> {
                    System.out.println("clicou no botaooo");
            });

        
        for (int i=0; i<areaDesenho.estrutura_de_dados.pontos.getTamanho(); i++) {
            menuItem = menu_pontos.getItem(i);

            menuItem.addActionListener(e ->{
                        System.out.println("clicou no botaooo");
                });
        }
        for (int i=0; i<areaDesenho.estrutura_de_dados.retas.getTamanho(); i++) {
            menuItem = menu_retas.getItem(i);

            // menuItem.addActionListener(e ->{
            // menuItem.setArmed(true);
            // });
        }

        jbSair.addActionListener(e -> {
                    System.exit(0);
            });        
    }

    public void adicionaFigurasMenu(){
        //menu_retas=new JMenu("Retas");  
        //menu_pontos=new JMenu("Pontos");  
        menu_pontos.removeAll();
        menu_retas.removeAll();
        menu_retangulos.removeAll();
        menu_triangulos.removeAll();
        menu_circulos.removeAll();
        menu_mandalas.removeAll();
        String nomeFigura;
        for(int i=0; i<areaDesenho.estrutura_de_dados.pontos.getTamanho(); i++){
            nomeFigura = areaDesenho.estrutura_de_dados.pontos.buscar(i).getNomePto();
            figura=new JMenuItem(nomeFigura); 
            final int j = i;
            figura.addActionListener(e -> {
                
                areaDesenho.estrutura_de_dados.pontos.removerPonto(j);
            });
            
            menu_pontos.add(figura);
        }
        for(int i=0; i<areaDesenho.estrutura_de_dados.retas.getTamanho(); i++){
            nomeFigura = areaDesenho.estrutura_de_dados.retas.buscar(i).getNomeReta();
            figura=new JMenuItem(nomeFigura); 
            // adiciona reta na pilha
            final int j = i;
            figura.addActionListener(e -> {
                
                areaDesenho.estrutura_de_dados.retas.removerReta(j);
            });
            menu_retas.add(figura);
        }
        for(int i=0; i<areaDesenho.estrutura_de_dados.retangulos.getTamanho(); i++){
            nomeFigura = areaDesenho.estrutura_de_dados.retangulos.buscar(i).getNomeRetangulo();
            figura=new JMenuItem(nomeFigura); 
            final int j = i;
            figura.addActionListener(e -> {
                
                areaDesenho.estrutura_de_dados.retangulos.removerRetangulo(j);
            });
            
            menu_retangulos.add(figura);
        }
        for(int i=0; i<areaDesenho.estrutura_de_dados.triangulos.getTamanho(); i++){
            nomeFigura = areaDesenho.estrutura_de_dados.triangulos.buscar(i).getNomeTriangulo();
            figura=new JMenuItem(nomeFigura); 
            final int j = i;
            figura.addActionListener(e -> {
                
                areaDesenho.estrutura_de_dados.triangulos.removerTriangulo(j);
            });
            menu_triangulos.add(figura);
        }
        for(int i=0; i<areaDesenho.estrutura_de_dados.circulos.getTamanho(); i++){
            nomeFigura = areaDesenho.estrutura_de_dados.circulos.buscar(i).getNomeCirculo();
            figura=new JMenuItem(nomeFigura); 
            final int j = i;
            figura.addActionListener(e -> {
                
                areaDesenho.estrutura_de_dados.circulos.removerCirculo(j);
            });
            menu_circulos.add(figura);
        }
        for(int i=0; i<areaDesenho.estrutura_de_dados.mandalas.getTamanho(); i++){
            nomeFigura = areaDesenho.estrutura_de_dados.mandalas.buscar(i).getNome();
            figura=new JMenuItem(nomeFigura); 
            final int j = i;
            figura.addActionListener(e -> {
                
                areaDesenho.estrutura_de_dados.mandalas.removerMandala(j);
            });
            menu_mandalas.add(figura);
            
        }
    }
    
    public void transformacao_geometrica(){
        String nomeFigura;
        menu_triangulos_tg.removeAll();
        for(int i=0; i<areaDesenho.estrutura_de_dados.triangulos.getTamanho(); i++){
            nomeFigura = areaDesenho.estrutura_de_dados.triangulos.buscar(i).getNomeTriangulo();
            figura=new JMenuItem(nomeFigura); 
            final int j = i;
            figura.addActionListener(e -> {
                
                //areaDesenho.rotacionarTriangulo(j);
            });
            menu_triangulos_tg.add(figura);
        }
    }
}
