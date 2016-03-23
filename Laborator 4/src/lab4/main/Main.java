package lab4.main;

import lab4.controller.AudioManager;

/**
 *
 * @author Anca Adascalitei, Ionut Iacob
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AudioManager spotify = new AudioManager();
        spotify.start();
    }
    
    //majoritatea comenzilor merg cu path relativ si absolut
    //Find: dupa nume si dupa metadata (am folosit Apache Tika)
    //Play: merge global (cale absoluta sau locala) (doar pt muzica)
    //Fav: Serializare in fisier si deserializare melodie favorita
    //Rmfav: sterge din fisierul de serializare melodia favorita
    //Clasa Song cu un path si metadata
    //Help command - afiseaza toate comenzile disponibile
    //Report (face un raport dupa datele serializate) (am folosit Freemarker)
    
    /*
    Main: 2 linii de cod (instantiem, porneste aplicatia)
    AudioManager: (citesc comanda ca un sir de caractere si execut comanda dorita)
            - director current (modificat de clasa ChangeDirectory)
            - bucla while cu metoda readCommand() (return string), parse() (return comanda), execute()(executa comanda data)
    Command (interfata)
    AbstractCommand(clasa abstracta, contine cod comun)
            - metoda ce ma duce la Audio Manager (director curent etc.), set/get args, execute()
            - diverse implementari: ListCommand, PlayCommand ... (clase mostenite din AbstractCommand)
    
    Commands:
            - find: Files.walkFileTree, reg.ex (var. simplificata: glob)
            - list: Files.list + filtrare rezultate
            - info: folosirea unei biblioteci 3rd party (get meta data)(la alegere: ex Apache Tika)
            - fav: read, write to fisier (List<String/Song> lista trebuie sa ajunga in fisier), sau serializam (mai usor)
            - report(bonus): generare html (fisier) -> (separarea codului de html), folosirea unui template engine (FreeMarker)
    
    Exceptii:
            - trebuie tratate, macar un custom
            - example: Invalid Command Exception (Checked - trebuie tratata neaparat)
            - parseCommand(): arunca exceptii
    
    Instalare biblioteci: fisiere sursa .jar (trebuie adaugate in CLASSPATH -> NetBeans: add jar/library)
    */
}
