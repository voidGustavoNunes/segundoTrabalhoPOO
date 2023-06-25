/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.AlunoFileDao;
import domain.Aluno;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author Gustavo
 */
public class ControladoraAlunos {

    private Vector<Aluno> alunos;
    private int marcador;
    AlunoFileDao alunoDao;

    private String obterNomeColunaBanco(String coluna) {
        if (coluna.equals("Código")) {
            return "codigo";
        }
        if (coluna.equals("Nome")) {
            return "nome";
        }
        if (coluna.equals("Genero")) {
            return "genero";
        }
        if (coluna.equals("Idade")) {
            return "idade";
        }
        if (coluna.equals("Altura")) {
            return "altura";
        }
        return "id";
    }

    public ControladoraAlunos() {
        this.alunoDao = new AlunoFileDao();
    }

    private void atualizarAluno(Aluno aluno, Vector linha) {
        aluno.setCodigo(linha.get(0).toString());
        aluno.setNome(linha.get(1).toString());
        aluno.setGenero(linha.get(2).toString());

        int idade = (int) linha.get(3);
        aluno.setIdade(idade);

        double altura = (double) linha.get(4);
        aluno.setAltura(altura);

    }

    private Vector criarLinhaAluno(Aluno aluno) {
        Vector linha = new Vector();
        linha.addElement(aluno.getCodigo());
        linha.addElement(aluno.getNome());
        linha.addElement(aluno.getGenero());
        linha.addElement(aluno.getIdade());
        linha.addElement(aluno.getAltura());

        return linha;
    }

    public void inserirNovoAluno(Vector linha) throws FileNotFoundException, IOException, ClassNotFoundException {
        Aluno aluno = new Aluno();
        this.atualizarAluno(aluno, linha);
        this.alunos.add(aluno); //adiciona no vector de alunos
        alunoDao.salvarAlunos(this.alunos);
    }

    public void setMarcador(int marcador) {
        this.marcador = marcador;
    }

    public void alterarAluno(Vector linha) throws FileNotFoundException, IOException, ClassNotFoundException {
        Aluno aluno = alunos.get(marcador);
        this.atualizarAluno(aluno, linha);
        alunoDao.salvarAlunos(this.alunos);
    }

    public void excluirAluno() throws FileNotFoundException, IOException, ClassNotFoundException {
        alunos.remove(marcador);
        alunoDao.salvarAlunos(this.alunos);
    }

    private Vector<Aluno> obterAlunos(String coluna, boolean crescente) throws FileNotFoundException, IOException, ClassNotFoundException {
        String nomeColunaBanco = this.obterNomeColunaBanco(coluna);
        alunos = alunoDao.obterAlunos(nomeColunaBanco, crescente);
        return alunos;
    }

    public Vector obterLinhasAlunos(String coluna, boolean crescente) throws FileNotFoundException, IOException, ClassNotFoundException {
        Vector<Aluno> alunos = obterAlunos(coluna, crescente);
        Vector linhas = new Vector();

        // Montando as linhas
        for (int i = 0; i < alunos.size(); i++) {
            Aluno aluno = alunos.get(i);
            linhas.addElement(this.criarLinhaAluno(aluno));
        }
        return linhas;
    }

}
