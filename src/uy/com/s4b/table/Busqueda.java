package uy.com.s4b.table;

import net.rim.device.api.command.Command;
import net.rim.device.api.command.CommandHandler;
import net.rim.device.api.command.ReadOnlyCommandMetadata;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

/**
 * 
 * @author Pablo
 *
 */
public class Busqueda extends MainScreen {

	private VerticalFieldManager _container;
	private Bitmap _BG;
	private BasicEditField dni;
	private BasicEditField matricula;

	public Busqueda() {
		super(Manager.NO_VERTICAL_SCROLL);
		_BG = Bitmap.getBitmapResource("fondo_inicio.png");
		_container = new VerticalFieldManager( Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR ) {
            protected void paint( Graphics g )
            {
                // Instead of these next two lines, draw your bitmap
                int y = Busqueda.this.getMainManager().getVerticalScroll();
                
                g.drawBitmap( 0, y, _BG.getWidth(), _BG.getHeight(), _BG, 0, 0 );
                
                super.paint( g );
            }
            
            // The keydown and navigation overrides are a workaround
            // for an optimization hack RIM performed, perhaps assuming that
            // Managers would not try to take control of painting on
            // a MainScreen.
            protected boolean keyDown( int keycode, int status )
            {
                invalidate();
                
                return super.keyDown( keycode, status );
            }
            
            protected boolean navigationMovement( int dx, int dy, int status, int time )
            {
                invalidate();
                
                return super.navigationMovement( dx, dy, status, time );
            }
        };
        
        super.add( _container );
    
		Manager mainManager = getMainManager();
		
		dni = new BasicEditField("DNI: ", "", 10, BasicEditField.FILTER_DEFAULT);
		mainManager.add(dni);
		
		matricula = new BasicEditField("Matrícula: ", "", 9, BasicEditField.FILTER_DEFAULT);
		mainManager.add(matricula);
		
		ButtonField buscar = new ButtonField("Buscar Multas", ButtonField.CONSUME_CLICK);
        mainManager.add(buscar);
        
        DatosBusqueda datosBusqueda = new DatosBusqueda() {
			
			public String getMatricula() {
				return matricula.getText();
			}
			
			public String getDni() {
				return dni.getText();
			}
		};
		
		buscar.setCommandContext(datosBusqueda);
		buscar.setCommand(new Command(new CommandHandler() {
			
			public void execute(ReadOnlyCommandMetadata metadata, Object context) {
				Dialog.alert("" + context);
				DatosBusqueda datosBusqueda = (DatosBusqueda) context;
				if (datosBusqueda.getDni().equals("") || datosBusqueda.getMatricula().equals("")){
					Dialog.alert("Datos incorrectos");
				} else {
					UiApplication.getUiApplication().pushScreen(
							new Resultados(datosBusqueda.getDni(), datosBusqueda.getMatricula()));
				}
			}
		}));
	}
	
    public void add( Field field )
    {
        _container.add( field );
    }
    
    public void delete( Field field )
    {
        _container.delete( field );
    }
	
	interface DatosBusqueda{
		public String getDni();
		public String getMatricula();
	}
}

