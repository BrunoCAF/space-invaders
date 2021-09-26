package model;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Nave {
    private int x, y;
    private int dx, dy;
    private final int largura, altura;
    private int hp;
    private boolean isVisible;
    private int charge = 0;
    private boolean lock;
    private Image imagem;
    private final List<Missil> misseis;

    public Nave(){
        
        ImageIcon referencia = new ImageIcon("nave.gif");
        imagem = referencia.getImage();
        
        misseis = new ArrayList<>();
        hp = 5;
        this.altura = imagem.getHeight(null);
        this.largura = imagem.getWidth(null);
        this.x = 100;
        this.y = 100;
        
    }

    public void mexer(){
        if(!lock){
            if(!(x+dx<0 || x+dx>470)) x += dx;
            if(!(y+dy<0 || y+dy>390)) y += dy;
        }else{
            Missil m;
            if(!misseis.isEmpty()){
                m = misseis.get(misseis.size()-1);
                m.y += dy;
            }
        }
    }

    public void nyan(){
        ImageIcon nyan = new ImageIcon("nyan.gif");
        imagem = nyan.getImage();
    }
    
    public void charge(){
        this.charge += 1;
    }
    
    public List<Missil> getMisseis() {
        return misseis;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHp(){
        return this.hp;
    }
    
    public void sufferDamage(int dam){
        this.hp -= dam;
        if(this.hp <= 0) {
            this.hp = 0;
            this.setVisible(false);
        }
    }
    
    public Image getImagem() {
        return imagem;
    } 
    
    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    
    public void atira(){
        this.misseis.add(new Missil( largura + x, y + altura/2, this.charge, false));
        this.charge = 0;
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x,y,largura,altura);
    }
    
    public void keyPressed(KeyEvent tecla){
        int code = tecla.getKeyCode();
        switch (code){
            case KeyEvent.VK_UP:
                dy = -1;
                break;
            case KeyEvent.VK_DOWN:
                dy = 1;
                break;
            case KeyEvent.VK_LEFT:
                dx = -1;
                break;
            case KeyEvent.VK_RIGHT:
                dx = 1;
                break;
            case KeyEvent.VK_X:
                lock = true;
                break;
            case KeyEvent.VK_SPACE:
                this.charge();
                break;
            case KeyEvent.VK_N:
                this.nyan();
                break;
        }
    }
    
    public void keyReleased(KeyEvent tecla){
        int code = tecla.getKeyCode();
        switch (code){
            case KeyEvent.VK_UP:
                dy = 0;
                break;
            case KeyEvent.VK_DOWN:
                dy = 0;
                break;
            case KeyEvent.VK_LEFT:
                dx = 0;
                break;
            case KeyEvent.VK_RIGHT:
                dx = 0;
                break;
            case KeyEvent.VK_X:
                lock = false;
                break;
            case KeyEvent.VK_SPACE:
                this.atira();
                break;
        }      
    }
}