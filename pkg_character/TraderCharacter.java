package pkg_character;



import java.util.ArrayList;
import pkg_game.GameEngine;
import pkg_item.Item;
import pkg_game.Player;

/**
 *
 */
public class TraderCharacter extends MovingCharacter
{

    /**
     *
     */
    public TraderCharacter( final String pCharacterName, final String pTextSentences, final ArrayList pRoomList )
    {
        super(pCharacterName, pTextSentences, pRoomList);
    } // TraderCharacter(...)

    /**
     *
     */
    @Override public String getTalkSentences()
    {
        return super.getTalkSentences() + "\n" + this.getTraderItems();
    } // getTalkSentences()

    /**
     *
     */
    public void buy( final String pItemName, final GameEngine pEngine )
    {
        Player vPlayer = pEngine.getPlayer();
        Item vItem = this.getInventoryItem(pItemName);

        if ( vItem != null ){
            int vItemPrice = vItem.getItemPrice();
            if ( vPlayer.getPlayerMoney() >= vItemPrice ){
                if ( vPlayer.canBeTake(vItem) ){
                    if ( pItemName.equals("Potion") ){
                        if ( vPlayer.getInventoryItem("Potion") != null ) {
                            pEngine.getGui().println(this.getCharacterName() + " :\nJe crois que tu possèdes déjà cette item dans ton inventaire." +
                                    " L'Association des Marchands a décidé qu'on ne peut plus vendre plusieurs Potion a un joueur. Achète moi un " +
                                    "autre item." );
                            return;
                        }
                    }
                    vPlayer.addInventoryItem(vItem);
                    this.removeInventoryItem(pItemName);
                    vPlayer.setPlayerMoney(vPlayer.getPlayerMoney() - vItemPrice);
                    pEngine.getGui().println(this.getCharacterName() + " :\nC'est un plaisir de faire affaire avec toi mercenaire." +
                            " Tu possède maintenant l'item : " + pItemName + " . Merci pour les " + vItemPrice + " Coins." );
                } else {
                    pEngine.getGui().println(this.getCharacterName() + " :\nTon Bracelet m'indique que tu n'a plus assez d'espace de stockage" +
                            " pour acheter cet item mercenaire !");
                }
            } else {
                pEngine.getGui().println(this.getCharacterName() + " :\nTu ne possède pas assez de Coins mercenaire !");
            }
        } else {
            pEngine.getGui().println(this.getCharacterName() + " :\nJe ne possède pas l'item que tu veux m'acheter mercenaire.");
        }
    } // buy(..)

    /**
     *
     */
    public void sale( final String pItemName, final GameEngine pEngine )
    {
        Player vPlayer = pEngine.getPlayer();
        Item vItem = vPlayer.getInventoryItem(pItemName);

        if ( vItem != null ){
            int vItemPrice = vItem.getItemPrice();
            if ( vItemPrice == 1000000 ){
                pEngine.getGui().println(this.getCharacterName() + " :\nJe ne peux pas t'acheter cet item, il est beaucoup trop précieux.");
                return;
            }
            if ( pItemName.equals("Potion") ) {
                pEngine.getGui().println(this.getCharacterName() + " :\nTu en auras besoin, je ne peux pas t'acheter cet item !");
                return;
            }
            this.addInventoryItem(vItem);
            vPlayer.removeInventoryItem(pItemName);
            int vGiveBackMoney = (int)(vItemPrice*0.6);
            vPlayer.setPlayerMoney(vPlayer.getPlayerMoney() + vGiveBackMoney);
            pEngine.getGui().println(this.getCharacterName() + " :\nTransaction effectuée. Je viens de t'acheter l'item : " + pItemName
                    + " et tu viens de recevoir : " + vGiveBackMoney + " Coins.");
        } else {
            pEngine.getGui().println(this.getCharacterName() + " :\nTu sembles ne pas posséder l'item que tu veux me vendre mercenaire !");
        }
    } // sale(..)

    /**
     *
     */
    public void inspectTraderItem( final String pItemName , final GameEngine pEngine )
    {
        Item vInspectItem = this.getInventoryItem(pItemName);
        if ( vInspectItem != null ){
            pEngine.getGui().println(this.getCharacterName() + " :\n" + vInspectItem.getLongItemDescription() );
        } else {
            pEngine.getGui().println(this.getCharacterName() + " :\nJe ne possède pas l'item dont tu veux des détails mercenaire.");
        }
    } // inspectItem(.)
} // TraderCommand
