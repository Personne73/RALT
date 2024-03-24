package pkg_command;

 

import pkg_character.MonsterCharacter;
import pkg_game.GameEngine;
import pkg_game.Player;

/**
 * Cette classe représente la commande utiliser
 *
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class UseCommand extends Command
{
    

    /**
     * Constructeur pour créer une commande utiliser
     */
    public UseCommand()
    {
        
    } // UseCommand()

    /**
     * Fonction booléenne qui vérifie que la ligne de commande possède un second mot,
     * puis utilise l'objet qui a pour nom le second mot de commande.
     * @param pEngine le moteur du jeu pour accèder aux éléménts nécéssaires
     * @return toujours faux
     */
    @Override public boolean execute( final GameEngine pEngine )
    {

        if (!pEngine.getFightMode() ) {
            pEngine.getGui().visibilityInventory(false);
            if (this.hasSecondWord()) {
                if (this.hasThirdWord()) {
                    pEngine.getGui().println("Cette commande ne prend pas de troisème mot.");
                    return false;
                }
                pEngine.getPlayer().use(this.getSecondWord());
                Player vPlayer = pEngine.getPlayer();
                if ( vPlayer.getCurrentRoom().hasMonster() ){
                    MonsterCharacter vMonster = vPlayer.getCurrentRoom().getMonsterCharacter();
                    if ( vMonster.getTalkAttack() ) {
                        pEngine.getGui().fightVisibilityPane(true);
                        pEngine.setFightMode(true);
                        pEngine.getGui().enableButtons();
                        pEngine.getGui().setEnableAttackB(true);
                        pEngine.getGui().writeFight(vMonster.getTalkSentences() + "\n");
                        pEngine.getGui().showImage(vPlayer.getCurrentRoom().getImageName());
                        pEngine.getGui().addTextFight("\nUn combat est lancé : battez votre ennemi pour continuer !\n");
                        pEngine.getPlayer().getWeaponList();
                        pEngine.getGui().addTextFight("Vous avez " + pEngine.getPlayer().getPlayerLife() + " points de vie.\n");
                        return false;
                    }
                }
                if ( !this.getSecondWord().equals("Elixir") ) {
                    pEngine.printLocationInfo();
                }
            } else {
                pEngine.getGui().println("Vous devez spécifier l'item à utiliser.");
            }
        } else {
            pEngine.getGui().println("Cette commande n'est pas disponible en mode combat.");
        }
        return false;
    } // execute(.)
} // UseCommand
