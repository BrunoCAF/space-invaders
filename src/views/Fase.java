package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Boss;
import model.Inimigo;
import model.MP3;
import model.Missil;
import model.Nave;

import model.Usuario;

public class Fase extends JPanel implements ActionListener{
    
    private Image fundo;
    private final List<Image> fundos;
    
    private Nave nave;
    private final Usuario user;
    private List<Inimigo> inimigos;
    private Boss boss;
    private int points;
    private int mode;
    
    private final Timer timer;
    private Graphics2D graficos;
    private final MP3 music;
    
    private final TelaHome pai;
    
    //flags
    private boolean emJogo;
    private boolean paused;
    private boolean win;
    private boolean bossOn;
    private boolean bossDefeated;
    
    private int flagFundo;
    private double bossMove;
    private int spawnTime;
    private final int spawnFrequency;
    
    //constantes
    private static final int LARGURA_TELA = 500;
    private int NumInimigos;
    
    public Fase(Usuario u, int mode, TelaHome pai){
        if(u.isLogado()){
            this.user = u;
        }else{
            this.user = new Usuario(u);
        }
        this.pai = pai;
        setSize(500,420);
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TecladoAdapter());
        
        this.mode = mode;
        if(mode == 1) spawnFrequency = 400;
        else spawnFrequency = 200;
        spawnTime = 0;
        
        music = new MP3("MachinimaSound.com_-_Intruders.mp3");
        
        fundos = new ArrayList<>();
        ImageIcon referencia = new ImageIcon("fundo.png");
        fundos.add(referencia.getImage());
        ImageIcon referencia2 = new ImageIcon("olho_de_Deus.jpg");
        fundos.add(referencia2.getImage());
        ImageIcon referencia3 = new ImageIcon("planeta.jpg");
        fundos.add(referencia3.getImage());
        ImageIcon referencia4 = new ImageIcon("constelacao_vermelha_1.jpg");
        fundos.add(referencia4.getImage());
        ImageIcon referencia5 = new ImageIcon("boraco_negro.jpg");
        fundos.add(referencia5.getImage());
        
        inicializarFlags();
        
        timer = new Timer(5, this);
        timer.start();
    }
    
    public void pause(boolean b){
        if(paused = b) timer.stop();
        else timer.restart();
    }
    
    public void inicializarFlags(){
        int i = ((int)(10*Math.random()))%5;
        this.fundo = fundos.get(i);
        flagFundo = 0;
        points = 0;
        nave = new Nave();
        NumInimigos = 100;
        inimigos = new ArrayList<>();
        emJogo = true;
        win = false;
        bossOn = false;
        //music.play();
    }
    
    public void setMode(int mode){
        this.mode = mode;
    }
    
    public TelaHome getPai(){
        return this.pai;
    }
    
    public void salvarScore(){
        if(user.isLogado()){
            user.setPontos(points+(nave.getHp()*100*mode));
            pai.salvarPontuacao(user);
        }
    }
    
    public void summonBoss(){
        boss = new Boss(LARGURA_TELA-128, 146, mode);
        bossOn = true;
        bossDefeated = false;
        inimigos.add(boss);
    }
    
    public void endGame(){
        emJogo = false;
        pause(true);
        //music.close();
        if(nave.getHp() > 0){
            win = true;
        }else{
            
        }
        salvarScore();
    }
    
    @Override
    public void paint(Graphics g){
        this.graficos = (Graphics2D) g;
        flagFundo++;flagFundo%=500;
        graficos.drawImage(fundo, -flagFundo, 0, null);
        graficos.drawImage(fundo, 500-flagFundo, 0, null);
        if(emJogo){
            graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);
            
            if(bossOn){
                graficos.drawImage(boss.getImagem(), boss.getX(), boss.getY(), this);
                List<Missil> bossShots = boss.getMisseis();
                bossShots.stream().map((missil) -> (Missil) missil).forEach((m) -> {
                   graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
                });
            }
            
            List<Missil> misseis = nave.getMisseis();
            
            misseis.stream().map((missil) -> (Missil) missil).forEach((m) -> {
                graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
            });
            
            inimigos.stream().forEach((in) -> {
                graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
            });
            
            if(!bossOn){
                graficos.setColor(Color.WHITE);
                graficos.drawString("INIMIGOS RESTANTES: " + NumInimigos, 5, 15);
            }else{
                graficos.setColor(Color.WHITE);
                graficos.drawString("VIDAS DO CHEFÃO: " + boss.getHp(), 5, 15);
            }
            graficos.setColor(Color.RED);
            graficos.drawString("VIDAS: " + nave.getHp(), 5, 25);
            graficos.setColor(Color.BLUE);
            graficos.drawString("PONTOS: " + points, 400, 25);
            
            if(fundo == fundos.get(0)) graficos.setColor(Color.BLUE);
            else if(fundo == fundos.get(1)) graficos.setColor(Color.GREEN);
            else if(fundo == fundos.get(2)) graficos.setColor(Color.YELLOW);
            else if(fundo == fundos.get(3)) graficos.setColor(Color.CYAN);
            else graficos.setColor(Color.WHITE);
            String nome = "Convidado"; if(user.isLogado()) nome = user.getNome();
            graficos.drawString(nome, 400, 15);
            
            if(paused){
                if(fundo == fundos.get(0)) graficos.setColor(Color.YELLOW);
                else if(fundo == fundos.get(1)) graficos.setColor(Color.GREEN);
                else if(fundo == fundos.get(2)) graficos.setColor(Color.BLACK);
                else if(fundo == fundos.get(3)) graficos.setColor(Color.CYAN);
                else graficos.setColor(Color.WHITE);
                graficos.drawString("PAUSADO", 220, 180);
            }
        }else{
            if(win){
                ImageIcon youWin = new ImageIcon("ganhou.jpg");
                //music.close();
                graficos.drawImage(youWin.getImage(), 0, 0, null);
            }else{
                ImageIcon youLose = new ImageIcon("perdeu.jpg");
                //music.close();
                graficos.drawImage(youLose.getImage(), 0, 0, null);
            }
            graficos.setColor(Color.ORANGE);
            graficos.drawString("SUA PONTUAÇÃO: "+points, 40, 360);
            String bonus = "x100 = ";
            if(mode == 2) bonus = "x200 = ";
            graficos.drawString("Bônus de vidas: "+nave.getHp()+bonus+nave.getHp()*100*mode, 40, 380);
            graficos.drawString("Aperte ENTER para recomeçar", 280, 360);
            graficos.drawString("Aperte Q para sair", 280, 380);
            graficos.drawString("Aperte S para trocar a dificuldade", 280, 400);
        }
        g.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0){
        
        if(NumInimigos>0){
            spawnTime += 5;
            if(spawnTime == spawnFrequency){
                int Ry = (int) (1000*Math.random());
                Ry %= 360;
                inimigos.add(new Inimigo(LARGURA_TELA, Ry, mode));
                spawnTime = 0;
            }
        }
        
        if(bossOn){
            bossMove+=0.1;
            if(boss.isVisible()){
                boss.mexer(bossMove);
                int shotChance = (int) (1000*Math.random());
                if(shotChance < 20){
                    boss.atirar();
                }
            }else{
                bossDefeated = true;
            }
            List<Missil> bossShots = boss.getMisseis();
            
            for(int i = 0; i<bossShots.size(); i++){
                Missil m = (Missil) bossShots.get(i);
                if(m.isVisible())
                    m.mexer();
                else
                    bossShots.remove(i);   
            }
        
        }
        List<Missil> misseis = nave.getMisseis();
        for(int i = 0; i<misseis.size(); i++){
            Missil m = (Missil) misseis.get(i);
            if(m.isVisible())
                m.mexer();
            else
                misseis.remove(i);   
        }
        
        for(int i = 0; i<inimigos.size(); i++){
            Inimigo in = (Inimigo) inimigos.get(i);
            if(in.isVisible() && in.getMaxHp() < 10){
                in.mexer(nave.getX(), nave.getY());
            }else if(!in.isVisible()){
                points += (in.getMaxHp() * 10 * mode);
                inimigos.remove(i);
                if(NumInimigos>0) NumInimigos--;
            }   
        }
        checarColisoes();
        /*if(music.isComplete()){
            music.close();
            music.play();
        }*/
        nave.mexer();
        
        if((nave.getHp() > 0) && (NumInimigos == 0) && (!bossOn))
            summonBoss();
        
        if(nave.getHp() <= 0 || NumInimigos == 0 && bossDefeated)
            endGame();
        
        repaint();
    }
    
    public void checarColisoes(){
        Rectangle formaNave = nave.getBounds();
        Rectangle formaInimigo;
        Rectangle formaMissil;
        Rectangle formaBoss;
        if(bossOn){
            formaBoss = boss.getBounds();
            List<Missil> bossShots = boss.getMisseis();
            
            for (Missil tempMissil : bossShots) {
                formaMissil = tempMissil.getBounds();
                if(formaMissil.intersects(formaNave)){
                    int hp = nave.getHp(), dam = tempMissil.getPower();
                    if(dam > 0) nave.sufferDamage(dam);
                    if(hp > 0) tempMissil.losePower(hp);
                } 
            }
        }
                
        List<Missil> misseis = nave.getMisseis();
        
        for (Inimigo inimigo : inimigos) {
            Inimigo tempInimigo = (Inimigo) inimigo;
            formaInimigo = tempInimigo.getBounds();
            if(formaNave.intersects(formaInimigo)){
                int hpNave = nave.getHp(), hpIn = tempInimigo.getHp();
                if(hpIn > 0) nave.sufferDamage(hpIn);
                if(hpNave > 0) tempInimigo.sufferDamage(hpNave);
            }
            for (Missil missil : misseis) {
                Missil tempMissil = (Missil) missil;
                formaMissil = tempMissil.getBounds();
                if(formaMissil.intersects(formaInimigo)){
                    int hp = tempInimigo.getHp(), dam = tempMissil.getPower();
                    if(dam > 0) tempInimigo.sufferDamage(dam);
                    if(hp > 0) tempMissil.losePower(hp);
                }
            }
        }
    }
    
    private class TecladoAdapter extends KeyAdapter{
        
        @Override
        public void keyPressed(KeyEvent e){
            if(!emJogo){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    inicializarFlags();
                    pause(false);
                }
                if(e.getKeyCode() == KeyEvent.VK_Q){
                    pai.sairJogo();
                }
                if(e.getKeyCode() == KeyEvent.VK_S){
                    MenuDificuldade md = new MenuDificuldade(pai.fase);
                    md.setLocationRelativeTo(null);
                    md.setVisible(true);
                    pai.setEnabled(false);
                }
            }else{ 
                if(e.getKeyCode() == KeyEvent.VK_P){
                    pause(!paused);
                    if(paused){
                        MenuPause mp = new MenuPause(pai.fase);
                        mp.setLocationRelativeTo(null); 
                        mp.setVisible(true);
                        pai.setEnabled(false);
                    }
                }
                if(!paused) nave.keyPressed(e);
            }
        }
        
        @Override
        public void keyReleased(KeyEvent e){
            if(!paused) nave.keyReleased(e);
        }
    }
}