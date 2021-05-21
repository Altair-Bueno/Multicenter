package App.Multicenter.GUI;

public class AppWindow {
    //Attributes
    PersonalSpaceView ps;
    SideBar sb;
  
    //Constructor
    public AppWindow(PersonalSpaceView personalSpaceView, SideBar sideBar) {
        ps = personalSpaceView;
        sb = sideBar;
    }
    
    //Methods
    /**
     * Cambia el espacio personal a mostrar en la vista principal de la aplicación
     * 
     * @param newPs Espacio personal a mostrar
     */
    public void changePersonalSpace(PersonalSpaceView newPs) {
        ps = newPs;   
    }
    
    /**
     * Crea y muestra todos los elementos visuales de la aplicación
     *
     */
    public void createAndShowGUI() {
        
    }
}
