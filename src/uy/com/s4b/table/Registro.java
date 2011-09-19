/**
 * 
 */
package uy.com.s4b.table;

import java.rmi.RemoteException;

import net.rim.device.api.command.Command;
import net.rim.device.api.command.CommandHandler;
import net.rim.device.api.command.ReadOnlyCommandMetadata;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.EmailAddressEditField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.GridFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.decor.Background;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;
import uy.com.s4b.webservice.ArrayOfLocalizadorBoletinesInfo;
import uy.com.s4b.webservice.LocalizadorBoletinesInfo;
import uy.com.s4b.webservice.WebServiceSoap_Stub;

/**
 * @author Pablo
 *
 */
public class Registro extends MainScreen {
	private static Screen registroScreen;

	protected static final int BLACKBERRY = 4;
	private BasicEditField dni;
	private BasicEditField matricula;
	private BasicEditField email;
	private BasicEditField telefono;
	private CheckboxField quiereRecibirInfo;

	public Registro() {
		super(Manager.NO_VERTICAL_SCROLL);
		registroScreen = this;
		ButtonField volverBtn = new ButtonField("Volver");
		volverBtn.setCommand(new Command(new CommandHandler() {
			
			public void execute(ReadOnlyCommandMetadata metadata, Object context) {
				UiApplication.getUiApplication().pushScreen(new Busqueda());
				UiApplication.getUiApplication().popScreen(registroScreen);
			}
		}));
		add(volverBtn);
		
		
		Manager mainManager = getMainManager();

		XYEdges margin = new XYEdges(8, 0, 10, 0);
		XYEdges edges = new XYEdges(3, 3, 2, 2);
		Border border = BorderFactory.createRoundedBorder(edges, Color.GRAY, Border.STYLE_FILLED);
		Background gradient = BackgroundFactory.createLinearGradientBackground(Color.WHITE, Color.WHITE, Color.LIGHTGRAY, Color.LIGHTGRAY);
		
		setTitle("Darse de Alta");
		
		String text = "Si sales publicado en algún boletín te avisaremos gratuitamente de inmediato:";
		RichTextField textField = new RichTextField(text);
		mainManager.add(textField);
		
		dni = new BasicEditField("", "", 10, BasicEditField.FILTER_DEFAULT);
		dni.setBackground(gradient);
		dni.setBorder(border);
		dni.setMargin(margin);

		matricula = new BasicEditField("", "", 9, BasicEditField.FILTER_DEFAULT);
		matricula.setBackground(gradient);
		matricula.setBorder(border);
		matricula.setMargin(margin);
		
		email = new EmailAddressEditField("", "(para poder avisar)", 50, BasicEditField.FILTER_EMAIL);
		email.setBackground(gradient);
		email.setBorder(border);
		email.setMargin(margin);
		
		telefono = new BasicEditField("", "(opcional)", 50, BasicEditField.FILTER_PHONE);
		telefono.setBackground(gradient);
		telefono.setBorder(border);
		telefono.setMargin(margin);
        
        GridFieldManager grid = new GridFieldManager(4, 2, 0);
        LabelField dniLbl = new LabelField("DNI ", FIELD_RIGHT);
		grid.insert(dniLbl, 0, FIELD_RIGHT);
        grid.insert(dni, 1);
        
        LabelField matriculaLbl = new LabelField("Matrícula ", FIELD_RIGHT);
		grid.insert(matriculaLbl, 2, FIELD_RIGHT);
        grid.insert(matricula, 3);
        
        LabelField emailLbl = new LabelField("Email ", FIELD_RIGHT);
		grid.insert(emailLbl, 4, FIELD_RIGHT);
        grid.insert(email, 5);
       
        LabelField telefonoLbl = new LabelField("Teléfono ", FIELD_RIGHT);
        grid.insert(telefonoLbl, 6, FIELD_RIGHT);
        grid.insert(telefono, 7);
        
        mainManager.add(grid);
        
        String acgText = "Sí, he leído y acepto las Condiciones Generales";
        final CheckboxField aceptaCondicionesGenerales = new CheckboxField(acgText, false);
        aceptaCondicionesGenerales.setMargin(margin);
        mainManager.add(aceptaCondicionesGenerales);

        String qriText = "Sí, acepto recibir información sobre los productos comercializados por Dvuelta, Asistencia Legal S.L.";
        quiereRecibirInfo = new CheckboxField(qriText, false);
        quiereRecibirInfo.setMargin(margin);
        mainManager.add(quiereRecibirInfo);

        ButtonField darseDeAltaBtn = new ButtonField("Darse de Alta", ButtonField.CONSUME_CLICK);
        mainManager.add(darseDeAltaBtn);
        
        DatosRegistro datos = new DatosRegistro(){
        	public String getDni(){
        		return dni.getText();
        	}

			public String getMatricula() {
				return matricula.getText();
			}

			public String getEmail() {
				return email.getText();
			}

			public String getTelefono() {
				return telefono.getText();
			}

			public boolean isAceptaCondiciones() {
				return aceptaCondicionesGenerales.getChecked();
			}

			public boolean isRecibirInfo() {
				return quiereRecibirInfo.getChecked();
			}
        };
        
        darseDeAltaBtn.setCommandContext(datos);
        darseDeAltaBtn.setCommand(new Command(new CommandHandler() {
			
			public void execute(ReadOnlyCommandMetadata metadata, Object context) {
				DatosRegistro datos = (DatosRegistro) context;
				try {
					ArrayOfLocalizadorBoletinesInfo resultado = grabarDatos(datos);
					LocalizadorBoletinesInfo listaInfor [] = resultado.getLocalizadorBoletinesInfo();
					
					if (listaInfor != null){
						for (int i = 0; i < listaInfor.length; i++) {
							if (listaInfor[i].getResultado().equals("OK")){
								Dialog.alert("El proceso se ha realizado satisfactoriamente");
							} else {
								Dialog.alert(listaInfor[i].getResultado());
							}
						}
					}
				} catch (RemoteException e) {
					Dialog.alert(e.getMessage());
				}
			}

			private ArrayOfLocalizadorBoletinesInfo grabarDatos(DatosRegistro datos) throws RemoteException {
				WebServiceSoap_Stub webservices = new WebServiceSoap_Stub();
				ArrayOfLocalizadorBoletinesInfo resultado = webservices.grabaDatos("iphonewebsrv", "Webiphonex14k", datos.getDni(), 
						datos.getMatricula(), datos.getEmail(), null, datos.getTelefono(), 0, 
						0, datos.isAceptaCondiciones() ? 1 : 0,
						datos.isRecibirInfo() ? 1 : 0, BLACKBERRY);
				return resultado;
			}
		}));
	}
	
	interface DatosRegistro {
		public String getDni();
		public String getMatricula();
		public String getEmail();
		public String getTelefono();
		public boolean isAceptaCondiciones();
		public boolean isRecibirInfo();
	}
}
