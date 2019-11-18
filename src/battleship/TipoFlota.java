/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

/**
 * enum para los diferenres tipos de flotas
 * @author wildg
 */
public enum TipoFlota {
    NORMAL  (new TipoNave[] {TipoNave.PORTAAVIONES, TipoNave.SUBMARINO ,
                             TipoNave.SUBMARINO   , TipoNave.ACORAZADO ,
                             TipoNave.DESTRUCTOR  , TipoNave.DESTRUCTOR,
                             TipoNave.DESTRUCTOR  , TipoNave.FRAGATA   ,
                             TipoNave.FRAGATA});
    
    private final TipoNave[] flota;
    
    /**
     * Constructor
     * @param flota 
     */
    private TipoFlota(TipoNave[] flota){
        this.flota=flota;
    }
    
    /**
     * Función que obtener array que contirne las naves de la flota.
     * @return TipoNave[] que contirne las naves de la flota.
     */
    public TipoNave[] getFlota() {
        return flota;
    }
    
    
}
