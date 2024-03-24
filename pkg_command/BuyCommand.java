package pkg_command;

import pkg_character.Character;
import pkg_game.GameEngine;
import pkg_character.TraderCharacter;
/**
 *
 */
public class BuyCommand extends Command
{

    /**
     *
     */
    public BuyCommand()
    {

    } // BuyCommand()

    /**
     *
     */
    @Override public boolean execute( final GameEngine pEngine )
    {
        if ( !pEngine.getFightMode() ) {
            pEngine.getGui().visibilityInventory(false);
            if (this.hasSecondWord()) {
                if (this.hasThirdWord()) {
                    Character vPnj = pEngine.getPlayer().getCurrentRoom().getCharacter(this.getThirdWord());
                    if (vPnj != null) {
                        if (vPnj.getCharacterName().equals("Marchand")) {
                            TraderCharacter vMarchand = (TraderCharacter) (vPnj);
                            vMarchand.buy(this.getSecondWord(), pEngine);
                        } else {
                            pEngine.getGui().println("Seul le Marchand peut vous vendre des items !");
                        }
                    } else {
                        pEngine.getGui().println("L'Habitant auquel vous voulez acheter cet item n'existe pas dans cette pièce.");
                    }
                } else {
                    pEngine.getGui().println("A qui voulez-vous acheter cet item ?");
                }
            } else {
                pEngine.getGui().println("Que voulez-vous acheter ?");
            }
        } else {
            pEngine.getGui().addTextFight("Cette commande n'est pas disponible en mode combat.");
        }
        return false;
    }
}
