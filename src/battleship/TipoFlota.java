/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

/**
 *
 * @author wildg
 */
public enum TipoFlota {
    NORMAL  (new TipoNave[] {TipoNave.PORTAAVIONES, TipoNave.SUBMARINO ,
                             TipoNave.SUBMARINO   , TipoNave.ACORAZADO ,
                             TipoNave.DESTRUCTOR  , TipoNave.DESTRUCTOR,
                             TipoNave.DESTRUCTOR  , TipoNave.FRAGATA   ,
                             TipoNave.FRAGATA});
    
    private final TipoNave[] flota;
    
    private TipoFlota(TipoNave[] flota){
        this.flota=flota;
    }

    public TipoNave[] getFlota() {
        return flota;
    }
    
    
}
