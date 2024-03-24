package pkg_game;

import pkg_game.UserInterface;
import pkg_item.Beamer;
import pkg_item.Item;
import pkg_item.ItemList;
import pkg_room.Door;
import pkg_room.Room;
import pkg_room.TransporterRoom;
import java.util.Stack;
/**
 * La classe Player réprésente le joueur et gère tout ce qui lui concerne
 * 
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class Player
{
    private String aPlayerName;
    private int aMaxWeight;
    private Room aCurrentRoom;
    private Stack<Room> aPreviousRooms;
    private UserInterface aGui;
    private ItemList aInventory;
    private int aPlayerMoney;
    private int aPlayerLife;

    /**
     * Constructeur qui initialise le nom du joueur et 
     * le poids maximum qu'il peut porter
     * @param pPlayerName le nom du joueur
     * @param pMaxWeight le poids maximum à emporté par le joueur
     */
    public Player( final String pPlayerName, final int pMaxWeight )
    {
        this.aPlayerName = pPlayerName;
        this.aMaxWeight = pMaxWeight;
        this.aPlayerMoney = 0;
        this.aPreviousRooms = new Stack<Room>();
        this.aInventory = new ItemList();
        this.aPlayerLife = 200;
    } // Player(..)

    /**
     * Accesseur qui retourne le nom du joueur
     * @return le nom du joueur
     */
    public String getPlayerName()
    {
        return this.aPlayerName;
    } // getPlayerName()

    /**
     *
     */
    public int getPlayerMoney()
    {
        return this.aPlayerMoney;
    }

    /**
     *
     */
    public int getPlayerLife()
    {
        return this.aPlayerLife;
    }

    /**
     * Accesseur pour accéder au poids maximum d'objets 
     * que le joueur peut transporter
     * @return le poids maximal
     */
    public int getMaxWeight()
    {
        return this.aMaxWeight;
    } // getMaxWeight()

    /**
     * Accesseur qui permet d'avoir la pièce courante du joueur
     * @return la pièce courante
     */
    public Room getCurrentRoom()
    {
        return this.aCurrentRoom;
    } // getCurrentRoom()

    /**
     * Accesseur qui permet de retourner l'item de l'inventaire
     * du joueur correspondant au nom passer en paramètre s'il
     * existe
     * @param pItemName le nom de l'item recherché
     * @return l'item correspondant au nom passé en paramètre
     */
    public Item getInventoryItem( final String pItemName )
    {
        return this.aInventory.getItem(pItemName);
    } // getInventoryItem(.)

    /**
     * Fonction qui retourne un chaine de caractère de l'inventaire du joueur
     * @return les détails de l'inventaire du joueur, ainsi que l'espace encore disponnible
     */
    public String inventoryList()
    {
        int vAvailableWeight = this.getMaxWeight() - this.aInventory.getItemListWeight();
        return this.aInventory.getItemList() + "Espace encore disponibles : " 
        + vAvailableWeight + " blocs\n" + "Votre monnaie : " + this.aPlayerMoney + " Coins";
    } /// inventoryList()

    /**
     * Modificateur qui initialise l'attribut aGui
     * @param pUserInterface l'interface de jeu
     */
    public void setGUI( final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
    } // setGUI(.)

    /**
     * Modificateur pour modifier la pièce courante dans laquel 
     * le joueur se trouve et la mémoriser
     * @param pCurrentRoom la prochaine pièce
     */
    public void setCurrentRoom( final Room pCurrentRoom )
    {
        if( this.aCurrentRoom != null){
            this.aPreviousRooms.push(this.aCurrentRoom);
            this.aGui.setEnableBackB(true);
        }
        this.aCurrentRoom = pCurrentRoom;
    } // setCurrentRoom(.)

    /**
     *
     */
    public void setPlayerMoney( final int pPlayerMoney )
    {
        this.aPlayerMoney = pPlayerMoney;
    }

    /**
     *
     */
    public void setPlayerLife( final int pPlayerLife )
    {
        this.aPlayerLife = pPlayerLife;
    }

    /**
     * Modificateur qui modifie le poids maximum que le joueur
     * peut transporter
     * @param pMaxWeight le nouveau poids maximum du joueur
     */
    private void setMaxWeight( final int pMaxWeight )
    {
        this.aMaxWeight = pMaxWeight;
    } // setMawWeight()

    /**
     * Procédure qui retourne un message lorsque le joueur 
     * a manger
     * @return le message indiquant que le joueur a mangé
     */
    public String eat()
    {
        return "Vous avez mangé, et maintenant vous n'avez plus faim.";
    } // eat()

    /**
     * Procédure qui change la salle courante en permettant
     * au joueur de revenir dans une salle déjà visitée
     */
    public void back()
    {
        if ( this.aPreviousRooms.empty() ){
            this.aGui.setEnableBackB(false);
            this.aGui.println("Vous n'avez pas mémoriser de trajet, par conséquent, il vous est impossible " 
                + "\nde retourner sur vos pas.");
        } else {
            if ( this.wasTransporterRoom() ){
                this.aPreviousRooms.clear();
                this.aGui.println("Vous venez d'une pièce transporteuse, vous n'avez pas mémorisez de trajet par conséquent !");
                this.aGui.setEnableBackB(false);
                return;
            }
            if ( this.aCurrentRoom.isExit(this.aPreviousRooms.peek()) ){
                if ( this.getCurrentRoom() instanceof TransporterRoom ){
                    TransporterRoom vRoom = (TransporterRoom)(this.getCurrentRoom());
                    this.setCurrentRoom(vRoom.getExit("SudOuest"));
                    this.aGui.println("Vous étiez dans une pièce transporteuse, sortir de cette pièce vous déplace aléatoirement !!");
                    return;
                }
                this.aCurrentRoom = this.aPreviousRooms.pop();
            } else {
                this.aGui.println("Vous avez franchi une porte à sens unique. Il vous est impossible de retourner en arrière.");
                this.aPreviousRooms.clear();
                this.aGui.setEnableBackB(false);
            }
        }
    } // back()

    /**
     * Fonction booléenne qui vérifie que la pièce précédente était ou non une Transporter Room
     * @return vrai si la pièce précédente est une Transporter Room sinon retourne faux le cas échéant
     */
    private boolean wasTransporterRoom()
    {
        if ( this.aPreviousRooms.peek() instanceof TransporterRoom)return true;
        else return false;
    } // wasTranswasTransporterRoom()

    /**
     * Procédure qui permet d'ajouter un item à l'inventaire
     * du joueur
     * @param pItem l'item à ajouter
     */
    public void addInventoryItem( final Item pItem )
    {
        this.aInventory.addItemList(pItem);
    } // addInventoryItem(.)

    /**
     * Procédure qui permet de retirer un item de 
     * l'inventaire du joueur
     * @param pItemName le nom de l'item à retiré
     */
    public void removeInventoryItem( final String pItemName )
    {
        this.aInventory.removeItemList(pItemName);
    } // removeInventoryItem(.)

    /**
     *
     */
    public void getWeaponList()
    {
        this.aGui.addTextFight(this.aInventory.getWeaponList());
    } // getWeaponList()

    /**
     * Procédure qui permet au joueur de prendre un item
     * disponible dans la pièce courante
     * @param pItemName le nom de l'item à prendre
     */
    public void take( final String pItemName )
    {
        Item vItem = this.aCurrentRoom.getItem(pItemName);
        if ( vItem != null ) {
            if ( pItemName.equals("Bracelet") ){
                this.setMaxWeight( 300 );
            }
            if ( pItemName.equals("Coins") ){
                this.setPlayerMoney(this.aPlayerMoney + 500);
                this.aGui.println("Vous avez ramassé 500 Coins.");
                this.aCurrentRoom.removeItem(pItemName);
                return;
            }
            if ( this.canBeTake(vItem) ){
                this.addInventoryItem(vItem);
                this.aCurrentRoom.removeItem(pItemName);
                this.aGui.println("Vous avez pris l'item : " + pItemName);
                this.aGui.setEnableInventoryB(true);
                if ( pItemName.equals("Poigné")  || pItemName.equals("Plume") || pItemName.equals("Flacon") || pItemName.equals("Liste") ){
                    this.aCurrentRoom.clearList();
                    this.aGui.println("La Bague de l'Illusion réagit à votre action, les autres items ont été effacer de cette pièce !");
                    return;
                }
            } else if( this.aInventory.getItem("Bracelet") == null ){
                this.aGui.println("Vous ne pouvez pas prendre d'item tant que vous ne possèdez pas le Bracelet de Stockage.");
            } else {
                this.aGui.println("L'indicateur de poids de votre Bracelet de Stockage s'agite :"
                    + "\nvous avez dépassez la capacité disponible dans l'espace dimensionnel !!" 
                    + "\nIl est impossible de prendre cet item au risque d'une saturation.");
                this.aGui.setEnableInventoryB(true);
            }
        } else {
            this.aGui.println("L'item que vous essayer de prendre n'existe pas dans cette pièce !");
        }
    } // take(.)

    /**
     * Procédure qui permet au joueur de déposer un item
     * dans la pièce courante
     * @param pItemName le nom de l'item à déposer
     */
    public void drop( final String pItemName )
    {
        Item vItem = this.getInventoryItem(pItemName);

        if ( vItem != null ){
            if( vItem.getItemName().equals("Bracelet") ){
                this.aGui.println("Il vous est impossible de déposer votre inventaire !");
                return;
            }

            this.aCurrentRoom.addItem(vItem);
            this.removeInventoryItem(pItemName);
            this.aGui.println("Vous avez déposer l'item : " + pItemName);
        } else {
            this.aGui.println("Vous ne possédez pas l'item : " + pItemName);
        }
    } // drop(.)

    /**
     * Méthode booléen qui vérifie que l'item peut être pris par le joueur
     * @param pItem item sur lequel la vérifiaction sera faite
     * @return retourne false si le poids de l'item plus celui de l'inventaire est 
     * supérieur au poids maximum supporter par le joueur, sinon retourne true
     */
    public boolean canBeTake( final Item pItem )
    {
        int vItemsWeight = this.aInventory.getItemListWeight() + pItem.getItemWeight();
        if ( vItemsWeight > this.getMaxWeight() ) return false;
        return true;
    } // canBeTake()

    /**
     * Procédure qui double le poids maximum que le joueur peut transporter
     */
    private void doubleMaxWeight()
    {
        this.aMaxWeight = 2*this.getMaxWeight();
    } // doubleMaxWeight()

    /**
     * Procédure qui vérifie que l'item associé au nom passer en paramètre peut
     * être utiliser et réalise l'action que l'item es censé faire
     * @param pItemName le nom de l'item que le joueur veut utiliser
     */
    public void use( final String pItemName )
    {
        Item vItem = this.getInventoryItem(pItemName);

        if ( vItem != null ){
            if ( pItemName.equals("Parchemin") ){
                this.doubleMaxWeight();
                this.removeInventoryItem(pItemName);
                this.aGui.println("Le Parchemin entre en résonnance avec votre Bracelet de Stockage."
                    + "\nUne lueur vous aveugle ! La capacité de votre Bracelet viens d'être doubler !!");
            } else if ( pItemName.equals("Rune") ){
                Beamer vBeamer = (Beamer)(vItem);
                if ( this.getCurrentRoom().equals(vBeamer.getBeamerRoom()) ){
                    this.aGui.println("Vous devez effectuez un déplacement avant de vous téléporter au risque de perdre définitivement la Rune");
                    return;
                }
                if (vBeamer.getBeamerRoom() != null) {
                    this.fireBeamer(vBeamer);
                    this.aGui.println("Vous avez été téléporter dans la pièce mémorisé lors du chargement."
                            + "\nLa Rune de téléportation perd de son éclat, elle vient de se briser !!!");
                } else {
                    this.aGui.println("Vous devez charger la Rune avant de l'utiliser !");
                    return;
                }
            } else if ( pItemName.equals("Talisman") ){
                if ( vItem instanceof Beamer ){
                    Beamer vBeamer = (Beamer)(vItem);
                    this.fireBeamer(vBeamer);
                    this.aGui.println("Vous avez été téléporter vers le lieu choisi par le Créateur."
                        + "\nLe Talisman de téléportation s'efface de la réalité.");
                }  else {
                    this.aGui.println("Vous possedez une version incomplète du Talisman : le Plastron."
                        + "\nRéunissez les pièces manquantes et assemblez le Talisman pour obtenir une version complète.");
                }
            }else if ( pItemName.equals("BM") ){
                if ( vItem instanceof Beamer ){
                    Beamer vBeamer = (Beamer)(vItem);
                    this.fireBeamer(vBeamer);
                    this.aGui.println("Vous avez été téléporté par le mystérieux téléporteur.");
                }
            } else if ( pItemName.equals("Elixir") ){
                if ( this.getPlayerLife() == 200 ){
                    this.aGui.println("Vous n'avez subis aucun dégâts, il est inutile de vous soigner.");
                    return;
                }
                this.setPlayerLife(350);
                this.aGui.println("L'Elixir fait effet, vous avez été soigné. Toute vos blessures disparaissent !");
                this.aGui.println("Vos points de vie : " + this.getPlayerLife() + " PV");
            } else if ( pItemName.equals("Potion") ) {
                if (this.getPlayerLife() == 200) {
                    this.aGui.println("Vous n'avez subis aucun dégâts, il est inutile de vous soigner.");
                    return;
                }
                this.setPlayerLife(200);
                this.aGui.println("La potion agit, vous avez été soigné. Toute vos blessures disparaissent !");
                this.aGui.println("Vos points de vie : " + this.getPlayerLife() + " PV");
            } else if ( pItemName.equals("Amulette") ){
                if ( this.aCurrentRoom.getIsDark() ) {
                    this.aCurrentRoom.setIsDark(false);
                    this.aGui.println("L'amulette éclairsi la pièce, vous voyez de mieux en mieux.");
                } else {
                    this.aGui.println("Il a trop de lumière pour utiliser l'amulette, elle ne réagis pas.");
                }
            } else  {
                this.aGui.println("Vous pouvez seulement utiliser un téléporteur ou un item d'augmentation de stockage");
            }
        } else {
            this.aGui.println("L'item que vous essayer d'utiliser ne se trouve pas dans votre Bracelet.");
        }
    } // use(.)

    /**
     * Procédure privée qui permet d'utiliser le beamer
     * @param pBeamer le téléporteur à utiliser
     */
    private void fireBeamer( final Beamer pBeamer ) // crée pour éviter la dupplication de code
    {
            this.aCurrentRoom = pBeamer.getBeamerRoom();
            this.removeInventoryItem(pBeamer.getItemName());
            this.aPreviousRooms.clear();

    } // fireBeamer(.)

    /**
     * Procédure qui permet de charger un téléporteur si le joueur est en possession de l'item
     * passe en paramètre
     * @param pBeamerName le nom de l'item à charger
     */
    public void chargeBeamer( final String pBeamerName )
    {
        Item vItem = this.getInventoryItem(pBeamerName);

        if ( vItem != null ){ //le joueur possède l'item dans son inventaire
            //le joueur possède un télépoteur
            if ( vItem instanceof Beamer ){ // vérifie que vItem est une instance de la classe pkg_item.Beamer
                Beamer vBeamer = (Beamer)(vItem);
                if ( vBeamer.getBeamerRoom() == null ){
                    vBeamer.setBeamerRoom(this.getCurrentRoom());
                    this.aGui.println("Vous avez charger le téléporteur, il a mémoriser la pièce ou vous vous trouvez actuellement !");
                } else {
                    if ( vBeamer.getItemName().equals("Talisman") ){
                        this.aGui.println("Le Talisman de téléportation, ne peut être chargé puisqu'il vous téléportera"
                            + "\nvers le lieu prédéfini lors de sa création. Il a été charger par le Sorcier Suprême l'ayant fabriqué.");
                        return;
                    }
                    if ( vBeamer.getItemName().equals("BM") ){
                        this.aGui.println("Le BM semble déjà être chargé !");
                        return;
                    }
                    this.aGui.println("L'item " + pBeamerName + " est déjà chargé !");
                }
            } else {
                this.aGui.println("Vous pouvez seulement charger un téléporteur");
            }
        } else {
            this.aGui.println("Vous ne possèdez pas l'item : " + pBeamerName);
        }
    } // chargeBeamer(.)

    /**
     * Procédure qui vérifie que le joueur possède son inventaire puis affiche 
     * les détails de ce dernière dans la pannel associé
     */
    public void inventory()
    {
        if ( this.getInventoryItem("Bracelet") != null ){
            this.aGui.inverseVisibility();
            this.aGui.fontInventoryPanel();
            this.aGui.writeInventory(this.inventoryList());
            this.aGui.setEnableInventoryB(true);
        } else {
            this.aGui.println("Vous ne possédez pas l'inventaire : allez prendre le " + "\"" + "Bracelet" + "\"" + " !");
        }
    } // inventory()

    /**
     * Procédure qui permet de regarder les détails de l'item passer en paramètre
     * dans l'inventaire si le joueur le possède
     * @param pItemName le nom de l'item à inspecter
     */
    public void inspectItem( final String pItemName )
    {
        Item vItem = this.getInventoryItem(pItemName);
        if ( this.getInventoryItem("Bracelet") != null ){

            if ( vItem != null ){
                this.aGui.visibilityInventory(true);
                this.aGui.fontInventoryPanel();
                this.aGui.writeInventory("Vous fouillez dans la base de données de votre Bracelet."
                    + "\nIl vous montre les détails de l'item : " + pItemName +
                    "\n\n"  + vItem.getLongItemDescription());
            } else {
                this.aGui.println("Votre Bracelet de Stockage indique une erreur : vous ne possédez pas l'item dont vous chercher les détails.");
                this.aGui.visibilityInventory(false);
            }
        } else { 
            this.aGui.println("Vous devez posseder le Bracelet de Stockage afin d'inspecter un item dans votre inventaire.");
        }
    } // inspectItem(.)

    /**
     * Fonction booleénne qui vérifie que le joueur possède les items nécéssaire pour assembler l'item qui 
     * à pour nom celui passé en paramètre
     * @param pItemName le nom de l'item à assembler
     * @return vrai si l'item est assemblé, sinon retorne faux lorsque le joueur ne peux pas assembler l'item
     */
    public boolean build( final String pItemName ) 
    {
        Item vItem = this.getInventoryItem(pItemName);

        if ( vItem != null ){
            if ( vItem.getItemName().equals("Plastron") ){
                if ( this.getInventoryItem("Fiole") != null && this.getInventoryItem("Vanadinite") != null ){
                    this.removeInventoryItem("Fiole");
                    this.removeInventoryItem("Vanadinite");
                    this.removeInventoryItem("Plastron");
                    this.aGui.println("Vous avez assemblé le Plastron, il est passé de l'état incomplet, à l'état complet.");
                    this.aGui.println("Vous êtes désormais en possession d'un Talisman de téléportation complet.\nIl est prêt à être utilisé.");
                    return true;
                } else {
                    this.aGui.println("Il vous manque la Fiole d'Énergie Lunaire ou la Vanadinite dans votre inventaire.");
                    return false;
                }
            } else {
                this.aGui.println("Vous pouvez seulement assemblez un Plastron de téléportation incomplet !");
                return false;
            }
        } else {
            this.aGui.println("Vous ne possédez pas l'item dans votre inventaire.");
            return false;
        }
    } // build(.)

    /**
     * Procédure qui permet d'ouvrir la porte qui se trouve dans la direction passé en paramètre
     * si il y a une porte
     * @param pDirection la direction dans laquel on veut ouvrir une porte
     */
    public void openDoor( final String pDirection )
    {
        Door vDoor = this.getCurrentRoom().getDoor(pDirection); // la porte dans la direction souhaiter

        if ( vDoor != null ){ // si il y a une porte dans la direction demandé
            if ( !vDoor.getCanBeCrossed() ){ // si la porte ne peut pas être traversée
                Item vKeyDoor = vDoor.getKeyDoor(); // la clé de la porte
                Item vKeyInventory = this.getInventoryItem(vKeyDoor.getItemName()); // la clé de la porte dans l'inventaire 

                if ( vKeyInventory != null ){ // le joueur possède la clé de la porte dans son inventaire
                    vDoor.setCanBeCrossed(true);
                    this.aGui.println("La porte dans la direction " + "\"" + pDirection + "\"" + " est maintenant dévérouillé.");
                } else {
                    this.aGui.println("Vous devez récupérer la clé " + "(" + vKeyDoor.getItemName() + ")" + " de la porte pour pouvoir l'ouvrir !");
                }
            } else {
                this.aGui.println("Cette porte est déjà ouverte !");
            }
        } else {
            this.aGui.println("Il n'y a pas de porte vérouillée dans cette direction.");
        }
    } // openDoor(.)
} // Player
