package pkg_command;

 

import pkg_game.GameEngine;

/**
 * Cette classe représente la commande quitter
 *
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version (2021.05
 */
public class QuitCommand extends Command
{

    /**
     * Constructeur pour créer une commande quitter
     */
    public QuitCommand()
    {

    } // QuitCommand()

    /**
     * Fonction booléenne qui permet de vérifier que la commande ne possède pas de second mot
     * avant de quitter
     * @param pEngine le moteur du jeu pour accèder aux éléménts nécéssaires
     * @return vrai si la commande ne possède pas de second mot et qu'on doit quitter le jeu,
     * sinon faux et le jeu continue dans ce cas
     */
    @Override public boolean execute( final GameEngine pEngine)
    {
        pEngine.getGui().visibilityInventory(false);
        if ( this.hasSecondWord() ){
            pEngine.getGui().println("Quitter quoi ?");
            return false;
        } else {
            pEngine.endGame();
            return true;
        }
    } // execute(.)
} // QuitCommand
