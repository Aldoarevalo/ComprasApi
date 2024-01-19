package Genericos;

public enum Respuesta {
    RESPUESTA ( "OPERACION EXITOSA",
                "OPERACION ERRONEA",
                "PARAMETROS INCORRECTOS",
                "FALTAN PARAMETROS",
                "TOKEN INCORRECTO",
                "NO SE LOCALIZO EL TOKEN"
                );
    
    private final String operacionExitosa;
    private final String operacionErronea;
    private final String parametrosIncorrectos;
    private final String parametrosInexistentes;
    private final String tokenIncorrecto;
    private final String tokenNoLocalizado;

    private Respuesta(String operacionExitosa, String operacionErronea, String parametrosIncorrectos, String parametrosInexistentes, String tokenIncorrecto, String tokenNoLocalizado) {
        this.operacionExitosa = operacionExitosa;
        this.operacionErronea = operacionErronea;
        this.parametrosIncorrectos = parametrosIncorrectos;
        this.parametrosInexistentes = parametrosInexistentes;
        this.tokenIncorrecto = tokenIncorrecto;
        this.tokenNoLocalizado = tokenNoLocalizado;
    }

    public String getOperacionExitosa() {
        return operacionExitosa;
    }

    public String getOperacionErronea() {
        return operacionErronea;
    }

    public String getParametrosIncorrectos() {
        return parametrosIncorrectos;
    }

    public String getParametrosInexistentes() {
        return parametrosInexistentes;
    }

    public String getTokenIncorrecto() {
        return tokenIncorrecto;
    }

    public String getTokenNoLocalizado() {
        return tokenNoLocalizado;
    }

    
    
}
