package model;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public class Inimigo {
    protected Image imagem;
    protected int x, y;
    protected int largura, altura;
    protected boolean isVisible;
    private int hp;
    private int maxHp;
    protected static final int LARGURA_TELA = 500;
    protected static final int VELOCIDADE = 3;
    protected static int velocidade;
    
    public Inimigo(int x, int y, int mode){
        this.x = x;
        this.y = y;
        
        ImageIcon referencia;
        int tipoInimigo = (int) (10*Math.random());
        if(tipoInimigo%3 == 1){
            referencia = new ImageIcon("invader_1.png");
            hp = 2;
        }else if(tipoInimigo%3 == 2){
            referencia = new ImageIcon("invader_2.png");
            hp = 3;
        }else{
            referencia = new ImageIcon("invader_3.png");
            hp = 4;
        }
        imagem = referencia.getImage();
        maxHp = hp;
        velocidade = VELOCIDADE;
        if(mode == 1){
            hp--; maxHp--; velocidade--;
        }
        
        this.altura = imagem.getHeight(null);
        this.largura = imagem.getWidth(null);
                                      
        isVisible = true;
        
    }
    
    public void setHp(int hp){
        this.hp = hp;
    }
    
    public void setMaxHp(int maxHp){
        this.maxHp = maxHp;
    }
    
    public int getHp(){
        return this.hp;
    }
    
    public int getMaxHp(){
        return maxHp;
    }
    
    public void sufferDamage(int dam){
        this.hp -= dam;
        if(hp <= 0) this.setVisible(false);
    }
    
    public void mexer(int x, int y){
        this.x -= velocidade;
        if(this.x < 0){
            this.x = LARGURA_TELA;
            int Ry = (int) (1000*Math.random());
            Ry %= 360;            
            this.y = Ry;
        }
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