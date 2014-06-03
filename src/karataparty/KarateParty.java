/**
 * Main class.
 * Administrational class.
 * Holds all Minigames + Metagame + MainMenu and makes the transitions between them.
 */
package karataparty;

import karataparty.metagame.Board;
import karataparty.metagame.PaintComponent;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by the karatekidz on 26/03/14.
 */
public class KarateParty {
    private JFrame frame;

    private PaintComponent metaComponent = null;
    private AbstractComponent miniComponent = null;

    private Board metaBoard = null;
    private AbstractMinigame miniBoard = null;
    private Timer timer = null;
    private Action tick = null;

    private boolean meta;
    private Random random = null;
    private int miniWinner;

    private java.util.List<AbstractMinigame> miniBoards = null;
    private List<AbstractComponent> miniComponents = null;

    private MainMenu mainMenu;
    private MainMenuComponent mainMenuComponent;

    public KarateParty() {
        createFrame();
        mainMenu();
    }

    //MainMenu StartPress
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

    //Creates the Main menu.
    private void mainMenu(){
        mainMenu = new MainMenu(this);
        Image image = null;
        try {
            //noinspection HardcodedFileSeparator
            image = ImageIO.read(new File("src/karataparty/meny.png"));
        } catch (IOException ex){
            //noinspection ThrowablePrintedToSystemOut
            System.out.println(ex);
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
    /*
        game = new karataparty.ninjasnake.Board();
        miniBoards.add(game);
        miniComponents.add(new karataparty.ninjasnake.PaintComponent(game));

        game = new karataparty.sumobird.Board();
        miniBoards.add(game);
        miniComponents.add(new karataparty.sumobird.PaintComponent(game));
     */
        game = new karataparty.judojuking.Board();
        miniBoards.add(game);
        miniComponents.add(new karataparty.judojuking.PaintComponent(game));
    }

    private void initRandomMiniGame(){
        int n = random.nextInt(miniBoards.size());
        miniBoard = miniBoards.get(n);
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
        metaComponent.setPreferredSize(metaComponent.getPreferredSize());

        frame.pack();
    }

    private void setMiniComponent(AbstractComponent component){
        this.miniComponent = component;
        frame.remove(metaComponent);
        frame.add(miniComponent);

        miniComponent.setPreferredSize(miniComponent.getPreferredSize());
        frame.pack();
    }

    private void setMetaComponent(){
        frame.remove(miniComponent);
        frame.add(metaComponent);

        metaComponent.setPreferredSize(metaComponent.getPreferredSize());
        frame.pack();
    }


    private void createTimer(){
        this.timer = new Timer(17, this.tick);
        timer.setCoalesce(true);
        timer.start();
    }

    //Pause program for n ms
    private void sleep(int n){
        try {
            Thread.sleep(n);
        } catch(InterruptedException ex) {
            //noinspection ThrowablePrintedToSystemOut
            System.out.println(ex);
            Thread.currentThread().interrupt();
        }
    }

    private void createActions(){
        //Main Loop
        this.tick = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if(meta){ //Meta round done
                    if(metaBoard.tick(metaComponent)){
                        meta = false;
                        initRandomMiniGame();
                        sleep(1000);
                    }
                } else {
                    miniWinner = miniBoard.tick(miniComponent);
                    if(miniWinner != -1){ //MInigame done
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
        //noinspection ResultOfObjectAllocationIgnored
        new KarateParty();
    }
}
