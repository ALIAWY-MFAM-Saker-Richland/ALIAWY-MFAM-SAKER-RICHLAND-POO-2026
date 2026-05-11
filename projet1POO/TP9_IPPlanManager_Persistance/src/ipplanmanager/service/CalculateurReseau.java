package ipplanmanager.service;

public class CalculateurReseau {

    public int calculerCIDR(int hotes) {

        int bits = 0;

        while ((Math.pow(2, bits) - 2) < hotes) {
            bits++;
        }

        return 32 - bits;
    }

    public int calculerCapacite(int cidr) {

        return (int) Math.pow(2, 32 - cidr) - 2;
    }
}