package pkg_command;

import pkg_game.GameEngine;
import pkg_game.Player;
import pkg_item.Item;

/**
 *
 */
public class CloseCommand extends Command
{

    /**
     *
     */
    public CloseCommand()
    {

    } // CloseCommand()

    /**
     *
     */
    @Override public boolean execute( final GameEngine pEngine) {
        if ( !pEngine.getFightMode() ) {
            if (this.hasSecondWord()) {
                Player vPlayer = pEngine.getPlayer();
                Item vItem = vPlayer.getInventoryItem(this.getSecondWord());
                if (vItem != null) {
                    if (this.hasThirdWord()) {
                        pEngine.getGui().println("Cette commande ne prend pas de troisème mot.");
                        return false;
                    }
                    if (this.getSecondWord().equals("Carte")) {
                        pEngine.getGui().mapVisibility(false);
                        pEngine.getGui().println("Vous fermez la carte magique.");
                    } else {
                        pEngine.getGui().println("Seul la Carte peut être fermer");
                    }
                } else {
                    pEngine.getGui().println("Vous n'avez pas l'item " + this.getSecondWord() + " en votre possesion !");
                }
            } else {
                pEngine.getGui().println("Que voulez-vous fermer ?");
            }
        } else {
            pEngine.getGui().fightVisibilityPane(true);
            pEngine.getGui().addTextFight("Cette commande n'est pas disponible en mode combat.");
        }
        return false;
    } // execute(.)
} // CloseCommand
