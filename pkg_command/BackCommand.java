package pkg_command;

 

import pkg_game.GameEngine;

/**
 * Cette classe représente la commande 'retour'
 *
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class BackCommand extends Command
{
    

    /**
     * Constructeur pour créer une nouvelle commande retour
     */
    public BackCommand()
    {
        
    } // BackCommand()

    /**
     * Fonction booléenne qui vérifie l'absence de second mot, et qui change
     * la salle courante en revenant dans une salle précédemment visitée
     * @param pEngine le moteur du jeu pour accèder aux éléménts nécéssaires
     * @return toujours faux
     */
    @Override public boolean execute( final GameEngine pEngine)
    {
        if ( !pEngine.getFightMode() ) {
            pEngine.getGui().visibilityInventory(false);
            if (this.hasSecondWord()) {
                pEngine.getGui().println("La commande 'retour' n'accepte pas de second mot !");
                return false;
            }

            pEngine.moveCharacter();
            pEngine.getPlayer().back();
            pEngine.printLocationInfo();
        } else {
            pEngine.getGui().addTextFight("Cette commande n'est pas disponible en mode combat.");
        }
        return false;
    } // execute(.)
} // BackCommand
