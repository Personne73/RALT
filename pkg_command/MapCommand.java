package pkg_command;

import pkg_game.GameEngine;
import pkg_item.Item;
/**
 * Cette classe représente la commande carte.
 *
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class MapCommand extends Command
{

    /**
     * Constructeur pour créer une commande carte
     */
    public MapCommand()
    {
    } // MapCommand()

    /**
     * Fonction booléenne qui vérifie que la ligne de commande passé en paramètre ne possède
     * pas de second mot pour pouvoir assembler l'item ayant pour nom ce second mot
     * @param pEngine le moteur du jeu pour accèder aux éléménts nécéssaires
     * @return toujours faux
     */
    @Override public boolean execute( final GameEngine pEngine )
    {
        if ( !pEngine.getFightMode() ) {
            if (this.hasSecondWord()) {
                pEngine.getGui().println("La commande 'carte' ne prend pas de second mot !");
            } else {
                Item vMap = pEngine.getPlayer().getInventoryItem("Carte");
                if (vMap != null) {
                    pEngine.getGui().setEnableMapB(true);
                    pEngine.getGui().mapVisibility(true);
                    pEngine.getGui().println("Vous ouvrez la carte magique. Elle vous montre une image des lieux.");
                } else {
                    pEngine.getGui().setEnableMapB(false);
                    pEngine.getGui().println("Vous ne possédez pas la carte !");
                }
            }
        } else {
            pEngine.getGui().fightVisibilityPane(true);
            pEngine.getGui().addTextFight("Cette commande n'est pas disponible en mode combat.");
        }
        return false;
    } // execute(.)
} // MapCommand
