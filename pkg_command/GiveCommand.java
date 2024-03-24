package pkg_command;


import pkg_game.GameEngine;
import pkg_item.Item;
import pkg_character.Character;
import pkg_character.SenseiCharacter;
/**
 *
 */
public class GiveCommand extends Command
{

    /**
     *
     */
    public GiveCommand()
    {

    } // GiveCommand()

    /**
     *
     */
    @Override public boolean execute( final GameEngine pEngine )
    {
        if ( !pEngine.getFightMode() ) {
            pEngine.getGui().visibilityInventory(false);
            if (this.hasSecondWord()) {
                Item vItem = pEngine.getPlayer().getInventoryItem(this.getSecondWord());
                if (this.hasThirdWord()) {
                    Character vPnj = pEngine.getPlayer().getCurrentRoom().getCharacter(this.getThirdWord());
                    if (vItem != null) {
                        if (vPnj != null) {
                            if (vPnj.getCharacterName().equals("Protecteur")) {
                                if (vItem.getItemName().equals("Pendentif")) {
                                    pEngine.getGui().println(vPnj.getCharacterName() + " : \n( Ému ) Snif, merci d'avoir retrouver mon pendentif !" +
                                            "\nIl est vraiment très précieux à mes yeux. Vous meritez que je vous apporte mon aide étranger :" +
                                            "\nVoici une carte des lieux, je l'ai hérité de la longue lignée des Protecteurs. Elle analyse les lieux dans lequelle " +
                                            "elle se trouve pour générer un image représentant une carte.");
                                    pEngine.getPlayer().removeInventoryItem(vItem.getItemName());
                                    vPnj.addInventoryItem(vItem);
                                    Item vReward = vPnj.getInventoryItem("Carte");
                                    pEngine.getPlayer().addInventoryItem(vReward);
                                    pEngine.getGui().setEnableMapB(true);
                                    vPnj.removeInventoryItem("Carte");
                                    vPnj.setTalkSentences("Vous avez ma bénédiction voyageur !");
                                    return false;
                                } else if ( vItem.getItemName().equals("Anneau") ) {
                                    pEngine.getGui().fightVisibilityPane(true);
                                    pEngine.setFightMode(true);
                                    pEngine.getGui().enableButtons();
                                    pEngine.getGui().setEnableAttackB(true);
                                    pEngine.getGui().writeFight("Protecteur :\n Ce n'est pas l'objet que je recherche. Je vous avais prévenu étranger, il ne fallait" +
                                            "pas me déranger.\n");
                                    pEngine.getGui().addTextFight("\nUn combat est lancé : battez votre ennemi pour continuer !\n");
                                    pEngine.getPlayer().getWeaponList();
                                    pEngine.getGui().addTextFight("Vous avez " + pEngine.getPlayer().getPlayerLife() + " points de vie.\n");
                                    return false;
                                }
                            }
                            if (vPnj.getCharacterName().equals("Ancienne")) {
                                SenseiCharacter vSensei = (SenseiCharacter) (vPnj);
                                vSensei.giveBackItem(vItem.getItemName(), pEngine);
                                vSensei.setTalkSentences("Que le Créateur de cette Tour t'accorde sa bénédiction !");
                                return false;
                            }
                            pEngine.getGui().println(vPnj.getCharacterName() + " : \nJe ne peux pas prendre cet objet !");
                        } else {
                            pEngine.getGui().println("L'Habitant auquel vous voulez donner l'item n'existe pas dans cette pièce.");
                        }
                    } else {
                        pEngine.getGui().println("Vous ne possédez pas l'item que vous voulez donner.");
                    }
                } else {
                    pEngine.getGui().println("A quel habitant voulez-vous donner un item ?");
                }
            } else {
                pEngine.getGui().println("Quel item voulez-vous donner ?");
            }
        } else {
            pEngine.getGui().addTextFight("Cette commande n'est pas disponible en mode combat.");
        }
        return false;
    }
} // GiveCommand
