/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.ComparatorAlunoPorCodigoCrescente;
import domain.ComparatorAlunoPorCodigoDecrescente;
import domain.ComparatorAlunoPorGeneroCrescente;
import domain.ComparatorAlunoPorGeneroDecrescente;
import domain.ComparatorAlunoPorNomeCrescente;
import domain.ComparatorAlunoPorNomeDecrescente;
import domain.ComparaAlunoPorAlturaCrescente;
import domain.ComparaAlunoPorAlturaDecrescente;
import domain.ComparaAlunoPorIdadeCrescente;
import domain.ComparaAlunoPorIdadeDecrescente;
import domain.Aluno;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.util.Collections;
import java.util.Vector;

/**
 *
 * @author Gustavo
 */
public class AlunoFileDao {

    public void salvarAlunos(Vector<Aluno> alunos) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileOutputStream arquivo = new FileOutputStream(AlunoFileInformation.getCaminhoArquivo() + AlunoFileInformation.getNomeArquivo());

        //Classe responsavel por inserir os objetos
        ObjectOutputStream objGravar = new ObjectOutputStream(arquivo);

        //Grava o objeto de vetor de alunos no arquivo
        objGravar.writeObject(alunos);
        objGravar.flush();
        objGravar.close();
        arquivo.flush();
        arquivo.close();
    }

    private boolean estaArquivoVazio() throws FileNotFoundException, IOException {
        //Carrega o arquivo
        String local_arquivo = AlunoFileInformation.getCaminhoArquivo() + AlunoFileInformation.getNomeArquivo();
        FileInputStream arquivoLeitura = new FileInputStream(local_arquivo);
        boolean estaVazio = (arquivoLeitura.read() == -1);
        arquivoLeitura.close();
        return estaVazio;
    }

    public Vector<Aluno> obterAlunos() throws FileNotFoundException, IOException, ClassNotFoundException {
        //Carrega o arquivo
        if (estaArquivoVazio()) {
            return new Vector();
        } else {
            //Classe responsavel por recuperar os objetos do arquivo
            String local_arquivo = AlunoFileInformation.getCaminhoArquivo() + AlunoFileInformation.getNomeArquivo();
            FileInputStream arquivoLeitura = new FileInputStream(local_arquivo);
            ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
            Vector<Aluno> vetor = (Vector<Aluno>) objLeitura.readObject();
            objLeitura.close();
            arquivoLeitura.close();
            return vetor;
        }
    }

    public Vector<Aluno> obterAlunos(String coluna, boolean crescente) throws FileNotFoundException, IOException, ClassNotFoundException {
        Vector<Aluno> alunos = this.obterAlunos();
        switch (coluna) {
            case "codigo" -> {
                if (crescente) {
                    Collections.sort(alunos, new ComparatorAlunoPorCodigoCrescente());
                } else {
                    Collections.sort(alunos, new ComparatorAlunoPorCodigoDecrescente());
                }
            }
            case "nome" -> {
                if (crescente) {
                    Collections.sort(alunos, new ComparatorAlunoPorNomeCrescente());
                } else {
                    Collections.sort(alunos, new ComparatorAlunoPorNomeDecrescente());
                }
            }
            case "genero" -> {
                if (crescente) {
                    Collections.sort(alunos, new ComparatorAlunoPorGeneroCrescente());
                } else {
                    Collections.sort(alunos, new ComparatorAlunoPorGeneroDecrescente());
                }
            }
            case "idade" -> {
                if (crescente){
                    Collections.sort(alunos, new ComparaAlunoPorIdadeCrescente());
                }
                else{
                    Collections.sort(alunos, new ComparaAlunoPorIdadeDecrescente());
                }
            }
            case "altura" -> {
                if (crescente){
                    Collections.sort(alunos, new ComparaAlunoPorAlturaCrescente());
                }
                else{
                    Collections.sort(alunos, new ComparaAlunoPorAlturaDecrescente());
                }
            }
            default -> {
            }
        }
        return alunos;
    }
}