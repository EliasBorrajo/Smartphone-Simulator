package ch.hevs.smartphone.parameters;

/**
 * Enum qui contient les valeures fixes de nos panels
 *
 * @author Lonfat Milena, Borrajo ELias
 */

public enum ScreenSizeEnum {
    WIDTH(300),                 // WIDTH = largeur
    HEIGHT(600),                // HEIGHT = hauteur

    CONTENT_PANEL_WIDTH(300),   // CONTENT_PANEL_WIDTH = largeur du panneau contenu
    CONTENT_PANEL_HEIGHT(600),  // CONTENT_PANEL_HEIGHT = hauteur du paneau contenu

    HEADER_FOOTER_HEIGHT(30);   // HEADER_FOOTER_HEIGHT = hauteur de l'entête et du pied de page

    private int size;

    private ScreenSizeEnum(int size) { this.size = size; }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public int getSize() {
        return size;
    }

}