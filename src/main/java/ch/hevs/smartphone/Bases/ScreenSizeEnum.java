package ch.hevs.smartphone.Bases;

public enum ScreenSizeEnum {
    // WIDTH = largeur
    // HEIGHT = hauteur
    // HEADER_HEIGHT = hauteur de l'entête
    // CONTENT_PANEL_WIDTH = largeur du panneau contenu
    // CONTENT_PANEL_WEIGHT = hauteur du paneau contenu
    // FOOTER_HEIGHT = hauteur pied de page

    WIDTH(400),
    HEIGHT(700),

    CONTENT_PANEL_WIDTH(300),
    CONTENT_PANEL_HEIGHT(600),

    HEADER_HEIGHT(50),
    FOOTER_HEIGHT(50);

    private int size;

    private ScreenSizeEnum(int size){
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
