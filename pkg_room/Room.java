package pkg_room;

import java.util.HashMap;
import java.util.Set;

import pkg_item.ItemList;
import pkg_item.Item;
import pkg_character.Character;
import pkg_character.MonsterCharacter;
/**
 * La classe Room permet la création de pièces
 * 
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class Room
{
    private String aDescription;
    private HashMap<String, Room> aExits;
    private HashMap<String, Door> aDoors;
    private String aImageName;
    private ItemList aItems;
    private HashMap<String, Character> aCharacterList;
    private boolean aIsDark;

    /**
     * Constructeur naturel pour créer une nouvelle pièce 
     * @param pDescription description de la pièce 
     * @param pImageName le nom de l'image et son extension
     */
    public Room( final String pDescription, final String pImageName )
    {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
        this.aDoors = new HashMap<String, Door>();
        this.aCharacterList = new HashMap<String, Character>();
        this.aImageName = pImageName;
        this.aItems = new ItemList();
        this.aIsDark = false;
    } // Room (..)

    /**
     *
     */
    public Room ( final String pDescription, final String pImageName , final boolean pIsDark )
    {
        this(pDescription, pImageName);
        this.aIsDark = true;
    } // Room(...)

    /**
     *
     */
    public boolean getIsDark()
    {
        return this.aIsDark;
    } // getIsDark()

    /**
     * Accesseur pour récupérer la description de la pièce
     * @return la description de la pièce 
     */
    public String getDescription()
    {
        return this.aDescription;
    } // getDescription()

    /**
     * Méthode qui renvoie la description de la pièce
     * ainsi que les sorties disponibles et les items
     * présent
     * @return la description et les sorties et les items 
     * sous forme de chaine de caractère
     */
    public String getLongDescription()
    {
        return "Vous êtes " + this.getDescription() + "\n" 
        + this.getCharacterString() + "\n" + this.getExitString() + "\n" + this.getItemsString();
    } // getLongDescription()

    /**
     *
     */
    public String getDarkRoomDescription()
    {
        return "Vous êtes " + this.getDescription() + "\nIl fait sombre, très sombre... Vous ne voyez rien, utilisez l'Amulette de lumière, ou " +
                "demander de l'aide au Marchand !";
    } // getDarkRoomDescription()

    /**
     * Accesseur pour récupérer la pièce qui se trouve dans la direction rentrée
     * @param pDirection la direction sous forme de chaine de caractère
     * @return la pièce qui se trouve dans la direction du paramère 
     */
    public Room getExit( final String pDirection )
    {
        return this.aExits.get(pDirection);
    } // getExit(.)

    /**
     * Accesseur pour récuperer la porte qui se trouve dans la direction rentrée
     * @param pDirection la direction ou se trouve la potentiel porte
     * @return la porte qui se trouve dans le direction passé en paramètre
     */
    public Door getDoor( final String pDirection )
    {
        return this.aDoors.get(pDirection);
    } // getDoor(.)

    /**
     * Accesseur pour récuperer un PNJ de la liste
     * @param pCharacterName le nom du PNJ
     * @return le PNJ qui se trouve dans la pièce avec le nom passé en paramètre
     */
    public Character getCharacter( final String pCharacterName )
    {
        return this.aCharacterList.get(pCharacterName);
    } // getCharacter(.)

    /**
     * Accesseur qui retourne le PNJ de la liste de type MonsterCharacter
     * @return le pnj de la pièce qui sera un adversaire du joueur
     */
    public MonsterCharacter getMonsterCharacter()
    {
        Set<String> vCharactersNames = this.aCharacterList.keySet();
        for( String vCharacterName : vCharactersNames ){
            Character vCharacter = this.getCharacter(vCharacterName);
            if ( vCharacter instanceof MonsterCharacter ){
                return (MonsterCharacter) (vCharacter);
            }
        }
        return null;
    } // getMonsterCharacter()

    /**
     * Accesseur qui retourne sous forme de chaine de caractère
     * les sorties de la pièce courante disponibles
     * @return les sorties disponibles
     */
    public String getExitString()
    {
        if ( this.aExits.size() > 0 ){
            String vExit = "Sorties possibles :\n";
            Set<String> vKeys = this.aExits.keySet();
            for(String vExit2 : vKeys){
                vExit += "- " + vExit2 + "\n";
            }
            return vExit;
        } else return "Il n'y a pas de sorties disponibles.";

    } // getExitString()
    
    /**
     * Accesseur qui retourne sous forme de chaine de caractère
     * s'il y a ou non des personnages non joueur dans la pièce
     * @return le nom du/des personnages(s) qui se trouve dans la pièce ou qu'il n'y en a pas
     */
    public String getCharacterString()
    {
        if ( this.aCharacterList.size() > 0 ){
            String vCharacter = "Vous voyez la/les personne(s) suivante(s) :\n";
            Set<String> vKeys = this.aCharacterList.keySet();
            for( String vCharacterName : vKeys ){
                vCharacter += "-> " + vCharacterName + "\n";
            }
            return vCharacter;
        } else return "Il semble n'y avoir aucun habitant(s) de la Tour dans cette pièce.";
    } // getCharacterString()

    /**
     * Accesseur qui retourne l'item correspondant à celui rentré
     * en paramètre
     * @param pItemName le nom de l'item recherché
     * @return l'item possédant le nom passé en paramètre
     */
    public Item getItem(final String pItemName )
    {
        return this.aItems.getItem(pItemName);
    } // getItem(.)

    /**
     * Accesseur qui retourne sous forme de chaine de caractère
     * s'il y a ou non un ou des items dans la pièce
     * @return les/l'item(s) disponible(s) ou qu'il n'y en a pas
     */
    public String getItemsString()
    {
        return this.aItems.getItemListString();       
    } // getItemString()

    /**
     * Méthode qui retourne une chaine de caractère qui décrit l'image
     * @return la description de l'image
     */
    public String getImageName()
    {
        return this.aImageName;
    } // getImageName()

    /**
     * Modificateur qui attribue une sortie pour une seule pièce
     * @param pDirection direction de la sortie
     * @param pNeighbor la salle dans la direction donnée
     */
    public void setExit( final String pDirection, final Room pNeighbor )
    {
        this.aExits.put(pDirection, pNeighbor);
    } // setExit(..)

    /**
     * Modificateur pour pouvoir ajouter des portes à la pièce
     * @param pDirection la direction de la porte
     * @param pDoor la porte à associer
     */
    public void setDoor( final String pDirection, final Door pDoor )
    {
        this.aDoors.put(pDirection, pDoor);
    } // setDoor(..)

    /**
     *
     */
    public void setIsDark ( final boolean pIsDark )
    {
        this.aIsDark = pIsDark;
    } // setIsDark(.)

    /**
     * Procédure qui ajoute des items à la liste de ceux 
     * de la pièce courante
     * @param pItem un item a ajouter
     */
    public void addItem( final Item pItem ) //anciennement setItem()
    {
        this.aItems.addItemList(pItem);
    } // addItem(.)
    
    /**
     * Procédure qui ajoute des PNJ à la liste de ceux présent dans la pièce
     * @param pCharacter le personnage à ajouter dans la pièce
     */
    public void addCharacter( final Character pCharacter )
    {
        this.aCharacterList.put( pCharacter.getCharacterName(), pCharacter );
    } // addCharacter(.)

    /**
     *
     */
    public void removeCharacter( final String pCharacterName )
    {
        this.aCharacterList.remove(pCharacterName);
    } // addCharacter(.)
    
    /**
     * Procédure qui retire un item de la liste 
     * des items de la pièce
     * @param pItemName le nom de l'item à enlever
     */
    public void removeItem( final String pItemName )
    {
        this.aItems.removeItemList(pItemName);
    } // removeItem(.)

    /**
     * Procédure qui permet de vider la liste des items présent dans la pièce
     */
    public void clearList()
    {
        this.aItems.clearList();
    } // clearList()

    /**
     * Fonction booléen qui vérifie que la pièce passé en paramètre
     * est l'une des sorties de la pièce courante
     * @param pRoom la pièce à comparer
     * @return vrai si la pièce est une sorties de la pièce courante 
     * sinon faux
     */
    public boolean isExit( final Room pRoom )
    {
        Set<String> vKeys = this.aExits.keySet();
        for( String vExit : vKeys ){
            Room vRoom = this.getExit(vExit);
            if ( pRoom.equals(vRoom) ){
                return true;
            }
        }
        return false;
    } // isExit(.)

    /**
     *
     */
    public boolean hasMonster()
    {
        Set<String> vCharactersNames = this.aCharacterList.keySet();
        for( String vCharacterName : vCharactersNames ){
            Character vCharacter = this.getCharacter(vCharacterName);
            if ( vCharacter instanceof MonsterCharacter ){
                return true;
            }
        }
        return false;
    }
} // Room
