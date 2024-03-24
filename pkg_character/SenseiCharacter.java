package pkg_character;

import pkg_game.GameEngine;
import pkg_game.Player;
import pkg_item.Item;

public class SenseiCharacter extends Character {

    /**
     *
     */
    public SenseiCharacter(final String pCharacterName, final String pTalkSentences) {
        super(pCharacterName, pTalkSentences);
    } // SenseiCharacter

    /**
     *
     */
    public void giveBackItem(final String pItemName, final GameEngine pEngine) {
        Player vPlayer = pEngine.getPlayer();
        Item vItem = vPlayer.getInventoryItem(pItemName);

        if ( vItem != null ) {

            if ( pItemName.equals("Poigné") ){
                Item vGiveBack = this.getInventoryItem("Protector");

                if ( vPlayer.canBeTake(vGiveBack) ){
                    this.addInventoryItem(vItem);
                    this.removeInventoryItem("Protector");
                    vPlayer.addInventoryItem(vGiveBack);
                    vPlayer.removeInventoryItem(pItemName);
                    pEngine.getGui().println(this.getCharacterName() + " :\nTu sembles aimé le combat... Tu m'as l'air avide de pouvoir Voyageur." +
                            " En tant qu'Ancienne et Sensei, je vois très bien tes capacités. Compétent tu me parais... Les recherches des Anciens ont déterminé" +
                            " qu'il s'agit d'une des armes utilisée par Arès lui même.\n" +
                            "Je te donne le Protector et te considère comme un de mes élèves Voyageur !");
                } else {
                    pEngine.getGui().println(this.getCharacterName() + ":\nTu ne possédez pas assez de place dans ton inventaire Voyageur ! " +
                            "Reviens me voir quand tu auras fais le nécessaire.");
                    return;
                }
            } else if ( pItemName.equals("Flacon") ){
                Item vGiveBack = this.getInventoryItem("Elixir");

                if ( vPlayer.canBeTake(vGiveBack) ){
                    this.addInventoryItem(vItem);
                    this.removeInventoryItem("Elixir");
                    vPlayer.addInventoryItem(vGiveBack);
                    vPlayer.removeInventoryItem(pItemName);
                    pEngine.getGui().println(this.getCharacterName() + " :\nTu sembles aimé le combat... Tu m'as l'air avide de pouvoir Voyageur." +
                            " En tant qu'Ancienne et Sensei, je vois très bien tes capacités. Des épreuves arrivent encore... \nJe te donne cet Elixir" +
                            " et te considère comme un de mes élèves Voyageur !");
                } else {
                    pEngine.getGui().println(this.getCharacterName() + ":\nTu ne possédez pas assez de place dans ton inventaire Voyageur ! " +
                            "Reviens me voir quand tu auras fais le nécessaire.");
                    return;
                }
            } else if ( pItemName.equals("Plume") ){
                Item vGiveBack = this.getInventoryItem("Page");

                if ( vPlayer.canBeTake(vGiveBack) ){
                    this.addInventoryItem(vItem);
                    this.removeInventoryItem("Page");
                    vPlayer.addInventoryItem(vGiveBack);
                    vPlayer.removeInventoryItem(pItemName);
                    pEngine.getGui().println(this.getCharacterName() + " :\nLe savoir semble t'être important... Tu mérites de connaitre..." +
                            " En tant qu'Ancienne et Sensei, je vois très bien tes grandes connaissances. Avide de savoir tu me parais... Je te donne " +
                            "la Page et te considère comme un de mes élèves Voyageur !");
                } else {
                    pEngine.getGui().println(this.getCharacterName() + ":\nTu ne possédez pas assez de place dans ton inventaire Voyageur ! " +
                            "Reviens me voir quand tu auras fais le nécessaire.");
                    return;
                }
            } else if ( pItemName.equals("Liste") ){
                Item vGiveBack = this.getInventoryItem("Poussière");

                if ( vPlayer.canBeTake(vGiveBack) ){
                    this.addInventoryItem(vItem);
                    this.removeInventoryItem("Poussière");
                    vPlayer.addInventoryItem(vGiveBack);
                    vPlayer.removeInventoryItem(pItemName);
                    pEngine.getGui().println(this.getCharacterName() + " :\nL'Alchimie t'intrigue toi aussi... Tu peux les trouver..." +
                            " En tant qu'Ancienne et Sensei, je vois très bien tes capacités. Seul un membre du conseil de la Grande Sorcelerie en saurait l'utilité." +
                            " Ils se réunissent tous les 100 ans. C'est pour bientot... Tu trouveras peut être...  \nJe te donne la Poussière" +
                            " et te considère comme un de mes élévès Voyageur !");
                } else {
                    pEngine.getGui().println(this.getCharacterName() + ":\nTu ne possédez pas assez de place dans ton inventaire Voyageur ! " +
                            "Reviens me voir quand tu auras fais le nécessaire.");
                    return;
                }
            } else {
                    pEngine.getGui().println(this.getCharacterName() + " :\nJe ne peux pas prendre cet objet !");
            }
        } else {
            pEngine.getGui().println(this.getCharacterName() + " :\nOn dirait que tu ne possèdes pas l'item que tu veux me donner Voyageur");
        }
    } // giveBackItem(..)

} // SenseiCharacter
