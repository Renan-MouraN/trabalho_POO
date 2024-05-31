package LimaOscarLima.GarenR;

import LimaOscarLima.BaraoVermelho.Projectile;
import LimaOscarLima.CapitaoMagico.*;
import LimaOscarLima.BaraoVermelho.Character;
import LimaOscarLima.Interfaces.Interface;
import LimaOscarLima.UrfRider.Background;

public class Nautilus implements Interface {

    public void inicializaClasse(Projectile p) {
        int d =1 ;
        p = inicializaProjetile(d);
    }

    private Projectile inicializaProjetile(int d){
        Projectile projectile = new Projectile(d);
        projectile.inicializaClasse();
        return projectile;
    };
}
