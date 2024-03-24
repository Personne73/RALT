package pkg_command;

 

import pkg_character.MonsterCharacter;
import pkg_game.GameEngine;
import pkg_game.Player;
import pkg_room.Room;
import pkg_room.Door;
/**
 * Cette classe représente la commande aller
 *
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version (un2021.05
 */
public class GoCommand extends Command
{

    /**
     * Constructeur pour créer une commande aller
     */
    public GoCommand()
    {  
    } // GoCommand()

    /**
     * Fonction booléenne pour changer de pièce s'il y a bien une porte, et si elle est ouverte
     * @param pEngine le moteur du jeu pour accèder aux éléménts nécéssaires
     * @return toujours faux
     */
    @Override public boolean execute( final GameEngine pEngine )
    {

        if ( !pEngine.getFightMode() ) {
            pEngine.getGui().visibilityInventory(false);
            if (!this.hasSecondWord()) {
                pEngine.getGui().println("Aller où ?");
                return false;
            }

            String vDirection;
            if (this.hasThirdWord()) {
                vDirection = this.getSecondWord() + " " + this.getThirdWord();
            } else vDirection = this.getSecondWord();

            Room vNextRoom = pEngine.getPlayer().getCurrentRoom().getExit(vDirection);

            if (vNextRoom == null) {
                pEngine.getGui().println("Il n'y a pas de porte !");
                return false;
            }

            // si il y a une salle dans la direction, on test maintenant si il y a une porte
            Door vNextRoomDoor = pEngine.getPlayer().getCurrentRoom().getDoor(vDirection);
            if (vNextRoomDoor != null) {
                if ( !vNextRoomDoor.getCanBeCrossed() ) { // si la porte  ne peut pas être traversée
                    pEngine.getGui().println("La porte est vérouillée, vous devez trouver la clé afin de la dévérouillée pour pouvoir passer !");
                    return false;
                } else {
                    pEngine.getPlayer().setCurrentRoom(vNextRoom);
                    vNextRoomDoor.setCanBeCrossed(false);
                }
            }

            pEngine.moveCharacter();
            pEngine.getPlayer().setCurrentRoom(vNextRoom);


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
                } else {
                    pEngine.printLocationInfo();
                    pEngine.getGui().println("Vous avez " + pEngine.getPlayer().getPlayerLife() + " points de vie.");
                }
            } else {
                if (vPlayer.getCurrentRoom().getIsDark()) {
                    pEngine.getGui().showImage(vPlayer.getCurrentRoom().getImageName());
                    pEngine.getGui().println(vPlayer.getCurrentRoom().getDarkRoomDescription());
                    pEngine.getGui().println("Vous avez " + pEngine.getPlayer().getPlayerLife() + " points de vie.");
                } else {
                    pEngine.printLocationInfo();
                    pEngine.getGui().println("Vous avez " + pEngine.getPlayer().getPlayerLife() + " points de vie.");
                }
            }

        } else {
            pEngine.getGui().addTextFight("Cette commande n'est pas disponible en mode combat.");
        }
        return false;
    } // execute(.)
} // GoCommand
