package pkg_command;

import pkg_game.GameEngine;
import pkg_game.Fight;
import pkg_character.MonsterCharacter;
/**
 *
 */
public class AttackCommand extends Command
{

    /**
     *
     */
    public AttackCommand()
    {

    } // AttackCommand()

    /**
     *
     */
    @Override public boolean execute( final GameEngine pEngine ) {
        pEngine.getGui().fightVisibilityPane(true);
        if ( pEngine.getFightMode() ){
            MonsterCharacter vMonsterCharacter = pEngine.getPlayer().getCurrentRoom().getMonsterCharacter();
            Fight vFight = new Fight(vMonsterCharacter, pEngine);
            if ( this.hasSecondWord() ){
                if ( this.getSecondWord().equals("avec") ){
                    if ( this.hasThirdWord() ){
                        vFight.playerAttack(this.getThirdWord());
                        if ( vFight.getPlayerHasAttacked() ){
                            pEngine.getGui().addTextFight("\n");
                            if ( vMonsterCharacter.getMonsterLife() > 0 ) vFight.monsterAttack();
                        }
                        vFight.endFight();
                    } else {
                        pEngine.getGui().addTextFight("Avec quel arme voulez-vous attaquer ?");
                    }
                } else {
                    pEngine.getGui().addTextFight("Le second mot de la commande peut seulement être : avec");
                }

            } else {
                vFight.playerHandsAttack();
                pEngine.getGui().addTextFight("\n");
                if ( vMonsterCharacter.getMonsterLife() > 0 ) vFight.monsterAttack();
                vFight.endFight();
            }
        } else {
            pEngine.getGui().addTextFight("Cette commande n'est disponible qu'en mode combat.");
        }
        return false;
    } // execute(.)
} // AttackCommand
