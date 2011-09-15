/**
 * 
 */
package uy.com.s4b.table;

import java.rmi.RemoteException;


import net.rim.device.api.command.Command;
import net.rim.device.api.command.CommandHandler;
import net.rim.device.api.command.ReadOnlyCommandMetadata;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.component.SeparatorField;
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

		XYEdges thickPadding = new XYEdges(10, 10, 10, 10);
		Border bevelBorder = BorderFactory.createRoundedBorder(thickPadding, Border.STYLE_TRANSPARENT);
		Background bitmapBackground = BackgroundFactory.createBitmapBackground(
				Bitmap.getBitmapResource("input_datos.png"),
				Background.POSITION_X_RIGHT, Background.POSITION_Y_CENTER, Background.REPEAT_NONE);

		Background gradientBackground = BackgroundFactory.createLinearGradientBackground(
				Color.GRAY, Color.GRAY, Color.WHITESMOKE, Color.WHITESMOKE);
		
		setTitle("Darse de Alta");
		
		String text = "Si sales publicado en algún boletín te avisaremos gratuitamente de inmediato:";
		RichTextField textField = new RichTextField(text);
		mainManager.add(textField);
		
		add(new SeparatorField());

		dni = new BasicEditField("DNI: ", "", 10, BasicEditField.FILTER_DEFAULT);
		dni.setBackground(gradientBackground);
		dni.setBorder(bevelBorder);
		try {
			FontFamily family = FontFamily.forName("BBCasual");
			Font font = family.getFont(Font.PLAIN, 40 );
			dni.setFont(font);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mainManager.add(dni);

		matricula = new BasicEditField("Matrícula: ", "", 9, BasicEditField.FILTER_DEFAULT);
		matricula.setBackground(gradientBackground);
		matricula.setBorder(bevelBorder);
		mainManager.add(matricula);
		
		email = new BasicEditField("Email: ", "(para poder avisar)", 50, BasicEditField.FILTER_EMAIL);
		email.setBackground(gradientBackground);
		email.setBorder(bevelBorder);
		mainManager.add(email);
		
		telefono = new BasicEditField("Teléfono: ", "(opcional)", 50, BasicEditField.FILTER_PHONE);
		telefono.setBackground(gradientBackground);
		telefono.setBorder(bevelBorder);
        mainManager.add(telefono);
        
        add(new SeparatorField());
        String acgText = "Sí, he leído y acepto las Condiciones Generales";
        final CheckboxField aceptaCondicionesGenerales = new CheckboxField(acgText, false);
        mainManager.add(aceptaCondicionesGenerales);

        String qriText = "Sí, acepto recibir información sobre los productos comercializados por Dvuelta, Asistencia Legal S.L.";
        quiereRecibirInfo = new CheckboxField(qriText, false);
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
				Dialog.alert(datos.getEmail() + " " + datos.isAceptaCondiciones() + " " + datos.isRecibirInfo());
				
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
