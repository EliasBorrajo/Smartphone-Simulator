package ch.hevs.smartphone.parameters;

/**
 * @author Lonfat Milena, Borrajo ELias
 * Enum which contains the fixed values of the panels
 */

public enum ScreenSizeEnum {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    WIDTH(300),
    HEIGHT(600),

    CONTENT_PANEL_WIDTH(300),
    CONTENT_PANEL_HEIGHT(600),

    HEADER_FOOTER_HEIGHT(30);

    private int size;

    //*****************************************************************************
    // C O N S T R U C T O R
    //*****************************************************************************
    /**
     * Constructor
     * @param size
     */
    private ScreenSizeEnum(int size) { this.size = size; }

    //*****************************************************************************
    // G E T T E R S
    //*****************************************************************************
    public int getSize() {
        return size;
    }
}