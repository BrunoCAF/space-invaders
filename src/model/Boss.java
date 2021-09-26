package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Boss extends Inimigo{
    private final List<Missil> misseis;
    
    public Boss(int x, int y, int mode){
        super(x,y,mode);
        this.x = x;
        this.y = y;
        ImageIcon referencia = new ImageIcon("invader.png");
        this.imagem = referencia.getImage();
        this.setHp(100*mode);
        this.setMaxHp(getHp());
        System.out.print(getHp());
        this.altura = imagem.getHeight(null);
        this.largura = imagem.getWidth(null);
        isVisible = true;
        misseis = new ArrayList<>();
    }
    
    public void mexer(double y){
        this.y = (int)(150*Math.sin((y*2*Math.PI)/100) + altura+10);
    }
    
    public void atirar(){
        int charge;
        if(getHp() > getMaxHp()/2) charge = 5;
        else if(getHp() > getMaxHp()/4) charge = 15;
        else charge = 25;
        this.misseis.add(new Missil( x - largura/2, y + altura/2, charge, true));
    }
    
    public List<Missil> getMisseis() {
        return misseis;
    }
}