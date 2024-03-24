import pkg_game.GameEngine;
import pkg_game.UserInterface;

/**
 * La classe Game permet d'initialiser le jeu en créant un moteur ainsi qu'une interface graphique
 * 
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class Game
{
    private UserInterface aGui;
    private GameEngine aEngine;
   
    /**
     * Constructeur par défaut pour créer un nouveau jeu
     */
    public Game()
    {
        this.aEngine = new GameEngine();
        this.aGui = new UserInterface(this.aEngine);
        this.aEngine.setGUI(this.aGui);
    } // Game()  
    
    /**
     * Méthode principale du jeu
     */
    public static void main(String[] args){
        new Game();
    } // main(.)
    
} // Game