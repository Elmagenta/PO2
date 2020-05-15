package patterns;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Singleton {

    public static class Display {
        // Eventuali campi privati non-statici

        @Nullable
        private static Display instance =  null;

        /* Mettendo il costruttore privato questo può essere acceduto solo dai membri della classe */
        private Display() {
            // Inizializza tutti i tuoi campi non-statici
        }

        @NotNull
        public static Display getInstance() {
            if (instance == null) {
                instance = new Display();
            }

            return instance;
        }
    }

    public void main(String[] args) {
        Display d = Display.getInstance();
        Display d2 = Display.getInstance();
    }
}
