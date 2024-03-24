package pkg_character;


import pkg_item.ItemList;
import pkg_item.Item;

/**
 * Cette classe permet de créer des PNJ
 *
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class Character
{
    private String aCharacterName;
    private ItemList aInventory;
    private String aTalkSentences;

    /**
     * Constructeur naturel qui permet de créer un PNJ
     * @param pCharacterName le nom du PNJ
     * @param pTalkSentences la/les phrase(s) que le PNJ dira lorsque le joueur lui parlera
     */
    public Character( final String pCharacterName,  final String pTalkSentences )
    {
        this.aCharacterName = pCharacterName;
        this.aInventory = new ItemList();
        this.aTalkSentences = pTalkSentences;
    } // Character()

    /**
     * Accesseur qui permet d'avoir le nom du personnage non joueur
     * @return le nom du PNJ
     */
    public String getCharacterName()
    {
        return this.aCharacterName;
    } // getCharacterName()

    /**
     * Accesseur qui permet de retourner l'item de l'inventaire
     * du PNJ correspondant au nom passer en paramètre s'il existe
     * @param pItemName le nom de l'item recherché
     * @return l'item correspondant au nom passé en paramètre
     */
    public Item getInventoryItem( final String pItemName ) {
        return this.aInventory.getItem(pItemName);
    } // getInventory(.)

    /**
     *
     */
    public String getTraderItems()
    {
        return this.aInventory.getTraderList();
    }

    /**
     * Accesseur qui permet  d'avoir les répliques du personnages non joueur
     * @return les phrases que le PNJ dit lorsque le joueur rentre en contact avec lui
     */
    public String getTalkSentences()
    {
        return this.aCharacterName + " :\n" + this.aTalkSentences;
    } // getTalkSentences()

    /**
     *
     */
    public void setTalkSentences( final String pTalkSentences )
    {
        this.aTalkSentences = pTalkSentences;
    }

    /**
     * Procédure qui permet d'ajouter un item à l'inventaire du PNJ
     * @param pItem l'item à ajouter
     */
    public void addInventoryItem( final Item pItem )
    {
        this.aInventory.addItemList(pItem);
    } // addInventoryItem(.)

    /**
     * Procédure qui permet de retirer un item de l'inventaire du PNJ
     * @param pItemName le nom de l'item à retiré
     */
    public void removeInventoryItem( final String pItemName )
    {
        this.aInventory.removeItemList(pItemName);
    } // removeInventoryItem(.)

} // Character
