package pkg_command;

 

import pkg_game.GameEngine;

/**
 * Cette classe représente la commande assembler.
 *
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class BuildCommand extends Command
{
    

    /**
     * Constructeur pour créer une commande assembler
     */
    public BuildCommand()
    {
        
    } // BuildCommand()

    /**
     * Fonction booléenne qui vérifie que la ligne de commande passé en paramètre possède
     * un second mot pour pouvoir assembler l'item ayant pour nom ce second mot
     * @param pEngine le moteur du jeu pour accèder aux éléménts nécéssaires
     * @return toujours faux
     */
    @Override public boolean execute( final GameEngine pEngine )
    {
        if ( !pEngine.getFightMode() ) {
            pEngine.getGui().visibilityInventory(false);
            if (this.hasSecondWord()) {
                if (this.hasThirdWord()) {
                    pEngine.getGui().println("Cette commande ne prend pas de troisème mot.");
                    return false;
                }
                if (pEngine.getPlayer().build(this.getSecondWord())) {
                    pEngine.getPlayer().addInventoryItem(pEngine.getTalisman());
                }
            } else {
                pEngine.getGui().println("Quel item voulez-vous à assembler ?");
            }
        } else {
            pEngine.getGui().addTextFight("Cette commande n'est pas disponible en mode combat.");
        }
        return false;
    } // execute(.)
} // BuildCommand
