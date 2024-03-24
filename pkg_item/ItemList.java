package pkg_item;

import java.util.HashMap;
import java.util.Set;
/**
 * Cette classe permet de créer une liste associant un nom et son item
 * 
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class ItemList
{
    private HashMap<String, Item> aItemList;

    /**
     * Constructeur qui initialise l'attribut
     */
    public ItemList()
    {
        this.aItemList = new HashMap<String, Item>();
    } // ItemList()

    /**
     * Accesseur qui retourne l'item de la liste correspondant
     * à celui rentré en paramètre
     * @param pItemName le nom de l'item recherché
     * @return l'item possédant le nom passé en paramètre
     */
    public Item getItem( final String pItemName ) 
    {
        return this.aItemList.get(pItemName);
    } // getItem(.)

    /**
     * Accesseur qui retourne la liste des items contenus dans liste
     * accompagnés de leur poids
     * @return une chaine de caractère contenant les items et leur poids
     */
    public String getItemList()
    {
        if( this.aItemList.size() > 0 ){
            String vInventory = "Vous possédez :\n";
            Set<String> vKeys = this.aItemList.keySet();
            for(String vItemName : vKeys){
                Item vReturn = this.aItemList.get(vItemName);
                vInventory += "- " + vReturn.getItemName() + " (Poids : " + vReturn.getItemWeight() + " blocs)\n";
            }
            return vInventory + "Poids de tout les items stockés : " + this.getItemListWeight() + " blocs\n";
        }
        return "L'inventaire est vide.";
    } // getItemList()

    /**
     * Accesseur qui retourne une chaine de caractère
     * contenant tout les items de la liste accompagnés 
     * de leurs descriptions
     * @return les items de la liste et leurs descriptions.
     */
    public String getItemListString()
    {
        if( this.aItemList.size() > 0 ){
            String vItemsDescriptions = "Item(s) disponible(s) :\n";
            Set<String> vKeys = this.aItemList.keySet();

            for(String vItemName : vKeys){
                if ( this.aItemList.size() == 1 && vItemName.equals("Dominator") ){
                    return "Il n'y a pas d'item(s)";
                }
                if ( !vItemName.equals("Dominator") ) {
                    Item vReturn = this.aItemList.get(vItemName);
                    vItemsDescriptions += vReturn.getLongItemDescription() + "\n";
                }
            }
            return vItemsDescriptions;
        }
        return "Il n'y a pas d'item(s)";
    } // getItemListString()

    /**
     *
     */
    public String getTraderList()
    {
        if ( this.aItemList.size() > 0 ){
            String vSentence = "J'ai de fabuleux items à te proposer :\n";
            Set<String> vKeys = this.aItemList.keySet();

            for(String vItemName : vKeys){
                Item vItem = this.aItemList.get(vItemName);
                vSentence += "- " + vItemName + " : Poids " + vItem.getItemWeight() + " blocs | Prix : " + vItem.getItemPrice() + " Coins\n";
            }
            return vSentence;
        }
        return "Je n'ai rien à te vendre.";
    }

    /**
     *
     */
    public String getWeaponList()
    {
        if ( this.aItemList.size() > 0 ){
            String vWeaponsNames = "Les armes suivantes sont stockés dans votre Bracelet :\n";
            Set<String> vKeys = this.aItemList.keySet();
            for ( String vItemName : vKeys ){
                Item vItem = this.aItemList.get(vItemName);
                if ( vItem instanceof Weapon ){
                    Weapon vWeapon = (Weapon)(vItem);
                    vWeaponsNames +=  "- Rang : " + vWeapon.getWeaponRank() + " | Nom : " + vItemName + " | Dégats : "
                            + vWeapon.getWeaponDamage() + "\n";
                }
            }
            if ( vWeaponsNames.equals("Les armes suivantes sont stockés dans votre Bracelet :\n") ){
                vWeaponsNames = "Vous ne possédez pas d'armes ! Seul vos poings peuvent venir a bout de votre ennemi.\n";
            }
            return vWeaponsNames;
        }
        return "Vous ne possédez pas d'armes ! Seul vos poings peuvent venir a bout de votre ennemi.\n";
    } // getWeaponList()

    /**
     * Accesseur qui retourne la somme des poids de tout les items contenus
     * dans la liste
     * @return le poids de la liste d'item
     */
    public int getItemListWeight()
    {
        int vItemsWeight = 0;
        Set<String> vKeys = this.aItemList.keySet();
        for(String vItemName : vKeys){
            Item vReturn = this.aItemList.get(vItemName);
            vItemsWeight += vReturn.getItemWeight();
        } 
        return vItemsWeight;
    } // getItemListWeight()

    /**
     * Procédure qui ajoute des items à la liste d'items
     * @param pItem l'item à ajouter
     */
    public void addItemList( final Item pItem )
    {
        this.aItemList.put(pItem.getItemName(), pItem);
    } // addItem(.)

    /**
     * Procédure qui retire un item de la liste des items
     * @param pItemName le nom de l'item à enlever
     */
    public void removeItemList( final String pItemName )
    {
        this.aItemList.remove(pItemName);
    } // removeItemList(.)

    /**
     *
     */
    public void clearList()
    {
        this.aItemList.clear();
    } // clearList
} // ItemList
