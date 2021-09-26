package model;

import java.awt.*;
import javax.swing.ImageIcon;

public class Missil {
    
    private final Image imagem;
    protected int x, y;
    private final int largura, altura;
    private boolean isVisible;
    private boolean boss;
    private int power;
    private static final int LARGURA_TELA = 500;
    private static final int VELOCIDADE = 2;
    
    public Missil(int x, int y, int charge, boolean boss){
        this.x = x;
        this.y = y;
        this.boss = boss;
        int yFix;
        ImageIcon referencia;
        if(charge <= 5){
            referencia = new ImageIcon("missel.png");
            power = 1;
            yFix = 5;
        }else if(charge > 5 && charge < 20){
            referencia = new ImageIcon("tiroverde2.gif");
            power = 3;
            yFix = 15;
        }else{
            referencia = new ImageIcon("tiroazulfoda.gif");
            power = 5;
            yFix = 25;
        }
        imagem = referencia.getImage();
        this.y -= yFix;
        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);
        
        isVisible = true;
        
    }
    
    public boolean isBossShot(){
        return this.boss;
    }
    
    public void losePower(int los){
         this.power -= los;
        if(this.power <= 0) this.setVisible(false);
    }
    
    public int getPower(){
        return this.power;
    }
    
    public void mexer(){
        if(!(this.x+VELOCIDADE>LARGURA_TELA) && !(x-VELOCIDADE<0)){
            if(!boss) this.x += VELOCIDADE;
            else this.x -= VELOCIDADE;
        }
        else this.isVisible = false;
    }
    
    public boolean isVisible(){
        return isVisible;
    }
    
    public void setVisible(boolean isVisible){
        this.isVisible = isVisible;
    }

    public Image getImagem() {
        return imagem;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,largura,altura);
    }
    
}