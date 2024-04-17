package clases;

/**
 * Clase encargada de contener los estados de los permisos de un usuario. Se usa
 * para acceder fácilmente a los permisos de un usuario previamente cargado.
 */
public class Permisos {
// PERMISOS MODIFICADOS POR DCG 26/02/2024
    private boolean registrarDemograficos, registrarOrdenes, registrarResultados, modificarDemograficos, modificarOrdenes, modificarResultados, consultar, imprimir, validar, desvalidar, validarSegundaVez, desvalidarSegundaVez, crearUsuarios, modificarUsuarios, configuracion, verConfidencial,crearSedes, adminsitrarSedes,croles,cmedico,cservicios,canalizador,centidades,ctarifas ,soporte;

    //Constructor
    public Permisos(Object[][] pPermisos) {
        this.registrarDemograficos = pPermisos[0][0].toString().equals("1");
        this.registrarOrdenes = pPermisos[0][1].toString().equals("1");
        this.registrarResultados = pPermisos[0][2].toString().equals("1");
        this.modificarDemograficos = pPermisos[0][3].toString().equals("1");
        this.modificarOrdenes = pPermisos[0][4].toString().equals("1");
        this.modificarResultados = pPermisos[0][5].toString().equals("1");
        this.consultar = pPermisos[0][6].toString().equals("1");
        this.imprimir = pPermisos[0][7].toString().equals("1");
        this.validar = pPermisos[0][8].toString().equals("1");
        this.desvalidar = pPermisos[0][9].toString().equals("1");
        this.validarSegundaVez = pPermisos[0][10].toString().equals("1");
        this.desvalidarSegundaVez = pPermisos[0][11].toString().equals("1");
        this.crearUsuarios = pPermisos[0][12].toString().equals("1");
        this.modificarUsuarios = pPermisos[0][13].toString().equals("1");
        this.configuracion = pPermisos[0][14].toString().equals("1");
        this.verConfidencial = pPermisos[0][15].toString().equals("1");
        this.crearSedes = pPermisos[0][16].toString().equals("1");
        this.adminsitrarSedes = pPermisos[0][17].toString().equals("1");
        this.croles = pPermisos[0][18].toString().equals("1");
        this.cmedico = pPermisos[0][19].toString().equals("1");
       this.cservicios = pPermisos[0][20].toString().equals("1");
       this.canalizador = pPermisos[0][21].toString().equals("1");
       this.centidades = pPermisos[0][22].toString().equals("1");
       this.ctarifas = pPermisos[0][22].toString().equals("1");
       this.soporte = pPermisos[0][23].toString().equals("1");
    }
    public boolean isRegistrarDemograficos() {
        return registrarDemograficos;
    }

    public boolean isRegistrarOrdenes() {
        return registrarOrdenes;
    }

    public boolean isRegistrarResultados() {
        return registrarResultados;
    }

    public boolean isModificarDemograficos() {
        return modificarDemograficos;
    }

    public boolean isModificarOrdenes() {
        return modificarOrdenes;
    }

    public boolean isModificarResultados() {
        return modificarResultados;
    }

    public boolean isConsultar() {
        return consultar;
    }

    public boolean isImprimir() {
        return imprimir;
    }

    public boolean isValidar() {
        return validar;
    }

    public boolean isDesvalidar() {
        return desvalidar;
    }

    public boolean isValidarSegundaVez() {
        return validarSegundaVez;
    }

    public boolean isDesvalidarSegundaVez() {
        return desvalidarSegundaVez;
    }

    public boolean isCrearUsuarios() {
        return crearUsuarios;
    }

    public boolean isModificarUsuarios() {
        return modificarUsuarios;
    }

    public boolean isConfigurar() {
        return configuracion;
    }

    public boolean isSoporte() {
        return soporte;
    }

    public boolean isVerConfidencial() {
        return verConfidencial;
    }



    public boolean isConfiguracion() {
        return configuracion;
    }

    public boolean isCrearSedes() {
        return crearSedes;
    }

    public boolean isAdministrarSedes() {
        return adminsitrarSedes;
    }



    public boolean isAdminsitrarSedes() {
        return adminsitrarSedes;
    }
    public boolean iscroles() {
        return croles;
    }
    public boolean iscmedico() {
        return cmedico;
    }
    public boolean iscservicios() {
        return cservicios;
    }
    public boolean iscanalizador() {
        return canalizador;
    }
     public boolean icentidades() {
        return centidades;
    }
     public boolean ictarifas() {
        return ctarifas;
    }

  
    
    
}
