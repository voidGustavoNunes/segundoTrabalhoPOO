/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package windows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author Gustavo
 */
public class JanelaPrincipalAluno extends JanelaVisualizarAluno {

    @Override
    protected void montarCabecalho() {
        super.montarCabecalho();
        Vector colunas = this.obterNomeColunasTabela();
        int qtMenus = colunas.size() * 2;
        System.out.println(" " + (qtMenus + 1) + ") Incluir Novo Aluno");
        System.out.println(" " + (qtMenus + 2) + ") Alterar Aluno");
        System.out.println(" " + (qtMenus + 3) + ") Excluir Aluno");
    }

    private Vector lerDados() {
        System.out.println("===========================================================================================================");
        System.out.println("Digite os seguintes dados: ");
        Vector colunas = this.obterNomeColunasTabela();
        Scanner leitor = new Scanner(System.in);
        Vector dados = new Vector<>();
        String valor;
        int valorInt;
        double valorDouble;
        for (int i = 0; i < colunas.size() - 2; i++) {
            System.out.println(colunas.get(i));
            valor = leitor.next();
            dados.add(valor);
        }
        System.out.println(colunas.get(3));
        valorInt = leitor.nextInt();
        dados.add(valorInt);
        
        System.out.println(colunas.get(4));
        valorDouble = leitor.nextDouble();
        dados.add(valorDouble);
        
        
        System.out.println("");
        System.out.println("===========================================================================================================");
        return dados;
    }
    
    

    @Override
    public void executar() {
        Vector colunas = this.obterNomeColunasTabela();
        Scanner leitorOpcao = new Scanner(System.in);
        int opcao;
        int qtMenus = colunas.size() * 2;
        do {
            this.montarLayout();

            opcao = leitorOpcao.nextInt();
            // sair

            if (opcao == 0) {
                break;
            } // ordenação
            else if (opcao <= qtMenus) {
                limparTabelaAlunos();
                this.coluna = (String) colunas.get((opcao - 1) / 2);
                if ((opcao % 2) == 1) {
                    this.crescente = true;
                } else {
                    this.crescente = false;
                }
            } // incluir
            else if (opcao == qtMenus + 1) {
                limparTabelaAlunos();
                Vector dados = lerDados();
                try {
                    this.controladora.inserirNovoAluno(dados);
                } catch (FileNotFoundException ex) {
                    System.out.println("Erro: Arquivo não encontrado. Verifique o caminho do arquivo: " + ex.getMessage());
                } catch (IOException ex) {
                    System.out.println("Erro: Ocorreu um problema ao ler o arquivo: " + ex.getMessage());
                    System.out.println("Arquivo: " + ex.getClass().getName());
                } catch (ClassNotFoundException ex) {
                    System.out.println("Erro: Classe não encontrada: " + ex.getMessage());
                    System.out.println("Classe: " + ex.getClass().getName());
                }

            } // alterar
            else if (opcao == qtMenus + 2) {
                System.out.println("Digite o número da linha que deseja alterar: ");
                this.controladora.setMarcador(leitorOpcao.nextInt());
                limparTabelaAlunos();
                Vector dados = lerDados();
                try {
                    this.controladora.alterarAluno(dados);
                } catch (FileNotFoundException ex) {
                    System.out.println("Erro: Arquivo não encontrado. Verifique o caminho do arquivo: " + ex.getMessage());
                } catch (IOException ex) {
                    System.out.println("Erro: Ocorreu um problema ao ler o arquivo: " + ex.getMessage());
                    System.out.println("Arquivo: " + ex.getClass().getName());
                } catch (ClassNotFoundException ex) {
                    System.out.println("Erro: Classe não encontrada: " + ex.getMessage());
                    System.out.println("Classe: " + ex.getClass().getName());
                }

            } // excluir
            else if (opcao == qtMenus + 3) {
                System.out.println("Digite o número da linha que deseja excluir: ");
                this.controladora.setMarcador(leitorOpcao.nextInt());
                try {
                    this.controladora.excluirAluno();
                } catch (FileNotFoundException ex) {
                    System.out.println("Erro: Arquivo não encontrado. Verifique o caminho do arquivo: " + ex.getMessage());
                } catch (IOException ex) {
                    System.out.println("Erro: Ocorreu um problema ao ler o arquivo: " + ex.getMessage());
                    System.out.println("Arquivo: " + ex.getClass().getName());
                } catch (ClassNotFoundException ex) {
                    System.out.println("Erro: Classe não encontrada: " + ex.getMessage());
                    System.out.println("Classe: " + ex.getClass().getName());
                }

                limparTabelaAlunos();

            } else {
                System.out.println("Falha GERAL");
            }

        } while (true);
        leitorOpcao.close();
    }
}
