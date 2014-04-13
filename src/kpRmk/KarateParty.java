package kpRmk;

import kpRmk.metagame.Board;
import kpRmk.metagame.PaintComponent;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by the karatekidz on 26/03/14.
 */
public class KarateParty {
    private JFrame frame;

    private PaintComponent metaComponent;
    private AbstractComponent miniComponent;

    private Board metaBoard;
    private AbstractMinigame miniBoard;
    private Timer timer;
    private Action tick;

    private boolean meta;
    private Random random;
    private int miniWinner;

    private ArrayList<AbstractMinigame> miniBoards;
    private ArrayList<AbstractComponent> miniComponents;

    private MainMenu mainMenu;
    private MainMenuComponent mainMenuComponent;

    public KarateParty() {
        createFrame();
        mainMenu();
    }

    public void startGame(){
        frame.remove(mainMenu);
        frame.remove(mainMenuComponent);
        createBoard();
        createMetaComponent();
        createActions();
        createTimer();
        createMiniGames();
        this.meta = true;
        this.random = new Random();
    }

    private void mainMenu(){
        mainMenu = new MainMenu(this);
        Image image = null;
        try {
            image = ImageIO.read(new File("src/kpRmk/meny.png"));
        } catch (IOException ex) {
            // handle exception...
        }
        frame.setPreferredSize(new Dimension(1000,840));
        mainMenuComponent = new MainMenuComponent(image);
        frame.add(mainMenu, BorderLayout.SOUTH);
        frame.add(mainMenuComponent);
        frame.pack();
        mainMenuComponent.boardChanged();

    }

    private void createMiniGames(){
        miniBoards = new ArrayList<>();
        miniComponents = new ArrayList<>();


        AbstractMinigame game;

        game = new kpRmk.ninjaSnake.Board();
        miniBoards.add(game);
        miniComponents.add(new kpRmk.ninjaSnake.PaintComponent(game));
       // */
      //  /*
        game = new kpRmk.sumoBird.Board();
        miniBoards.add(game);
        miniComponents.add(new kpRmk.sumoBird.PaintComponent(game));
       // */
    }

    private void initRandomMiniGame(){
        int n = random.nextInt(miniBoards.size());
        setMiniBoard(miniBoards.get(n));
        setMiniComponent(miniComponents.get(n));
    }
    private void createBoard(){
        this.metaBoard = new Board(2);
    }

    private void createFrame(){
        this.frame = new JFrame();
        frame.setVisible(true);
    }
    private void createMetaComponent(){
        metaComponent = new PaintComponent(this.metaBoard);
        frame.add(metaComponent);
        metaComponent.setPreferredSize(metaComponent.getPreferredSize(metaBoard.getSize()));

        frame.pack();
    }

    private void setMiniComponent(AbstractComponent component){
        this.miniComponent = component;
        frame.remove(metaComponent);
        frame.add(miniComponent);

        miniComponent.setPreferredSize(miniComponent.getPreferredSize(metaBoard.getSize()));
        frame.pack();
    }
    private void setMiniBoard(AbstractMinigame minigame){
        this.miniBoard = minigame;
    }

    private void setMetaComponent(){
        frame.remove(miniComponent);
        frame.add(metaComponent);

        metaComponent.setPreferredSize(metaComponent.getPreferredSize(metaBoard.getSize()));
        frame.pack();
    }


    private void createTimer(){
        this.timer = new Timer(17, this.tick);
        timer.setCoalesce(true);
        timer.start();
    }

    private void sleep(int n){
        try {
            Thread.sleep(n);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private void createActions(){
        this.tick = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if(meta){
                    if(metaBoard.tick(metaComponent)){
                        meta = false;
                        initRandomMiniGame();
                        sleep(1000);
                    }
                } else {
                    miniWinner = miniBoard.tick(miniComponent);
                    if(miniWinner != -1){
                        miniComponent.boardChanged();
                        meta = true;
                        setMetaComponent();
                        metaBoard.newRound(miniWinner);
                        miniBoard.resetBoard();
                        sleep(1000);
                    }
                }
            }
        };
    }

    public static void main(String[] args) {
        new KarateParty();
    }
}
