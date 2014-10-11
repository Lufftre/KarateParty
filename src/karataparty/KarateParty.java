/**
 * Main class.
 * Holds all Minigames + Metagame + MainMenu and makes the transitions between them.
 */
package karataparty;

import karataparty.mainmenu.MainMenu;
import karataparty.mainmenu.MainMenuComponent;
import karataparty.metagame.Board;
import karataparty.metagame.PaintComponent;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private boolean mainmenu;
    private Random random = null;
    private int miniWinner;

    private java.util.List<AbstractMinigame> miniBoards = null;
    private List<AbstractComponent> miniComponents = null;

    private MainMenu mainMenu = null;
    private MainMenuComponent mainMenuComponent = null;

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
        createMiniGames();
        this.mainmenu = false;
        this.meta = true;
        this.random = new Random();
        determineNextMiniGame();
    }

    //Creates the Main menu.
    private void mainMenu(){
        mainmenu = true;
        mainMenu = new MainMenu(this);
        Image image = null;
        try {
            //noinspection HardcodedFileSeparator
            image = ImageIO.read(new File("src/karataparty/mainmenu/meny.png"));
        } catch (IOException ex){
            //noinspection ThrowablePrintedToSystemOut
            System.out.println(ex);
        }
        frame.setPreferredSize(new Dimension(1000,840));
        mainMenuComponent = new MainMenuComponent(image);
        frame.add(mainMenu, BorderLayout.SOUTH);
        frame.add(mainMenuComponent);
        frame.pack();
        createActions();
        createTimer();
        mainMenuComponent.boardChanged();

    }

    private void createMiniGames(){
        miniBoards = new ArrayList<>();
        miniComponents = new ArrayList<>();


        AbstractMinigame game;

        game = karataparty.ninjaSnake.Board.getBoardObject();
        miniBoards.add(game);
        miniComponents.add(new karataparty.ninjaSnake.PaintComponent(game));

        game = karataparty.sumoBird.Board.getBoardObject();
        miniBoards.add(game);
        miniComponents.add(new karataparty.sumoBird.PaintComponent(game));

        game = karataparty.judojuking.Board.getBoardObject();
        miniBoards.add(game);
        miniComponents.add(new karataparty.judojuking.PaintComponent(game));
    }

    private void initRandomMiniGame(){
        frame.remove(metaComponent);
    }

    private void determineNextMiniGame(){
        int n = random.nextInt(miniBoards.size());
        miniBoard = miniBoards.get(n);
        setMiniComponent(miniComponents.get(n));
        metaBoard.setNextMiniGame(miniBoard);
    }
    private void createBoard(){
        this.metaBoard = new Board(2);
    }

    private void createFrame(){
        this.frame = new JFrame();
        createMenus();
        frame.setVisible(true);
    }
    private void createMenus(){
        final JMenu file = new JMenu("File");
        final JMenuItem quit = new JMenuItem("Main Menu", 'Q');
        file.add(quit);

        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(file);
        frame.setJMenuBar(menuBar);

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = JOptionPane.showConfirmDialog(frame, "Quit to Main Menu?","niggawhat?", JOptionPane.YES_NO_OPTION);
                if(answer == JOptionPane.YES_OPTION){goToMainMenu();}
            }
        });
    }

    private void goToMainMenu(){resetAll();mainMenu();}

    private void createMetaComponent(){
        metaComponent = new PaintComponent(this.metaBoard);
        frame.add(metaComponent);
        metaComponent.setPreferredSize(metaComponent.getPreferredSize());

        frame.pack();
    }

    private void setMiniComponent(AbstractComponent component){
        this.miniComponent = component;
        //frame.remove(metaComponent);
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
                if(mainmenu){
                    mainMenuComponent.boardChanged();
                } else if(meta){

                    if(metaBoard.tick(metaComponent)){ //Meta round done
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
                        determineNextMiniGame();
                        metaBoard.addRound();
                        if(metaBoard.getCurrentRound() >= metaBoard.getnRounds()) {
                            goToMainMenu();
                        }
                    }
                }
            }
        };
    }

    private void resetAll(){
        frame.setVisible(false);
        createFrame();
        metaComponent = null;
        miniComponent = null;
        metaBoard = null;
        miniBoard = null;
        timer.stop();
        timer = null;
        tick = null;
        meta = false;
        miniBoards = null;
        miniComponents = null;
        mainMenu = null;
        mainMenuComponent = null;
    }

    public static void main(String[] args) {
        //noinspection ResultOfObjectAllocationIgnored
        new KarateParty();
    }
}
