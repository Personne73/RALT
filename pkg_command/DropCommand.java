package pkg_command;

 

import pkg_game.GameEngine;

/**
 * Cette classe réprésente la commande déposer
 *
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class DropCommand extends Command
{
    

    /**
     * Constructeur créer une commande déposer
     */
    public DropCommand()
    {
        
    } // DropCommand()

    /**
     * Fonction booléenne qui vérifie que la commande possède bien un second mot pour pouvoir
     * déposer l'objet
     * @param pEngine le moteur du jeu pour accèder aux éléménts nécéssaires
     * @return toujours faux
     */
    @Override public boolean execute( final GameEngine pEngine)
    {
        if ( !pEngine.getFightMode() ) {
            pEngine.getGui().visibilityInventory(false);
            if (this.hasSecondWord()) {
                if (this.hasThirdWord()) {
                    pEngine.getGui().println("Cette commande ne prend pas de troisème mot.");
                    return false;
                }
                pEngine.getPlayer().drop(this.getSecondWord());
                if (pEngine.getPlayer().getInventoryItem("Carte") != null) {
                    pEngine.getGui().setEnableMapB(false);
                }
            } else {
                pEngine.getGui().println("Vous ne pouvez déposer que les items que vous possédez !");
            }
        } else {
            pEngine.getGui().addTextFight("Cette commande n'est pas disponible en mode combat.");
        }
        return false;
    } // execute(.)
} // DropCommand
