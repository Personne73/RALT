package pkg_command;

 

import pkg_game.GameEngine;

/**
 * Cette classe représente la commande manger
 *
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class EatCommand extends Command
{
    

    /**
     * Constructeur pour créer une commande manger
     */
    public EatCommand()
    {
       
    } // EatCommand()

    /**
     * Fonction booléenne qui indique au joueur qu'il a mangé en lui affichant un message
     * @param pEngine le moteur du jeu pour accèder aux éléménts nécéssaires
     * @return toujours faux
     */
    @Override public boolean execute( final GameEngine pEngine)
    {
        if ( !pEngine.getFightMode() ) {
            pEngine.getGui().visibilityInventory(false);
            if (!this.hasSecondWord()) {
                pEngine.getGui().println(pEngine.getPlayer().eat());
            } else {
                pEngine.getGui().println("Vous ne pouvez pas manger d'objet en particulier !");
            }
        } else {
            pEngine.getGui().addTextFight("Cette commande n'est pas disponible en mode combat.");
        }
        return false;
    } // execute(.)
} // EatCommand
