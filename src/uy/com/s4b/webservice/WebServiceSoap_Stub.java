// This class was generated by 172 StubGenerator.
// Contents subject to change without notice.
// @generated

package uy.com.s4b.webservice;

import javax.xml.rpc.JAXRPCException;

import javax.xml.namespace.QName;
import javax.microedition.xml.rpc.Operation;
import javax.microedition.xml.rpc.Type;
import javax.microedition.xml.rpc.ComplexType;
import javax.microedition.xml.rpc.Element;

public class WebServiceSoap_Stub implements uy.com.s4b.webservice.WebServiceSoap, javax.xml.rpc.Stub {
	private String[] _propertyNames;
	private Object[] _propertyValues;

	public WebServiceSoap_Stub() {
		_propertyNames = new String[] {ENDPOINT_ADDRESS_PROPERTY};
		_propertyValues = new Object[] {"http://www.buscamultas.com/DesktopModules/ValorIndirecto.viWeb/webservice.asmx"};
	}

	public void _setProperty(String name, Object value) {
		int size = _propertyNames.length;
		for (int i = 0; i < size; ++i) {
			if (_propertyNames[i].equals(name)) {
				_propertyValues[i] = value;
				return;
			}
		}
		// Need to expand our array for a new property
		String[] newPropNames = new String[size + 1];
		System.arraycopy(_propertyNames, 0, newPropNames, 0, size);
		_propertyNames = newPropNames;
		Object[] newPropValues = new Object[size + 1];
		System.arraycopy(_propertyValues, 0, newPropValues, 0, size);
		_propertyValues = newPropValues;

		_propertyNames[size] = name;
		_propertyValues[size] = value;
	}

	public Object _getProperty(String name) {
		for (int i = 0; i < _propertyNames.length; ++i) {
			if (_propertyNames[i].equals(name)) {
				return _propertyValues[i];
			}
		}
		if (ENDPOINT_ADDRESS_PROPERTY.equals(name) || USERNAME_PROPERTY.equals(name) || PASSWORD_PROPERTY.equals(name)) {
			return null;
		}
		if (SESSION_MAINTAIN_PROPERTY.equals(name)) {
			return new java.lang.Boolean(false);
		}
		throw new JAXRPCException("Stub does not recognize property: "+name);
	}

	protected void _prepOperation(Operation op) {
		for (int i = 0; i < _propertyNames.length; ++i) {
			op.setProperty(_propertyNames[i], _propertyValues[i].toString());
		}
	}

	// 
	//  Begin user methods
	// 

	public java.lang.String encryptData(java.lang.String username, java.lang.String password, java.lang.String unEncryptedData, java.lang.String key) throws java.rmi.RemoteException {
		// Copy the incoming values into an Object array if needed.
		Object[] inputObject = new Object[4];
		inputObject[0] = username;
		inputObject[1] = password;
		inputObject[2] = unEncryptedData;
		inputObject[3] = key;

		Operation op = Operation.newInstance(_qname_EncryptData, _type_EncryptData, _type_EncryptDataResponse);
		_prepOperation(op);
		op.setProperty(Operation.SOAPACTION_URI_PROPERTY, "http://www.buscamultas.com/webservices/EncryptData");
		Object resultObj;
		try {
			resultObj = op.invoke(inputObject);
		} catch (JAXRPCException e) {
			Throwable cause = e.getLinkedCause();
			if (cause instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) cause;
			}
			throw e;
		}
		java.lang.String result;
		// Convert the result into the right Java type.
		// Unwrapped return value
		Object encryptDataResultObj = ((Object[])resultObj)[0];
		result = (java.lang.String)encryptDataResultObj;
		return result;
	}

	public java.lang.String unEncryptData(java.lang.String username, java.lang.String password, java.lang.String encryptedData, java.lang.String key) throws java.rmi.RemoteException {
		// Copy the incoming values into an Object array if needed.
		Object[] inputObject = new Object[4];
		inputObject[0] = username;
		inputObject[1] = password;
		inputObject[2] = encryptedData;
		inputObject[3] = key;

		Operation op = Operation.newInstance(_qname_UnEncryptData, _type_UnEncryptData, _type_UnEncryptDataResponse);
		_prepOperation(op);
		op.setProperty(Operation.SOAPACTION_URI_PROPERTY, "http://www.buscamultas.com/webservices/UnEncryptData");
		Object resultObj;
		try {
			resultObj = op.invoke(inputObject);
		} catch (JAXRPCException e) {
			Throwable cause = e.getLinkedCause();
			if (cause instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) cause;
			}
			throw e;
		}
		java.lang.String result;
		// Convert the result into the right Java type.
		// Unwrapped return value
		Object unEncryptDataResultObj = ((Object[])resultObj)[0];
		result = (java.lang.String)unEncryptDataResultObj;
		return result;
	}

	public uy.com.s4b.webservice.ArrayOfLocalizadorBoletinesInfo leeBoletin(java.lang.String username, java.lang.String password, java.lang.String DNI, java.lang.String matricula) throws java.rmi.RemoteException {
		// Copy the incoming values into an Object array if needed.
		Object[] inputObject = new Object[4];
		inputObject[0] = username;
		inputObject[1] = password;
		inputObject[2] = DNI;
		inputObject[3] = matricula;

		Operation op = Operation.newInstance(_qname_LeeBoletin, _type_LeeBoletin, _type_LeeBoletinResponse);
		_prepOperation(op);
		op.setProperty(Operation.SOAPACTION_URI_PROPERTY, "http://www.buscamultas.com/webservices/LeeBoletin");
		Object resultObj;
		try {
			resultObj = op.invoke(inputObject);
		} catch (JAXRPCException e) {
			Throwable cause = e.getLinkedCause();
			if (cause instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) cause;
			}
			throw e;
		}
		uy.com.s4b.webservice.ArrayOfLocalizadorBoletinesInfo result;
		// Convert the result into the right Java type.
		// Unwrapped return value
		Object[] leeBoletinResultObj = (Object[]) ((Object[])resultObj)[0];
		if (leeBoletinResultObj == null) {
			result = null;
		} else {
			result = new uy.com.s4b.webservice.ArrayOfLocalizadorBoletinesInfo();
			uy.com.s4b.webservice.LocalizadorBoletinesInfo[] localizadorBoletinesInfoArray;
			Object[] localizadorBoletinesInfoObj = (Object[]) leeBoletinResultObj[0];
			if (localizadorBoletinesInfoObj == null) {
				localizadorBoletinesInfoArray = null;
			} else {
				int localizadorBoletinesInfoArraySize = localizadorBoletinesInfoObj.length;
				localizadorBoletinesInfoArray = new uy.com.s4b.webservice.LocalizadorBoletinesInfo[localizadorBoletinesInfoArraySize];
				for (int localizadorBoletinesInfoArrayIndex = 0; 
					localizadorBoletinesInfoArrayIndex < localizadorBoletinesInfoArraySize; 
					++localizadorBoletinesInfoArrayIndex) {
					if (localizadorBoletinesInfoObj[localizadorBoletinesInfoArrayIndex] == null) {
						localizadorBoletinesInfoArray[localizadorBoletinesInfoArrayIndex] = null;
					} else {
						localizadorBoletinesInfoArray[localizadorBoletinesInfoArrayIndex] = new uy.com.s4b.webservice.LocalizadorBoletinesInfo();
						java.lang.String string;
						Object resultadoObj = ((Object[])localizadorBoletinesInfoObj[localizadorBoletinesInfoArrayIndex])[0];
						string = (java.lang.String)resultadoObj;
						localizadorBoletinesInfoArray[localizadorBoletinesInfoArrayIndex].setResultado(string);
						java.lang.String string2;
						Object codigoObj = ((Object[])localizadorBoletinesInfoObj[localizadorBoletinesInfoArrayIndex])[1];
						string2 = (java.lang.String)codigoObj;
						localizadorBoletinesInfoArray[localizadorBoletinesInfoArrayIndex].setCodigo(string2);
						int a_int;
						Object boletinIDObj = ((Object[])localizadorBoletinesInfoObj[localizadorBoletinesInfoArrayIndex])[2];
						a_int = ((java.lang.Integer)boletinIDObj).intValue();
						localizadorBoletinesInfoArray[localizadorBoletinesInfoArrayIndex].setBoletinID(a_int);
						java.lang.String string3;
						Object fechaInfraccionObj = ((Object[])localizadorBoletinesInfoObj[localizadorBoletinesInfoArrayIndex])[3];
						string3 = (java.lang.String)fechaInfraccionObj;
						localizadorBoletinesInfoArray[localizadorBoletinesInfoArrayIndex].setFechaInfraccion(string3);
						java.lang.String string4;
						Object DNIObj = ((Object[])localizadorBoletinesInfoObj[localizadorBoletinesInfoArrayIndex])[4];
						string4 = (java.lang.String)DNIObj;
						localizadorBoletinesInfoArray[localizadorBoletinesInfoArrayIndex].setDNI(string4);
						java.lang.String string5;
						Object matriculaObj = ((Object[])localizadorBoletinesInfoObj[localizadorBoletinesInfoArrayIndex])[5];
						string5 = (java.lang.String)matriculaObj;
						localizadorBoletinesInfoArray[localizadorBoletinesInfoArrayIndex].setMatricula(string5);
						java.lang.String string6;
						Object puntosObj = ((Object[])localizadorBoletinesInfoObj[localizadorBoletinesInfoArrayIndex])[6];
						string6 = (java.lang.String)puntosObj;
						localizadorBoletinesInfoArray[localizadorBoletinesInfoArrayIndex].setPuntos(string6);
						java.lang.String string7;
						Object importeSancionObj = ((Object[])localizadorBoletinesInfoObj[localizadorBoletinesInfoArrayIndex])[7];
						string7 = (java.lang.String)importeSancionObj;
						localizadorBoletinesInfoArray[localizadorBoletinesInfoArrayIndex].setImporteSancion(string7);
						java.lang.String string8;
						Object recurribleObj = ((Object[])localizadorBoletinesInfoObj[localizadorBoletinesInfoArrayIndex])[8];
						string8 = (java.lang.String)recurribleObj;
						localizadorBoletinesInfoArray[localizadorBoletinesInfoArrayIndex].setRecurrible(string8);
					}
				}
			}
			result.setLocalizadorBoletinesInfo(localizadorBoletinesInfoArray);
		}
		return result;
	}

	public uy.com.s4b.webservice.ArrayOfLocalizadorBoletinesInfo grabaDatos(java.lang.String username, java.lang.String password, java.lang.String DNI, java.lang.String matricula, java.lang.String email, java.lang.String matricula2, java.lang.String telefono, int tieneMultas, int tieneMultasRecurribles, int condiciones, int informacion, int origen) throws java.rmi.RemoteException {
		// Copy the incoming values into an Object array if needed.
		Object[] inputObject = new Object[12];
		inputObject[0] = username;
		inputObject[1] = password;
		inputObject[2] = DNI;
		inputObject[3] = matricula;
		inputObject[4] = email;
		inputObject[5] = matricula2;
		inputObject[6] = telefono;
		inputObject[7] = new java.lang.Integer(tieneMultas);
		inputObject[8] = new java.lang.Integer(tieneMultasRecurribles);
		inputObject[9] = new java.lang.Integer(condiciones);
		inputObject[10] = new java.lang.Integer(informacion);
		inputObject[11] = new java.lang.Integer(origen);

		Operation op = Operation.newInstance(_qname_GrabaDatos, _type_GrabaDatos, _type_GrabaDatosResponse);
		_prepOperation(op);
		op.setProperty(Operation.SOAPACTION_URI_PROPERTY, "http://www.buscamultas.com/webservices/GrabaDatos");
		Object resultObj;
		try {
			resultObj = op.invoke(inputObject);
		} catch (JAXRPCException e) {
			Throwable cause = e.getLinkedCause();
			if (cause instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) cause;
			}
			throw e;
		}
		uy.com.s4b.webservice.ArrayOfLocalizadorBoletinesInfo result;
		// Convert the result into the right Java type.
		// Unwrapped return value
		Object[] grabaDatosResultObj = (Object[]) ((Object[])resultObj)[0];
		if (grabaDatosResultObj == null) {
			result = null;
		} else {
			result = new uy.com.s4b.webservice.ArrayOfLocalizadorBoletinesInfo();
			uy.com.s4b.webservice.LocalizadorBoletinesInfo[] localizadorBoletinesInfoArray;
			Object[] localizadorBoletinesInfoObj = (Object[]) grabaDatosResultObj[0];
			if (localizadorBoletinesInfoObj == null) {
				localizadorBoletinesInfoArray = null;
			} else {
				int localizadorBoletinesInfoArraySize = localizadorBoletinesInfoObj.length;
				localizadorBoletinesInfoArray = new uy.com.s4b.webservice.LocalizadorBoletinesInfo[localizadorBoletinesInfoArraySize];
				for (int localizadorBoletinesInfoArrayIndex = 0; 
					localizadorBoletinesInfoArrayIndex < localizadorBoletinesInfoArraySize; 
					++localizadorBoletinesInfoArrayIndex) {
					if (localizadorBoletinesInfoObj[localizadorBoletinesInfoArrayIndex] == null) {
						localizadorBoletinesInfoArray[localizadorBoletinesInfoArrayIndex] = null;
					} else {
						localizadorBoletinesInfoArray[localizadorBoletinesInfoArrayIndex] = new uy.com.s4b.webservice.LocalizadorBoletinesInfo();
						java.lang.String string;
						Object resultadoObj = ((Object[])localizadorBoletinesInfoObj[localizadorBoletinesInfoArrayIndex])[0];
						string = (java.lang.String)resultadoObj;
						localizadorBoletinesInfoArray[localizadorBoletinesInfoArrayIndex].setResultado(string);
						java.lang.String string2;
						Object codigoObj = ((Object[])localizadorBoletinesInfoObj[localizadorBoletinesInfoArrayIndex])[1];
						string2 = (java.lang.String)codigoObj;
						localizadorBoletinesInfoArray[localizadorBoletinesInfoArrayIndex].setCodigo(string2);
						int a_int;
						Object boletinIDObj = ((Object[])localizadorBoletinesInfoObj[localizadorBoletinesInfoArrayIndex])[2];
						a_int = ((java.lang.Integer)boletinIDObj).intValue();
						localizadorBoletinesInfoArray[localizadorBoletinesInfoArrayIndex].setBoletinID(a_int);
						java.lang.String string3;
						Object fechaInfraccionObj = ((Object[])localizadorBoletinesInfoObj[localizadorBoletinesInfoArrayIndex])[3];
						string3 = (java.lang.String)fechaInfraccionObj;
						localizadorBoletinesInfoArray[localizadorBoletinesInfoArrayIndex].setFechaInfraccion(string3);
						java.lang.String string4;
						Object DNIObj = ((Object[])localizadorBoletinesInfoObj[localizadorBoletinesInfoArrayIndex])[4];
						string4 = (java.lang.String)DNIObj;
						localizadorBoletinesInfoArray[localizadorBoletinesInfoArrayIndex].setDNI(string4);
						java.lang.String string5;
						Object matriculaObj = ((Object[])localizadorBoletinesInfoObj[localizadorBoletinesInfoArrayIndex])[5];
						string5 = (java.lang.String)matriculaObj;
						localizadorBoletinesInfoArray[localizadorBoletinesInfoArrayIndex].setMatricula(string5);
						java.lang.String string6;
						Object puntosObj = ((Object[])localizadorBoletinesInfoObj[localizadorBoletinesInfoArrayIndex])[6];
						string6 = (java.lang.String)puntosObj;
						localizadorBoletinesInfoArray[localizadorBoletinesInfoArrayIndex].setPuntos(string6);
						java.lang.String string7;
						Object importeSancionObj = ((Object[])localizadorBoletinesInfoObj[localizadorBoletinesInfoArrayIndex])[7];
						string7 = (java.lang.String)importeSancionObj;
						localizadorBoletinesInfoArray[localizadorBoletinesInfoArrayIndex].setImporteSancion(string7);
						java.lang.String string8;
						Object recurribleObj = ((Object[])localizadorBoletinesInfoObj[localizadorBoletinesInfoArrayIndex])[8];
						string8 = (java.lang.String)recurribleObj;
						localizadorBoletinesInfoArray[localizadorBoletinesInfoArrayIndex].setRecurrible(string8);
					}
				}
			}
			result.setLocalizadorBoletinesInfo(localizadorBoletinesInfoArray);
		}
		return result;
	}
	// 
	//  End user methods
	// 

	protected static final QName _qname_BoletinID = new QName("http://www.buscamultas.com/webservices/", "BoletinID");
	protected static final QName _qname_Codigo = new QName("http://www.buscamultas.com/webservices/", "Codigo");
	protected static final QName _qname_Condiciones = new QName("http://www.buscamultas.com/webservices/", "Condiciones");
	protected static final QName _qname_DNI = new QName("http://www.buscamultas.com/webservices/", "DNI");
	protected static final QName _qname_Email = new QName("http://www.buscamultas.com/webservices/", "Email");
	protected static final QName _qname_EncryptData = new QName("http://www.buscamultas.com/webservices/", "EncryptData");
	protected static final QName _qname_EncryptDataResponse = new QName("http://www.buscamultas.com/webservices/", "EncryptDataResponse");
	protected static final QName _qname_EncryptDataResult = new QName("http://www.buscamultas.com/webservices/", "EncryptDataResult");
	protected static final QName _qname_EncryptedData = new QName("http://www.buscamultas.com/webservices/", "EncryptedData");
	protected static final QName _qname_FechaInfraccion = new QName("http://www.buscamultas.com/webservices/", "FechaInfraccion");
	protected static final QName _qname_GrabaDatos = new QName("http://www.buscamultas.com/webservices/", "GrabaDatos");
	protected static final QName _qname_GrabaDatosResponse = new QName("http://www.buscamultas.com/webservices/", "GrabaDatosResponse");
	protected static final QName _qname_GrabaDatosResult = new QName("http://www.buscamultas.com/webservices/", "GrabaDatosResult");
	protected static final QName _qname_ImporteSancion = new QName("http://www.buscamultas.com/webservices/", "ImporteSancion");
	protected static final QName _qname_Informacion = new QName("http://www.buscamultas.com/webservices/", "Informacion");
	protected static final QName _qname_Key = new QName("http://www.buscamultas.com/webservices/", "Key");
	protected static final QName _qname_LeeBoletin = new QName("http://www.buscamultas.com/webservices/", "LeeBoletin");
	protected static final QName _qname_LeeBoletinResponse = new QName("http://www.buscamultas.com/webservices/", "LeeBoletinResponse");
	protected static final QName _qname_LeeBoletinResult = new QName("http://www.buscamultas.com/webservices/", "LeeBoletinResult");
	protected static final QName _qname_LocalizadorBoletinesInfo = new QName("http://www.buscamultas.com/webservices/", "LocalizadorBoletinesInfo");
	protected static final QName _qname_Matricula = new QName("http://www.buscamultas.com/webservices/", "Matricula");
	protected static final QName _qname_Matricula2 = new QName("http://www.buscamultas.com/webservices/", "Matricula2");
	protected static final QName _qname_Origen = new QName("http://www.buscamultas.com/webservices/", "Origen");
	protected static final QName _qname_Password = new QName("http://www.buscamultas.com/webservices/", "Password");
	protected static final QName _qname_Puntos = new QName("http://www.buscamultas.com/webservices/", "Puntos");
	protected static final QName _qname_Recurrible = new QName("http://www.buscamultas.com/webservices/", "Recurrible");
	protected static final QName _qname_Resultado = new QName("http://www.buscamultas.com/webservices/", "Resultado");
	protected static final QName _qname_Telefono = new QName("http://www.buscamultas.com/webservices/", "Telefono");
	protected static final QName _qname_TieneMultas = new QName("http://www.buscamultas.com/webservices/", "TieneMultas");
	protected static final QName _qname_TieneMultasRecurribles = new QName("http://www.buscamultas.com/webservices/", "TieneMultasRecurribles");
	protected static final QName _qname_UnEncryptData = new QName("http://www.buscamultas.com/webservices/", "UnEncryptData");
	protected static final QName _qname_UnEncryptDataResponse = new QName("http://www.buscamultas.com/webservices/", "UnEncryptDataResponse");
	protected static final QName _qname_UnEncryptDataResult = new QName("http://www.buscamultas.com/webservices/", "UnEncryptDataResult");
	protected static final QName _qname_UnEncryptedData = new QName("http://www.buscamultas.com/webservices/", "UnEncryptedData");
	protected static final QName _qname_Username = new QName("http://www.buscamultas.com/webservices/", "Username");
	protected static final Element _type_EncryptData;
	protected static final Element _type_EncryptDataResponse;
	protected static final Element _type_UnEncryptData;
	protected static final Element _type_UnEncryptDataResponse;
	protected static final Element _type_LeeBoletin;
	protected static final Element _type_LeeBoletinResponse;
	protected static final Element _type_GrabaDatos;
	protected static final Element _type_GrabaDatosResponse;
	static {
		// Create all of the Type's that this stub uses, once.
		Element _type_Username;
		_type_Username = new Element(_qname_Username, Type.STRING, 0, 1, false);
		Element _type_Password;
		_type_Password = new Element(_qname_Password, Type.STRING, 0, 1, false);
		Element _type_UnEncryptedData;
		_type_UnEncryptedData = new Element(_qname_UnEncryptedData, Type.STRING, 0, 1, false);
		Element _type_Key;
		_type_Key = new Element(_qname_Key, Type.STRING, 0, 1, false);
		ComplexType _complexType_encryptData;
		_complexType_encryptData = new ComplexType();
		_complexType_encryptData.elements = new Element[4];
		_complexType_encryptData.elements[0] = _type_Username;
		_complexType_encryptData.elements[1] = _type_Password;
		_complexType_encryptData.elements[2] = _type_UnEncryptedData;
		_complexType_encryptData.elements[3] = _type_Key;
		_type_EncryptData = new Element(_qname_EncryptData, _complexType_encryptData);
		Element _type_EncryptDataResult;
		_type_EncryptDataResult = new Element(_qname_EncryptDataResult, Type.STRING, 0, 1, false);
		ComplexType _complexType_encryptDataResponse;
		_complexType_encryptDataResponse = new ComplexType();
		_complexType_encryptDataResponse.elements = new Element[1];
		_complexType_encryptDataResponse.elements[0] = _type_EncryptDataResult;
		_type_EncryptDataResponse = new Element(_qname_EncryptDataResponse, _complexType_encryptDataResponse);
		Element _type_EncryptedData;
		_type_EncryptedData = new Element(_qname_EncryptedData, Type.STRING, 0, 1, false);
		ComplexType _complexType_unEncryptData;
		_complexType_unEncryptData = new ComplexType();
		_complexType_unEncryptData.elements = new Element[4];
		_complexType_unEncryptData.elements[0] = _type_Username;
		_complexType_unEncryptData.elements[1] = _type_Password;
		_complexType_unEncryptData.elements[2] = _type_EncryptedData;
		_complexType_unEncryptData.elements[3] = _type_Key;
		_type_UnEncryptData = new Element(_qname_UnEncryptData, _complexType_unEncryptData);
		Element _type_UnEncryptDataResult;
		_type_UnEncryptDataResult = new Element(_qname_UnEncryptDataResult, Type.STRING, 0, 1, false);
		ComplexType _complexType_unEncryptDataResponse;
		_complexType_unEncryptDataResponse = new ComplexType();
		_complexType_unEncryptDataResponse.elements = new Element[1];
		_complexType_unEncryptDataResponse.elements[0] = _type_UnEncryptDataResult;
		_type_UnEncryptDataResponse = new Element(_qname_UnEncryptDataResponse, _complexType_unEncryptDataResponse);
		Element _type_DNI;
		_type_DNI = new Element(_qname_DNI, Type.STRING, 0, 1, false);
		Element _type_Matricula;
		_type_Matricula = new Element(_qname_Matricula, Type.STRING, 0, 1, false);
		ComplexType _complexType_leeBoletin;
		_complexType_leeBoletin = new ComplexType();
		_complexType_leeBoletin.elements = new Element[4];
		_complexType_leeBoletin.elements[0] = _type_Username;
		_complexType_leeBoletin.elements[1] = _type_Password;
		_complexType_leeBoletin.elements[2] = _type_DNI;
		_complexType_leeBoletin.elements[3] = _type_Matricula;
		_type_LeeBoletin = new Element(_qname_LeeBoletin, _complexType_leeBoletin);
		Element _type_Resultado;
		_type_Resultado = new Element(_qname_Resultado, Type.STRING, 0, 1, false);
		Element _type_Codigo;
		_type_Codigo = new Element(_qname_Codigo, Type.STRING, 0, 1, false);
		Element _type_BoletinID;
		_type_BoletinID = new Element(_qname_BoletinID, Type.INT);
		Element _type_FechaInfraccion;
		_type_FechaInfraccion = new Element(_qname_FechaInfraccion, Type.STRING, 0, 1, false);
		Element _type_Puntos;
		_type_Puntos = new Element(_qname_Puntos, Type.STRING, 0, 1, false);
		Element _type_ImporteSancion;
		_type_ImporteSancion = new Element(_qname_ImporteSancion, Type.STRING, 0, 1, false);
		Element _type_Recurrible;
		_type_Recurrible = new Element(_qname_Recurrible, Type.STRING, 0, 1, false);
		ComplexType _complexType_localizadorBoletinesInfo;
		_complexType_localizadorBoletinesInfo = new ComplexType();
		_complexType_localizadorBoletinesInfo.elements = new Element[9];
		_complexType_localizadorBoletinesInfo.elements[0] = _type_Resultado;
		_complexType_localizadorBoletinesInfo.elements[1] = _type_Codigo;
		_complexType_localizadorBoletinesInfo.elements[2] = _type_BoletinID;
		_complexType_localizadorBoletinesInfo.elements[3] = _type_FechaInfraccion;
		_complexType_localizadorBoletinesInfo.elements[4] = _type_DNI;
		_complexType_localizadorBoletinesInfo.elements[5] = _type_Matricula;
		_complexType_localizadorBoletinesInfo.elements[6] = _type_Puntos;
		_complexType_localizadorBoletinesInfo.elements[7] = _type_ImporteSancion;
		_complexType_localizadorBoletinesInfo.elements[8] = _type_Recurrible;
		Element _type_LocalizadorBoletinesInfo;
		_type_LocalizadorBoletinesInfo = new Element(_qname_LocalizadorBoletinesInfo, _complexType_localizadorBoletinesInfo, 0, -1, true);
		ComplexType _complexType_arrayOfLocalizadorBoletinesInfo;
		_complexType_arrayOfLocalizadorBoletinesInfo = new ComplexType();
		_complexType_arrayOfLocalizadorBoletinesInfo.elements = new Element[1];
		_complexType_arrayOfLocalizadorBoletinesInfo.elements[0] = _type_LocalizadorBoletinesInfo;
		Element _type_LeeBoletinResult;
		_type_LeeBoletinResult = new Element(_qname_LeeBoletinResult, _complexType_arrayOfLocalizadorBoletinesInfo, 0, 1, false);
		ComplexType _complexType_leeBoletinResponse;
		_complexType_leeBoletinResponse = new ComplexType();
		_complexType_leeBoletinResponse.elements = new Element[1];
		_complexType_leeBoletinResponse.elements[0] = _type_LeeBoletinResult;
		_type_LeeBoletinResponse = new Element(_qname_LeeBoletinResponse, _complexType_leeBoletinResponse);
		Element _type_Email;
		_type_Email = new Element(_qname_Email, Type.STRING, 0, 1, false);
		Element _type_Matricula2;
		_type_Matricula2 = new Element(_qname_Matricula2, Type.STRING, 0, 1, false);
		Element _type_Telefono;
		_type_Telefono = new Element(_qname_Telefono, Type.STRING, 0, 1, false);
		Element _type_TieneMultas;
		_type_TieneMultas = new Element(_qname_TieneMultas, Type.INT);
		Element _type_TieneMultasRecurribles;
		_type_TieneMultasRecurribles = new Element(_qname_TieneMultasRecurribles, Type.INT);
		Element _type_Condiciones;
		_type_Condiciones = new Element(_qname_Condiciones, Type.INT);
		Element _type_Informacion;
		_type_Informacion = new Element(_qname_Informacion, Type.INT);
		Element _type_Origen;
		_type_Origen = new Element(_qname_Origen, Type.INT);
		ComplexType _complexType_grabaDatos;
		_complexType_grabaDatos = new ComplexType();
		_complexType_grabaDatos.elements = new Element[12];
		_complexType_grabaDatos.elements[0] = _type_Username;
		_complexType_grabaDatos.elements[1] = _type_Password;
		_complexType_grabaDatos.elements[2] = _type_DNI;
		_complexType_grabaDatos.elements[3] = _type_Matricula;
		_complexType_grabaDatos.elements[4] = _type_Email;
		_complexType_grabaDatos.elements[5] = _type_Matricula2;
		_complexType_grabaDatos.elements[6] = _type_Telefono;
		_complexType_grabaDatos.elements[7] = _type_TieneMultas;
		_complexType_grabaDatos.elements[8] = _type_TieneMultasRecurribles;
		_complexType_grabaDatos.elements[9] = _type_Condiciones;
		_complexType_grabaDatos.elements[10] = _type_Informacion;
		_complexType_grabaDatos.elements[11] = _type_Origen;
		_type_GrabaDatos = new Element(_qname_GrabaDatos, _complexType_grabaDatos);
		Element _type_GrabaDatosResult;
		_type_GrabaDatosResult = new Element(_qname_GrabaDatosResult, _complexType_arrayOfLocalizadorBoletinesInfo, 0, 1, false);
		ComplexType _complexType_grabaDatosResponse;
		_complexType_grabaDatosResponse = new ComplexType();
		_complexType_grabaDatosResponse.elements = new Element[1];
		_complexType_grabaDatosResponse.elements[0] = _type_GrabaDatosResult;
		_type_GrabaDatosResponse = new Element(_qname_GrabaDatosResponse, _complexType_grabaDatosResponse);
	}

}