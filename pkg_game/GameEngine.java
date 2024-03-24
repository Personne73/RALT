package pkg_game;

import pkg_character.*;
import pkg_character.Character;
import pkg_command.Command;
import pkg_command.Parser;
import pkg_item.Beamer;
import pkg_item.Item;
import pkg_item.Weapon;
import pkg_room.Room;
import pkg_room.TransporterRoom;
import pkg_room.Door;

import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Cette classe représente le moteur du jeu, elle permet d'initialiser les salles, les items, et tout autre objets liés au jeu. 
 * Il s'agit aussi de cette classe qui s'occupe de l'interpretation des différentes commandes.
 * 
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class GameEngine implements ActionListener
{
    private ArrayList<Room> aRoomList;
    private Parser aParser;
    private UserInterface aGui;
    private Player aPlayer;
    private int aLimitTime;
    private Beamer aTalisman;
    private boolean aTestMode;
    private MovingCharacter[] aMovingCharacterList;
    private boolean aFightMode;

    /**
     * Constructeur par défaut pour créer un moteur de jeu
     */
    public GameEngine()
    {
        this.aPlayer = new Player( "Trey Thomas", 0 );
        this.aLimitTime = 2400000; // en milliseconde soit 20 min de jeu
        this.aRoomList = new ArrayList<Room>(16);
        this.createRooms();
        this.aParser = new Parser();
        this.timeLimit();
        this.aTestMode = false;
        this.aFightMode = false;
    } // GameEngine()

    /**
     *
     */
    public boolean getFightMode()
    {
        return this.aFightMode;
    } // getFightMode()

    /**
     * Accesseur qui retourne le GUI
     * @return le Gui pour permettre un accès
     */
    public UserInterface getGui()
    {
        return this.aGui;
    } // getGui()

    /**
     * Accesseur qui retourne le joueur
     * @return le joueur
     */
    public Player getPlayer()
    {
        return this.aPlayer;
    } // getPlayer()

    /**
     * Accesseur qui permet d'avoir accès au parser
     * @return l'attribut aParser
     */
    public Parser getParser()
    {
        return this.aParser;
    } // getParser()

    /**
     * Accesseur qui permet d'avoir accès a la valeur dans l'attribut aTalisman
     * @return le beamer qui se trouve dans l'attribut aTalisman
     */
    public Beamer getTalisman()
    {
        return this.aTalisman;
    } // getTalisman()

    /**
     * Accesseur qui retourne la valeur de l'attribut indiquant si l'on est en mode test
     * @return la valeur de l'attribut aTestMode donc vrai si on est en mode test
     * sinon faux
     */
    public boolean getTestMode()
    {
        return this.aTestMode;
    } // getTestMode()

    /**
     * Accesseur qui permet d'avoir accès à la pièce qui a le numéro
     * passer en paramètre
     * @param pInt l'entier représentant la position d'une pièce dans la liste
     * @return la room correspondant à ce numéro
     */
    public Room getRoom( final int pInt )
    {
        return this.aRoomList.get(pInt);
    } // getRoom(.)

    /**
     * Modificateur qui initialise l'attribut aGui ainsi que celui du joueur
     * et affiche le message de bienvenue en appellant la méthode associé
     * @param pUserInterface l'interface de jeu
     */
    public void setGUI( final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
        this.aPlayer.setGUI(pUserInterface);
        this.printWelcome();
    } // setGUI(.)

    /**
     *
     */
    public void setFightMode( final boolean pFightMode )
    {
        this.aFightMode = pFightMode;
    } // setFightMode(.)

    /**
     * Modificateur qui permet de modifier la valeur de l'attribut aTestMode
     * @param pTestMode la nouvelle valeur booléenne de l'attribut
     */
    public void setTestMode( final boolean pTestMode )
    {
        this.aTestMode = pTestMode;
    } // setTestMode(.)

    /**
     * Procédure pour accueillir le joueur au début du jeu
     */
    private void printWelcome()
    {
        this.aGui.println("Bienvenue dans The Ring and the Lost Tower");
        this.aGui.println("Vous incarnez Trey Thomas, un mercenaire vendant ses services au plus offrant.");
        this.aGui.println("Après avoir accepté le contrat proposé par le gouvernement américain, vous vous trouvez " +
            "dans la Tour Perdu.\nVotre mission : récupérer le relique convoitée par vos employeurs, La Bague de L'Illusion !");
        this.aGui.println("Toutefois, faites attention au temps : dès que vous avez mis les pieds dans la Tour, " +
            "les forces armées asiatiques ont été averties de votre présence ! Vous disposez seulement de 40 minutes avant de les voir débarquer.");
        this.aGui.println("La Tour possèdent des habitants, ils peuvent se montrer très coopératif, chaque mot compte ! Ne laissez rien au hasard !");
        this.aGui.println("Tapez 'aide' si vous avez besoin d'aide.\n\n");

        this.printLocationInfo();
    } // printWelcome()

    /**
     * Procédure qui permet la création des pièces du jeu, le 
     * réseau de lieux et les items
     */
    private void createRooms()
    {
        // Creation des lieux
        // 1er Etage
        Room vHall = new Room("dans le Grand Hall du donjon", "Hall.png");
        Room vIntermediate = new Room("dans la salle intermédiare", "Intermediate.png");
        Room vEquipments = new Room("dans la salle d'équipements. Les objets présents dans cette pièce peuvent-être très utile !!", "Equipment.png");
        Room vTransition = new Room("dans la salle de transition", "Transition.png");

        this.aRoomList.add(0, vHall);
        this.aRoomList.add(1, vIntermediate);
        this.aRoomList.add(2, vEquipments);
        this.aRoomList.add(3, vTransition);

        TransporterRoom vDimension = new TransporterRoom("dans la salle domensionelle. On dirait que la Bague de l'Illusion influe plus d'énergie dans cette salle " +
                "que dans les autres...", "Dimension.png", this.aRoomList);
        this.aRoomList.add(4, vDimension);

        // 2e Etage
        Room vSecondHall = new Room("dans le Hall du second étage", "SecondHall.png");
        Room vTomb = new Room("dans le tombeau du Créateur.", "Tomb.png");
        Room vTemp = new Room("dans la salle temporelle. Un bruit court comme quoi le dieu du temps, Chronos y insuffle continuellement son" +
                " énergie. On peut y voir des statues venérant des dieux anciens. Chaque statue " +
                "possède un item devant lui. On dirait qu'il va falloir faire un choix... Vous devez vous renseigner !", "Temp.png");
        Room vEquipments2 = new Room("dans la deuxième salle d'équipements. Les objets présents dans cette pièce semblent plus puissant que dans la première. " +
                "Il peuvent-être très utile !!", "Equipment2.png");
        Room vSavoir = new Room("dans la Chambre du Savoir. Beaucoup des connaissances ici présent ont été laissée par la déesse Athéna. Cette pièce " +
                "regorge d'informations sur les mystères de la Tour.", "Savoir.png");
        Room vAlpha = new Room("dans la salle de l'Alpha. C'est ici qu'à eu lieu la cérémonie de canalisation qui a permis à l'Alpha " +
                "d'appaître pour vous affronter.", "Alpha.png");
        
        this.aRoomList.add(5, vSecondHall);
        this.aRoomList.add(6, vTomb);
        this.aRoomList.add(7, vTemp);
        this.aRoomList.add(8, vEquipments2);
        this.aRoomList.add(9, vSavoir);
        this.aRoomList.add(10, vAlpha);

        // création de la liste de pièce des moving PNJ
        ArrayList<Room> vPnjRoom = new ArrayList<Room>();
        vPnjRoom.add(0, vSecondHall);
        vPnjRoom.add(1, vTomb);
        vPnjRoom.add(2, vTemp);
        vPnjRoom.add(3, vSavoir);

        // 3e Etage
        Room vThirdHall = new Room("dans le Hall du dernier étage", "ThirdHall.png");
        Room vObscurity = new Room("dans la salle de l'Obscurité. Vous semblez être observé...", "Obscurity.png", true);
        Room vSun = new Room("dans la salle du Soleil. Il s'agit de la pièce créer par le Dieu Hélios lors de son premier passage dans la Tour."
            + " L'autel érigé en son honneur par le Créateur de la Tour ainsi que des portes menant dans le domaine du Soleil se trouve dans la pièce. "
            + "On raconte que ces portes ont été installées par Helios.", "Sun.png");
        Room vWarden = new Room("dans la salle du Gardien. Vous semblez être observé...", "Warden.png");
        Room vIllusion = new Room("dans la salle de l'Illusion. Attention à ne pas tomber dans l'illusion, parmi tous ce que vous voyez, " 
            + "certaines choses sont peut-être fausses. La relique de l'Illusion a générer cette pièce dès votre venu dans la Tour.", "Illusion.png");
        
        this.aRoomList.add(11, vThirdHall);
        this.aRoomList.add(12, vObscurity);
        this.aRoomList.add(13, vSun);
        this.aRoomList.add(14, vWarden);
        this.aRoomList.add(15, vIllusion);

        // Création du "réseau" de lieux
        vHall.setExit("Sud Est", vDimension);
        vHall.setExit("Sud", vEquipments);
        vHall.setExit("Ouest", vIntermediate);
        vDimension.setExit("Nord Ouest", vHall);
        vDimension.setExit("Sud Ouest", vEquipments);
        vIntermediate.setExit("Est", vHall);
        vIntermediate.setExit("Sud", vTransition);
        vEquipments.setExit("Nord", vHall);
        vEquipments.setExit("Nord Est", vDimension);
        vTransition.setExit("Est", vEquipments);
        vTransition.setExit("Haut", vSecondHall);

        vSecondHall.setExit("Bas", vTransition);
        vSecondHall.setExit("Nord Ouest", vTomb);
        vTomb.setExit("Sud Est", vSecondHall);
        vTomb.setExit("Est", vTemp);
        vTemp.setExit("Nord", vSavoir);
        vTemp.setExit("Est", vEquipments2);
        vTemp.setExit("Ouest", vTomb);
        vEquipments2.setExit("Nord", vAlpha);
        vEquipments2.setExit("Sud Ouest", vSecondHall);
        vAlpha.setExit("Haut", vThirdHall);
        vAlpha.setExit("Ouest", vSavoir);
        vSavoir.setExit("Sud", vTemp);

        vThirdHall.setExit("Bas", vAlpha);
        vThirdHall.setExit("Nord Est", vSun);
        vThirdHall.setExit("Nord Ouest", vObscurity);
        vObscurity.setExit("Sud Est", vThirdHall);
        vSun.setExit("Sud Ouest", vThirdHall);
        vWarden.setExit("Sud", vIllusion);

        // Création des items
        Item vBracelet = new Item("Bracelet", "Il s'agit d'un Bracelet de "+ 
                "Stockage permettant de stocker des objets dans une autre dimension."
                + " Attention, il possède toutefois une limite de stockage correspondant à un poids."
                + "Espace disponible par défaut : 300 blocs.",0, 1000000);
        Item vParchemin = new Item("Parchemin", "Le Parchemin d'Augmentation " +
                "permet d'augmenter la capacité de stockage d'un bracelet de stockage.",
                10, 50);
        Item vBague = new Item("Bague", "La Bague de l'Illusion est une des 5 reliques sacrées. "
                + "Il s'agit d'une bague qui permet à son propriétaire de plongé toute personne de son souhait"
                + " dans un état de profond sommeil, le faisant ainsi vivre une illusion infini."
                + " On raconte qu'elle choisi elle même son propriétaire !", 25, 1000000);
        Item vPendentif = new Item("Pendentif", "Il s'agit d'un médaillon contenant une photo de famille. Il est remplit de souvenir. "
                + "Son propriétaire se trouve surement en ces lieux.", 10, 50);
        Item vAnneau = new Item("Anneau", "C'est un vieille anneau en mauvais état. On dirait qu'il peut se briser" +
                " à tout moment !", 10, 50);
        Item vMap = new Item("Carte", "Il s'agit d'une ancienne carte magique des lieux, elle semble en bonne état, et est réprésentatif" +
                "des lieux de l'étage. D'après les dire du Protecteur, elle analyse mon environement et génère une carte des lieux.", 50, 1000000);
        Item vPlastron = new Item("Plastron", "Le Plastron de téléportation incomplet"
                + " est un artéfact qui doit être assembler avec les pièces manquantes."
                + "\nUne fois construit, il devient un Talisman complet de téléportation qui pourra être utiliser.", 80, 1000000);
        this.aTalisman = new Beamer("Talisman", "Le Talisman de téléportation complet"  
            + " permet de se téleporter vers un endroit prédéfini par un Sorcier Suprême lors de sa création.", 80, 1000000, vWarden);
        Beamer vRune = new Beamer("Rune", "La Rune de Téléportation est un objet magique "
                + " permettant de se téléporter vers un endroit déjà visité. Une fois chargée, la rune mémorise la pièce dans laquelle elle est rechargée."
                + " Au moment de l'utilisation dans une autre pièce, elle vous ramène vers la pièce mémoriser lors de chargement.", 80, 200);
        Item vCoins = new Item("Coins", "Il s'agit de la monnaie utilisée dans la Tour. Dans ce sachet se trouve 500 Coins.",
        0, 500);
        Item vPotion = new Item("Potion", "Il s'agit d'un potion de soins permettant de soigné vos blessures. D'après " +
                "le forgeron elle est assez efficace.", 0, 1500);

        // Création des armes : classé du plus fort au moins fort (donc par rang)
        Weapon vDominator = new Weapon("Dominator", "Arme divine la plus puissante qui n'ai jamais exister, " +
                "on raconte que le Dominator a été crée par le Créateur lui même. Puissante de base, cette épée lourde est d'autant plus forte que " +
                "l'adverse de son propriétaire l'est. Elle semble adapter sa forme et sa taille en fonction de la morphologie son propriétaire.",
                50, 1000000, 250, "Divin");
        // Protector inventaire Ancienne
        Weapon vBlazewing = new Weapon("Blazewing", "Épée maudite flamboyante de niveau suprême et forgé en zinc, le Blazewing est marqué "
                + "par sa grande durabilité. Il est inscrit sur sa lame un nom devenu peu lisible avec le temps."
                + " Malgré ce temps écoulé depuis ça création, elle paraît encore très efficace !", 50, 1000000, 150, "Suprême");
        Weapon vGenesis = new Weapon("Genesis","Genesis est un épée ultra légère de rang suprême. Elle possède un pouvoir de création qui provoque des " +
                "dégats élémentaires lors d'un combat.", 50, 1000000, 180, "Suprême");
        // Sentiennelle vendu par le marchand ambulant
        Weapon vFairyHunter = new Weapon("FairyHunter", "La FairyHunter est un katana chasseur de monstre. Il possède actuellement un niveau or, néanmoins," +
                " on dirait que son véritable pouvoir semble avoir été scellé par quelqu'un. On reconnais des symboles d'alchimie sur sa lame.", 50, 2300,
                100, "Or");
        Weapon vSandana = new Weapon("Sandana","La Sandana est un couteau de niveau normal. Il possède un lame assez efficace" +
                " pour aider son propriétaire lors d'un combat assez rapide.", 50, 1000, 80, "Normal");
        Weapon vPiques =  new Weapon("Piques", "Les piques à brochettes sont des piques qui semblent avoir été utilisés par " +
                "un précédent mercenaire lors de son passage dans la Tour. Une devise semble être écrite sur le lot : La Mort c'est la vie. De rang normal," +
                " leur grand nombre dans le lot fait leur force, faisant d'eux une arme à fort potentiel.", 50, 1000, 80, "Normal Évolutif");
        Weapon vFrost = new Weapon("Frost", "Il s'agit d'une épée possédant une lame très usée. Elle semble d'ailleurs" +
                " très fragile pour pouvoir être utilisée sur un combat de longue durée.",
                50, 400, 50, "Commun");


        // si le prix dépasse l'entendement c'est parce qu'il s'agit d'un item qui ne peut pas être acheter
        Item vFiole = new Item("Fiole", "La Fiole d'Énergie Lunaire est un petit récipient contenant un mélange" 
                + " très particulier de résidus lunaire directement prélévé sur la Lune, "
                + "ainsi qu'une dose d'une huile très rare d'une roche : \nla roche de l'Éternité."
                + "\nSeul un Maître Sorcier Suprême connait les doses exactes pour une fabrication parfaite d'Énergie Lunaire.", 60, 1000000);
        Item vVanadinite = new Item("Vanadinite", "La Vanadinite est une pierre précieuse de niveau légendaire"
                + " utilisé par les Sorciers Suprêmes dans l'Alchimie."
                + "\nElle permet de rendre les artéfacts à un niveau supérieur ou alors de construire des objets qui sortent de l'ordinaire", 20, 1000000);

        // création des items en lien avec l'Ancienne
            // items salle temporelle
        Item vPoigne = new Item("Poigné", "Cet objet est un poigné renforcé par le dieu de la guerre lui même lors d'une " +
                "visite dans la Tour Perdu. Il semble être un des composants d'une arme.", 25, 1000000);
        Item vPlume = new Item("Plume","Il s'agit d'une plume ancienne. On peut remarquer des initiales suivis de la mention :" +
                " Sorcier Suprême.", 0, 5);
        Item vFlacon = new Item("Flacon", "Dans ce flacon se trouve des Larmes de Phénix. Il s'agit d'un ingrédient très désiré " +
                "par les Maitres Sorciers Suprêmes pour la conception de certaines potions.", 50,1000000);
        Item vListe = new Item("Liste", "On peut voir une série d'inscriptions sur ce papier. On dirait une recette " +
                "mais les ingrédients semblent... inconnus.", 10, 5000);

            // items inventaire de l'Ancienne
        Weapon vProtector = new Weapon("Protector", "Le Protector est une dague légéndaire des plus performantes. D'après l'Ancienne, " +
                "elle faisait partie de l'équipement personnel d'Arès, le dieu de la guerre.", 50, 1000000, 180, "Légendaire");
        Item vPage = new Item("Page", "On dirait une page arraché d'un manuscrit.", 10, 5000);
        Item vElixir = new Item("Elixir", "L'Elixir de vie est une potion concocté par un Sorcier Suprême capable de soigné " +
                "instantanément toute vos blessures, et d'augmenter votre capacité à encaissé des dégats", 50, 10000);
        Item vPoussiere = new Item("Poussière", "Dans ce sac se touve de la Poussière mystique, un matériau très demandé par les Sorciers Suprême. " +
                "Malheureusement, seul un membre du conseil de la Grande Sorcelerie connait l'utilité de cette ingrédient. D'après l'ancienne, " +
                "conseil ne se réunit qu'une fois tout les 100 ans...", 50, 10000);

        // items dans l'inventaire du Marchand
        Item vAmulette = new Item("Amulette", "L'Amulette de Lumière est un objet magique produisant de la " +
                "lumière lorsqu'elle se trouve à l'obscurité. Vu les capacacité de la Bague, tout est possible dans les différentes pièces : il peut être" +
                " plus que utile !!", 70, 0);
        Weapon vSentinelle = new Weapon("Sentinelle", "La Sentinelle est un sabre assasin de niveau or qui a autrefois" +
                " servi le Créateur de la Tour à dompté l'Alpha, le Maître des Dragons.", 50, 2300, 100, "Or");
        Beamer vBm = new Beamer("BM", "Le BM est un mystérieux téléporteur que même votre Bracelet de Stockage est incapable" +
                " d'analyser. Il n'y a aucune informations à son sujet.",80, 100, vTransition);

        // création des clés des portes
        Item vFirstKey = new Item("Oracle", "L'Oracle est une clé unique permettant d'ouvrir l'une des portes blindés.", 10, 1000000);
        Item vSecondKey = new Item("Obsidienne", "Forgé à partir de la pierre de même nom, l'Obsidienne est une clé"
                +"\npermettant d'ouvrir l'une des portes blindés.", 10, 1000000);

        // création des portes vérouillées
        Door vFirstDoor = new Door(false, vFirstKey);
        Door vSecondDoor = new Door(false, vSecondKey);

        // création des PNJ
        Character vAdam = new Character("Adam", "( Regard méprisant...) Raahhlaalaaa, encore un de ces mercenaires " +
                "fou qui tente de récupérer la Relique...");
        MonsterCharacter vProtecteur = new MonsterCharacter("Protecteur", "Je suis Le Protecteur de ces lieux, qui êtes vous inconnu ?" +
                "\nJe n'ai pas le temps de vous arrêter, je suis... préoccupé... j'ai perdu un objet qui m'est précieux... Et la carte ne peut pas m'indiquer son emplacement...",
                150, "Épée", 10, false);
        MovingCharacter vFou = new MovingCharacter("Fou", "NE VOYEZ VOUS PAS ? De son trône de pierre le Créateur " +
                "de la Tour nous observe à travers ses soldats !!", vSecondHall, vPnjRoom );
        SenseiCharacter vAncienne = new SenseiCharacter("Ancienne", "Je peux lire en toi comme un livre ouvert. Les items" +
                " ici présent sont caractéristiques de ta personnalité. Prend en un et rapporte le moi. Fais très attention : choisi bien... " +
                "Les dieux anciens Chronos et Kairos t'apporteront peut être leur bénédiction étranger.");
        TraderCharacter vMarchand = new TraderCharacter("Marchand","Salutation mercenaire, je suis le Marchand Ambulant. Nous pouvons faire affaire.",
                vPnjRoom);
        Character vAveugle = new Character("Aveugle", "Le Dominator se trouve dans la Tour, oui ici même ! A ce qu'il paraît, il s'agit de l'arme " +
                "la plus puissante qui n'ai jamais été créée. Je suis certes aveugle, mais j'ai entendu le conseil des Anciens dire lors d'une réunion" +
                " que cette épée accorderais puissance à son détenteur.");
        Character vJoel = new Character("Joël", "Bon dieu, vous avez reussi à atteindre les étages supérieurs. Pour vous" +
                " récompensez, voici le Genesis mercenaire. Vous en aurez peut-être besoin par la suite.");
        Character vForgeron = new Character("Forgeron", "Salutation mercenaire, je vais t'expliquer le système d'armement" +
                " adopté dans la Tour. Tout d'abord, tu dois savoir que chaque arme possède un grade. Tu dois donc t'en douter, plus le rang de l'arme" +
                " est élevé, plus elle va être puissante. Qu'est-ce que je pourrais dire d'autre...\nAh oui, il existe donc différents rangs, de Commun (blanc)" +
                " à Divin (magenta), en passant par Normal (blanc), Or (rose), Suprême (cyan) et Légendaire (jaune). D'après mon défunt maître, les armes de niveau Or " +
                "et plus possèdent des boosts sur leurs capacités : elles sont donc plus utiles que les autres.\nAhhhhh, je viens de me souvenir d'une rumeur : " +
                "j'ai entendu les habitants de la Tour parler d'une arme évolutif (rouge)... On raconte que son rang est faible mais sa puissance équivaut voir même surpasse de peu" +
                " celle d'une arme Suprême. Je parle beaucoup, on me le dit souvent hahah, pour te remercier m'avoir écouter, voici une Potion de soin assez efficace.");
        Character vSbire = new Character("Sbire", "Les gens disent que le Créateur de la Tour est mort il y a bien longtemps, mais étant l'un de ses sbires," +
                " je peux te dire que ce ne sont que des rumeurs... Du moins, je crois...");
        Character vCroyante = new Character("Croyante", "Chuuuttttt, faites moins de bruit !! Nous attendons la venu d'Hélios.");
        Character vMartial = new Character("Martial", "Tu peux m'appeller Maître Martial, je suis le responsable de la salle du Savoir. J'ai lu tout " +
                "les ouvrages présent dans cette pièce. Ouvrage 2506 : Chapitre 6 : Le Créateur :\nLe Créateur de la tour est un homme très mystérieux, il.... (Vous semblez avoir perdu" +
                " Martial dans son récit)");

        // création de mes deux bébés : les BOSS
        MonsterCharacter vAlphaMonster = new MonsterCharacter("Alpha", "Je suis l'Alpha, dragon ancestral, et Maître " +
                "des Dragons. Qui es-tu étranger pour venir me déranger pendant mon repos !? Comme les précédents voyageur, tu auras droit à te faire " +
                "dévorer par mes soins, et ainsi augmenter mon compteur de victoire. Je sais déjà ou je vais placer tes os dans ma pièce. Mis a part le " +
                "Créateur de la Tour, personne n'a eu l'exploit de me battre. N'espère pas en être capable, tu es... loin de son niveau...", 1500,
                "Les éléments", 15, true);
        MonsterCharacter vGuardianMonster = new MonsterCharacter("Gardien","Le Créateur de la Tour m'a dit que si personne ne prend la " +
                "Bague de l'Illusion, j'aurais des friandises tout les 5 ans (saute de joie) ! Il a fait de moi un Gardien et ma donné Dents de Loup la massue." +
                " Je suis le Gardien de la Bague, un colosse, le plus fort, oui je suis le plus fort. Jamais tu ne prendra le Trésor de cette tour, sinon" +
                " je n'aurais pas mes friandise dans 1 ans.", 2000,"Dents de Loup", 20, true);

        // ajout des moving PNJ au tableau
        this.aMovingCharacterList = new MovingCharacter[2];
        this.aMovingCharacterList[0] = vFou;
        this.aMovingCharacterList[1] = vMarchand;

        // ajout d'items dans l'inventaire des PNJ
        vProtecteur.addInventoryItem(vMap);
        vAncienne.addInventoryItem(vProtector);
        vAncienne.addInventoryItem(vPage);
        vAncienne.addInventoryItem(vElixir);
        vAncienne.addInventoryItem(vPoussiere);
        vJoel.addInventoryItem(vGenesis);
        vForgeron.addInventoryItem(vPotion);

        // ajout d'items dans l'inventaire du Marchand
        vMarchand.addInventoryItem(vAmulette);
        vMarchand.addInventoryItem(vSentinelle);
        vMarchand.addInventoryItem(vBm);
        vMarchand.addInventoryItem(vPotion);

        // ajout des portes dans le réseau de lieux
        vTransition.setDoor("Haut", vFirstDoor);
        vSecondHall.setDoor("Bas", vFirstDoor);
        vAlpha.setDoor("Haut", vSecondDoor);
        vThirdHall.setDoor("Bas", vSecondDoor);

        // ajout des items dans les pièces
        vHall.addItem(vBracelet);
        vDimension.addItem(vPendentif);
        vDimension.addItem(vAnneau);
        vEquipments2.addItem(vParchemin);
        vTransition.addItem(vRune);
        vThirdHall.addItem(vPlastron);
        vSun.addItem(vFiole);
        vObscurity.addItem(vVanadinite);
        vDimension.addItem(vFirstKey);
        vSavoir.addItem(vSecondKey);
        vIllusion.addItem(vBague);
        vTemp.addItem(vPoigne);
        vTemp.addItem(vPlume);
        vTemp.addItem(vFlacon);
        vTemp.addItem(vListe);
            // ajout des armes dans les pièces
        vTomb.addItem(vDominator);
        vEquipments2.addItem(vBlazewing);
        vEquipments2.addItem(vFairyHunter);
        vEquipments.addItem(vPiques);
        vEquipments.addItem(vSandana);
        vEquipments.addItem(vFrost);

        // ajout des Coins dans les pièces
        vEquipments.addItem(vCoins);
        vIntermediate.addItem(vCoins);
        vSecondHall.addItem(vCoins);
        vSavoir.addItem(vCoins);

        // ajout des PNJ dans les pièces
        vHall.addCharacter(vAdam);
        vTransition.addCharacter(vProtecteur);
        vSecondHall.addCharacter(vFou);
        vTemp.addCharacter(vAncienne);
        vTomb.addCharacter(vAveugle);
        vTomb.addCharacter(vSbire);
        vAlpha.addCharacter(vAlphaMonster);
        vWarden.addCharacter(vGuardianMonster);
        vThirdHall.addCharacter(vJoel);
        vEquipments.addCharacter(vForgeron);
        vSun.addCharacter(vCroyante);
        vSavoir.addCharacter(vMartial);

        // Lieu courant
        this.aPlayer.setCurrentRoom(vHall);
    } // createRooms()

    /**
     * Procédure qui affiche les informations de la salle courante
     * (description, sorties, item(s),...)
     */
    public void printLocationInfo()
    {
        this.aGui.println( this.aPlayer.getCurrentRoom().getLongDescription() );
        if ( this.aPlayer.getCurrentRoom().getImageName() != null ) {
            this.aGui.showImage( this.aPlayer.getCurrentRoom().getImageName() );
        }
    } // printLocationInfo()

    /**
     * Procédure qui mets fin au jeu
     */
    public void endGame()
    {
        this.aGui.println("Merci d'avoir jouer. Au revoir.");
        this.aGui.enable(false);
    } // endGame()

    /**
     * Procédure qui exécute la méthode appropriée à la commande rentrée
     * @param pCommandLine la ligne de commande
     */
    public void interpretCommand( final String pCommandLine ) 
    {
        if ( this.aFightMode ) {
            this.aGui.addTextFight("\n> " + pCommandLine);
        } else {
            this.aGui.println("\n> " + pCommandLine);
        }

        Command vCommand = this.aParser.getCommand( pCommandLine );

        if ( this.aFightMode ) {
            if ( vCommand == null ){
                this.aGui.addTextFight("Je ne sais pas de quoi vous parlez...");
                return;
            }
        } else {
            if (vCommand == null) {
                this.aGui.println("Je ne sais pas de quoi vous parlez...");
                return;
            }
        }
        vCommand.execute(this);
    } // interpretCommand(.)

    /**
     *
     */
    public void moveCharacter()
    {
        for( int i = 0; i< this.aMovingCharacterList.length; i++ ){
            this.aMovingCharacterList[i].moveCharacter();
        }
    }
    
    /**
     * Procédure qui initialise le timer au temps indiquer dans
     * l'attribut et lance le décompte.
     */
    private void timeLimit()
    {
        Timer vTimeLimit = new Timer(this.aLimitTime, this);
        vTimeLimit.start();
    } // timeLimit()

    /**
     * Porcédure qui vérifie le type de l'action passé en paramètre et réagis 
     * en conséquence 
     * @param pE l'action a vérifié
     */
    public void actionPerformed( final ActionEvent pE ) 
    {
        if ( pE.getSource() instanceof Timer) { // instanceOf vérifie que l'obejet 1 est une instance de la classe 2
            this.aGui.println("Le temps s'est écoulé. L'armée asiatique vous a rattrapée.\nVous avez été CAPTURER !!!"); // message a changé
            this.endGame();
            ((Timer)(pE.getSource())).stop();
        }
    } // actionPerformed(.)

} // GameEngine
