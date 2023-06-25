/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.Comparator;

/**
 *
 * @author Gustavo
 */
public class ComparaAlunoPorAlturaDecrescente implements Comparator<Aluno> {
    @Override
    public int compare(Aluno a1, Aluno a2) {
//        return Double.compare(a2.getAltura(), a1.getAltura());
        if(a1.getAltura() > a2.getAltura()) {
            return -1;
        } else if(a1.getAltura() < a2.getAltura()) {
            return 1;
        } else{
            return 0;
        }
    }
}
