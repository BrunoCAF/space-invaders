package views;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.Pontuacao;
import model.Usuario;


public class RankingPessoalTableModel extends AbstractTableModel{

    ArrayList<Pontuacao> pontuacao = new ArrayList();
    ArrayList<String> colunas = new ArrayList();
    
    public RankingPessoalTableModel(){
        colunas.add("Posição");
        colunas.add("Pontos");
        colunas.add("Data");
    }
    
    public RankingPessoalTableModel(ArrayList<Pontuacao> ponts){
        colunas.add("Posição");
        colunas.add("Pontos");
        colunas.add("Data");
        this.pontuacao = ponts;
    }
    
    @Override
    public int getRowCount() {
        return pontuacao.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0:
                return linha+1;
            case 1:
                return pontuacao.get(linha).getPontos();
            case 2:
                return pontuacao.get(linha).getData();
            default:
                return "";
        }
    }
    
    @Override
    public String getColumnName(int coluna) {
        switch(coluna){
            case 0:
                return "Posição";
            case 1:
                return "Pontos";
            case 2:
                return "Data";
            default:
                return "";
        }
    }
}