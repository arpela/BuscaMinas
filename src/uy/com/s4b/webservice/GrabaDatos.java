// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JSR-172 Reference Implementation wscompile 1.0, using: JAX-RPC Standard Implementation (1.1, build R59)

package uy.com.s4b.webservice;


public class GrabaDatos {
    protected java.lang.String username;
    protected java.lang.String password;
    protected java.lang.String DNI;
    protected java.lang.String matricula;
    protected java.lang.String email;
    protected java.lang.String matricula2;
    protected java.lang.String telefono;
    protected int tieneMultas;
    protected int tieneMultasRecurribles;
    protected int condiciones;
    protected int informacion;
    protected int origen;
    
    public GrabaDatos() {
    }
    
    public GrabaDatos(java.lang.String username, java.lang.String password, java.lang.String DNI, java.lang.String matricula, java.lang.String email, java.lang.String matricula2, java.lang.String telefono, int tieneMultas, int tieneMultasRecurribles, int condiciones, int informacion, int origen) {
        this.username = username;
        this.password = password;
        this.DNI = DNI;
        this.matricula = matricula;
        this.email = email;
        this.matricula2 = matricula2;
        this.telefono = telefono;
        this.tieneMultas = tieneMultas;
        this.tieneMultasRecurribles = tieneMultasRecurribles;
        this.condiciones = condiciones;
        this.informacion = informacion;
        this.origen = origen;
    }
    
    public java.lang.String getUsername() {
        return username;
    }
    
    public void setUsername(java.lang.String username) {
        this.username = username;
    }
    
    public java.lang.String getPassword() {
        return password;
    }
    
    public void setPassword(java.lang.String password) {
        this.password = password;
    }
    
    public java.lang.String getDNI() {
        return DNI;
    }
    
    public void setDNI(java.lang.String DNI) {
        this.DNI = DNI;
    }
    
    public java.lang.String getMatricula() {
        return matricula;
    }
    
    public void setMatricula(java.lang.String matricula) {
        this.matricula = matricula;
    }
    
    public java.lang.String getEmail() {
        return email;
    }
    
    public void setEmail(java.lang.String email) {
        this.email = email;
    }
    
    public java.lang.String getMatricula2() {
        return matricula2;
    }
    
    public void setMatricula2(java.lang.String matricula2) {
        this.matricula2 = matricula2;
    }
    
    public java.lang.String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(java.lang.String telefono) {
        this.telefono = telefono;
    }
    
    public int getTieneMultas() {
        return tieneMultas;
    }
    
    public void setTieneMultas(int tieneMultas) {
        this.tieneMultas = tieneMultas;
    }
    
    public int getTieneMultasRecurribles() {
        return tieneMultasRecurribles;
    }
    
    public void setTieneMultasRecurribles(int tieneMultasRecurribles) {
        this.tieneMultasRecurribles = tieneMultasRecurribles;
    }
    
    public int getCondiciones() {
        return condiciones;
    }
    
    public void setCondiciones(int condiciones) {
        this.condiciones = condiciones;
    }
    
    public int getInformacion() {
        return informacion;
    }
    
    public void setInformacion(int informacion) {
        this.informacion = informacion;
    }
    
    public int getOrigen() {
        return origen;
    }
    
    public void setOrigen(int origen) {
        this.origen = origen;
    }
}