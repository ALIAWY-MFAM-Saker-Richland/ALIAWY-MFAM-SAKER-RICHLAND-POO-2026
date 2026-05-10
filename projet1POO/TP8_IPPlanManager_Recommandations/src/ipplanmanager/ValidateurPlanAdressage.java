package ipplanmanager;

import java.util.ArrayList;

public class ValidateurPlanAdressage {

    public boolean verifierDoublonsVLAN(
            ArrayList<VLAN> vlans) {

        for (int i = 0; i < vlans.size(); i++) {

            for (int j = i + 1; j < vlans.size(); j++) {

                if (vlans.get(i).getId()
                        == vlans.get(j).getId()) {

                    return false;
                }
            }
        }

        return true;
    }
}