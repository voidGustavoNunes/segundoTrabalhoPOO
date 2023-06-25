/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package windows;

import control.ControladoraAlunos;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author Gustavo
 */
public class JanelaVisualizarAluno {

    protected ControladoraAlunos controladora;
    protected String coluna;
    protected boolean crescente;
    private Vector colunas = null;

    protected Vector obterNomeColunasTabela() {
        if (colunas == null) {
            colunas = new Vector(10);

            colunas.addElement("Código");
            colunas.addElement("Nome");
            colunas.addElement("Genero");
            colunas.addElement("Idade");
            colunas.addElement("Altura");
        }

        return colunas;
    }

    protected void limparTabelaAlunos() {
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
    }

    protected void preencherTabelaAlunos() {
        try {
            Vector linhas;
            linhas = controladora.obterLinhasAlunos((String) coluna, crescente);

            System.out.println("Legenda da ordem das colunas");
            for (int i = 0; i < colunas.size() - 1; i++) {
                System.out.print(colunas.get(i) + "   -   ");
            }
            System.out.print(colunas.get(colunas.size() - 1));
            System.out.println("");
            System.out.println("===========================================================================================================");

            int numLinhas = linhas.size();
            for (int i = 0; i < numLinhas; i++) {
                Vector linha = (Vector) linhas.get(i);
                for (int j = 0; j < colunas.size() - 1; j++) {
                    System.out.print(linha.get(j) + "   -   ");
                }

                String valor = String.valueOf(linha.get(colunas.size() - 1));

                if (valor.equals("")) {
                    System.out.print(" null ");
                } else {
                    System.out.print(valor);
                }
                System.out.println("");
                System.out.println("----------------------------------------------------------------------------------------------------------");

            }
            System.out.println("===========================================================================================================");
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado. Verifique o caminho do arquivo: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro: Ocorreu um problema ao ler/escrever o arquivo: " + ex.getMessage());
            System.out.println("Arquivo: " + ex.getClass().getName());
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro: Classe não encontrada: " + ex.getMessage());
            System.out.println("Classe: " + ex.getClass().getName());
        }
    }

    protected void montarCabecalho() {

        System.out.println(" Escolha uma das opções abaixo:");
        System.out.println(" 0) Sair");
        int desloque = 0;
        for (int i = 0; i < colunas.size(); i++) {
            System.out.println(" " + (i + 1 + desloque) + ") " + colunas.get(i) + " - Crescente");
            System.out.println(" " + (i + 2 + desloque) + ") " + colunas.get(i) + " - Decrescente");
            desloque++;
        }

    }

    protected void montarLayout() {
        // Montar cabeçalho
        System.out.println("===========================================================================================================");
        this.montarCabecalho();
        System.out.println("===========================================================================================================");
        // Preenchendo a Tabela de Filmes
        this.preencherTabelaAlunos();
    }

    public JanelaVisualizarAluno() {
        controladora = new ControladoraAlunos();
        this.obterNomeColunasTabela();
        this.coluna = colunas.get(0).toString();
        this.crescente = true;

    }

    public void executar() {
        Scanner leitorOpcao = new Scanner(System.in);
        int opcao;
        do {
            this.montarLayout();

            opcao = leitorOpcao.nextInt();

            limparTabelaAlunos();
            // sair
            if (opcao == 0) {
                break;
            }

            this.coluna = (String) this.colunas.get((opcao - 1) / 2);
            if ((opcao % 2) == 1) {
                this.crescente = true;
            } else {
                this.crescente = false;
            }

        } while (true);
        leitorOpcao.close();
    }
}
