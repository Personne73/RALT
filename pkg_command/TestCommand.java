package pkg_command;

 

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import pkg_game.GameEngine;
/**
 * Cette classe représente la commande test
 *
 * @author MBIAPA KETCHECKMEN Joël Trésor
 * @version 2021.05
 */
public class TestCommand extends Command
{
    

    /**
     * Constructeur pour créer une commande test
     */
    public TestCommand()
    {
        
    } // TestCommand()

    /**
     * Fonction booléenne qui vérifie qu'un second mot de command est présent
     * pour accéder au fichier ayant pour nom ce second mot de commande
     * de manière à le Scanner et lire les instructions qui s'y trouvent
     * @param pEngine le moteur du jeu pour accèder aux éléménts nécéssaires
     * @return toujours faux
     */
    @Override public boolean execute( final GameEngine pEngine)
    {

        if ( !pEngine.getFightMode() ) {
            pEngine.getGui().visibilityInventory(false);
            if (this.hasSecondWord()) {
                if (this.hasThirdWord()) {
                    pEngine.getGui().println("Cette commande ne prend pas de troisème mot.");
                    return false;
                }
                pEngine.setTestMode(true);
                Scanner vFileText;
                String vFileName = this.getSecondWord();

                //Association du fichier a la variable vFileText
                //try : pour essayer les instructions
                try {
                    vFileText = new Scanner(new File(vFileName + ".txt"));
                    //while : pour traiter la ligne lue
                    while (vFileText.hasNextLine()) {
                        String vLine = vFileText.nextLine();
                        pEngine.getGui().println(vLine);
                        pEngine.interpretCommand(vLine);
                    }
                } catch (final FileNotFoundException pFNFE) {
                    System.out.println(pFNFE);
                }
                pEngine.setTestMode(false);
            } else {
                pEngine.getGui().println("Je sais seulement tester des fichiers .txt");
            }
        } else {
            pEngine.getGui().addTextFight("Cette commande n'est pas disponible en mode combat.");
        }
        return false;
    } // execute(.)
} // TestCommand
