package views;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.Usuario;
import model.Pontuacao;

public class RankingGeralTableModel extends AbstractTableModel{

    ArrayList<Pontuacao> pontuacao = new ArrayList();
    ArrayList<String> colunas = new ArrayList();
    
    public RankingGeralTableModel(){
        colunas.add("Posição");
        colunas.add("Nome");
        colunas.add("Pontos");
        colunas.add("Data");
    }
    
    public RankingGeralTableModel(ArrayList<Pontuacao> ponts){
        colunas.add("Posição");
        colunas.add("Nome");
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
                return pontuacao.get(linha).getNome();
            case 2:
                return pontuacao.get(linha).getPontos();
            case 3:
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
                return "Nome";
            case 2:
                return "Pontos";
            case 3:
                return "Data";
            default:
                return "";
        }
    }
    
}