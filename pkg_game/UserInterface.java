package pkg_game;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Image;

// couleur des objets (boutons,...)
import java.awt.Color;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.URL;

// police d'écriture
import java.awt.Font;
/**
 * La classe UserInterface permet d'initialiser l'interface homme machine avec lequel le joueur intéragira
 * 
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame aMyFrame;
    private JTextField aEntryField;
    private JTextArea aLog;
    private JLabel aImage;
    private JButton[] aButtonTab;
    private JScrollPane aListScroller;
    private JScrollPane aInventoryScroller;
    private JScrollPane aFightScroller;
    private JTextArea aInventory;
    private JTextArea aFight;
    private boolean aMapImage;
    
    /**
     * Constructeur naturel de UserInteface
     * @param pGameEngine le moteur du jeu
     */
    public UserInterface( final GameEngine pGameEngine ) 
    {
        this.aEngine = pGameEngine;
        this.aMapImage = false;
        this.createGUI();
    } // UserInterface(.)

    /**
     * Methode qui affiche du texte
     * @param pText ligne de texte
     */
    public void print( final String pText )
    {
        this.aLog.append(pText);
        this.aLog.setCaretPosition(this.aLog.getDocument().getLength());
    } // print(.)

    /**
     * Méthode qui affiche le texte avec un saut de ligne
     * @param pText ligne de texte
     */
    public void println( final String pText )
    {
        this.print(pText + "\n");
    } // println(.)

    /**
     * Procédure qui permet de montrer une image dans l'interface
     * @param pImageName le nom de l'image
     */
    public void showImage( final String pImageName )
    {
        String vImagePath = "Images/" + pImageName; // pour changer le repertoire
        URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image non trouvée : " + vImagePath );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * Active ou désactive la saisi dans le champ de saisi.
     * @param pOnOff boolean qui active ou desactive la saisi
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff ); // enable/disable
        if ( ! pOnOff ) { // disable
            this.aEntryField.getCaret().setBlinkRate( 0 ); // cursor won't blink
            this.aEntryField.removeActionListener( this ); // won't react to entry
        }
    } // enable(.)

    /**
     * Procédure qui crée l'interface graphique de l'utilisateur
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "R&LT - by 113 Block | Tokenizer" ); // change the title
        this.aEntryField = new JTextField( 34 );

        this.aLog = new JTextArea();
        // permet de passer a la ligne lorsque l'on arrive au bout de l'écran
        this.aLog.setLineWrap(true);// permet de couper la phrase lorsqu'on arrive a la limite de l'espace dispo pour faire un retour a la ligne
        this.aLog.setWrapStyleWord(true);//permet de ne pas couper le mot lorsqu'on arrive a la fin de l'espace dispo et d'aller a la ligne

        this.aLog.setEditable( false );
        this.aListScroller = new JScrollPane( this.aLog );

        JPanel vPanel = new JPanel();
        this.aImage = new JLabel();
        
        //modification de la police
        this.aLog.setFont(new Font("Calibri", Font.PLAIN, 12));
        
        //creation d'un panneau pour les boutons
        JPanel vButtonPanel = new JPanel();

        //création d'un panneau pour l'inventaire/affichage/combat
        JPanel vAffichagePanel = new JPanel();

        //création d'une zone de texte pour l'inventaire
        this.aInventory = new JTextArea();
        this.aInventory.setEditable( false );
        this.aInventory.setLineWrap(true); 
        this.aInventory.setWrapStyleWord(true);
            // zone de texte pour les combats
        this.aFight = new JTextArea();
        this.aFight.setEditable(false);
        this.aFight.setLineWrap(true);
        this.aFight.setWrapStyleWord(true);

        //ajout de la zone de texte dans le panneau scrollable
        this.aInventoryScroller = new JScrollPane( this.aInventory );
        this.aFightScroller = new JScrollPane( this.aFight );

        // création des boutons
        JButton vHelpButton = new JButton("aide");
        JButton vQuitButton = new JButton("quitter");
        JButton vBackButton = new JButton("retour");
        JButton vInventoryButton = new JButton("inventaire");
        JButton vMap = new JButton("carte");
        JButton vAttack = new JButton("attaquer");
        JButton vDominator = new JButton("Dominator");
        JButton vProtector = new JButton("Protector");
        JButton vBlazewing = new JButton("Blazewing");
        JButton vFairyHunter = new JButton("FairyHunter");
        JButton vPiques = new JButton("Piques");
        JButton vFrost = new JButton("Frost");

        // modification de la couleur des boutons
        vHelpButton.setBackground( Color.darkGray );
        vHelpButton.setForeground( Color.green );
        vQuitButton.setBackground( Color.darkGray );
        vQuitButton.setForeground( Color.green );
        vBackButton.setBackground( Color.darkGray );
        vBackButton.setForeground( Color.green );
        vInventoryButton.setBackground( Color.darkGray );
        vInventoryButton.setForeground( Color.green );
        vMap.setBackground( Color.darkGray );
        vMap.setForeground( Color.green );
        vAttack.setBackground( Color.darkGray );
        vAttack.setForeground( Color.white);
        vDominator.setBackground( Color.darkGray );
        vDominator.setForeground( Color.magenta );
        vProtector.setBackground( Color.darkGray );
        vProtector.setForeground( Color.yellow );
        vBlazewing.setBackground( Color.darkGray );
        vBlazewing.setForeground( Color.cyan );
        vFairyHunter.setBackground( Color.darkGray );
        vFairyHunter.setForeground( Color.pink );
        vPiques.setBackground( Color.darkGray );
        vPiques.setForeground( Color.red );
        vFrost.setBackground( Color.darkGray );
        vFrost.setForeground( Color.white );

        // modification de la couleur de l'interface graphique
        this.aEntryField.setBackground( Color.gray );
        this.aEntryField.setForeground( Color.white );
        this.aLog.setBackground( Color.gray );
        this.aLog.setForeground( Color.white );
        vPanel.setBackground( Color.gray );
        vButtonPanel.setBackground( Color.gray );

        vAffichagePanel.setBackground( Color.gray );
        this.aInventory.setBackground( Color.gray );
        this.aInventory.setForeground( Color.white );
        this.aFight.setBackground( Color.gray );
        this.aFight.setForeground( Color.white );

        this.aButtonTab = new JButton[12];
        // ajout de bouton dans le tableau
        this.aButtonTab[0] = vHelpButton;
        this.aButtonTab[1] = vBackButton;
        this.aButtonTab[2] = vInventoryButton;
        this.aButtonTab[3] = vQuitButton;
        this.aButtonTab[4] = vMap;
        this.aButtonTab[5] = vAttack;
        this.aButtonTab[6] = vDominator;
        this.aButtonTab[7] = vProtector;
        this.aButtonTab[8] = vBlazewing;
        this.aButtonTab[9] = vFairyHunter;
        this.aButtonTab[10] = vPiques;
        this.aButtonTab[11] = vFrost;

        // taille des panneaux
        //vPanel.setPreferredSize( new Dimension(700,600) );
        vPanel.setSize(900, 800);
        
        // dimension panneau d'affichage et zone de texte
        int vPanelWidth = (int)(vPanel.getWidth() * 0.8);
        int vPanelHeight = (int)(vPanel.getHeight() * 0.5);
        this.aListScroller.setPreferredSize( new Dimension(vPanelWidth, vPanelHeight) );
        this.aInventoryScroller.setPreferredSize( new Dimension(vPanelWidth, vPanelHeight) );
        this.aFightScroller.setPreferredSize( new Dimension(vPanelWidth, vPanelHeight) );

        // dimension zone d'image
        int vImageHeight = (int)(vPanel.getHeight() * 0.4);
        this.aImage.setPreferredSize( new Dimension(vPanelWidth, vImageHeight) );
        
        // ajout de composants au panneau de l'interface
        vPanel.setLayout( new BorderLayout() ); // ==> only five places
        vPanel.add( this.aImage, BorderLayout.NORTH );
        vPanel.add( vAffichagePanel, BorderLayout.CENTER );
        vPanel.add( this.aEntryField, BorderLayout.SOUTH );
        vPanel.add( vButtonPanel, BorderLayout.WEST );
        
        //ajout de composants au panneau des boutons
        vButtonPanel.setLayout( new GridLayout(12, 0) );
        vButtonPanel.add( vHelpButton );
        vButtonPanel.add( vMap );
        vButtonPanel.add( vInventoryButton );
        vButtonPanel.add( vBackButton );
        vButtonPanel.add( vDominator );
        vButtonPanel.add( vProtector );
        vButtonPanel.add( vBlazewing );
        vButtonPanel.add( vFairyHunter );
        vButtonPanel.add( vPiques );
        vButtonPanel.add( vFrost );
        vButtonPanel.add( vAttack );
        vButtonPanel.add( vQuitButton );

        // ajout de composants au panneau de l'affichage
        vAffichagePanel.setLayout( new FlowLayout() );
        vAffichagePanel.add( this.aListScroller );
        vAffichagePanel.add( this.aInventoryScroller );
        vAffichagePanel.add( this.aFightScroller );
        

        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );
        // add some event listeners to some components
        this.aEntryField.addActionListener( this );
        vHelpButton.addActionListener( this );
        vBackButton.addActionListener( this );
        vQuitButton.addActionListener( this );
        vInventoryButton.addActionListener( this );
        vMap.addActionListener( this );
        vDominator.addActionListener( this );
        vProtector.addActionListener( this );
        vBlazewing.addActionListener( this );
        vFairyHunter.addActionListener( this );
        vPiques.addActionListener( this );
        vFrost.addActionListener( this );
        vAttack.addActionListener( this );

        // to end program when window is closed
        this.aMyFrame.addWindowListener( new WindowAdapter() {
                public void windowClosing(WindowEvent e) { System.exit(0); }
            } );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
        this.visibilityInventory(false); // rend le panneau d'inventaire invisible par défaut
        this.fightVisibilityPane(false); // rend le panneau des combats invisible par défaut
        this.setEnableInventoryB(false); // rend le bouton d'inventaire inutilisable par défaut
        this.setEnableBackB(false); // rend le bouton de retour inutilisable par défaut
        this.setEnableMapB(false); // rend le bouton carte inutilisable par défaut
        this.enableButtons(); // rend les boutons d'armes inutilisable par défaut
        this.setEnableAttackB(false); // rend le bouton d'attaque inutilisable par
    } // createGUI()  

    /**
     * Procédure qui permet de modifier la police d'écriture du panneau
     * d'inventaire lorsque celui-ci est visible
     */
    public void fontInventoryPanel()
    {
        if ( this.aInventoryScroller.isVisible() ){
            this.aInventory.setFont(new Font("Calibri", Font.PLAIN, 12));
        }
    } // fontInventoryPanel()

    /**
     * Procédure qui permet de modifier la police d'écriture du panneau
     * de combat lorsque celui-ci est visible
     */
    public void fontFightPanel()
    {
        if ( this.aFightScroller.isVisible() ){
            this.aFight.setFont(new Font("Calibri", Font.PLAIN, 12));
        }
    } // fontFightPanel()

    /**
     *
     */
    public void enableButtons()
    {
        for ( int i = 6; i < this.aButtonTab.length; i++ ){
            if ( this.aEngine.getFightMode() ) {
                if ( this.aEngine.getPlayer().getInventoryItem(this.aButtonTab[i].getActionCommand()) != null ) {
                    this.aButtonTab[i].setEnabled(true);
                } else {
                    this.aButtonTab[i].setEnabled(false);
                }
            } else {
                this.aButtonTab[i].setEnabled(false);
            }
        }
    } // enableButtons()

    /**
     * Procédure qui permet de rendre visible ou non le panneau 
     * scrollable de l'inventaire, et de mettre la visibilité opposé
     * au panneau d'affichage du texte du jeu
     * @param pVisibility le booléen indiquant la visibilité des panneaux
     */
    public void visibilityInventory( final boolean pVisibility )
    {
        this.aInventoryScroller.setVisible(pVisibility);
        this.aListScroller.setVisible(!pVisibility);
        this.aFightScroller.setVisible(false);
    } // visibilityInventory(.)

    /**
     * Procédure qui permet de rendre visible ou non le panneau
     * scrollable de combat, et de mettre la visibilité opposé
     * au panneau d'affichage du texte du jeu
     * @param pVisibility le booléen indiquant la visibilité des panneaux
     */
    public void fightVisibilityPane( final boolean pVisibility )
    {
        this.aFightScroller.setVisible(pVisibility);
        this.aListScroller.setVisible(!pVisibility);
        this.aInventoryScroller.setVisible(false);
        this.fontFightPanel();
    } // FightVisibilityPane(.)

    /**
     * Procédure qui inverse la visibilité du panneau d'inventaire
     */
    public void inverseVisibility()
    {
        this.visibilityInventory( !this.aInventoryScroller.isVisible() );
        if ( this.aInventoryScroller.isVisible() ) this.println("Bracelet : Ouverture de l'inventaire");
        else this.println("Bracelet : Fermeture de l'inventaire");
    } // inverseVisibility() 
    
    /**
     * Fonction booléenne qui affiche la map si le booléen est mis a vrai sinon l'image de la pièce
     * @param pVisibility le booléen indiquant la visibilité de la map
     */
    public void mapVisibility( final boolean pVisibility )
    {
        if ( pVisibility ){
            for ( int i = 0; i<= 4; i++){
                if( this.aEngine.getRoom(i).equals(this.aEngine.getPlayer().getCurrentRoom()) ) this.showImage("Etage1.png");
            }
            for ( int i = 5; i<= 10; i++){
                if( this.aEngine.getRoom(i).equals(this.aEngine.getPlayer().getCurrentRoom()) ) this.showImage("Etage2.png");
            }
            for ( int i = 10; i<= 15; i++){
                if( this.aEngine.getRoom(i).equals(this.aEngine.getPlayer().getCurrentRoom()) ) this.showImage("Etage3.png");
            }
            this.aMapImage = true;
        } else {
            this.showImage(this.aEngine.getPlayer().getCurrentRoom().getImageName());
            this.aMapImage = false;
        }
    } // mapVisibility(.)

    /**
     *
     */
    public boolean getMapImage()
    {
        return this.aMapImage;
    }

    /**
     * Procédure qui permet d'écrire dans le panneau d'inventaire
     * en remplacant le texte présent et en affichant le nouveau
     * @param pText le texte à afficher
     */
    public void writeInventory( final String pText )
    {
        this.aInventory.setText(pText);
        this.aMyFrame.pack(); // permet de charger le texte pour l'afficher
    } // writeInventory(.)

    /**
     * Procédure qui permet d'écrire dans le panneau de combat
     * en remplacant le texte présent et en affichant le nouveau
     * @param pText le texte à afficher
     */
    public void writeFight( final String pText )
    {
        this.aFight.setText(pText);
        this.aMyFrame.pack(); // permet de charger le texte pour l'afficher
    } // writeFight(.)

    /**
     * Procédure qui permet d'écrire dans le panneau de combat
     * en ajoutant au texte présent le texte passé en paramètre
     * @param pText le texte à afficher en plus de l'ancien
     */
    public void addTextFight( final String pText )
    {
        this.aFight.append(pText + "\n"); // append pour ajouter du texte dans le panneau sans supprimer l'ancier au contraire de setText()
        this.aMyFrame.pack(); // permet de charger le texte pour l'afficher
    } // addTextFight(.)

    /**
     * Porcédure qui permet d'activer ou de désactiver le bouton 
     * d'inventaire pour le rendre utilisable ou non
     * @param pEnable le booléen qui indique si le bouton sera actif ou non
     */
    public void setEnableInventoryB( final boolean pEnable )
    {
        this.aButtonTab[2].setEnabled(pEnable);
    } // setEnableInventoryB(.)

    /**
     *
     * @param pEnable
     */
    public void setEnableAttackB( final boolean pEnable )
    {
        this.aButtonTab[5].setEnabled(pEnable);
    } // setEnableInventoryB(.)

    /**
     * Porcédure qui permet d'activer ou de désactiver le bouton 
     * de retour pour le rendre utilisable ou non
     * @param pEnable le booléen qui indique si le bouton sera actif ou non
     */
    public void setEnableBackB( final boolean pEnable )
    {
        this.aButtonTab[1].setEnabled(pEnable);
    } // setEnableBackB(.)

    /**
     *
     */
    public void setEnableMapB( final boolean pEnable )
    {
        this.aButtonTab[4].setEnabled(pEnable);
    }

    /**
     * Porcédure qui vérifie le type de l'action passé en paramètre et réagis 
     * en conséquence en fonction de s'il s'agit d'un clic ou d'un appuie 
     * sur 'entré' dans le clavier
     * @param pE l'action a vérifié
     */
    public void actionPerformed( final ActionEvent pE ) 
    {
        // no need to check the type of action at the moment
        // because there is only one possible action (text input) :
        for ( int i = 0; i<this.aButtonTab.length; i++ ) {
            if ( pE.getSource() == this.aButtonTab[i] ){
                if ( i < 6 ) {
                    this.aEngine.interpretCommand( pE.getActionCommand());
                } else {
                    this.aEngine.interpretCommand("attaquer avec " + pE.getActionCommand());
                }
                return;
            }
        }
        this.processCommand(); // never suppress this line
    } // actionPerformed(.)

    /**
     * Procédure qui lis la commande et l'excécute 
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
    } // processCommand()

} // UserInterface